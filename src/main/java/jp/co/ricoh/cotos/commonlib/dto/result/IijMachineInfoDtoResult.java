package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "シリアル番号", required = false, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * ディストリビューションID
	 */
	@Schema(description = "ディストリビューションID", required = false, allowableValues = "range[0,255]")
	private String distributionId;

	/**
	 * お問い合わせ番号
	 */
	@Schema(description = "お問い合わせ番号", required = false, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * RJ管理番号
	 */
	@Schema(description = "RJ管理番号", required = false, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Schema(description = "契約番号", required = false, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Schema(description = "契約番号枝番", required = true, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 設定状態
	 */
	@Schema(description = "設定状態", required = false, allowableValues = "未設定(\"0\"), 設定済(\"1\"), 解除済(\"2\")")
	private SettingStatus settingStatus;

}
