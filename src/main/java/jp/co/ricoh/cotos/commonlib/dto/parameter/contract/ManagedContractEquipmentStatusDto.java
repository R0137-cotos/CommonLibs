package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ManagedContractEquipmentStatusDto extends DtoBase {

	/**
	 * 契約機種ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約機種ID", required = true, position = 3)
	private Long contractEquipmentId;

	/**
	 * 継続フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "継続フラグ", required = false, position = 4, allowableValues = "range[0,9]")
	private Integer continueFlg;

	/**
	 * 再契約不可フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "再契約不可フラグ", required = false, position = 5, allowableValues = "range[0,9]")
	private Integer reContractNotAllowedFlg;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "削除フラグ", required = false, position = 6, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * 契約年数
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "契約年数", required = false, position = 7, allowableValues = "range[0,99999]")
	private Integer contractYears;

	/**
	 * 機器削除契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "機器削除契約ID", required = false, position = 8, allowableValues = "range[0,9223372036854775807]")
	private Long equipmentDeletedContractId;
}
