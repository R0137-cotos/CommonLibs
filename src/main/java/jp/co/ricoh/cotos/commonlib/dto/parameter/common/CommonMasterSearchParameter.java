package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import io.swagger.annotations.ApiParam;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;

@Data
public class CommonMasterSearchParameter {

	/**
	 * サービスカテゴリ
	 */
	@ApiParam(value = "サービスカテゴリ", required = false, allowableValues = "共通(\"0\"), 見積(\"1\"), 契約(\"2\"), 手配(\"3\")")
	private ServiceCategory serviceCategory;

	/**
	 * 空行追加フラグ
	 */
	@ApiParam(value = "空行追加フラグ", required = true, allowableValues = "true, false")
	private boolean addBlankRowFlg = false;
	
	/**
	 * カラム名
	 */
	@ApiParam(value = "カラム名", required = false, allowableValues = "range[0,255]")
	private String columnName;	
}
