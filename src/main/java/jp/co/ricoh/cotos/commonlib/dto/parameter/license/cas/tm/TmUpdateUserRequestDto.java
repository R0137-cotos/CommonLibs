package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import lombok.Data;

/**
 * ユーザーアカウント更新リクエストDTO
 */
@Data
public class TmUpdateUserRequestDto implements AbstractTmRequestDto {

	/**
	 * メールアドレス
	 */
	private String email;

}
