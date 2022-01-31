package jp.co.ricoh.cotos.commonlib.logic.trendmicro;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.AbstractTmRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmCallServiceResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmCreateCustomerRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmCreateCustomerResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmCreateCustomerResponseDto.TmCreateCustomerResponseDtoUser;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmCreateSubscriptionRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmCreateSubscriptionResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmGetCustomerResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmSuspendSubscriptionRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmSuspendSubscriptionResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmUpdateCustomerRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmUpdateCustomerResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmUpdateSubscriptionRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmUpdateSubscriptionResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmUpdateUserRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmUpdateUserResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmUpdateUserResponseDto.TmUpdateUserResponseDtoPhone;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmSuspendSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmSuspendSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateUserRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateUserResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork.TmRequestStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmResponseWork.TmLicenceMappedStatus;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateCustomerRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateCustomerResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateSubscriptionRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateSubscriptionResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmSuspendSubscriptionRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmSuspendSubscriptionResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateCustomerRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateCustomerResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateSubscriptionRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateSubscriptionResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateUserRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateUserResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.util.LMPIProperties;
import lombok.extern.log4j.Log4j;

/**
 * TrendMicro LMPI連携 ヘルパークラス
 * BatchesLightTemplateでも使用することを想定するため、コンポーネント化しない
 * @author z00se03039
 *
 */
@Log4j
public class LMPIConnectionHelper {

	private static final LMPIConnectionHelper INSTANCE = new LMPIConnectionHelper();

	private TMConverter tmConverter;

	private LMPIProperties properties;

	private RestTemplate rest;

	private ObjectMapper mapper;

	private TmCreateCustomerRequestWorkRepository tmCreateCustomerRequestWorkRepository;

	private TmCreateCustomerResponseWorkRepository tmCreateCustomerResponseWorkRepository;

	private TmUpdateCustomerRequestWorkRepository tmUpdateCustomerRequestWorkRepository;

	private TmUpdateCustomerResponseWorkRepository tmUpdateCustomerResponseWorkRepository;

	private TmUpdateUserRequestWorkRepository tmUpdateUserRequestWorkRepository;

	private TmUpdateUserResponseWorkRepository tmUpdateUserResponseWorkRepository;

	private TmCreateSubscriptionRequestWorkRepository tmCreateSubscriptionRequestWorkRepository;

	private TmCreateSubscriptionResponseWorkRepository tmCreateSubscriptionResponseWorkRepository;

	private TmUpdateSubscriptionRequestWorkRepository tmUpdateSubscriptionRequestWorkRepository;

	private TmUpdateSubscriptionResponseWorkRepository tmUpdateSubscriptionResponseWorkRepository;

	private TmSuspendSubscriptionRequestWorkRepository tmSuspendSubscriptionRequestWorkRepository;

	private TmSuspendSubscriptionResponseWorkRepository tmSuspendSubscriptionResponseWorkRepository;

	private LMPIConnectionHelper() {
		// シングルトン
	}

	public static void init(ApplicationContext context) {
		init(//
				context.getBean(LMPIProperties.class), //
				context.getBean(TmCreateCustomerRequestWorkRepository.class), //
				context.getBean(TmCreateCustomerResponseWorkRepository.class), //
				context.getBean(TmUpdateCustomerRequestWorkRepository.class), //
				context.getBean(TmUpdateCustomerResponseWorkRepository.class), //
				context.getBean(TmUpdateUserRequestWorkRepository.class), //
				context.getBean(TmUpdateUserResponseWorkRepository.class), //
				context.getBean(TmCreateSubscriptionRequestWorkRepository.class), //
				context.getBean(TmCreateSubscriptionResponseWorkRepository.class), //
				context.getBean(TmUpdateSubscriptionRequestWorkRepository.class), //
				context.getBean(TmUpdateSubscriptionResponseWorkRepository.class), //
				context.getBean(TmSuspendSubscriptionRequestWorkRepository.class), //
				context.getBean(TmSuspendSubscriptionResponseWorkRepository.class)); //
	}

	private static void init( //
			LMPIProperties properties, //
			TmCreateCustomerRequestWorkRepository tmCreateCustomerRequestWorkRepository, //
			TmCreateCustomerResponseWorkRepository tmCreateCustomerResponseWorkRepository, //
			TmUpdateCustomerRequestWorkRepository tmUpdateCustomerRequestWorkRepository, //
			TmUpdateCustomerResponseWorkRepository tmUpdateCustomerResponseWorkRepository, //
			TmUpdateUserRequestWorkRepository tmUpdateUserRequestWorkRepository, //
			TmUpdateUserResponseWorkRepository tmUpdateUserResponseWorkRepository, //
			TmCreateSubscriptionRequestWorkRepository tmCreateSubscriptionRequestWorkRepository, //
			TmCreateSubscriptionResponseWorkRepository tmCreateSubscriptionResponseWorkRepository, //
			TmUpdateSubscriptionRequestWorkRepository tmUpdateSubscriptionRequestWorkRepository, //
			TmUpdateSubscriptionResponseWorkRepository tmUpdateSubscriptionResponseWorkRepository, //
			TmSuspendSubscriptionRequestWorkRepository tmSuspendSubscriptionRequestWorkRepository, //
			TmSuspendSubscriptionResponseWorkRepository tmSuspendSubscriptionResponseWorkRepository) {

		RestTemplate rest = new RestTemplate();
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
		INSTANCE.tmConverter = new TMConverter();

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);
		rest.setRequestFactory(requestFactory);

		// リポジトリ設定
		INSTANCE.tmCreateCustomerRequestWorkRepository = tmCreateCustomerRequestWorkRepository;
		INSTANCE.tmCreateCustomerResponseWorkRepository = tmCreateCustomerResponseWorkRepository;
		INSTANCE.tmUpdateCustomerRequestWorkRepository = tmUpdateCustomerRequestWorkRepository;
		INSTANCE.tmUpdateCustomerResponseWorkRepository = tmUpdateCustomerResponseWorkRepository;
		INSTANCE.tmUpdateUserRequestWorkRepository = tmUpdateUserRequestWorkRepository;
		INSTANCE.tmUpdateUserResponseWorkRepository = tmUpdateUserResponseWorkRepository;
		INSTANCE.tmCreateSubscriptionRequestWorkRepository = tmCreateSubscriptionRequestWorkRepository;
		INSTANCE.tmCreateSubscriptionResponseWorkRepository = tmCreateSubscriptionResponseWorkRepository;
		INSTANCE.tmUpdateSubscriptionRequestWorkRepository = tmUpdateSubscriptionRequestWorkRepository;
		INSTANCE.tmUpdateSubscriptionResponseWorkRepository = tmUpdateSubscriptionResponseWorkRepository;
		INSTANCE.tmSuspendSubscriptionRequestWorkRepository = tmSuspendSubscriptionRequestWorkRepository;
		INSTANCE.tmSuspendSubscriptionResponseWorkRepository = tmSuspendSubscriptionResponseWorkRepository;
	}

	public static LMPIConnectionHelper getInstance() {
		if (INSTANCE.properties == null) {
			throw new IllegalStateException("初期化されていません。initを先に呼び出してください。");
		}
		return INSTANCE;
	}

	/**
	 * [POST] 顧客作成API
	 */
	public TmCreateCustomerResponseWork postCustomers(TmCreateCustomerRequestWork requestWork) {
		String url = "/customers";
		try {
			TmCreateCustomerRequestDto requestDto = tmConverter.convertRequestToDto(requestWork);
			TmCallServiceResponseDto serviceResponse = callService(url, HttpMethod.POST, requestDto);

			// リクエストの更新
			this.setRequestData(requestWork, serviceResponse);
			TmCreateCustomerRequestWork updatedWork = tmCreateCustomerRequestWorkRepository.save(requestWork);

			// レスポンスの登録
			TmCreateCustomerResponseWork responseWork = tmConverter.convertDtoToResponseWork(mapper.readValue(serviceResponse.getResponseEntity().getBody(), TmCreateCustomerResponseDto.class), updatedWork);
			responseWork.setHttpStatus(serviceResponse.getResponseEntity().getStatusCode().toString());
			return tmCreateCustomerResponseWorkRepository.save(responseWork);
		} catch (URISyntaxException | IOException e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * [PUT] 会社情報更新API
	 */
	public TmUpdateCustomerResponseWork putCustomers(TmUpdateCustomerRequestWork requestWork) {
		String url = "/customers/" + requestWork.getCustomerId();
		try {
			TmUpdateCustomerRequestDto requestDto = tmConverter.convertRequestToDto(requestWork);
			TmCallServiceResponseDto serviceResponse = callService(url, HttpMethod.PUT, requestDto);

			// リクエストの更新
			this.setRequestData(requestWork, serviceResponse);
			TmUpdateCustomerRequestWork updatedWork = tmUpdateCustomerRequestWorkRepository.save(requestWork);

			// レスポンスの登録
			TmUpdateCustomerResponseWork responseWork = tmConverter.convertDtoToResponseWork(mapper.readValue(serviceResponse.getResponseEntity().getBody(), TmUpdateCustomerResponseDto.class), updatedWork);
			responseWork.setHttpStatus(serviceResponse.getResponseEntity().getStatusCode().toString());
			return tmUpdateCustomerResponseWorkRepository.save(responseWork);
		} catch (URISyntaxException | IOException e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * [PUT] ユーザーアカウント更新API
	 */
	public TmUpdateUserResponseWork putUsers(TmUpdateUserRequestWork requestWork) {
		String url = "/customers/" + requestWork.getCustomerId() + "/users/" + requestWork.getUserId();
		try {
			TmUpdateUserRequestDto requestDto = tmConverter.convertRequestToDto(requestWork);
			TmCallServiceResponseDto serviceResponse = callService(url, HttpMethod.PUT, requestDto);

			// リクエストの更新
			this.setRequestData(requestWork, serviceResponse);
			TmUpdateUserRequestWork updatedWork = tmUpdateUserRequestWorkRepository.save(requestWork);

			// レスポンスの登録
			TmUpdateUserResponseWork responseWork = tmConverter.convertDtoToResponseWork(mapper.readValue(serviceResponse.getResponseEntity().getBody(), TmUpdateUserResponseDto.class), updatedWork);
			responseWork.setHttpStatus(serviceResponse.getResponseEntity().getStatusCode().toString());
			return tmUpdateUserResponseWorkRepository.save(responseWork);
		} catch (URISyntaxException | IOException e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * [POST] サブスクリプション作成API
	 */
	public TmCreateSubscriptionResponseWork postSubscriptions(TmCreateSubscriptionRequestWork requestWork) {
		String url = "/customers/" + requestWork.getCustomerId() + "/subscriptions";
		try {
			TmCreateSubscriptionRequestDto requestDto = tmConverter.convertRequestToDto(requestWork);
			TmCallServiceResponseDto serviceResponse = callService(url, HttpMethod.POST, requestDto);

			// リクエストの更新
			this.setRequestData(requestWork, serviceResponse);
			TmCreateSubscriptionRequestWork updatedWork = tmCreateSubscriptionRequestWorkRepository.save(requestWork);

			// レスポンスの登録
			TmCreateSubscriptionResponseWork responseWork = tmConverter.convertDtoToResponseWork(mapper.readValue(serviceResponse.getResponseEntity().getBody(), TmCreateSubscriptionResponseDto.class), updatedWork);
			responseWork.setHttpStatus(serviceResponse.getResponseEntity().getStatusCode().toString());
			return tmCreateSubscriptionResponseWorkRepository.save(responseWork);
		} catch (URISyntaxException | IOException e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * [PUT]   サブスクリプション更新API
	 */
	public TmUpdateSubscriptionResponseWork putSubscriptions(TmUpdateSubscriptionRequestWork requestWork) {
		String url = "/customers/" + requestWork.getCustomerId() + "/subscriptions/" + requestWork.getSubscriptionId();
		try {
			TmUpdateSubscriptionRequestDto requestDto = tmConverter.convertRequestToDto(requestWork);
			TmCallServiceResponseDto serviceResponse = callService(url, HttpMethod.PUT, requestDto);

			// リクエストの更新
			this.setRequestData(requestWork, serviceResponse);
			TmUpdateSubscriptionRequestWork updatedWork = tmUpdateSubscriptionRequestWorkRepository.save(requestWork);

			// レスポンスの登録
			TmUpdateSubscriptionResponseWork responseWork = tmConverter.convertDtoToResponseWork(mapper.readValue(serviceResponse.getResponseEntity().getBody(), TmUpdateSubscriptionResponseDto.class), updatedWork);
			responseWork.setHttpStatus(serviceResponse.getResponseEntity().getStatusCode().toString());
			return tmUpdateSubscriptionResponseWorkRepository.save(responseWork);
		} catch (URISyntaxException | IOException e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * [PUT] サブスクリプション解約API
	 */
	public TmSuspendSubscriptionResponseWork putSuspend(TmSuspendSubscriptionRequestWork requestWork) {
		String url = "/customers/" + requestWork.getCustomerId() + "/subscriptions/" + requestWork.getSubscriptionId() + "/suspend";
		try {
			TmSuspendSubscriptionRequestDto requestDto = tmConverter.convertRequestToDto(requestWork);
			TmCallServiceResponseDto serviceResponse = callService(url, HttpMethod.PUT, requestDto);

			// リクエストの更新
			this.setRequestData(requestWork, serviceResponse);
			TmSuspendSubscriptionRequestWork updatedWork = tmSuspendSubscriptionRequestWorkRepository.save(requestWork);

			// レスポンスの登録
			// 解約成功時にはBodyが返らないのでnullチェックを行う。
			TmSuspendSubscriptionResponseDto responseBodyDto = null;
			if (serviceResponse.getResponseEntity().getBody() != null) {
				responseBodyDto = mapper.readValue(serviceResponse.getResponseEntity().getBody(), TmSuspendSubscriptionResponseDto.class);
			}
			TmSuspendSubscriptionResponseWork responseWork = tmConverter.convertDtoToResponseWork(responseBodyDto, updatedWork);
			responseWork.setHttpStatus(serviceResponse.getResponseEntity().getStatusCode().toString());
			return tmSuspendSubscriptionResponseWorkRepository.save(responseWork);
		} catch (URISyntaxException | IOException e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * [GET] 更新ユーザー取得API
	 */
	public TmGetCustomerResponseDto getCustomers(Date start, Date end) {

		String url = "/customers";

		//パラメータ設定
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		String user_modified_start = sdf.format(start);
		String user_modified_end = sdf.format(end);
		String buildUrl = UriComponentsBuilder
				.fromUriString(url)
				.queryParam("user_modified_start", user_modified_start)
				.queryParam("user_modified_end", user_modified_end)
				.toUriString();
		try {
			TmCallServiceResponseDto serviceResponse = callService(buildUrl, HttpMethod.GET, null);
			return mapper.readValue(serviceResponse.getResponseEntity().getBody(), TmGetCustomerResponseDto.class);
		} catch (URISyntaxException | IOException e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * [GET] サービスプランID取得
	 */
	public String getServicePlanId() {
		String url = "/me/serviceplans";
		//パラメータ設定
		try {
			TmCallServiceResponseDto serviceResponse = callService(url, HttpMethod.GET, null);
			log.info("サービスプランID:" + serviceResponse.getResponseEntity().getBody());
			return serviceResponse.getResponseEntity().getBody();
		} catch (URISyntaxException | IOException e) {
			log.error(e);
		}
		return null;
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
	private TmCallServiceResponseDto callService(String url, HttpMethod method, AbstractTmRequestDto requestDto) throws JsonProcessingException, RestClientException, URISyntaxException, UnsupportedEncodingException {
		log.info("LMPI call : " + url);
		String body = null;
		if (requestDto != null) {
			body = mapper.writeValueAsString(requestDto);
		}
		log.info("LMPI requestBody : " + body);
		URI uri = new URI(INSTANCE.properties.getUrlPrefix() + url);
		HttpHeaders header = getHttpHeaders(uri, method, body);
		RequestEntity<String> requestEntity = new RequestEntity<String>(body, header, method, uri);
		ResponseEntity<String> responseEntity = rest.exchange(requestEntity, String.class);
		log.info("LMPI response : " + responseEntity.getBody());
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

	private void setRequestData(AbstractTmRequestWork requestWork, TmCallServiceResponseDto serviceResponse) {
		requestWork.setHttpBody(serviceResponse.getHttpBody());
		requestWork.setHttpHeader(serviceResponse.getHttpHeaders().toString());
		requestWork.setRequestTime(serviceResponse.getRequestTime());
		requestWork.setUrl(serviceResponse.getUrl());
		if (serviceResponse.getResponseEntity().getStatusCode().is2xxSuccessful()) {
			requestWork.setRequestStatus(TmRequestStatus.連携済);
		} else {
			requestWork.setRequestStatus(TmRequestStatus.連携エラー);
		}
	}

	/**
	 * トレンドマイクロ連携WORK、DTOのコンバーター
	 * @author z00se03039
	 *
	 */
	private static class TMConverter {

		// start -- DtoToResponseWork --
		public TmCreateCustomerRequestDto convertRequestToDto(TmCreateCustomerRequestWork work) {
			TmCreateCustomerRequestDto dto = new TmCreateCustomerRequestDto();
			dto.getCompany().setCity(work.getCompanyCity());
			dto.getCompany().setCountry(work.getCompanyCountry());
			dto.getCompany().setName(work.getCompanyName());
			dto.getCompany().setState(work.getCompanyState());
			dto.getUser().setEmail(work.getUserEmail());
			dto.getUser().setLanguage(work.getUserLanguage());
			dto.getUser().setLastName(work.getUserLastName());
			dto.getUser().setLoginName(work.getUserLoginName());
			dto.getUser().setFirstName(work.getUserFirstName());
			dto.getUser().setTimeZone(work.getUserTimeZone());
			return dto;
		}

		public TmCreateSubscriptionRequestDto convertRequestToDto(TmCreateSubscriptionRequestWork work) {
			TmCreateSubscriptionRequestDto dto = new TmCreateSubscriptionRequestDto();
			BeanUtils.copyProperties(work, dto);
			return dto;
		}

		public TmSuspendSubscriptionRequestDto convertRequestToDto(TmSuspendSubscriptionRequestWork work) {
			TmSuspendSubscriptionRequestDto dto = new TmSuspendSubscriptionRequestDto();
			BeanUtils.copyProperties(work, dto);
			return dto;
		}

		public TmUpdateCustomerRequestDto convertRequestToDto(TmUpdateCustomerRequestWork work) {
			TmUpdateCustomerRequestDto dto = new TmUpdateCustomerRequestDto();
			BeanUtils.copyProperties(work, dto);
			return dto;
		}

		public TmUpdateSubscriptionRequestDto convertRequestToDto(TmUpdateSubscriptionRequestWork work) {
			TmUpdateSubscriptionRequestDto dto = new TmUpdateSubscriptionRequestDto();
			BeanUtils.copyProperties(work, dto);
			return dto;
		}

		public TmUpdateUserRequestDto convertRequestToDto(TmUpdateUserRequestWork work) {
			TmUpdateUserRequestDto dto = new TmUpdateUserRequestDto();
			BeanUtils.copyProperties(work, dto);
			return dto;
		}
		// end -- DtoToResponseWork --

		// start -- DtoToResponse --

		public TmCreateCustomerResponseWork convertDtoToResponseWork(TmCreateCustomerResponseDto dto, TmCreateCustomerRequestWork requestWork) {
			TmCreateCustomerResponseWork work = new TmCreateCustomerResponseWork();
			work.setCompanyId(dto.getCustomerId());
			TmCreateCustomerResponseDtoUser user = dto.getUser();
			if (user != null) {
				work.setUserId(user.getId());
				work.setUserLoginName(user.getLoginName());
				work.setUserPassword(user.getPassword());
				work.setUserResetpasswordurl(user.getResetpasswordURL());
			}
			work.setHttpStatus(dto.getHttpStatus());
			if (dto.getErrorMessage() != null) {
				work.setErrorMessage(dto.getErrorSubject() + ":" + dto.getErrorMessage());
			}
			work.setRequestWork(requestWork);
			work.setLicenceMappedStatus(TmLicenceMappedStatus.未反映);
			return work;
		}

		public TmCreateSubscriptionResponseWork convertDtoToResponseWork(TmCreateSubscriptionResponseDto dto, TmCreateSubscriptionRequestWork requestWork) {
			TmCreateSubscriptionResponseWork work = new TmCreateSubscriptionResponseWork();
			BeanUtils.copyProperties(dto, work);
			if (dto.getLicenses() != null) {
				BeanUtils.copyProperties(dto.getLicenses()[0], work);
			}
			work.setRequestWork(requestWork);
			work.setLicenceMappedStatus(TmLicenceMappedStatus.未反映);
			return work;
		}

		public TmSuspendSubscriptionResponseWork convertDtoToResponseWork(TmSuspendSubscriptionResponseDto dto, TmSuspendSubscriptionRequestWork requestWork) {
			TmSuspendSubscriptionResponseWork work = new TmSuspendSubscriptionResponseWork();
			if (dto != null) {
				BeanUtils.copyProperties(dto, work);
			}
			work.setRequestWork(requestWork);
			work.setLicenceMappedStatus(TmLicenceMappedStatus.未反映);
			return work;
		}

		public TmUpdateCustomerResponseWork convertDtoToResponseWork(TmUpdateCustomerResponseDto dto, TmUpdateCustomerRequestWork requestWork) {
			TmUpdateCustomerResponseWork work = new TmUpdateCustomerResponseWork();
			BeanUtils.copyProperties(dto, work);
			work.setRequestWork(requestWork);
			work.setLicenceMappedStatus(TmLicenceMappedStatus.未反映);
			return work;
		}

		public TmUpdateSubscriptionResponseWork convertDtoToResponseWork(TmUpdateSubscriptionResponseDto dto, TmUpdateSubscriptionRequestWork requestWork) {
			TmUpdateSubscriptionResponseWork work = new TmUpdateSubscriptionResponseWork();
			BeanUtils.copyProperties(dto, work);
			if (dto.getLicenses() != null) {
				BeanUtils.copyProperties(dto.getLicenses()[0], work);
			}
			work.setRequestWork(requestWork);
			work.setLicenceMappedStatus(TmLicenceMappedStatus.未反映);
			return work;
		}

		public TmUpdateUserResponseWork convertDtoToResponseWork(TmUpdateUserResponseDto dto, TmUpdateUserRequestWork requestWork) {
			TmUpdateUserResponseWork work = new TmUpdateUserResponseWork();
			BeanUtils.copyProperties(dto, work);
			TmUpdateUserResponseDtoPhone phoneDto = dto.getPhone();
			if (phoneDto != null) {
				BeanUtils.copyProperties(phoneDto, work);
			}
			work.setRequestWork(requestWork);
			work.setLicenceMappedStatus(TmLicenceMappedStatus.未反映);
			return work;
		}

		// end -- DtoToResponseWork --
	}
}
