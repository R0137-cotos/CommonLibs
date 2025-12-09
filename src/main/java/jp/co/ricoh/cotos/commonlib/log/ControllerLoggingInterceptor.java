package jp.co.ricoh.cotos.commonlib.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;
import jp.co.ricoh.cotos.commonlib.security.CotosAuthenticationDetails;
import jp.co.ricoh.cotos.commonlib.util.LogRequestProperties;
import jp.co.ricoh.cotos.commonlib.util.LogResponseProperties;

@Aspect
@Component
public class ControllerLoggingInterceptor {

	/** ロガー */
	private static final Log log = LogFactory.getLog(ControllerLoggingInterceptor.class);

	@Autowired
	MessageUtil messageUtil;

	@Autowired
	LogUtil logUtil;

	@Autowired
	LogRequestProperties logRequestProperties;

	@Autowired
	LogResponseProperties logResponseProperties;

	@Around("execution(* jp.co.ricoh.cotos.*.controller.*Controller.*(..))")
	public Object traceController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		// 認証情報を取得
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// 現在のリクエストを取得
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

		Object ret = null;

		try {
			log.info(messageUtil.createMessageInfo("PerformLogInfo", Arrays.asList(request.getMethod(), request.getRequestURL().toString(), "start", userInfo.getSingleUserId(), userInfo.getMomEmployeeId(), formatter.format(LocalDateTime.now())).toArray(new String[0])).getMsg());

			ret = proceedingJoinPoint.proceed();
			return ret;
		} finally {
			log.info(messageUtil.createMessageInfo("PerformLogInfo", Arrays.asList(request.getMethod(), request.getRequestURL().toString(), "end", userInfo.getSingleUserId(), userInfo.getMomEmployeeId(), formatter.format(LocalDateTime.now())).toArray(new String[0])).getMsg());
		}
	}

	/**
	 * API実行前ログアスペクト
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("execution(* jp.co.ricoh.cotos.*.controller.*Controller.*(..))")
	public void traceControllerBefore(JoinPoint joinPoint) throws Throwable {

		// APIメソッド情報
		log.info(String.format("\tCall method(%s)", joinPoint.getSignature()));

		// APIリクエスト情報(body)
		if (logRequestProperties.isOutputLog()) {
			int min = Math.min(joinPoint.getArgs().length, logRequestProperties.getMaxCount());
			IntStream.range(0, min).forEach(i -> {
				log.info(String.format("\tRequest Body(%d): %s", (i + 1), logUtil.outputLog(joinPoint.getArgs()[i])));
			});
		}
	}

	/**
	 * API正常処理時ログアスペクト
	 * @param joinPoint
	 * @throws JsonProcessingException
	 * @throws Throwable
	 */
	@AfterReturning(value = "execution(* jp.co.ricoh.cotos.*.controller.*Controller.*(..))", returning = "res")
	public void afterReturning(Object res) throws JsonProcessingException {

		// APIレスポンス情報(body)
		if (logResponseProperties.isOutputLog()) {
			if (res instanceof List) {
				List<?> entityList = List.class.cast(res);
				int min = Math.min(entityList.size(), logResponseProperties.getMaxCount());
				entityList.stream().filter(s -> entityList.indexOf(s) < min).forEach(s -> {
					log.info(String.format("\tResponse Body(%d): %s", entityList.indexOf(s) + 1, logUtil.outputLog(s)));
				});
			} else if (logUtil.isOutputBody(res)) {
				log.info(String.format("\tResponse Body: %s", logUtil.outputLog(res)));
			}
		}
	}
}
