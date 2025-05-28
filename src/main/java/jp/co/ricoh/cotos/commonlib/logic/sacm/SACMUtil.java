package jp.co.ricoh.cotos.commonlib.logic.sacm;

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
public class SACMUtil {

	private static final int RETRY_NUM = 5;
	private static final int RETRY_WAIT_TIME = 10000;

	/**
	 * SACMAPIコール
	 * @param rest
	 * @param requestEntity
	 * @return
	 */
	@Retryable(value = { ResourceAccessException.class }, maxAttempts = RETRY_NUM, backoff = @Backoff(delay = RETRY_WAIT_TIME))
	public ResponseEntity<String> callApi(RestTemplate rest, RequestEntity<String> requestEntity) {
		ResponseEntity<String> responseEntity = null;

		try {
			responseEntity = rest.exchange(requestEntity, String.class);
			log.info("============================================================");
			log.info("status  : " + responseEntity.getStatusCode().value());
			log.info("headers : " + responseEntity.getHeaders());
			log.info("response: " + responseEntity.getBody());
			log.info("============================================================");
			// HTTPステータスが200系以外はエラーとする。
			if (!responseEntity.getStatusCode().is2xxSuccessful()) {
				throw new RuntimeException("SACMAPI呼び出しでエラーが発生しました。ステータスコード： " + responseEntity.getStatusCode().value() + "、エラー内容：" + responseEntity.getBody());
			}
		} catch (ResourceAccessException e) {
			log.error(e);
			throw e;
		}

		return responseEntity;
	}

	/**
	 * SACMAPIエラー時に呼ばれるメソッド
	 * @param e リトライ対象例外クラスインスタンス
	 * @return (リトライ対象メソッドと定義を合わせるためなので未使用)
	 */
	@Recover
	private ResponseEntity<String> recoverCallApi(ResourceAccessException e) {
		log.info(String.format("%d回リトライしましたが、API呼び出しに失敗しました。", RETRY_NUM));
		throw e;
	}
}
