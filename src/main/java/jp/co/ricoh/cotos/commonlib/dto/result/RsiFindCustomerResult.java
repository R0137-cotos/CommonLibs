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
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String id;

	/**
	 * テナントID
	 */
	@Schema(description = "テナントID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String tenantId;

	/**
	 * 企業名
	 */
	@Schema(description = "企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerCompanyName;

	/**
	 * お客様管理者名
	 */
	@Schema(description = "お客様管理者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerAdminName;

	/**
	 * メールアドレス
	 */
	@Schema(description = "お客様管理者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerAdminMailAddress;

}
