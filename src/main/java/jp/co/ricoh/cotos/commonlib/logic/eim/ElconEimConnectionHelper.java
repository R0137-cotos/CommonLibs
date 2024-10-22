package jp.co.ricoh.cotos.commonlib.logic.eim;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests.DocumentDeleteRequest;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests.DocumentDeleteRequestProperties;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests.DocumentDeleteRequestSystem;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.ApiAuthResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.DocumentDeleteResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.DocumentGetResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.SystemAuthResponse;
import jp.co.ricoh.cotos.commonlib.util.ElconEimConnectionProperties;
import lombok.extern.log4j.Log4j;

/**
 * 
 * 電子契約EIM連携 ヘルパークラス
 *
 */
@Log4j
@Component
@EnableRetry
public class ElconEimConnectionHelper extends EimConnectionHelper {

	private static final String ERROR_DOCUMENT_ID = "[ドキュメントID:%s]";

	private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;

	private static final Charset CHARSET_ISO_8859_1 = StandardCharsets.ISO_8859_1;

	private static final int RETRY_NUM = 5;

	private static final int RETRY_WAIT_TIME = 1000;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	ElconEimConnectionProperties elconEimConnectionProperties;

	/**
	 * アプリケーション認証用ヘッダー情報を作成する
	 * 
	 * @return HttpHeaders
	 */
	@Override
	protected HttpHeaders createHttpHeadersApiAuth(SystemAuthResponse systemAuth) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("X-Application-Id", elconEimConnectionProperties.getXApplicationId());
		headers.add("X-Application-Key", elconEimConnectionProperties.getXApplicationKey());
		headers.add("X-Site-Id", elconEimConnectionProperties.getXSiteId());

		return headers;
	}

	/**
	 * [GET]電子契約EIMの文書取得API
	 * 
	 * @param 電子契約情報.電子契約ドキュメントID
	 * @return 文書取得レスポンスDTO
	 */
	@Retryable(value = { RestClientException.class }, maxAttempts = RETRY_NUM, backoff = @Backoff(delay = RETRY_WAIT_TIME))
	public DocumentGetResponse getDocument(String documentId) {

		try {
			RestTemplate restForEim = new RestTemplate();

			// アプリケーション認証APIコール
			ApiAuthResponse apiAuthRes = apiAuth(restForEim, null);

			// ヘッダー設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("content-type", "application/json; charset=UTF-8");
			headers.add("X-Site-Id", elconEimConnectionProperties.getXSiteId());
			headers.add("Cookie", "APISID=" + apiAuthRes.getAccess_token());
			HttpEntity<String> httpEntity = new HttpEntity<>(headers);

			// 文書取得APIコール
			String url = "https://" + elconEimConnectionProperties.getHostName() + "." + elconEimConnectionProperties.getDomainName() + "/" + elconEimConnectionProperties.getResourcesPath() + elconEimConnectionProperties.getAppId() + "/" + elconEimConnectionProperties.getDocumentsPath() + "/" + documentId;
			ResponseEntity<String> responseEntity = restForEim.exchange(new URI(url), HttpMethod.GET, httpEntity, String.class);

			// ステータスコードが「200：OK」以外はエラーとする
			if (HttpStatus.OK != responseEntity.getStatusCode()) {
				throw new RestClientException("【APIエラー】電子契約EIM文書取得 Status Code:" + responseEntity.getStatusCode());
			}

			// 文字コードの変換処理を実施
			String conversionRespomseBody = decodedToUTF8(responseEntity.getBody(), CHARSET_ISO_8859_1);
			DocumentGetResponse responseDto = mapper.readValue(conversionRespomseBody, DocumentGetResponse.class);

			return responseDto;

		} catch (Exception e) {
			log.error("電子契約EIMの文書取得API実行に失敗しました。" + String.format(ERROR_DOCUMENT_ID, documentId), e);
			throw new RestClientException("【APIエラー】電子契約EIM文書取得 Status Code:" + e.getMessage());
		}
	}

	/**
	 * 電子契約EIMの文書取得APIエラー時に呼ばれるメソッド
	 * 
	 * @param リトライ対象例外クラスインスタンス
	 * @return リトライ対象メソッドの戻り値
	 */
	@Recover
	private DocumentGetResponse recoverCallGetDocumentApi(RestClientException e) {
		log.error(String.format("%d回リトライしましたが、電子契約EIMの文書取得API呼び出しに失敗しました。", RETRY_NUM));
		throw e;
	}

	/**
	 * [PUT]電子契約EIMの文書更新（論理削除）API
	 * 
	 * @param 電子契約情報.電子契約ドキュメントID
	 * @return 文書更新（論理削除）レスポンスDTO
	 */
	@Retryable(value = { RestClientException.class }, maxAttempts = RETRY_NUM, backoff = @Backoff(delay = RETRY_WAIT_TIME))
	public DocumentDeleteResponse deleteDocument(String documentId) {

		try {
			// リクエストボディの設定
			DocumentDeleteRequestSystem systemDto = new DocumentDeleteRequestSystem();
			systemDto.setAppId(elconEimConnectionProperties.getAppId());
			systemDto.setModelId(elconEimConnectionProperties.getModelId());
			DocumentDeleteRequestProperties propertiesDto = new DocumentDeleteRequestProperties();
			propertiesDto.setDeleteFlag("1");
			DocumentDeleteRequest requestDto = new DocumentDeleteRequest();
			requestDto.setSystem(systemDto);
			requestDto.setProperties(propertiesDto);

			RestTemplate restForEim = new RestTemplate();

			// アプリケーション認証APIコール
			ApiAuthResponse apiAuthRes = apiAuth(restForEim, null);

			// ヘッダー設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("content-type", "application/json; charset=UTF-8");
			headers.add("X-Site-Id", elconEimConnectionProperties.getXSiteId());
			headers.add("Cookie", "APISID=" + apiAuthRes.getAccess_token());

			// 文書更新（論理削除）APIコール
			String url = "https://" + elconEimConnectionProperties.getHostName() + "." + elconEimConnectionProperties.getDomainName() + "/" + elconEimConnectionProperties.getResourcesPath() + elconEimConnectionProperties.getAppId() + "/" + elconEimConnectionProperties.getDocumentsPath() + "/" + documentId;
			RequestEntity<DocumentDeleteRequest> requestEntity = new RequestEntity<>(requestDto, headers, HttpMethod.PUT, new URI(url));
			ResponseEntity<String> responseEntity = restForEim.exchange(requestEntity, String.class);

			// ステータスコードが「202：ACCEPTED」以外はエラーとする
			if (HttpStatus.ACCEPTED != responseEntity.getStatusCode()) {
				throw new RestClientException("【APIエラー】電子契約EIM文書更新（論理削除） Status Code:" + responseEntity.getStatusCode());
			}

			// 文字コードの変換処理を実施
			String conversionRespomseBody = decodedToUTF8(responseEntity.getBody(), CHARSET_ISO_8859_1);
			DocumentDeleteResponse responseDto = mapper.readValue(conversionRespomseBody, DocumentDeleteResponse.class);

			return responseDto;

		} catch (Exception e) {
			log.error("電子契約EIMの文書更新（論理削除）API実行に失敗しました。" + String.format(ERROR_DOCUMENT_ID, documentId), e);
			throw new RestClientException("【APIエラー】電子契約EIM文書更新（論理削除） Status Code:" + e.getMessage());
		}
	}

	/**
	 * 電子契約EIMの文書更新（論理削除）APIエラー時に呼ばれるメソッド
	 * 
	 * @param リトライ対象例外クラスインスタンス
	 * @return リトライ対象メソッドの戻り値
	 */
	@Recover
	private DocumentDeleteResponse recoverCallDeleteDocumentApi(RestClientException e) {
		log.error(String.format("%d回リトライしましたが、電子契約EIMの文書更新（論理削除）API呼び出しに失敗しました。", RETRY_NUM));
		throw e;
	}

	/**
	 * 文字コードをUTF-8に変換した文字列を取得する
	 * 
	 * @param encode 変換対象文字列
	 * @param charset 変換前の文字コード
	 * @return String
	 */
	private String decodedToUTF8(String encode, Charset charset) throws UnsupportedEncodingException {
		return new String(encode.getBytes(charset), CHARSET_UTF8);
	}
}
