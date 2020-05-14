package jp.co.ricoh.cotos.commonlib.logic.eim;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests.DocumentUploadRequest;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.ApiAuthResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.DocumentUploadResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.FileDownloadResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.FileUploadResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.SystemAuthResponse;
import jp.co.ricoh.cotos.commonlib.util.EimConnectionProperties;
import lombok.Setter;

@Component
public class EimConnectionHelper {
	
	private static final Log log = LogFactory.getLog(EimConnectionHelper.class);
			
	@Autowired
	EimConnectionProperties eimConnectionProperties;
	
	@Setter
	@Autowired
	RestTemplate restForEmi;

	@Autowired
	ApiAuthResponse apiAuthResponse;
	
	/**
	 * EIMシステム認証
	 * @return
	 */
	private SystemAuthResponse systemAuth() {
		try {
			// EIMシステム認証
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getSystemAuthPath();
			return restForEmi.postForEntity(url, headers, SystemAuthResponse.class).getBody();
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
			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("applicationId", systemAuth.getApplicationId());
			headers.add("applicationKey", systemAuth.getApplicationKey());
			headers.add("siteId", systemAuth.getSiteId());
			
			// BODY設定
			LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
			params.add("loginUserName", eimConnectionProperties.getLoginUserName());
			params.add("loginPassword", eimConnectionProperties.getLoginPassword());
			HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getApiAuthPath();
			ResponseEntity<ApiAuthResponse> res = restForEmi.exchange(url, HttpMethod.POST, requestEntity, ApiAuthResponse.class);

			apiAuthResponse.setAccessToken(res.getBody().getAccessToken()); // アクセストークンを保持
			return res.getBody();
		} catch(Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 EIMアプリケーション認証 Status Code:" + e.getMessage());
		}
	}

	/**
	 * 添付ファイルのアップロードを要求
	 * @param fileName
	 * @param fileBody
	 * @return
	 */
	public ResponseEntity<FileUploadResponse> postFile(String fileName, byte[] fileBody) {
		try {
			//TODO 以下は必要か？　必要な場合、他のメソッドにも追加すること
			SystemAuthResponse systemRes = systemAuth();
			ApiAuthResponse apiRes = apiAuth(systemRes);
			
			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiAuthResponse.getAccessToken());
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			// 添付ファイルのアップロードを要求(GET)
			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getFileUploadPath() + "?" + "filename = test02.pdf";
			ResponseEntity<FileUploadResponse> res  = restForEmi.exchange(url, HttpMethod.GET, entity, FileUploadResponse.class);
			
			// 添付ファイルのアップロードを要求(PUT)
			// HEADER設定
			headers = new HttpHeaders();
			headers.add("x-ms-blob-content-disposition", res.getBody().getHeader().getX_ms_blob_content_disposition());
			headers.add("x-ms-blob-content-type", res.getBody().getHeader().getX_ms_blob_content_type());
			headers.add("x-ms-blob-type", res.getBody().getHeader().getX_ms_blob_type());

			// BODY設定
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("file", fileBody);

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			restForEmi.put(res.getBody().getUrl(), requestEntity);
			return res;
		} catch(Exception e) {
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
			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiAuthResponse.getAccessToken());
			headers.setContentType(MediaType.APPLICATION_JSON);
	
			// BODY設定
			LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonStr = mapper.writeValueAsString(request.getSystem());
			params.add("system", jsonStr);
			jsonStr = mapper.writeValueAsString(request.getProperties());
			params.add("properties", jsonStr);
			HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
			
			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getResourcesPath() + "/" + eimConnectionProperties.getAppIdMonthDB() + "/" + eimConnectionProperties.getDocumentsPath();
			return restForEmi.exchange(url, HttpMethod.POST, requestEntity, DocumentUploadResponse.class);
		} catch(Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 文書登録 Status Code:" + e.getMessage());
		}
	}
	
	/**
	 * 添付ファイルのダウンロードを要求
	 * @param fileId
	 * @return
	 */
	public byte[] getFile(String fileId) {
		try {
			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiAuthResponse.getAccessToken());
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getFileDownloadPath() + "/" + fileId;
			FileDownloadResponse res  = restForEmi.exchange(url, HttpMethod.GET, entity, FileDownloadResponse.class).getBody();
			
			return restForEmi.exchange(res.getUrl(), HttpMethod.GET, entity, byte[].class).getBody();
		} catch(Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 付ファイルのダウンロード要求 Status Code:" + e.getMessage());
		}
	}
	
	/**
	 * 文書を文書キーで保存
	 * @param request
	 */
	public void putDocument(DocumentUploadRequest request) {
		try {
			// HEADER設定
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cookie", "APISID=" + apiAuthResponse.getAccessToken());
			headers.setContentType(MediaType.APPLICATION_JSON);
			// BODY設定
			LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(request.getProperties());
			params.add("properties", jsonStr);
			HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
			
			String url = "https://" + eimConnectionProperties.getHostName() + "." + eimConnectionProperties.getDomainName() + "/" + eimConnectionProperties.getResourcesPath() + "/" + eimConnectionProperties.getAppIdMonthDB() + "/" + eimConnectionProperties.getDocumentsPath() + "?documentKey=" + request.getProperties().getDocumentUniqueID();
			restForEmi.put(url, requestEntity);
			
		} catch(Exception e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("【APIエラー】 文書を文書キーで保存 Status Code:" + e.getMessage());
		}
	}
}
