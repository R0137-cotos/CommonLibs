package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 帳票特定結果パラメーター
 */
@Data
public class ReportSpecificResult {
	/**
	 * テンプレートID
	 */
	@Schema(description = "テンプレートID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * テンプレート名(帳票作成時不要)
	 */
	@Schema(description = "テンプレート名(帳票作成時不要)", allowableValues = "range[255]")
	private String templateName;

	/**
	 * 拡張子(帳票作成時不要)
	 */
	@Schema(description = "拡張子(帳票作成時不要)", allowableValues = "range[255]")
	private String extension;

	/**
	 * ページIDリスト
	 */
	@NotEmpty
	@Schema(description = "ページIDリスト", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[255]")
	private List<Long> pageIdList;
}
