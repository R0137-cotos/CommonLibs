package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * COTOS お客様テナント情報一覧取得API レスポンスのDTO
 */

@Data
public class RsiFindCustomerResult {

	/**
	 * ID
	 */
	@Schema(description = "ID", required = false)
	private String id;

	/**
	 * テナントID
	 */
	@Schema(description = "テナントID", required = false)
	private String tenantId;

	/**
	 * 企業名
	 */
	@Schema(description = "企業名", required = false)
	private String customerCompanyName;

	/**
	 * お客様管理者名
	 */
	@Schema(description = "お客様管理者名", required = false)
	private String customerAdminName;

	/**
	 * メールアドレス
	 */
	@Schema(description = "お客様管理者メールアドレス", required = false)
	private String customerAdminMailAddress;

}
