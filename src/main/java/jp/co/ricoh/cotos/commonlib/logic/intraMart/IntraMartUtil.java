package jp.co.ricoh.cotos.commonlib.logic.intraMart;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import jp.co.ricoh.cotos.commonlib.dto.json.estimation.IspSettingRootDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.IspGetSettingInfoParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.AbstractTmRequestDto;
import jp.co.ricoh.cotos.commonlib.logic.json.JsonUtil;
import jp.co.ricoh.cotos.commonlib.rest.ExternalIspRestTemplate;
import jp.co.ricoh.cotos.commonlib.rest.ExternalRestTemplate;
import jp.co.ricoh.cotos.commonlib.util.AppProperties;
import lombok.extern.log4j.Log4j;

/**
 * Intra-mart用外部API実行クラス<br>
 * <b>ローカル環境で確認する場合、<a href=https://ex-redmine-1.cotos.ricoh.co.jp/issues/16331>SSL証明書の登録</a>が必要になるため注意</b>
 *
 */
@Log4j
@Component
public class IntraMartUtil {

	/** S&S作業依頼（Web受注分）IM連携バッチ時の返却値に関する文字コード */
	public static final Charset CHARSET_S_AND_S_RESULT = Charset.forName("ISO-8859-1");

	private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;

	@Autowired
	ExternalRestTemplate externalRestTemplate;

	@Autowired
	ExternalIspRestTemplate externalIspRestTemplate;

	@Autowired
	JsonUtil jsonUtil;

	@Autowired
	AppProperties appProperties;

	/**
	 * Intra-mart用外部APIを実行します<br>
	 * <b>呼び出し元のymlに設定しているユーザとパスワードのどちらかがnullの場合は認証しない</b>
	 * @param method 対象HTTPメソッド
	 * @param requestDto リクエスト（GETパラメータ時はnull）
	 * @param url REST実行先URL
	 * @param responseCharset responseの文字コード（null、またはUTF-8指定時はログ出力用変換処理を実施しない）
	 * @return
	 * @see <a href=https://mygithub.ritscm.xyz/cotos/Batches/blob/master/Contract/CreateSAndSWorkRequestWebIntraMartLinkageData/src/main/java/jp/co/ricoh/cotos/component/base/BatchStepComponent.java#L318-L344>Batchesの参考</a>
	 * @see jp.co.ricoh.cotos.commonlib.logic.trendmicro.LMPIConnectionHelper#callService(String, HttpMethod, AbstractTmRequestDto)
	 */
	public <T> ResponseEntity<String> callService(HttpMethod method, T requestDto, String url, Charset responseCharset) {
		RestTemplate rest = externalIspRestTemplate.createRestTemplateForJson();
		String body = null;

		ResponseEntity<String> responseEntity = null;
		String conversionRespomseBody = null;

		try {
			if (requestDto != null) {
				body = jsonUtil.convertToStr(requestDto);
			}

			URI uri = new URI(url);
			HttpHeaders headers = createHttpHeaders(appProperties.getIntramartProperties().getUser(), appProperties.getIntramartProperties().getPassword());

			RequestEntity<String> requestEntity = new RequestEntity<String>(body, headers, method, uri);
			log.info("============================================================");
			log.info("request : " + requestEntity.getBody());
			log.info("============================================================");
			responseEntity = rest.exchange(requestEntity, String.class);
			if (responseCharset == null || CHARSET_UTF8.name().equals(responseCharset.name())) {
				conversionRespomseBody = responseEntity.getBody();
			} else {
				// 文字コードがUTF-8ではないため、変換処理を実施する（ログ出力のためだけの変換なため注意）
				conversionRespomseBody = decodedToUTF8(responseEntity.getBody(), responseCharset);
			}
			log.info("============================================================");
			log.info("status  : " + responseEntity.getStatusCodeValue());
			log.info("headers : " + responseEntity.getHeaders());
			log.info("response: " + conversionRespomseBody);
			log.info("============================================================");

		} catch (ResourceAccessException | HttpClientErrorException | URISyntaxException | UnsupportedEncodingException e) {
			log.error("【APIエラー】  ", e);
			throw new RuntimeException(e);
		}
		return responseEntity;
	}

	/**
	 * Intra-mart用ISP設定情報取得APIを実行します
	 * @param ispGetSettingInfoParameter
	 * @return
	 */
	public Map<HttpStatus, IspSettingRootDto> callIspGetSettingInfo(IspGetSettingInfoParameter ispGetSettingInfoParameter) {
		String apiUrl = appProperties.getIntramartProperties().getUrl() + "/ss_000172_fd202?rjManageNumber=" + ispGetSettingInfoParameter.getRjManageNumber() //
				+ "&contractNumber=" + ispGetSettingInfoParameter.getContractNumber() //
				+ "&contractBranchNumber=" + ispGetSettingInfoParameter.getContractBranchNumber() //
				+ "&whole_status=" + ispGetSettingInfoParameter.getWholeStatus();
		log.info("apiUrl = " + apiUrl);
		// Stringでデータを受け取った後、変換する
		ResponseEntity<String> serviceResult = callService(HttpMethod.GET, null, apiUrl, null);
		IspSettingRootDto ispSettingRootDto = jsonUtil.convertToDto(serviceResult.getBody(), IspSettingRootDto.class);

		Map<HttpStatus, IspSettingRootDto> result = new HashMap<>();
		result.put(serviceResult.getStatusCode(), ispSettingRootDto);

		return result;
	}

	/**
	 * 文字コードをUTF-8に変換した文字列を取得する
	 * @param encode 変換対象文字列
	 * @param charset 変換前の文字コード
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String decodedToUTF8(String encode, Charset charset) throws UnsupportedEncodingException {
		return new String(encode.getBytes(charset), CHARSET_UTF8);
	}

	/**
	 * Intra-mart用ヘッダー情報を作成する
	 * @return
	 */
	private HttpHeaders createHttpHeaders(String basicUser, String basicPassword) {
		HttpHeaders headers = new HttpHeaders();
		if(StringUtils.isNotEmpty(basicUser) || StringUtils.isNotEmpty(basicPassword)) {
			String strAuth = basicUser + ":" + basicPassword;
			String base64Auth = new String(Base64.getEncoder().encode(strAuth.getBytes()));
			headers.add("Authorization", "Basic " + base64Auth);

			// パスワードはログ出力しない。別途テスト時の確認用にコードのみ残す形とした。
			//			log.info("============================================================");
			//			log.info("Authorization: " + headers.get("Authorization"));
			//			log.info("============================================================");
		}

		return headers;
	}
}
