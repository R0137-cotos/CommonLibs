package jp.co.ricoh.cotos.commonlib.entity.license;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "アカウント")
	private String account;

	/**
	 * 担当者氏名
	 */
	@Schema(description = "担当者氏名")
	private String picName;

	/**
	 * 担当者メールアドレス
	 */
	@Schema(description = "担当者メールアドレス")
	private String accountPicMailAddress;

	/**
	 * アカウント企業名
	 */
	@Schema(description = "アカウント企業名")
	private String accountCompanyName;

	/**
	 * お客様住所都道府県
	 */
	@Schema(description = "お客様住所都道府県")
	private String prefectures;

	/**
	 * 電話番号
	 */
	@Schema(description = "電話番号")
	private String picPhoneNumber;

	/**
	 * お客様企業名カナ
	 */
	@Schema(description = "お客様企業名カナ")
	private String companyNameKana;

	/**
	 * お客様企業名
	 */
	@Schema(description = "お客様企業名")
	private String companyName;

	/**
	 * 企事部ID
	 */
	@Schema(description = "企事部ID")
	private String momCustId;

	/**
	 * 担当者メールアドレス
	 */
	@Schema(description = "メールアドレス")
	private String picMailAddress;

	/**
	 * 文書番号
	 */
	@Schema(description = "文書番号")
	private String contractNumber;

	/**
	 * 文書番号枝番
	 */
	@Schema(description = "文書番号枝番")
	private Integer contractBranchNumber;

	/**
	 * 企業ID
	 */
	@Schema(description = "企業ID")
	private String momCompanyId;

	/**
	 * 費用種別
	 */
	@Schema(description = "費用種別")
	private String costType;

	/**
	 * 数量
	 */
	@Schema(description = "数量")
	private Integer licenseQuantity;

	/**
	 * 商品名
	 */
	@Schema(description = "商品名")
	private String productName;

	/**
	 * インストールURL
	 */
	@Schema(description = "インストールURL")
	private String installUrl;

	/**
	 * ライセンスアカウント区分名称
	 */
	@Schema(description = "ライセンスアカウント区分名称")
	private String licenseAccountDivName;
}