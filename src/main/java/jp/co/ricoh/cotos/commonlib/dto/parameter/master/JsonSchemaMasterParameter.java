package jp.co.ricoh.cotos.commonlib.dto.parameter.master;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JsonSchemaMasterParameter {

	/**
	 * 商品マスタID
	 */
	@ApiModelProperty(value = "商品マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long productMasterId;

	/**
	 * 見積種別
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "見積種別:契約種別未設定時のみ設定可能", required = false, position = 2, allowableValues = "新規(\"1\"), 契約変更(\"2\")")
	private String estimationType;

	/**
	 * 契約種別
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約種別:見積種別未設定時のみ設定可能", required = false, position = 3, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\")")
	private String contractType;
}