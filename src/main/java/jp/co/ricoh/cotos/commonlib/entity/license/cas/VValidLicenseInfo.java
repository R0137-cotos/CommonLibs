package jp.co.ricoh.cotos.commonlib.entity.license.cas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "MVBアカウント")
	private String mvbAccount;

	/**
	 * 担当者氏名
	 */
	@Schema(description = "担当者氏名")
	private String picName;

	/**
	 * 担当者メールアドレス
	 */
	@Schema(description = "担当者メールアドレス")
	private String picMailAddress;

	/**
	 * TrendMicroアカウント企業名
	 */
	@Schema(description = "TrendMicroアカウント企業名")
	private String tmAccountCompanyName;

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
}