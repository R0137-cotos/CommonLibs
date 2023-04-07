package jp.co.ricoh.cotos.commonlib.logic.intraMart;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.AbstractTmRequestDto;
import jp.co.ricoh.cotos.commonlib.logic.json.JsonUtil;
import jp.co.ricoh.cotos.commonlib.rest.ExternalRestTemplate;
import lombok.extern.log4j.Log4j;

/**
 * Intra-mart用外部API実行クラス<br>
 * <b>ローカル環境で確認する場合、<a href=https://ex-redmine-1.cotos.ricoh.co.jp/issues/16331>SSL証明書の登録</a>が必要になるため注意</b>
 *
 */
@Log4j
@Component
public class IntraMartUtil {

	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	/** Basic認証ユーザ（脱RITOSカテゴリBで使用した「ISP設定情報取得」「設定情報PDF参照」APIで使用） */
	private static final String BASIC_USER = "API_SS_0172_USER";
	/** Basic認証パスワード（脱RITOSカテゴリBで使用した「ISP設定情報取得」「設定情報PDF参照」APIで使用） */
	private static final String BASIC_PASSWORD = "M4FhkTRg";

	/** S&S作業依頼（Web受注分）IM連携バッチ時の返却値に関する文字コード */
	public static final Charset CHARSET_S_AND_S_RESULT = Charset.forName("ISO-8859-1");

	private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;

	@Autowired
	ExternalRestTemplate externalRestTemplate;

	@Autowired
	JsonUtil jsonUtil;

	/**
	 * Intra-mart用外部APIを実行します
	 * @param method 対象HTTPメソッド
	 * @param requestDto リクエスト（GETパラメータ時はnull）
	 * @param url REST実行先URL
	 * @param responseCharset responseの文字コード（null、またはUTF-8指定時はログ出力用変換処理を実施しない）
	 * @param basicUser BASIC認証用ユーザ（ユーザとパスワードのどちらかがnullの場合は認証しない）
	 * @param basicPassword BASIC認証用パスワード（ユーザとパスワードのどちらかがnullの場合は認証しない）
	 * @return
	 * @see <a href=https://mygithub.ritscm.xyz/cotos/Batches/blob/master/Contract/CreateSAndSWorkRequestWebIntraMartLinkageData/src/main/java/jp/co/ricoh/cotos/component/base/BatchStepComponent.java#L318-L344>Batchesの参考</a>
	 * @see jp.co.ricoh.cotos.commonlib.logic.trendmicro.LMPIConnectionHelper#callService(String, HttpMethod, AbstractTmRequestDto)
	 */
	public <T> ResponseEntity<String> callService(HttpMethod method, T requestDto, String url, Charset responseCharset, String basicUser, String basicPassword) {
		RestTemplate rest = externalRestTemplate.createRestTemplateForJson();
		String body = null;

		ResponseEntity<String> responseEntity = null;
		String conversionRespomseBody = null;

		try {
			if (requestDto != null) {
				body = jsonUtil.convertToStr(requestDto);
			}

			URI uri = new URI(url);
			HttpHeaders headers = createHttpHeaders(basicUser, basicPassword);

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
	 * 脱RITOSカテゴリBで連携されたユーザとパスワードを使用して、Intra-mart用外部APIを実行します
	 * @param method 対象HTTPメソッド
	 * @param requestDto リクエスト（GETパラメータ時はnull）
	 * @param url REST実行先URL
	 * @param responseCharset responseの文字コード（null、またはUTF-8指定時はログ出力用変換処理を実施しない）。S&S作業依頼時は{@link IntraMartUtil#CHARSET_S_AND_S_RESULT}を設定した
	 * @return
	 * @see IntraMartUtil#BASIC_USER
	 * @see IntraMartUtil#BASIC_PASSWORD
	 */
	public <T> ResponseEntity<String> callService(HttpMethod method, T requestDto, String url, Charset responseCharset) {
		return callService(method, requestDto, url, responseCharset, BASIC_USER, BASIC_PASSWORD);
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
		headers.add("content-type", CONTENT_TYPE);
		if(StringUtils.isNotEmpty(basicUser) || StringUtils.isNotEmpty(basicPassword)) {
			String strAuth = basicUser + ":" + basicPassword;
			String base64Auth = new String(Base64.getEncoder().encode(strAuth.getBytes()));
			headers.add("Authorization", base64Auth);
		}

		return headers;
	}
}
