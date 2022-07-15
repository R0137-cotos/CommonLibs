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

	private static final int RETRY_NUM = 5;
	private static final int RETRY_WAIT_TIME = 10000;

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
			log.info("============================================================");
			log.info("status  : " + responseEntity.getStatusCodeValue());
			log.info("headers : " + responseEntity.getHeaders());
			log.info("response: " + responseEntity.getBody());
			log.info("============================================================");
			// TrendMicroAPIはエラーでもエラー内容を正常終了と同じようにレスポンスBodyに設定する為、
			// HTTPステータスが500系エラーの場合はリトライさせる目的で例外をスローする。
			if (responseEntity.getStatusCode().is5xxServerError()) {
				throw new ResourceAccessException("TrendMicroAPIでエラーが発生しました。ステータスコード： " + responseEntity.getStatusCodeValue() + "、エラー内容： " + responseEntity.getBody());
			}
		} catch (ResourceAccessException e) {
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
