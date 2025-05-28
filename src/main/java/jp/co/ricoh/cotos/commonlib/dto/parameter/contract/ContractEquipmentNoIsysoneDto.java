package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "機器区分（Isys-Oneへの連携なし）", required = false, allowableValues = "ハードディスク(\"1\"), 内蔵オプション(\"2\"), 外付オプション(\"3\"), 導入ソフトウェア(\"4\")", example = "1", position = 2)
	private MachineTypeNoIsysone machineTypeNoIsysone;

	/**
	 * 製品番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "製品番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String itemNo;

	/**
	 * 製品名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "製品名", required = false, position = 4, allowableValues = "range[0,255]")
	private String goodsName;

	/**
	 * 機番
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "機番", required = false, position = 5, allowableValues = "range[0,255]")
	private String equipmentNo;

	/**
	 * オプション名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "オプション名", required = false, position = 6, allowableValues = "range[0,255]")
	private String optionName;

}
