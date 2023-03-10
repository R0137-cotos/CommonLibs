package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.estimation.CpqUpgradeItemO365Dto;
import lombok.Data;

/**
 * 商品（契約用）拡張項目.品種（契約用）DTO（O365）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemContractO365Dto {

	/**
	 * 品種マスターID
	 */
	private Long itemMasterId;

	/**
	 * 品種種別詳細(コード値は契約種別詳細と同じ)
	 */
	private ContractTypeDetails detailsProductType;

	/**
	 * 自動更新日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date autoUpdateDate;

	/**
	 * 減数可能期間（From）
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date possibleReduceTermStart;

	/**
	 * 減数可能期間（To）
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date possibleReduceTermEnd;

	/**
	 * アップグレード品種リスト
	 */
	@JsonProperty("upgradeItemList")
	private List<CpqUpgradeItemO365Dto> upgradeItemList;

}
