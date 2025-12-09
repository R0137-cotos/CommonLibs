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
	@Schema(description = "対象文書キー", required = false, allowableValues = "range[0,255]")
	private String targetDocKey;

	/**
	 * 対象文書番号
	 */
	@Schema(description = "対象文書番号", required = false, allowableValues = "range[0,255]")
	private String targetDocNumber;

	/**
	 * 対象文書画面URL
	 */
	@Schema(description = "対象文書画面URL", required = false, allowableValues = "range[0,255]")
	private String targetDocUrl;

	/**
	 * 案件名
	 */
	@Schema(description = "案件名", required = false, allowableValues = "range[0,255]")
	private String taskTitle;

	/**
	 * 作成日
	 */
	@Schema(description = "作成日", required = false)
	private Date taskCreatedAt;

	/**
	 * 企業名
	 */
	@Schema(description = "企業名", required = false, allowableValues = "range[0,255]")
	private String taskCompanyName;

	/**
	 * 事業所名
	 */
	@Schema(description = "事業所名", required = false, allowableValues = "range[0,255]")
	private String taskOfficeName;

	/**
	 * 販売店名
	 */
	@Schema(description = "販売店名", required = false, allowableValues = "range[0,255]")
	private String taskSalesName;

	/**
	 * 担当SA
	 */
	@Schema(description = "担当SA", required = false, allowableValues = "range[0,255]")
	private String employeeName;
}
