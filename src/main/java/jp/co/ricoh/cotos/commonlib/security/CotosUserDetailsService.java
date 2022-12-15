package jp.co.ricoh.cotos.commonlib.security;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.ActionDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.AuthDiv;
import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;
import jp.co.ricoh.cotos.commonlib.repository.master.DummyUserMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.SuperUserMasterRepository;
import jp.co.ricoh.cotos.commonlib.security.mom.MomAuthorityService;
import jp.co.ricoh.cotos.commonlib.security.mom.MomAuthorityService.AuthLevel;
import jp.co.ricoh.cotos.commonlib.util.ClaimsProperties;
import jp.co.ricoh.cotos.commonlib.util.JwtProperties;

@Component
public class CotosUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

	/** ロガー */
	private static final Log log = LogFactory.getLog(CotosUserDetailsService.class);

	public static final String DUMMY_MOM_AUTH = "NO_AUTHORITIES";

	// バッチユーザ、UIユーザが使用するシングルユーザID
	public static final String BATCH_UI_USER_SUID = "sid";

	// バッチユーザーが使用するMoM社員ID
	public static final String BATCH_USER_MID = "COTOS_BATCH_USER";

	@Autowired
	JwtProperties jwtProperties;

	@Autowired
	ClaimsProperties claimsProperties;

	@Autowired
	MomAuthorityService momAuthorityService;

	@Autowired
	MessageUtil messageUtil;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	SuperUserMasterRepository superUserMasterRepository;

	@Autowired
	private DummyUserMasterRepository dummyUserMasterRepository;

	@Override
	public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {

		// 認証用ヘッダーを取得
		String authenticationHeader = token.getPrincipal().toString();
		CotosAuthenticationDetails cotosAuthenticationDetails = null;

		try {
			cotosAuthenticationDetails = this.decodeAuthentication(authenticationHeader);
			if (cotosAuthenticationDetails == null || StringUtils.isAnyBlank(cotosAuthenticationDetails.getMomEmployeeId(), cotosAuthenticationDetails.getSingleUserId(), cotosAuthenticationDetails.getOrigin(), cotosAuthenticationDetails.getApplicationId())) {
				throw new UsernameNotFoundException("user not found");
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("failure verification", e);
		}

		// リクエスト元のOriginと、JWTのオリジンを比較
		String requestOrigin = token.getCredentials().toString();

		// リクエスト元のOriginに、比較をスキップするOrigin名が含まれていた場合、比較処理を行わない
		boolean skipCheckOriginFlg = false;
		if (!CollectionUtils.isEmpty(jwtProperties.getSkipCheckOriginName())) {
			skipCheckOriginFlg = jwtProperties.getSkipCheckOriginName().stream().anyMatch(skipCheckOriginName -> requestOrigin.contains(skipCheckOriginName));
		}
		if (!StringUtils.isBlank(requestOrigin) && !requestOrigin.equals(cotosAuthenticationDetails.getOrigin()) && !skipCheckOriginFlg) {
			throw new UsernameNotFoundException("Origin Not Allowed");
		}

		return cotosAuthenticationDetails;
	}

	private CotosAuthenticationDetails decodeAuthentication(String authenticationHeader) throws Exception {

		// Bearer属性を取得
		if (authenticationHeader.startsWith("Bearer ")) {

			String jwtString = authenticationHeader.substring("Bearer ".length());

			Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecretKey());
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(jwtString);

			// スーパーユーザーか判定
			boolean isSuperUser = superUserMasterRepository.existsByUserId(jwt.getClaim(claimsProperties.getMomEmpId()).asString());

			boolean isDummyUser = dummyUserMasterRepository.existsByUserId(jwt.getClaim(claimsProperties.getMomEmpId()).asString());

			Map<ActionDiv, Map<AuthDiv, AuthLevel>> momAuthorities = null;

			// バッチユーザ以外のユーザであればMoM認証を行う
			if (!BATCH_UI_USER_SUID.equals(jwt.getClaim(claimsProperties.getSingleUserId()).asString())) {
				// 認証ドメインでMoM権限が取得できた場合(取得できないとJWTからmomAuthの項目が削除される)
				if (!jwt.getClaim(claimsProperties.getMomAuth()).isNull() && !jwt.getClaim(claimsProperties.getMomAuth()).asString().equals(DUMMY_MOM_AUTH)) {
					// JWTにある権限情報を取得
					momAuthorities = objectMapper.readValue(jwt.getClaim(claimsProperties.getMomAuth()).asString(), new TypeReference<Map<ActionDiv, Map<AuthDiv, AuthLevel>>>() {
					});
				} else if (jwt.getClaim(claimsProperties.getMomAuth()).isNull()) {
					// シングルユーザーIDに紐づく権限情報を取得
					try {
						momAuthorities = momAuthorityService.searchAllMomAuthorities(jwt.getClaim(claimsProperties.getSingleUserId()).asString());
					} catch (Exception e) {
						throw e;
					}
				}

				// 一般ユーザーで、MoM権限ユーザーが取得できない場合はエラー
				if (!isSuperUser && momAuthorities == null) {
					log.error(messageUtil.createMessageInfo("NoMomAuthoritiesError", Arrays.asList(jwt.getClaim(claimsProperties.getSingleUserId()).asString()).toArray(new String[0])).getMsg());
					throw new Exception();
				}
			} else {
				log.info("バッチユーザもしくはUIユーザのためMoM認証をスキップします。");
			}

			return new CotosAuthenticationDetails(jwt.getClaim(claimsProperties.getMomEmpId()).asString(), jwt.getClaim(claimsProperties.getSingleUserId()).asString(), jwt.getClaim(claimsProperties.getOrigin()).asString(), jwt.getClaim(claimsProperties.getApplicationId()).asString(), jwtString, isSuperUser, isDummyUser, momAuthorities);
		}

		return null;
	}

	/**
	 * バッチユーザーかどうかを判定
	 * @param
	 * @return
	 */
	public boolean isBatchUser() {
		// ユーザー情報の取得
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (BATCH_USER_MID.equals(userInfo.getMomEmployeeId())) {
			return true;
		}
		return false;
	}
}