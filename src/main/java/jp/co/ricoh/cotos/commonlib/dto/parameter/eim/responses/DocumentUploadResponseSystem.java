package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * 文書アップロードのsystemレスポンスボディ
 */

@Data
public class DocumentUploadResponseSystem {

	/**
	 * formKey
	 */
	private String formKey;

	/**
	 * siteId
	 */
	private String siteId;

	/**
	 * appId
	 */
	private String appId;

	/**
	 * modelId
	 */
	private String modelId;
	
	/**
	 * condition
	 */
	private String condition;
}
