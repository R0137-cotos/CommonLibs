package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Intra-martのISP設定情報取得API戻り値DTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IspSettingRootDto {

	@JsonProperty("CONTRACT")
	private IspContractDto ispContract;
}
