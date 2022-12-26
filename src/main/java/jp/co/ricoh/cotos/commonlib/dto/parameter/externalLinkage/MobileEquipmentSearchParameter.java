package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MobileEquipmentSearchParameter {

	/**
	 * 見積ID
	 */
	@Min(0)
	@ApiModelProperty(value = "見積ID", required = false, position = 1, allowableValues = "range[0,9999999999999999999]")
	private Long estimationId;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 2, allowableValues = "range[0,9999999999999999999]")
	private Long contractId;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "解約フラグ", required = false, position = 3, allowableValues = "range[0,9]")
	private Integer disengagementFlg;
}
