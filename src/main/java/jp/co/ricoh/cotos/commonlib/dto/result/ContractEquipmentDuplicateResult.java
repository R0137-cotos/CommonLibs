package jp.co.ricoh.cotos.commonlib.dto.result;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 契約機種重複一覧取得APIの検索結果用パラメーター
 */

@Data
public class ContractEquipmentDuplicateResult {

	/**
	 * 商品種類区分
	 */
	@Size(max = 255)
	@Schema(description = "商品種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productClassDiv;

	/**
	 * RJ管理番号
	 */
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 機種コード
	 */
	@Size(max = 255)
	@Schema(description = "機種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String equipmentCode;

	/**
	 * 機番
	 */
	@Size(max = 255)
	@Schema(description = "機番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String equipmentNo;
}
