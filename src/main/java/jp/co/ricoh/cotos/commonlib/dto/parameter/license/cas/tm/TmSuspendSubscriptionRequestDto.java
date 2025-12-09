package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * トレンドマイクロサブスクリプション解約リクエストDTO
 */
@Data
public class TmSuspendSubscriptionRequestDto implements AbstractTmRequestDto {

	/**
	 * ライセンス終了日
	 */
	@JsonProperty("license_expiration_date")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Tokyo")
	private Date licenseExpirationDate;

}
