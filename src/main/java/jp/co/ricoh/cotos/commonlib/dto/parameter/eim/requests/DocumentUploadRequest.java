package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests;

import lombok.Data;

/**
 * DocumentUploadRequest
 */

@Data
public class DocumentUploadRequest {

	/**
	 * system
	 */
	private DocumentUploadSystem system;

	/**
	 * properties
	 */
	private DocumentUploadProperties properties;
}
