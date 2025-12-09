package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * トレンドマイクロサブスクリプション作成リクエストDTO
 */
@Data
public class TmCreateSubscriptionRequestDto implements AbstractTmRequestDto {

	/**
	 * サービスプランID
	 */
	@JsonProperty("service_plan_id")
	private String servicePlanId;

	/**
	 * ユニット数
	 */
	@JsonProperty("units_per_license")
	private String unitsPerLicense;

	/**
	 * ライセンス開始日
	 */
//	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "Asia/Tokyo")
	@JsonProperty("license_start_date")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Date licenseStartDate;

}
