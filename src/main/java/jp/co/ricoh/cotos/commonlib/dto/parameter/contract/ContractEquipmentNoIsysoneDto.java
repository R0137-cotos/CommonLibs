package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentNoIsysone.MachineTypeNoIsysone;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ContractEquipmentNoIsysoneDto extends DtoBase {

	/**
	 * 機器区分
	 */
	@Schema(description = "機器区分（Isys-Oneへの連携なし）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "ハードディスク(\"1\"), 内蔵オプション(\"2\"), 外付オプション(\"3\"), 導入ソフトウェア(\"4\")", example = "1")
	private MachineTypeNoIsysone machineTypeNoIsysone;

	/**
	 * 製品番号
	 */
	@Size(max = 255)
	@Schema(description = "製品番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemNo;

	/**
	 * 製品名
	 */
	@Size(max = 255)
	@Schema(description = "製品名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String goodsName;

	/**
	 * 機番
	 */
	@Size(max = 255)
	@Schema(description = "機番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String equipmentNo;

	/**
	 * オプション名
	 */
	@Size(max = 255)
	@Schema(description = "オプション名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String optionName;

}
