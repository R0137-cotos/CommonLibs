package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ファイル連携先ID", required = true, position = 3)
	private long attachedFileLinkageId;

	/**
	 * ファイル連携先
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル連携先", required = true, position = 4, allowableValues = "range[0,255]")
	private String attachedFileLinkageName;

	/**
	 * 連携ステータス
	 */
	@NotNull
	@ApiModelProperty(value = "連携ステータス", required = true, allowableValues = "連携対象外(\"0\"), 未連携(\"1\"), 連携済(\"2\"), 送付済(\"3\")", example = "0", position = 5)
	private FileLinkageStatus linkageStatus;
	
	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "解約フラグ", required = false, position = 6, allowableValues = "range[0,9]")
	private Integer disengagementFlg;
}
