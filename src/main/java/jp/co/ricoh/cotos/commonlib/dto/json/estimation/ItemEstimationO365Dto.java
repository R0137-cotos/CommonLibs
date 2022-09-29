package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（見積用）拡張項目.品種（見積用）DTO（O365）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemEstimationO365Dto {

	/**
	 * 品種マスターID
	 */
	private Long itemMasterId;

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
	 * 減数反映日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date reflectionDate;

}
