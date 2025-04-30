package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.IijMachineInfo.SettingStatus;
import lombok.Data;

/**
 * IIJ機器情報の検索条件を設定するためのキー項目クラスを表します。
 */

@Data
public class IijMachineInfoSearchParameter {

	/**
	 * IIJ機器情報ID
	 */
	@Min(0)
	@ApiModelProperty(value = "IIJ機器情報ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * シリアル番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "シリアル番号", required = false, position = 2, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * ディストリビューションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ディストリビューションID", required = false, position = 3, allowableValues = "range[0,255]")
	private String distributionId;

	/**
	 * お問い合わせ番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "お問い合わせ番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 5, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約番号", required = false, position = 6, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@ApiModelProperty(value = "契約番号枝番", required = false, position = 7, allowableValues = "range[0,2]")
	private String contractBranchNumber;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = true, position = 8, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 設定状態
	 */
	@ApiModelProperty(value = "設定状態", required = false, position = 9, allowableValues = "未設定(\"0\"), 設定済(\"1\"), 解除済(\"2\")")
	private SettingStatus settingStatus;

}
