package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.LicenseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LicenseAccountDto extends DtoBase {
	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ユーザーID", required = false, position = 1, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * 初期パスワード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "初期パスワード", required = false, position = 2, allowableValues = "range[0,255]")
	private String initialPassword;

	/**
	 * ライセンスキー情報
	 */
	@ApiModelProperty(value = "ライセンスキー情報", required = false, position = 3)
	@Valid
	private List<LicenseKeyInfoDto> licenseKeyInfoList;

	/**
	 * アカウント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アカウント", required = false, position = 4, allowableValues = "range[0,255]")
	private String account;

	/**
	 * ライセンス状態
	 */
	@ApiModelProperty(value = "ライセンス状態", required = false, position = 5, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private LicenseStatus licenseStatus;

	/**
	 * 担当者氏名_姓
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者氏名_姓", required = false, position = 6, allowableValues = "range[0,255]")
	private String picNameSei;

	/**
	 * 担当者氏名_名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者氏名_名", required = false, position = 7, allowableValues = "range[0,255]")
	private String picNameMei;

}
