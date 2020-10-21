package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ファイル取込管理を設定するためのキー項目クラスを表します。
 */

@Data
public class FileImportManagementParameter {

	/**
	 * ファイル種別管理ID
	 */
	@ApiModelProperty(value = "ファイル種別管理ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long fileKindManagementId;

	/**
	 * ファイル名
	 */
	@ApiModelProperty(value = "ファイル名", required = true, position = 2, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * 添付ファイルID
	 */
	@ApiModelProperty(value = "添付ファイルID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long attachmentId;

	/**
	 * 取込実施者
	 */
	@ApiModelProperty(value = "取込実施者", required = true, position = 4, allowableValues = "range[0,255]")
	private String importUser;

}
