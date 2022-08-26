package jp.co.ricoh.cotos.commonlib.logic.intramart;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.StringJoiner;
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
import org.springframework.web.client.HttpClientErrorException;
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
import lombok.Getter;
import lombok.extern.log4j.Log4j;


/**
 * Intra-mart RestAPI連携 ヘルパークラス
 */
@Log4j
public class IntraMartConnectionHelper {

	private IntraMartProperties properties;

	private RestTemplate rest;

	private ObjectMapper mapper;

	@Getter
	private SAndSWebResponseDto responseDto;

	public void init(ApplicationContext context, ExternalRestTemplate externalRestTemplate) {
		init( //
				context.getBean(IntraMartProperties.class), //
				externalRestTemplate);
	}

	private void init( //
			IntraMartProperties properties, //
			ExternalRestTemplate externalRestTemplate) {

		RestTemplate rest = externalRestTemplate.loadRestTemplate();
		rest.setErrorHandler(new DefaultResponseErrorHandler());
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
		rest.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		this.rest = rest;
		this.properties = properties;
		mapper = new ObjectMapper();
		mapper.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);
		rest.setRequestFactory(requestFactory);
	}

	/**
	 * [POST] S&S作業依頼API
	 *@throws RestClientException
	 */
	public SAndSWebResponseDto postIntraMart(SAndSWebRequestDto requestDto) throws RestClientException {
		try {
			ImCallServiceResponseDto serviceResponse = callService(HttpMethod.POST, requestDto);
			// レスポンスの取得
			String conversion = decodedToUTF8(serviceResponse.getResponseEntity().getBody());
			SAndSWebResponseDto responseDto = mapper.readValue(conversion, SAndSWebResponseDto.class);
			return responseDto;
		} catch (RuntimeException | IOException e) {
			log.error("【APIエラー】  ", e);
			throw new RestClientException("Intra-mart S&S作業依頼APIで想定外のエラーが発生しました。", e);
		}
	}

	/**
	 * サービスをコールします
	 * @throws RuntimeException
	 * @throws IOException
	 */
	private ImCallServiceResponseDto callService(HttpMethod method, SAndSWebRequestDto requestDto) throws RuntimeException, IOException {
		ResponseEntity<String> responseEntity = null;
		String conversionRespomseBody = null;
		try {
			String body = createBody(requestDto);
			URI uri = new URI(properties.getApiUrl());
			HttpHeaders headers = new HttpHeaders();
			headers.add("content-type", "application/json");
			headers.add("authorization", "Basic QVBJX1NGX05JTTAwNl9VU0VSOlNEN0RHblN4");

			RequestEntity<String> requestEntity = new RequestEntity<String>(body, headers, method, uri);
			responseEntity = rest.exchange(requestEntity, String.class);
			conversionRespomseBody = decodedToUTF8(responseEntity.getBody());
			log.info("============================================================");
			log.info("status  : " + responseEntity.getStatusCodeValue());
			log.info("headers : " + responseEntity.getHeaders());
			log.info("response: " + conversionRespomseBody);
			log.info("============================================================");
		} catch (ResourceAccessException | HttpClientErrorException | URISyntaxException e) {
			log.error("【APIエラー】  ", e);
			throw new RuntimeException(e);
		}

		this.responseDto = mapper.readValue(conversionRespomseBody, SAndSWebResponseDto.class);
		ErrorContent[] array = this.responseDto.getRegisterResultInfoDto().getErrorInfo().getErrorContent();
		if (array != null) {
			StringJoiner sj = new StringJoiner("\n");
			sj.add("Intra-mart S&S作業依頼APIでエラーが発生しました。");
			Arrays.asList(array).stream().forEach(error -> sj.add("契約番号：" + error.getErrorDocumentid() + "、エラー内容：" + error.getErrorMsg()));
			throw new RuntimeException(sj.toString());
		}

		ImCallServiceResponseDto ret = new ImCallServiceResponseDto();
		ret.setResponseEntity(responseEntity);

		return ret;
	}

	/**
	 *
	 * @throws JsonProcessingException
	 */
	protected String createBody(SAndSWebRequestDto requestDto) throws JsonProcessingException {
		String body = null;
		if (requestDto != null) {
			body = mapper.writeValueAsString(requestDto);
		}
		return body;
	}

	/**
	 *
	 * @throws UnsupportedEncodingException
	 */
	private String decodedToUTF8(String encodedWithISO88591) throws UnsupportedEncodingException {
		return new String(encodedWithISO88591.getBytes("ISO-8859-1"), "UTF-8");
	}
}
