package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import java.util.List;

import lombok.Data;

/**
 * 文書アップロードのレスポンスボディ
 */

@Data
public class DocumentUploadResponse {

	/**
	 * url
	 */
	private String system;

	/**
	 * documentType
	 */
	private String documentType;

	/**
	 * readers
	 */
	private List<String> readers;

	/**
	 * writers
	 */
	private List<String> writers;
	
	/**
	 * title
	 */
	private String title;

	/**
	 * documentId
	 */
	private String documentId;

	/**
	 * documentKey
	 */
	private String documentKey;

	/**
	 * createDatetime
	 */
	private String createDatetime;

	/**
	 * lastModifiedDatetime
	 */
	private String lastModifiedDatetime;

	/**
	 * createUserId
	 */
	private String createUserId;

	/**
	 * lastModifiedUserId
	 */
	private String lastModifiedUserId;

	/**
	 * attachmentFiles
	 */
	private String attachmentFiles;

	/**
	 * version
	 */
	private String version;

	/**
	 * status
	 */
	private String status;
}
