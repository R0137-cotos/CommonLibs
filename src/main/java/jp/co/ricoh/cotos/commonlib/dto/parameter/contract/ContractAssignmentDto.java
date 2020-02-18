package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Size;

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
	@Size(max = 1000)
	@ApiModelProperty(value = "メモ", required = false, position = 3, allowableValues = "range[0,1000]")
	private String memo;

	/**
	 * 契約業務添付ファイル
	 */
	@Valid
	@OneToMany(mappedBy = "contractAssignment")
	@ApiModelProperty(value = "契約業務添付ファイル", required = false, position = 4)
	private List<ContractAssignmentAttachedFileDto> contractAssignmentAttachedFileList;
}
