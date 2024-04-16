package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * RSI 基本ユーザー情報APIの管理対象テナントの基本ユーザー情報検索API 情報検索用パラメータ
 */

@Data
public class RsiFindManagedBasicUsersParameter {

	/**
	 * テナントID
	 */
	@ApiParam(value = "テナントID", required = false)
	@ApiModelProperty(value = "テナントID", required = false, allowableValues = "range[0,255]")
	private String tenantId;

	/**
	 * メールアドレス
	 */
	@ApiParam(value = "メールアドレス", required = false)
	@ApiModelProperty(value = "メールアドレス", required = false, allowableValues = "range[0,255]")
	private String loginMailAddress;

	/**
	 * 企業名
	 */
	@ApiParam(value = "企業名", required = false)
	@ApiModelProperty(value = "企業名", required = false, allowableValues = "range[0,255]")
	private String tenantName;

}
