package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * RSI 基本ユーザー情報APIの管理対象テナントの基本ユーザー情報検索API レスポンスの管理対象テナントの基本ユーザー情報のDTO
 */

@Data
public class RsiManagedBasicUsersResultsDto {

	/**
	 * テナントID
	 */
	@Schema(description = "テナントID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String tenantId;

	/**
	 * ユーザーID
	 */
	@Schema(description = "ユーザーID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String userId;

	/**
	 * 姓
	 */
	@Schema(description = "姓", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String familyName;

	/**
	 * 名
	 */
	@Schema(description = "名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String givenName;

	/**
	 * メールアドレス
	 */
	@Schema(description = "メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String loginMailAddress;

}