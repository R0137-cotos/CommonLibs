package jp.co.ricoh.cotos.commonlib.entity.license.cas;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;

/**
 * COTOSライセンス情報MVIEW
 */
@Entity
@Data
@Table(name = "mv_cotos_license_info")
public class MvCotosLicenseInfo {

	/** MVBアカウント */
	@Id
	private String mvbAccount;

	/** カスタマーID */
	private String customerId;

	/** トレンドユーザーID */
	private String trendUserId;

	/** TrendMicroアカウントステータス */
	private Integer tmAccountStatus;

	/** 担当者氏名_名 */
	private String picNameMei;

	/** 担当者氏名_姓 */
	private String picNameSei;

	/** 担当者メールアドレス */
	private String picMailAddress;

	/** MoM企業ID */
	private String momCompanyId;

	/** TrendMicroアカウント企業名 */
	private String tmAccountCompanyName;

	/** 都道府県 */
	private String prefectures;

	/** 市区町村 */
	private String municipality;

	/** サービスプランID */
	private String servicePlanId;

	/** サブスクリプションID */
	private String subscriptionId;

	/** ライセンス開始日 */
	@Temporal(TemporalType.DATE)
	private Date licenseTermStart;

	/** 数量 */
	private int quantity;
}