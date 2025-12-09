package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 更新ユーザー取得リクエストDTO
 */
@Data
@AllArgsConstructor
public class TmGetCustomerRequestDto implements AbstractTmRequestDto {

	/**
	 * 開始日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Tokyo")
	@JsonProperty("user_modified_start")
	private Date userModifiedStart;

	/**
	 * 終了日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Tokyo")
	@JsonProperty("user_modified_end")
	private Date userModifiedEnd;
}
