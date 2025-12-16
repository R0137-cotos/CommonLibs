package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;

@Data
public class CommonMasterSearchParameter {

	/**
	 * サービスカテゴリ
	 */
	@Parameter(description = "サービスカテゴリ", required = false, schema = @Schema(allowableValues = { "共通(\"0\")", "見積(\"1\")", "契約(\"2\")", "手配(\"3\")" }))
	private ServiceCategory serviceCategory;

	/**
	 * 空行追加フラグ
	 */
	@Parameter(description = "空行追加フラグ", required = true, schema = @Schema(allowableValues = { "true", "false" }))
	private boolean addBlankRowFlg = false;

	/**
	 * カラム名
	 */
	@Parameter(description = "カラム名", required = false, schema = @Schema(allowableValues = "range[0,255]"))
	private String columnName;
}
