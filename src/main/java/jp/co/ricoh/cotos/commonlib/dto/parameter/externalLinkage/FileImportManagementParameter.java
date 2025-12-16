package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ファイル種別管理マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long fileKindManagementMasterId;

	/**
	 * ファイル名
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "ファイル名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * 添付ファイル
	 */
	@NotNull
	@Schema(description = "添付ファイル", requiredMode = Schema.RequiredMode.REQUIRED)
	private AttachedFile attachedFile;

	/**
	 * 取込実施者
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "取込実施者", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String importUser;

	/**
	 * 関連ファイル取込管理ID
	 */
	@Min(0)
	@Schema(description = "関連ファイル取込管理ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long relatedFileImportManagementId;

}
