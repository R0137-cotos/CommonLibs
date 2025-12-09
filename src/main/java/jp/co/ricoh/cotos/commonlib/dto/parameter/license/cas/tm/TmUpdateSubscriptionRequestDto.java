package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * トレンドマイクロサブスクリプション更新リクエストDTO
 */
@Data
public class TmUpdateSubscriptionRequestDto implements AbstractTmRequestDto {

	/**
	 * ユニット数
	 */
	@JsonProperty("units_per_license")
	private String unitsPerLicense;
}
