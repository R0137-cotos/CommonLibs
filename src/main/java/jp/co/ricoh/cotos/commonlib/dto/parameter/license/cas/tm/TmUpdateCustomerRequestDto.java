package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import lombok.Data;

/**
 * トレンドマイクロ会社情報更新リクエストDTO
 */
@Data
public class TmUpdateCustomerRequestDto implements AbstractTmRequestDto {

	/**
	 * 会社
	 */
	private String name;

	/**
	 * 都道府県
	 */
	private String state;

	/**
	 * 国
	 */
	private String country;

	/**
	 * 市区町村
	 */
	private String city;

}
