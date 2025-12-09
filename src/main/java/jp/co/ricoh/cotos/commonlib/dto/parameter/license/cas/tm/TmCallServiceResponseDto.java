package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import lombok.Data;

/**
 * 
 * @author z00se03039
 *
 */
@Data
public class TmCallServiceResponseDto {

	private ResponseEntity<String> responseEntity;

	// リクエスト時の情報
	private HttpHeaders httpHeaders;

	private Date requestTime;

	private String url;

	private String httpBody;
}
