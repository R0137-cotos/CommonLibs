package jp.co.ricoh.cotos.commonlib.logic.intramart;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.ImCallServiceResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.RegisterResultInfoDto.ErrorInfo.ErrorContent;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.SAndSWebRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.SAndSWebResponseDto;
import jp.co.ricoh.cotos.commonlib.rest.ExternalRestTemplate;
import jp.co.ricoh.cotos.commonlib.util.IntraMartProperties;
import lombok.extern.log4j.Log4j;

/**
 * Intra-mart RestAPI連携 ヘルパークラス
 */
@Log4j
public class IntraMartConnectionHelper {

	private static final IntraMartConnectionHelper INSTANCE = new IntraMartConnectionHelper();

	private IntraMartProperties properties;

	private RestTemplate rest;

	private ObjectMapper mapper;

	private IntraMartConnectionHelper() {
		// シングルトン
	}

	public static void init(ApplicationContext context, ExternalRestTemplate externalRestTemplate) {
		init( //
				context.getBean(IntraMartProperties.class), //
				externalRestTemplate);
	}

	private static void init( //
			IntraMartProperties properties, //
			ExternalRestTemplate externalRestTemplate) {

		RestTemplate rest = externalRestTemplate.loadRestTemplate();
		rest.setErrorHandler(new DefaultResponseErrorHandler() {
			//			@Override
			//			public void handleError(ClientHttpResponse response) throws IOException {
			//				// 何も書かないことで、サーバーエラーとクライアントエラーが起きても例外を発生させずにBodyにエラーメッセージを返す。
			//				// ★★★トレンド特有の処理。エラーが発生したときも200エラーが返ってきて、エラー内容がBodyに入ってしまっているため。不要かどうか確認し、不要であれば削除する。
			//			}
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
	}

	public static IntraMartConnectionHelper getInstance() {
		if (INSTANCE.properties == null) {
			throw new IllegalStateException("初期化されていません。initを先に呼び出してください。");
		}
		return INSTANCE;
	}

	/**
	 * [POST] S&S作業依頼API
	 */
	public SAndSWebResponseDto postIntraMart(SAndSWebRequestDto requestDto) {
		try {
			ImCallServiceResponseDto serviceResponse = callService(HttpMethod.POST, requestDto);
			String conversion = decodedToUTF8(serviceResponse.getResponseEntity().getBody());
			SAndSWebResponseDto result = mapper.readValue(conversion, SAndSWebResponseDto.class);
			if (result.getRegisterResultInfoDto().getErrorInfo() != null) {
				ErrorContent[] array = result.getRegisterResultInfoDto().getErrorInfo().getErrorContent();
				for (int i = 0; i < array.length; i++) {
					log.info("Intra-mart S&S作業依頼API StatusCode：" + array[i].getErrorDocumentid() + "、エラー内容：" + array[i].getErrorMsg());
				}
			}
			return result;
		} catch (URISyntaxException | IOException e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			throw new RestClientException("Intra-mart S&S作業依頼APIで想定外のエラーが発生しました。", e);
		}
	}

	/**
	 * サービスをコールします
	 */
	private ImCallServiceResponseDto callService(HttpMethod method, SAndSWebRequestDto requestDto) throws JsonProcessingException, URISyntaxException, UnsupportedEncodingException {
		String body = null;
		if (requestDto != null) {
			body = mapper.writeValueAsString(requestDto);
		}
		URI uri = new URI(INSTANCE.properties.getApiUrl());
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("authorization", "Basic QVBJX1NGX05JTTAwNl9VU0VSOlNEN0RHblN4");

		RequestEntity<String> requestEntity = new RequestEntity<String>(body, headers, method, uri);
		ResponseEntity<String> responseEntity = null;

		try {
			responseEntity = rest.exchange(requestEntity, String.class);
			String conversionRespomseBody = decodedToUTF8(responseEntity.getBody());
			log.info("============================================================");
			log.info("status  : " + requestEntity);
			log.info("status  : " + responseEntity.getStatusCodeValue());
			log.info("headers : " + responseEntity.getHeaders());
			log.info("response: " + conversionRespomseBody);
			log.info("============================================================");
		} catch (ResourceAccessException e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			throw e;
		}

		// HTTPステータスが200系以外はエラーとする
		if (!responseEntity.getStatusCode().is2xxSuccessful()) {
			throw new RuntimeException("Intra-martAPIでエラーが発生しました。ステータスコード： " + responseEntity.getStatusCodeValue() + "、エラー内容： " + responseEntity.getBody());
		}

		ImCallServiceResponseDto ret = new ImCallServiceResponseDto();
		ret.setResponseEntity(responseEntity);

		return ret;
	}

	private String decodedToUTF8(String encodedWithISO88591) throws UnsupportedEncodingException {
		return new String(encodedWithISO88591.getBytes("ISO-8859-1"), "UTF-8");
	}
}
