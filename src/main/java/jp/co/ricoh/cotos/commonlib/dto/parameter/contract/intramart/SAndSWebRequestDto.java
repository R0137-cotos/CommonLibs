package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * S&S作業依頼（WEB）リクエストDTO
 */
@Data
public class SAndSWebRequestDto {

	@JsonProperty("ss_data")
	private List<RegisterInfoDto> registerInfoDtoList;

}
