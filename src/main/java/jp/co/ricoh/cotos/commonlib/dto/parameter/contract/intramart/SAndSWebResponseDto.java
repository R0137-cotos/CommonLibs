package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * S&S作業依頼（WEB）レスポンスDTO
 */
@Data
public class SAndSWebResponseDto {

	@JsonProperty("result")
	private RegisterResultInfoDto registerResultInfoDto;

}
