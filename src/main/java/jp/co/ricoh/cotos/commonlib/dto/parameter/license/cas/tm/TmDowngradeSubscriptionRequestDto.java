package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * トレンドマイクロサブスクリプション更新リクエストDTO(ダウングレード用)
 */
@Data
public class TmDowngradeSubscriptionRequestDto implements AbstractTmRequestDto {

	/**
	 * ユニット数
	 */
	@JsonProperty("units_per_license")
	private String unitsPerLicense;

	/**
	 * ライセンス終了日
	 */
	@JsonProperty("license_expiration_date")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Date licenseExpirationDate;
}
