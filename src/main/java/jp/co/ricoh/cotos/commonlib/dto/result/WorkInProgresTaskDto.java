package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 処理中タスクを示すDto
 */
@Data
public class WorkInProgresTaskDto {

	/**
	 * 対象文書キー
	 */
	@Schema(description = "対象文書キー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String targetDocKey;

	/**
	 * 対象文書番号
	 */
	@Schema(description = "対象文書番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String targetDocNumber;

	/**
	 * 対象文書画面URL
	 */
	@Schema(description = "対象文書画面URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String targetDocUrl;

	/**
	 * 案件名
	 */
	@Schema(description = "案件名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String taskTitle;

	/**
	 * 作成日
	 */
	@Schema(description = "作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date taskCreatedAt;

	/**
	 * 企業名
	 */
	@Schema(description = "企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String taskCompanyName;

	/**
	 * 事業所名
	 */
	@Schema(description = "事業所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String taskOfficeName;

	/**
	 * 販売店名
	 */
	@Schema(description = "販売店名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String taskSalesName;

	/**
	 * 担当SA
	 */
	@Schema(description = "担当SA", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String employeeName;
}
