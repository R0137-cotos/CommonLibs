package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.List;

import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContractAssignmentDto extends DtoBase {

	/**
	 * メモ
	 */
	@ApiModelProperty(value = "メモ", required = false, position = 3)
	@Lob
	private String memo;

	/**
	 * 契約業務添付ファイル
	 */
	@Valid
	@OneToMany(mappedBy = "contractAssignment")
	@ApiModelProperty(value = "契約業務添付ファイル", required = false, position = 4)
	private List<ContractAssignmentAttachedFileDto> contractAssignmentAttachedFileList;

	/**
	 * 更新フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "更新フラグ", required = false, position = 5, allowableValues = "range[0,9]")
	private Integer updateFlg;
}
