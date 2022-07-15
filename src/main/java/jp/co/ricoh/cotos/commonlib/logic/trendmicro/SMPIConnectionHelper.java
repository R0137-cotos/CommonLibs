package jp.co.ricoh.cotos.commonlib.logic.trendmicro;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.AbstractTmRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmCallServiceResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmGetWfbssDomainsResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPostWfbssReportRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPostWfbssReportResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto;
import jp.co.ricoh.cotos.commonlib.rest.ExternalRestTemplate;
import jp.co.ricoh.cotos.commonlib.util.SMPIProperties;
import lombok.extern.log4j.Log4j;

/**
 * TrendMicro SMPI連携 ヘルパークラス
 * BatchesLightTemplateでも使用することを想定するため、コンポーネント化しない
 *
 */
@Log4j
public class SMPIConnectionHelper {

	private static final SMPIConnectionHelper INSTANCE = new SMPIConnectionHelper();

	private SMPIProperties properties;

	private RestTemplate rest;

	private ObjectMapper mapper;

	private TrendMicroUtil trendMicroUtil;

	private SMPIConnectionHelper() {
		// シングルトン
	}

	public static void init(ApplicationContext context, ExternalRestTemplate externalRestTemplate) {
		init( //
				context.getBean(SMPIProperties.class), //
				context.getBean(TrendMicroUtil.class), //
				externalRestTemplate);
	}

	private static void init( //
			SMPIProperties properties, //
			TrendMicroUtil trendMicroUtil, //
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
		rest.setRequestFactory(requestFactory);
		// Util設定
		INSTANCE.trendMicroUtil = trendMicroUtil;
	}

	public static SMPIConnectionHelper getInstance() {
		if (INSTANCE.properties == null) {
			throw new IllegalStateException("初期化されていません。initを先に呼び出してください。");
		}
		return INSTANCE;
	}

	/**
	 * [GET] 顧客のドメイン取得API
	 * @param customerId お客様(会社)を一意に表すID
	 */
	public TmGetWfbssDomainsResponseDto getWfbssDomains(String customerId) {
		String url = "/wfbss/api/domains?cids=" + customerId;
		try {
			TmCallServiceResponseDto serviceResponse = callService(url, HttpMethod.GET, null);
			// ステータスコードの確認
			log.info("TrendMicroドメイン取得API StatusCode:" + serviceResponse.getResponseEntity().getStatusCode());
			// レスポンスの取得
			TmGetWfbssDomainsResponseDto responseDto = mapper.readValue(serviceResponse.getResponseEntity().getBody(), TmGetWfbssDomainsResponseDto.class);
			return responseDto;
		} catch (URISyntaxException | IOException e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			// このクラスを使用している軽量テンプレートバッチでErrorCheckExceptionが使用できない為、RuntimeExceptionでthrowしています。
			throw new RuntimeException("[TM] 顧客のドメイン取得APIで想定外のエラーが発生しました。");
		}
	}

	/**
	 * [POST] WFBSS初期化API
	 * @param customerId お客様(会社)を一意に表すID
	 */
	public void postWfbssInitialize(String customerId) {
		String url = "/wfbss/api/initialize?cids=" + customerId;
		try {
			TmCallServiceResponseDto serviceResponse = callService(url, HttpMethod.POST, null);
			// ステータスコードの確認
			log.info("TrendMicroWFBSS初期化 StatusCode:" + serviceResponse.getResponseEntity().getStatusCode());
		} catch (JsonProcessingException | UnsupportedEncodingException | URISyntaxException e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			// このクラスを使用している軽量テンプレートバッチでErrorCheckExceptionが使用できない為、RuntimeExceptionでthrowしています。
			throw new RuntimeException("[TM] WFBSS初期化APIで想定外のエラーが発生しました。");
		}
	}

	/**
	 * [POST] レポート作成API
	 * TrendMicro側で作成されるレポートのスケジュール設定を行う
	 * @param customerId お客様(会社)を一意に表すID
	 * @param requestDto トレンドマイクロWfbssレポート作成リクエストDTO
	 */
	public TmPostWfbssReportResponseDto postWfbssReport(String customerId, TmPostWfbssReportRequestDto requestDto) {
		String url = "/wfbss/api/report?cids=" + customerId;
		try {
			TmCallServiceResponseDto serviceResponse = callService(url, HttpMethod.POST, requestDto);
			// ステータスコードの確認
			log.info("TrendMicroレポート作成API StatusCode:" + serviceResponse.getResponseEntity().getStatusCode());
			// レスポンスの取得
			TmPostWfbssReportResponseDto responseDto = mapper.readValue(serviceResponse.getResponseEntity().getBody(), TmPostWfbssReportResponseDto.class);
			return responseDto;
		} catch (URISyntaxException | IOException e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			// このクラスを使用している軽量テンプレートバッチでErrorCheckExceptionが使用できない為、RuntimeExceptionでthrowしています。
			throw new RuntimeException("[TM] レポート作成APIで想定外のエラーが発生しました。");
		}
	}

	/**
	 * [PUT] 通知設定変更API
	 * @param customerId お客様(会社)を一意に表すID
	 * @param requestDto トレンドマイクロWfbss通知設定更新リクエストDTO
	 */
	public void putWfbssNotifSettings(String customerId, TmPutWfbssNotifSettingsRequestDto requestDto) {
		String url = "/wfbss/api/notif_settings?cids=" + customerId + "&sync=true";
		try {
			TmCallServiceResponseDto serviceResponse = callService(url, HttpMethod.PUT, requestDto);
			// ステータスコードの確認
			log.info("TrendMicro通知設定変更API StatusCode:" + serviceResponse.getResponseEntity().getStatusCode());
			// sync=true の為戻り値無し
		} catch (JsonProcessingException | UnsupportedEncodingException | URISyntaxException e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			// このクラスを使用している軽量テンプレートバッチでErrorCheckExceptionが使用できない為、RuntimeExceptionでthrowしています。
			throw new RuntimeException("[TM] 通知設定変更APIで想定外のエラーが発生しました。");
		}
	}

	/**
	 * HttpHeadersを返します。
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private HttpHeaders getHttpHeaders(URI uri, HttpMethod method, String bodyJson) throws UnsupportedEncodingException {

		HttpHeaders headers = new HttpHeaders();
		byte[] content = bodyJson != null ? bodyJson.getBytes("UTF-8") : null;
		String path = uri.getPath();
		if (StringUtils.isNotEmpty(uri.getQuery())) {
			path = path + "?" + uri.getQuery();
		}
		long posix_time = INSTANCE.getPosixTime();
		headers.add("x-access-token", properties.getAccessToken());
		headers.add("x-signature",
				this.xSignatureGenerate(//
						properties.getSecretKey(), //
						posix_time, //
						method.toString(), //
						path, //
						content));
		headers.add("x-posix-time", String.valueOf(posix_time));
		headers.add("x-traceid", UUID.randomUUID().toString());
		headers.add("content-type", "application/json;charset=UTF-8");
		return headers;
	}

	/**
	 * サービスをコールします。
	 * エラー時を考慮し、String型で受け取る。
	 * @param <T>
	 * @param uri
	 * @param method
	 * @param requestWork
	 * @param responseClass
	 * @return
	 * @throws JsonProcessingException
	 * @throws URISyntaxException
	 * @throws UnsupportedEncodingException
	 */
	private TmCallServiceResponseDto callService(String url, HttpMethod method, AbstractTmRequestDto requestDto) throws JsonProcessingException, URISyntaxException, UnsupportedEncodingException {
		String body = null;
		if (requestDto != null) {
			body = mapper.writeValueAsString(requestDto);
		}
		URI uri = new URI(INSTANCE.properties.getUrlPrefix() + url);
		HttpHeaders header = getHttpHeaders(uri, method, body);
		RequestEntity<String> requestEntity = new RequestEntity<String>(body, header, method, uri);
		ResponseEntity<String> responseEntity = trendMicroUtil.callApi(rest, requestEntity);
		// HTTPステータスが200系以外はエラーとする。
		if (!responseEntity.getStatusCode().is2xxSuccessful()) {
			// このクラスを使用している軽量テンプレートバッチでErrorCheckExceptionが使用できない為、RuntimeExceptionでthrowしています。
			throw new RuntimeException("TrendMicroAPIでエラーが発生しました。ステータスコード： " + responseEntity.getStatusCodeValue() + "、エラー内容：" + responseEntity.getBody());
		}
		TmCallServiceResponseDto ret = new TmCallServiceResponseDto();
		ret.setResponseEntity(responseEntity);
		// リクエスト情報の保持
		// posix_timeの時間制限がるため送信前に情報を保持できないため、
		// リクエスト実施後に保持をする。
		ret.setUrl(uri.toString());
		ret.setHttpHeaders(header);
		ret.setHttpBody(body);
		ret.setRequestTime(new Date());
		return ret;
	}

	private long getPosixTime() {
		return new Date().getTime() / 1000L;
	}

	/**
	 * Generate a x-signature header value that is required to invoke LMPI
	 * @param secret The secret key assigned by Trend Micro
	 * @param unixTimestamp The x-posix-time attribute specified in header, it is suppose to the request time in unix timestamp format.
	 * @param method The HTTP method that is used to invoke LMPI
	 * @param uri The absolute uri being requested. The url should url-encoded that is similar to something like:
	 *  /customers?name=some%20customer%20name
	 * @param content The HTTP content that is to be hashed, pass null if there's no content to be hashed.
	 * @return a SHA-256 hashed digest in Base64 string.
	 */
	private String xSignatureGenerate(String secret, long unixTimestamp,
			String method, String uri, byte[] content) {
		MessageDigest md = null;

		String posix = String.valueOf(unixTimestamp);
		String payload = posix + method.toUpperCase() + uri;
		String contentBase64 = "";

		// Create a MD5 hash of content if not null.
		if (content != null) {
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(content);
				contentBase64 = Base64.encodeBase64String(md.digest());
			} catch (NoSuchAlgorithmException e) {
				log.error(e);
			}
			payload += contentBase64;
		}
		try {
			Mac hmac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
			hmac.init(secret_key);

			byte[] hashed = hmac.doFinal(payload.getBytes("UTF-8"));
			return new Base64().encodeAsString(hashed);

		} catch (Exception ex) {
			log.error("unable to create message hash." + ex.toString());
		}
		return null;
	}
}
