package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.CasLicenseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CASライセンス基本情報DTO
 * @author z00se03039
 *
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CasLicenseBasicInfoDto extends DtoBase {

	/**
	 * カスタマーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "カスタマーID", required = false, position = 1, allowableValues = "range[0,25]")
	private String customerId;

	/**
	 * トレンドユーザーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "トレンドユーザーID", required = false, position = 2, allowableValues = "range[0,25]")
	private String trendUserId;

	/**
	 * MVBアカウント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MVBアカウント", required = false, position = 3, allowableValues = "range[0,25]")
	private String mvbAccount;

	/**
	 * ライセンス状態
	 */
	@ApiModelProperty(value = "ライセンス状態", required = false, position = 4, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private CasLicenseStatus licenseStatus;

	/**
	 * MoM企業ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MoM企業ID", required = false, position = 5, allowableValues = "range[0,25]")
	private String momCompanyId;

	/**
	 * 担当者氏名_姓
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者氏名_姓", required = false, position = 6, allowableValues = "range[0,25]")
	private String picNameSei;

	/**
	 * 担当者氏名_名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者氏名_名", required = false, position = 7, allowableValues = "range[0,25]")
	private String picNameMei;

	/**
	 * 担当者メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者メールアドレス", required = false, position = 8, allowableValues = "range[0,25]")
	private String picMailAddress;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名", required = false, position = 9, allowableValues = "range[0,25]")
	private String companyName;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都道府県", required = false, position = 10, allowableValues = "range[0,25]")
	private String prefectures;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "市区町村", required = false, position = 11, allowableValues = "range[0,25]")
	private String municipality;

	/**
	 * 初期パスワード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "初期パスワード", required = true, position = 12, allowableValues = "range[0,25]")
	private String initialPassword;
	
	/**
	 * CASライセンス管理情報
	 */
	@Valid
	@ApiModelProperty(value = "CASライセンス管理情報", required = false, position = 13)
	private List<CasLicenseManagementInfoDto> casLicenseManagementInfoList;
}
