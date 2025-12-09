package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import lombok.Data;

/**
 * トレンドマイクロサブスクリプション取得リクエストDTO
 */
@Data
public class TmGetSubscriptionRequestDto implements AbstractTmRequestDto {

	/**
	 * 会社ID
	 */
	private String customerId;

	/**
	 * サブスクリプションID
	 */
	private String subscriptionId;
}
