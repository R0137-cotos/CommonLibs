package jp.co.ricoh.cotos.commonlib.logic.eim;

import java.net.URI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests.ApiAuthRequest;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests.DocumentUploadRequest;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.ApiAuthResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.DocumentUploadResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.FileDownloadResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.FileUploadResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.FileUploadResponseHeader;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.SystemAuthResponse;
import jp.co.ricoh.cotos.commonlib.util.EimConnectionProperties;

@Component
public class EimConnectionHelper {

	private static final Log log = LogFactory.getLog(EimConnectionHelper.class);

	@Autowired
	EimConnectionProperties eimConnectionProperties;

	/**
	 * EIMシステム認証
	 * @return
	 */
	private SystemAuthResponse systemAuth() {
		try {
			// EIMシステム認証
			RestTemplate restForEmi = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getSystemAuthPath();
			HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(null, headers);
			return restForEmi.exchange(url, HttpMethod.POST, requestEntity, SystemAuthResponse.class).getBody();
		} catch (Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 EIMシステム認証 Status Code:" + e.getMessage());
		}
	}

	/**
	 * アプリケーション認証
	 * @param systemAuth
	 * @return
	 */
	private ApiAuthResponse apiAuth(SystemAuthResponse systemAuth) {
		try {
			// アプリケーション認証
			RestTemplate restForEmi = new RestTemplate();

			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getApiAuthPath();

			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("X-Application-Id", systemAuth.getApplicationId());
			headers.add("X-Application-Key", systemAuth.getApplicationKey());
			headers.add("X-Site-Id", systemAuth.getSiteId());

			ApiAuthRequest apiAuthRequest = new ApiAuthRequest();
			apiAuthRequest.setLoginUserName(eimConnectionProperties.getLoginUserName());
			apiAuthRequest.setLoginPassword(eimConnectionProperties.getLoginPassword());

			RequestEntity<ApiAuthRequest> requestEntity = new RequestEntity<ApiAuthRequest>(apiAuthRequest, headers, HttpMethod.POST, new URI(url));
			ResponseEntity<ApiAuthResponse> res = restForEmi.exchange(requestEntity, ApiAuthResponse.class);
			return res.getBody();
		} catch (Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 EIMアプリケーション認証 Status Code:" + e.getMessage());
		}
	}

	/**
	 * 添付ファイルアップロード要求
	 * @param fileName
	 * @param fileBody
	 * @return
	 */
	public ResponseEntity<FileUploadResponse> postFile(String fileName, byte[] fileBody, MediaType contentType) {
		try {
			RestTemplate restForEmi = new RestTemplate();
			// EIMシステム認証
			SystemAuthResponse systemRes = systemAuth();
			// アプリケーション認証
			ApiAuthResponse apiRes = apiAuth(systemRes);

			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiRes.getAccess_token());
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			// 添付ファイルのアップロードを要求(GET)
			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getFileUploadPath() + "?" + "filename=" + fileName;
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
			RestTemplate restForEmi = new RestTemplate();
			// EIMシステム認証
			SystemAuthResponse systemRes = systemAuth();
			// アプリケーション認証
			ApiAuthResponse apiRes = apiAuth(systemRes);

			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiRes.getAccess_token());
			headers.setContentType(MediaType.APPLICATION_JSON);

			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getResourcesPath() + eimConnectionProperties.getAppId() + "/" + eimConnectionProperties.getDocumentsPath();
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
			RestTemplate restForEmi = new RestTemplate();
			// EIMシステム認証
			SystemAuthResponse systemRes = systemAuth();
			// アプリケーション認証
			ApiAuthResponse apiRes = apiAuth(systemRes);

			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiRes.getAccess_token());
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("X-Site-Id", systemRes.getSiteId());
			headers.add("Cookie", "X-Application-Token=" + systemRes.getApplicationKey());
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getFileDownloadPath() + "/" + fileId;
			// GET1回目
			ResponseEntity<FileDownloadResponse> responseEntity = restForEmi.exchange(url, HttpMethod.GET, entity, FileDownloadResponse.class);
			FileDownloadResponse res = responseEntity.getBody();
			// GET2回目
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
			RestTemplate restForEmi = new RestTemplate();
			// EIMシステム認証
			SystemAuthResponse systemRes = systemAuth();
			// アプリケーション認証
			ApiAuthResponse apiRes = apiAuth(systemRes);

			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiRes.getAccess_token());
			headers.setContentType(MediaType.APPLICATION_JSON);
			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getResourcesPath() + eimConnectionProperties.getAppId() + "/" + eimConnectionProperties.getDocumentsPath() + "?documentKey=" + request.getProperties().getDocumentUniqueID();
			RequestEntity<?> requestEntity = new RequestEntity<>(request, headers, HttpMethod.PUT, new URI(url));
			restForEmi.put(new URI(url), requestEntity);
		} catch (Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 文書を文書キーで保存 Status Code:" + e.getMessage());
		}
	}
}
