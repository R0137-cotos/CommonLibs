package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.FileLinkageStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ArrangementWorkAttachedFileLinkageDto extends DtoBase {

	/**
	 * ファイル連携先
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル連携先", required = true, position = 3, allowableValues = "range[0,255]")
	private String attachedFileLinkageName;

	/**
	 * 連携ステータス
	 */
	@NotNull
	@ApiModelProperty(value = "連携ステータス", required = true, allowableValues = "連携対象外(\"0\"), 未連携(\"1\"), 連携済(\"2\"), 送付済(\"3\")", example = "0", position = 4)
	private FileLinkageStatus linkageStatus;
}
