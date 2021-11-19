package jp.co.ricoh.cotos.commonlib.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;
import jp.co.ricoh.cotos.commonlib.security.CotosAuthenticationDetails;
import jp.co.ricoh.cotos.commonlib.util.LogRequestProperties;
import jp.co.ricoh.cotos.commonlib.util.LogResponseProperties;

public class CotosRestTemplate extends RestTemplate {

	/** ロガー */
	private static final Log log = LogFactory.getLog(ControllerLoggingInterceptor.class);

	@Autowired
	MessageUtil messageUtil;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	LogRequestProperties logRequestProperties;

	@Autowired
	LogResponseProperties logResponseProperties;

	@Override
	public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {

		// 認証情報を取得
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// 現在のリクエストを取得
		HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		try {
			log.info(messageUtil.createMessageInfo("PerformLogInfo", Arrays.asList(servletRequest.getMethod(), servletRequest.getRequestURL().toString(), "start", userInfo.getSingleUserId(), userInfo.getMomEmployeeId(), formatter.format(LocalDateTime.now())).toArray(new String[0])).getMsg());
			if (request instanceof List) {
				List<?> entityList = List.class.cast(request);
				int min = Math.min(entityList.size(), logRequestProperties.getMaxCount());
				entityList.stream().filter(s -> entityList.indexOf(s) < min).forEach(s -> {
					log.info(String.format("\tRequest Body(%d): %s", entityList.indexOf(s) + 1, outputLog(s)));
				});
			} else if (isOutputBody(request)) {
				log.info(String.format("\tRequest Body: %s", outputLog(request)));
			}

			ResponseEntity<T> response = super.postForEntity(url, request, responseType);

			Object res = response.getBody();
			if (res instanceof List) {
				List<?> entityList = List.class.cast(res);
				int min = Math.min(entityList.size(), logResponseProperties.getMaxCount());
				entityList.stream().filter(s -> entityList.indexOf(s) < min).forEach(s -> {
					log.info(String.format("\tResponse Body(%d): %s", entityList.indexOf(s) + 1, outputLog(s)));
				});
			} else if (isOutputBody(res)) {
				log.info(String.format("\tResponse Body: %s", outputLog(res)));
			}
			return response;
		} finally {
			log.info(messageUtil.createMessageInfo("PerformLogInfo", Arrays.asList(servletRequest.getMethod(), servletRequest.getRequestURL().toString(), "end", userInfo.getSingleUserId(), userInfo.getMomEmployeeId(), formatter.format(LocalDateTime.now())).toArray(new String[0])).getMsg());
		}
	}

	/**
	 * ボディーをログ出力か否か
	 * @param obj
	 * @return
	 */
	private boolean isOutputBody(Object obj) {
		if (obj instanceof ResponseEntity) {
			ResponseEntity<?> entity = ResponseEntity.class.cast(obj);
			Object bodyObject = entity.getBody();

			// ResponseEntity<InputStreamResource>の場合は以下例外対策のためヘッダー情報のみログ出力する
			// 例外：java.lang.IllegalStateException: InputStream has already been read - do not use InputStreamResource if a stream needs to be read multiple times
			if (bodyObject instanceof InputStreamSource) {
				return false;
			}

			// 帳票はバイト配列であり、ログが大量に出力されるためヘッダー情報のみログ出力する
			if (bodyObject instanceof byte[]) {
				return false;
			}
		}

		return true;
	}

	/**
	 * ログ出力
	 * ※JSON変換できる場合はJSONで出力、変換不可の場合はtoStringで出力
	 *
	 * @param obj
	 * @throws JsonProcessingException
	 */
	private String outputLog(Object obj) {
		if (null == obj) {
			return null;
		}

		String log = null;
		try {
			log = String.format("[JSON] %s", mapper.writeValueAsString(obj));
		} catch (Exception e) {
			log = String.format("[TEXT] %s", obj.toString());
		}
		return log;
	}

}
