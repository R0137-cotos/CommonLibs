package jp.co.ricoh.cotos.commonlib.entity.license.cas;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String tmPicMailAddress;

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