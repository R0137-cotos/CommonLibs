package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.FileLinkageStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContractAttachedFileLinkageDto extends DtoBase {

	/**
	 * ファイル連携先ID
	 */
	@Schema(description = "ファイル連携先ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long attachedFileLinkageId;

	/**
	 * ファイル連携先
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "ファイル連携先", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String attachedFileLinkageName;

	/**
	 * 連携ステータス
	 */
	@NotNull
	@Schema(description = "連携ステータス", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "連携対象外(\"0\"), 未連携(\"1\"), 連携済(\"2\"), 送付済(\"3\")", example = "0")
	private FileLinkageStatus linkageStatus;
	
	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer disengagementFlg;
}
