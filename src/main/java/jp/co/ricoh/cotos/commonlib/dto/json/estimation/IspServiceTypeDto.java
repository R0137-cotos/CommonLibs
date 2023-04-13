package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Intra-martのISP設定情報取得API戻り値DTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IspServiceTypeDto {

	/**
	 * 手配業務ID
	 */
	@JsonProperty("ARRANGE_ID")
	private String arrangeId;

	/**
	 * 契約番号
	 */
	@JsonProperty("CONTRACT_ID")
	private String contractId;

	/**
	 * 契約番号枝番
	 */
	@JsonProperty("CONTRACT_BRANCH")
	private String contractBranch;

	/**
	 * ISP設定ステータス
	 */
	@JsonProperty("ISP_STATUS")
	private String ispStatus;

	/**
	 * 設定値
	 */
	@JsonProperty("SETTING_VALUE")
	private IspSettingDto ispSettingDto;
}
