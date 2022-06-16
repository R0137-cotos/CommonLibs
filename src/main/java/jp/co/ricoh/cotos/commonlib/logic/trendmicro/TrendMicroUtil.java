package jp.co.ricoh.cotos.commonlib.logic.trendmicro;

import java.util.Arrays;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
@EnableRetry
public class TrendMicroUtil {

	private static final int RETRY_NUM = 3;
	private static final int RETRY_WAIT_TIME = 5000;

	/**
	 * TrendMicroAPIコール
	 * @param rest RestTemplateインスタンス
	 * @param requestEntity リクエスト
	 * @return レスポンス
	 */
	@Retryable(value = { ResourceAccessException.class }, maxAttempts = RETRY_NUM, backoff = @Backoff(delay = RETRY_WAIT_TIME))
	public ResponseEntity<String> callApi(RestTemplate rest, RequestEntity<String> requestEntity) {
		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = rest.exchange(requestEntity, String.class);
		} catch (ResourceAccessException e) {
			// リトライ処理毎にログに出力する為、try-catchしている。
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			throw e;
		}
		return responseEntity;
	}

	/**
	 * TrendMicroAPIエラー時に呼ばれるメソッド
	 * @param e リトライ対象例外クラスインスタンス
	 * @return (リトライ対象メソッドと定義を合わせるためなので未使用)
	 */
	@Recover
	private ResponseEntity<String> recoverCallApi(ResourceAccessException e) {
		log.info(String.format("%d回リトライしましたが、API呼び出しに失敗しました。", RETRY_NUM));
		throw e;
	}
}
