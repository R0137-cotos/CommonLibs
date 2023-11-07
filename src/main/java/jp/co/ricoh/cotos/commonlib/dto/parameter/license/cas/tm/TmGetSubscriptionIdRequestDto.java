package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import lombok.Data;

/**
 * トレンドマイクロサブスクリプションID取得リクエストDTO
 */
@Data
public class TmGetSubscriptionIdRequestDto implements AbstractTmRequestDto {

	/**
	 * 会社ID
	 */
	private String customerId;

	/**
	 * サービスプランID
	 */
	private String servicePlanId;
}
