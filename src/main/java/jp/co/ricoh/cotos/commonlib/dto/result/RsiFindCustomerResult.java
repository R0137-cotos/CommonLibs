package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * COTOS お客様テナント情報一覧取得API レスポンスのDTO
 */

@Data
public class RsiFindCustomerResult {

	/**
	 * ID
	 */
	@ApiModelProperty(value = "ID", required = false, position = 1)
	private String id;

	/**
	 * テナントID
	 */
	@ApiModelProperty(value = "テナントID", required = false, position = 2)
	private String tenantId;

	/**
	 * 企業名
	 */
	@ApiModelProperty(value = "企業名", required = false, position = 3)
	private String customerName;

	/**
	 * お客様管理者名
	 */
	@ApiModelProperty(value = "お客様管理者名", required = false, position = 4)
	private String customerAdminName;

	/**
	 * メールアドレス
	 */
	@ApiModelProperty(value = "お客様管理者メールアドレス", required = false, position = 5)
	private String customerAdminMailAddress;

}
