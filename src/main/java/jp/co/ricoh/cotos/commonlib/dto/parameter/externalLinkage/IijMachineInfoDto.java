package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.IijMachineInfo.SettingStatus;
import lombok.Data;

/**
 * IIJ機器情報を設定するためのキー項目クラスを表します。
 */

@Data
public class IijMachineInfoDto extends DtoBase {

	/**
	 * シリアル番号
	 */
	@Size(max = 255)
	@Schema(description = "シリアル番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * ディストリビューションID
	 */
	@Size(max = 255)
	@Schema(description = "ディストリビューションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributionId;

	/**
	 * お問い合わせ番号
	 */
	@Size(max = 255)
	@Schema(description = "お問い合わせ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Max(99)
	@Min(0)
	@Schema(description = "契約番号枝番", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 設定状態
	 */
	@Schema(description = "設定状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未設定(\"0\"), 設定済(\"1\"), 解除済(\"2\")")
	private SettingStatus settingStatus;

}
