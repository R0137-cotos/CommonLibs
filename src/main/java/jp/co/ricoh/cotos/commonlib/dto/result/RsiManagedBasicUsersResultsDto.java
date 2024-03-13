package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * RSI 基本ユーザー情報APIの管理対象テナントの基本ユーザー情報検索API レスポンスの管理対象テナントの基本ユーザー情報のDTO
 */

@Data
public class RsiManagedBasicUsersResultsDto {

	/**
	 * テナントID
	 */
	@ApiModelProperty(value = "テナントID", required = false, position = 1)
	private String tenantId;

	/**
	 * ユーザーID
	 */
	@ApiModelProperty(value = "ユーザーID", required = false, position = 2)
	private String userId;

	/**
	 * 姓
	 */
	@ApiModelProperty(value = "姓", required = false, position = 3)
	private String familyName;

	/**
	 * 名
	 */
	@ApiModelProperty(value = "名", required = false, position = 4)
	private String givenName;

	/**
	 * メールアドレス
	 */
	@ApiModelProperty(value = "メールアドレス", required = false, position = 5)
	private String loginMailAddress;

}