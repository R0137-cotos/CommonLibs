package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWork;
import lombok.Data;

/**
 * 手配担当作業者を設定するためのキー項目クラスを表します。
 */

@Data
public class ArrangementAssignWorkerParameter {

	/**
	 * 手配業務リスト
	 */
	@Schema(description = "手配業務リスト", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<ArrangementWork> arrangementWorkList;

	/**
	 * MoM社員ID（手配担当作業者）
	 */
	@Schema(description = "MoM社員ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,24]")
	private String employeeId;
}
