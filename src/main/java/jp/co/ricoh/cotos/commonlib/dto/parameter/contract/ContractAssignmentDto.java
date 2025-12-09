package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.List;

import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContractAssignmentDto extends DtoBase {

	/**
	 * メモ
	 */
	@Schema(description = "メモ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String memo;

	/**
	 * 契約業務添付ファイル
	 */
	@Valid
	@OneToMany(mappedBy = "contractAssignment")
	@Schema(description = "契約業務添付ファイル", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractAssignmentAttachedFileDto> contractAssignmentAttachedFileList;

	/**
	 * 更新フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "更新フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer updateFlg;
}
