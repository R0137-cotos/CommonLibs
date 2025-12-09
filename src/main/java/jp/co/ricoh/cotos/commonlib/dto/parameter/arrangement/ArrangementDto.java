package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.Arrangement.WorkflowStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ArrangementDto extends DtoBase {

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long contractId;

	/**
	 * 解約フラグ
	 */
	@Min(0)
	@Max(9L)
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9]")
	private int disengagementFlg;

	/**
	 * ワークフロー状態
	 */
	@NotNull
	@Schema(description = "ワークフロー状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "手配中(\"1\"), 手配完了(\"2\")", example = "1")
	private WorkflowStatus workflowStatus;
}
