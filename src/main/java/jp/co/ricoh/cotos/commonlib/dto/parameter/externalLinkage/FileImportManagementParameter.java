package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.common.AttachedFile;
import lombok.Data;

/**
 * ファイル取込管理を設定するためのキー項目クラスを表します。
 */

@Data
public class FileImportManagementParameter {

	/**
	 * ファイル種別管理マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "ファイル種別管理マスタID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long fileKindManagementMasterId;

	/**
	 * ファイル名
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル名", required = true, position = 2, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * 添付ファイル
	 */
	@NotNull
	@ApiModelProperty(value = "添付ファイル", required = true, position = 3)
	private AttachedFile attachedFile;

	/**
	 * 取込実施者
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "取込実施者", required = true, position = 4, allowableValues = "range[0,255]")
	private String importUser;

}
