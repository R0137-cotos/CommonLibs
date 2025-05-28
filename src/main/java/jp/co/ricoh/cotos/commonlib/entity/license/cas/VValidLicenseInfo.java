package jp.co.ricoh.cotos.commonlib.entity.license.cas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 有効ライセンスVIEW
 */
@Entity
@Data
@Table(name = "v_valid_license_info")
public class VValidLicenseInfo {

	@Id
	private long id;

	/**
	 * MVBアカウント
	 */
	@ApiModelProperty(value = "MVBアカウント")
	private String mvbAccount;

	/**
	 * 担当者氏名
	 */
	@ApiModelProperty(value = "担当者氏名")
	private String picName;

	/**
	 * 担当者メールアドレス
	 */
	@ApiModelProperty(value = "担当者メールアドレス")
	private String picMailAddress;

	/**
	 * TrendMicroアカウント企業名
	 */
	@ApiModelProperty(value = "TrendMicroアカウント企業名")
	private String tmAccountCompanyName;

	/**
	 * お客様住所都道府県
	 */
	@ApiModelProperty(value = "お客様住所都道府県")
	private String prefectures;

	/**
	 * 電話番号
	 */
	@ApiModelProperty(value = "電話番号")
	private String picPhoneNumber;

	/**
	 * お客様企業名カナ
	 */
	@ApiModelProperty(value = "お客様企業名カナ")
	private String companyNameKana;

	/**
	 * お客様企業名
	 */
	@ApiModelProperty(value = "お客様企業名")
	private String companyName;

	/**
	 * 企事部ID
	 */
	@ApiModelProperty(value = "企事部ID")
	private String momCustId;

	/**
	 * 文書番号
	 */
	@ApiModelProperty(value = "文書番号")
	private String contractNumber;

	/**
	 * 文書番号枝番
	 */
	@ApiModelProperty(value = "文書番号枝番")
	private Integer contractBranchNumber;

	/**
	 * 企業ID
	 */
	@ApiModelProperty(value = "企業ID")
	private String momCompanyId;
}