package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * RSI 基本ユーザー情報APIの管理対象テナントの基本ユーザー情報検索API 情報検索用パラメータ
 */

@Data
public class RsiFindManagedBasicUsersParameter {

	/**
	 * テナントID
	 */
	@Parameter(description = "テナントID", required = false)
	@Schema(description = "テナントID", required = false, allowableValues = "range[0,255]")
	private String tenantId;

	/**
	 * メールアドレス
	 */
	@Parameter(description = "メールアドレス", required = false)
	@Schema(description = "メールアドレス", required = false, allowableValues = "range[0,255]")
	private String loginMailAddress;

	/**
	 * 企業名
	 */
	@Parameter(description = "企業名", required = false)
	@Schema(description = "企業名", required = false, allowableValues = "range[0,255]")
	private String tenantName;

}
