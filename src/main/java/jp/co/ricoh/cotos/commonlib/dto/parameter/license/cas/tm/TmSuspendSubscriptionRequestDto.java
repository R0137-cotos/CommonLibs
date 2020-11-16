package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * トレンドマイクロサブスクリプション解約リクエストDTO
 */
@Data
public class TmSuspendSubscriptionRequestDto implements AbstractTmRequestDto {

	/**
	 * 会社ID
	 */
	private String customerId;

	/**
	 * サブスクリプションID
	 */
	private String subscriptionId;

	/**
	 * ライセンス終了日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Tokyo")
	private Date licenseExpirationDate;

}
