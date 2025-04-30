package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import jakarta.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプションID取得レスポンスDTO
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class TmGetSubscriptionIdResponseDto extends AbstractTmResponseDto {

	/**
	 * サブスクリプションID
	 */
	@JsonProperty("subscription_id")
	private String subscriptionId;

}
