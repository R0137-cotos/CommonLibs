package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Intra-martのISP設定情報取得API戻り値DTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IspContractDto {

	/**
	 * RJ管理番号
	 */
	@JsonProperty("RJ_MANAGE_NO")
	private String rjManageNo;

	@JsonProperty("MAIL")
	private List<IspServiceTypeDto> serviceTypeMailList;

	@JsonProperty("ADD_MAIL")
	private List<IspServiceTypeDto> serviceTypeAddMailList;

	@JsonProperty("CIRCUIT_SETTING")
	private List<IspServiceTypeDto> serviceTypeCircuitSettingList;

	@JsonProperty("DOMAIN")
	private List<IspServiceTypeDto> serviceTypeDomainList;

	@JsonProperty("HOSTING")
	private List<IspServiceTypeDto> serviceTypeHostingList;
}
