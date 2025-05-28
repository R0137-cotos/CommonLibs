package jp.co.ricoh.cotos.commonlib.entity.license;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 有効ライセンスアカウント情報VIEW
 */
@Entity
@Data
@Table(name = "v_valid_license_account_info")
public class VValidLicenseAccountInfo {

	@Id
	private long id;

	/**
	 * アカウント
	 */
	@ApiModelProperty(value = "アカウント")
	private String account;

	/**
	 * 担当者氏名
	 */
	@ApiModelProperty(value = "担当者氏名")
	private String picName;

	/**
	 * 担当者メールアドレス
	 */
	@ApiModelProperty(value = "担当者メールアドレス")
	private String accountPicMailAddress;

	/**
	 * アカウント企業名
	 */
	@ApiModelProperty(value = "アカウント企業名")
	private String accountCompanyName;

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
	 * 担当者メールアドレス
	 */
	@ApiModelProperty(value = "メールアドレス")
	private String picMailAddress;

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

	/**
	 * 費用種別
	 */
	@ApiModelProperty(value = "費用種別")
	private String costType;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量")
	private Integer licenseQuantity;

	/**
	 * 商品名
	 */
	@ApiModelProperty(value = "商品名")
	private String productName;

	/**
	 * インストールURL
	 */
	@ApiModelProperty(value = "インストールURL")
	private String installUrl;

	/**
	 * ライセンスアカウント区分名称
	 */
	@ApiModelProperty(value = "ライセンスアカウント区分名称")
	private String licenseAccountDivName;
}