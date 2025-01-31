package jp.co.ricoh.cotos.commonlib.logic.eim;

import java.net.URI;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests.ApiAuthRequest;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests.DocumentUploadRequest;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.ApiAuthResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.DocumentUploadResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.FileDownloadResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.FileUploadResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.FileUploadResponseHeader;
import jp.co.ricoh.cotos.commonlib.util.EimConnectionProperties;

@Component
public class EimConnectionHelper {

	private static final Log log = LogFactory.getLog(EimConnectionHelper.class);

	@Autowired
	EimConnectionProperties eimConnectionProperties;

	/**
	 * propertiesクラス取得
	 *
	 * @return EimConnectionProperties
	 */
	protected EimConnectionProperties getProperties() {
		return eimConnectionProperties;
	}

	/**
	 * アプリケーション認証
	 * @param systemAuth
	 * @return
	 */
	public ApiAuthResponse apiAuth(RestTemplate restForEmi) {
		try {
			// propertiesを取得
			EimConnectionProperties properties = getProperties();

			// アプリケーション認証
			String url = "https://" + properties.getHostName() + "." + properties.getDomainName() + "/" + properties.getApiAuthPath();

			// HEADER設定
			HttpHeaders headers = createHttpHeadersApiAuth();

			ApiAuthRequest apiAuthRequest = new ApiAuthRequest();
			apiAuthRequest.setLoginUserName(properties.getLoginUserName());
			apiAuthRequest.setLoginPassword(properties.getLoginPassword());

			RequestEntity<ApiAuthRequest> requestEntity = new RequestEntity<ApiAuthRequest>(apiAuthRequest, headers, HttpMethod.POST, new URI(url));

			//ログ出力
			log.info("URL:" + url);
			log.info("X-Application-Id:" + headers.get("X-Application-Id"));
			log.info("X-Application-Key:" + headers.get("X-Application-Key"));
			log.info("X-Site-Id:" + headers.get("X-Site-Id"));

			ResponseEntity<ApiAuthResponse> res = restForEmi.exchange(requestEntity, ApiAuthResponse.class);
			return res.getBody();
		} catch (Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 EIMアプリケーション認証 Status Code:" + e.getMessage());
		}
	}

	/**
	 * アプリケーション認証用ヘッダー情報作成
	 * 
	 * @return HttpHeaders
	 */
	protected HttpHeaders createHttpHeadersApiAuth() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("X-Application-Id", eimConnectionProperties.getXApplicationId());
		headers.add("X-Application-Key", eimConnectionProperties.getXApplicationKey());
		headers.add("X-Site-Id", eimConnectionProperties.getXSiteId());

		return headers;
	}

	/**
	 * 添付ファイルアップロード要求
	 * @param fileName
	 * @param fileBody
	 * @return
	 */
	public ResponseEntity<FileUploadResponse> postFile(String fileName, byte[] fileBody, MediaType contentType) {
		try {
			// propertiesを取得
			EimConnectionProperties properties = getProperties();

			RestTemplate restForEmi = this.createEimRestTemplate();
			// アプリケーション認証
			ApiAuthResponse apiRes = apiAuth(restForEmi);

			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiRes.getAccess_token());
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			// 添付ファイルのアップロードを要求(GET)
			String url = "https://" + properties.getHostName() + "." + properties.getDomainName() + "/" + properties.getFileUploadPath() + "?" + "filename=" + fileName;
			ResponseEntity<FileUploadResponse> res = restForEmi.exchange(url, HttpMethod.GET, entity, FileUploadResponse.class);

			FileUploadResponseHeader headerRes = res.getBody().getHeader();
			// 添付ファイルのアップロードを要求(PUT)
			// HEADER設定
			headers = new HttpHeaders();
			if (null == contentType) {
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			} else {
				headers.setContentType(contentType);
			}
			headers.add("x-ms-blob-content-disposition", headerRes.getX_ms_blob_content_disposition());
			headers.add("x-ms-blob-content-type", headerRes.getX_ms_blob_content_type());
			headers.add("x-ms-blob-type", headerRes.getX_ms_blob_type());

			RequestEntity<?> requestEntity = new RequestEntity<>(fileBody, headers, HttpMethod.PUT, new URI(res.getBody().getUrl()));
			restForEmi.put(new URI(res.getBody().getUrl()), requestEntity);
			return res;
		} catch (Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 EIM添付ファイルアップロード Status Code:" + e.getMessage());
		}
	}

	/**
	 * アプリに文書を登録
	 * @param request
	 */
	public ResponseEntity<DocumentUploadResponse> postDocument(DocumentUploadRequest request) {
		try {
			// propertiesを取得
			EimConnectionProperties properties = getProperties();

			RestTemplate restForEmi = this.createEimRestTemplate();
			// アプリケーション認証
			ApiAuthResponse apiRes = apiAuth(restForEmi);

			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiRes.getAccess_token());
			headers.setContentType(MediaType.APPLICATION_JSON);

			String url = "https://" + properties.getHostName() + "." + properties.getDomainName() + "/" + properties.getResourcesPath() + properties.getAppId() + "/" + properties.getDocumentsPath();
			RequestEntity<DocumentUploadRequest> requestEntity = new RequestEntity<DocumentUploadRequest>(request, headers, HttpMethod.POST, new URI(url));
			return restForEmi.exchange(requestEntity, DocumentUploadResponse.class);
		} catch (Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 文書登録 Status Code:" + e.getMessage());
		}
	}

	/**
	 * 添付ファイルのダウンロード要求
	 * @param fileId
	 * @return
	 */
	public byte[] getFile(String fileId) {
		try {
			// propertiesを取得
			EimConnectionProperties properties = getProperties();

			log.info("start -- getFile　--");
			log.info("start -- EIM認証RestTemplate作成 --");
			RestTemplate restForEmi = this.createEimRestTemplate();
			log.info("end -- EIM認証RestTemplate作成 --");

			// アプリケーション認証
			log.info("start -- アプリケーション認証 --");
			ApiAuthResponse apiRes = apiAuth(restForEmi);
			log.info("end -- アプリケーション認証 --");
			// HEADER設定
			log.info("start -- ファイルダウンロード --");
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiRes.getAccess_token());
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("X-Site-Id", properties.getXSiteId());
			headers.add("Cookie", "X-Application-Token=" + properties.getXApplicationKey());
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			String url = "https://" + properties.getHostName() + "." + properties.getDomainName() + "/" + properties.getFileDownloadPath() + "/" + fileId;
			//ログ出力
			log.info("URL:" + url);
			log.info("Cookie:" + headers.get("Cookie"));
			log.info("X-Site-Id:" + headers.get("X-Site-Id"));
			// GET1回目
			ResponseEntity<FileDownloadResponse> responseEntity = restForEmi.exchange(url, HttpMethod.GET, entity, FileDownloadResponse.class);
			FileDownloadResponse res = responseEntity.getBody();
			// GET2回目
			log.info("URL:" + res.getUrl());
			return restForEmi.exchange(new URI(res.getUrl()), HttpMethod.GET, entity, byte[].class).getBody();
		} catch (Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 添付ファイルのダウンロード要求 Status Code:" + e.getMessage());
		}
	}

	/**
	 * 文書を文書キーで保存
	 * @param request
	 */
	public void putDocument(DocumentUploadRequest request) {
		try {
			// propertiesを取得
			EimConnectionProperties properties = getProperties();

			RestTemplate restForEmi = this.createEimRestTemplate();
			// アプリケーション認証
			ApiAuthResponse apiRes = apiAuth(restForEmi);

			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiRes.getAccess_token());
			headers.setContentType(MediaType.APPLICATION_JSON);
			String url = "https://" + properties.getHostName() + "." + properties.getDomainName() + "/" + properties.getResourcesPath() + properties.getAppId() + "/" + properties.getDocumentsPath() + "?documentKey=" + request.getProperties().getDocumentKey();
			RequestEntity<?> requestEntity = new RequestEntity<>(request, headers, HttpMethod.PUT, new URI(url));
			restForEmi.put(new URI(url), requestEntity);
		} catch (Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 文書を文書キーで保存 Status Code:" + e.getMessage());
		}
	}

	/**
	 * EIM認証RestTemplate作成
	 * @return
	 * @throws Exception
	 */
	public RestTemplate createEimRestTemplate() throws Exception {
		// propertiesを取得
		EimConnectionProperties properties = getProperties();

		if ("rfg3".equals(properties.getHostName())) {

			String key = "cotoscotoscotos";
			String algorithm = "BLOWFISH";
			SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), algorithm);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, sksSpec);
			final String username = new String(cipher.doFinal(Base64.getDecoder().decode("NWkNSo0c+pUdTkJ3iwrAyw==")));
			final String password = new String(cipher.doFinal(Base64.getDecoder().decode("9mcYkD5HEKEXVARy99kUJg==")));
			final String proxyUrl = "proxy.ricoh.co.jp";
			final int port = 8080;

			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
					new AuthScope(proxyUrl, port),
					new UsernamePasswordCredentials(username, password));

			HttpHost myProxy = new HttpHost(proxyUrl, port);
			HttpClientBuilder clientBuilder = HttpClientBuilder.create();

			clientBuilder
					.setProxy(myProxy)
					.setDefaultCredentialsProvider(credsProvider)
					.disableCookieManagement();

			HttpClient httpClient = clientBuilder.build();
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setHttpClient(httpClient);

			return new RestTemplate(factory);
		} else {
			return new RestTemplate();
		}
	}
}
