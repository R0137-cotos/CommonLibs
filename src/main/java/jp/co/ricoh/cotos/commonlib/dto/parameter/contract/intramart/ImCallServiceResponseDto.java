package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart;

import org.springframework.http.ResponseEntity;

import lombok.Data;

/**
 *
 * S&S作業依頼（WEB）レスポンスDTO
 *
 */
@Data
public class ImCallServiceResponseDto {

	private ResponseEntity<String> responseEntity;

}
