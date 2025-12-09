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
	@Schema(description = "テナントID", required = false)
	private String tenantId;

	/**
	 * ユーザーID
	 */
	@Schema(description = "ユーザーID", required = false)
	private String userId;

	/**
	 * 姓
	 */
	@Schema(description = "姓", required = false)
	private String familyName;

	/**
	 * 名
	 */
	@Schema(description = "名", required = false)
	private String givenName;

	/**
	 * メールアドレス
	 */
	@Schema(description = "メールアドレス", required = false)
	private String loginMailAddress;

}