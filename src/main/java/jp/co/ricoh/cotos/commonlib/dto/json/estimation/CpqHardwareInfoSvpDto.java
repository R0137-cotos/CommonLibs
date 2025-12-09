package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（見積用）拡張項目ハードウェア情報DTO（SVP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqHardwareInfoSvpDto {

	/**
	 * 分類コード
	 */
	private String classificCode;

	/**
	 * ハードウェアクラス区分
	 */
	private String hardwareClassDiv;

	/**
	 * 機種コード
	 */
	private String equipmentCode;

	/**
	 * 製品名
	 */
	private String offeringsName;

	/**
	 * 数量
	 */
	private Integer quantity;
}
