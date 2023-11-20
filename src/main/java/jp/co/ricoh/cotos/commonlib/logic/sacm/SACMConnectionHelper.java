package jp.co.ricoh.cotos.commonlib.logic.sacm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.TimeZone;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.SACMUpdateServiceAdapterInfoRequestParameter;
import jp.co.ricoh.cotos.commonlib.rest.ExternalRestTemplate;
import jp.co.ricoh.cotos.commonlib.util.SACMProperties;
import lombok.extern.log4j.Log4j;

/**
 * SACM連携 ヘルパークラス
 * BatchesLightTemplateでも使用するため、コンポーネント化しない
 *
 */
@Log4j
public class SACMConnectionHelper {

	private static final SACMConnectionHelper INSTANCE = new SACMConnectionHelper();

	private RestTemplate rest;

	private ObjectMapper mapper;

	private SACMProperties properties;

	private SACMUtil sacmUtil;

	private SACMConnectionHelper() {
		// シングルトン
	}

	public static void init(ApplicationContext context, ExternalRestTemplate externalRestTemplate) {
		init( //
				context.getBean(SACMProperties.class), //
				context.getBean(SACMUtil.class), //
				externalRestTemplate);
	}

	private static void init( //
			SACMProperties properties, //
			SACMUtil sacmUtil, //
			ExternalRestTemplate externalRestTemplate) {

		RestTemplate rest = externalRestTemplate.loadRestTemplate();
		rest.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				// 何も書かないことで、サーバーエラーとクライアントエラーが起きても例外を発生させずにBodyにエラーメッセージを返す。
			}
		});
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
		rest.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		INSTANCE.rest = rest;
		INSTANCE.properties = properties;
		INSTANCE.mapper = new ObjectMapper();
		INSTANCE.mapper.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);
		requestFactory.setConnectTimeout(10000);
		requestFactory.setReadTimeout(10000);
		rest.setRequestFactory(requestFactory);
		// Util設定
		INSTANCE.sacmUtil = sacmUtil;
	}

	public static SACMConnectionHelper getInstance() {
		if (INSTANCE.properties == null) {
			throw new IllegalStateException("初期化されていません。initを先に呼び出してください。");
		}
		return INSTANCE;
	}

	/**
	 * SACMサービスアダプタ情報更新API呼び出し
	 * @param userCode
	 * @param saCode
	 * @param name
	 * @param distributionId
	 */
	public void putUpdateServiceAdapterInfo(String userCode, String saCode, String name, String distributionId) {
		String url = properties.getApiUrl() + "/user/" + userCode + "/sa/" + saCode;

		SACMUpdateServiceAdapterInfoRequestParameter param = new SACMUpdateServiceAdapterInfoRequestParameter();

		param.setName(name);
		param.setDistributionId(distributionId);
		param.setDescription("");
		param.setPreferredPushMethod("none");

		log.info("SACMサービスアダプタ情報更新API呼び出し");
		log.info("url:" + url);
		log.info("user_code:" + userCode);
		log.info("sa_code:" + saCode);
		log.info("name:" + name);
		log.info("distributionId:" + distributionId);

		try {
			String body = null;
			if (param != null) {
				body = mapper.writeValueAsString(param);
			}
			URI uri;
			uri = new URI(url);
			HttpHeaders header = getHttpHeaders(uri, HttpMethod.PUT, body);
			RequestEntity<String> requestEntity = new RequestEntity<String>(body, header, HttpMethod.PUT, uri);
			sacmUtil.callApi(rest, requestEntity);
		} catch (URISyntaxException | JsonProcessingException | UnsupportedEncodingException e) {
			log.error(e);
			throw new RuntimeException("SACMサービスアダプタ情報更新APIでエラーが発生しました。");
		}

	}

	/**
	 * HttpHeadersを返します。
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private HttpHeaders getHttpHeaders(URI uri, HttpMethod method, String bodyJson) throws UnsupportedEncodingException {

		HttpHeaders headers = new HttpHeaders();
		// Authorizationの設定
		String accessKey = properties.getAccessKey();
		String accessKeySecret = properties.getAccessKeySecret();
		StringBuilder plainCredentialStringBuilder = new StringBuilder();
		String plainCredentials = plainCredentialStringBuilder.append(accessKey).append(":").append(accessKeySecret).toString();
		String base64Credentials = Base64.getEncoder().encodeToString(plainCredentials.getBytes(StandardCharsets.UTF_8));
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + base64Credentials);
		headers.add("content-type", "application/json;charset=UTF-8");
		return headers;
	}

}
