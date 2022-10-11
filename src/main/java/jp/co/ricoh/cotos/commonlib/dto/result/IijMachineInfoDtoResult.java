package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.IijMachineInfo.SettingStatus;
import lombok.Data;

/**
 * IIJ機器情報を取得するためのDTOです。
 */
@Data
public class IijMachineInfoDtoResult extends DtoBase {

	/**
	 * シリアル番号
	 */
	@ApiModelProperty(value = "シリアル番号", required = false, position = 2, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * ディストリビューションID
	 */
	@ApiModelProperty(value = "ディストリビューションID", required = false, position = 3, allowableValues = "range[0,255]")
	private String distributionId;

	/**
	 * お問い合わせ番号
	 */
	@ApiModelProperty(value = "お問い合わせ番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * RJ管理番号
	 */
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 5, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@ApiModelProperty(value = "契約番号", required = false, position = 6, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@ApiModelProperty(value = "契約番号枝番", required = true, position = 7, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = true, position = 8, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 設定状態
	 */
	@ApiModelProperty(value = "設定状態", required = false, position = 9, allowableValues = "未設定(\"0\"), 設定済(\"1\"), 解除済(\"2\")")
	private SettingStatus settingStatus;

}
