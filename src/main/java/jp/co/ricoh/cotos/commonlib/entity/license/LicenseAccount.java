package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.LicenseStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.RequestCreateStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンスアカウントを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_account")
public class LicenseAccount extends EntityBase {

	/**
	 * ライセンスアカウントID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_account_seq")
	@SequenceGenerator(name = "license_account_seq", sequenceName = "license_account_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンスアカウントID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンスアカウント区分マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "ライセンスアカウント区分マスタID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long licenseAccountDivMasterId;

	/**
	 * アカウント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アカウント", required = false, position = 3, allowableValues = "range[0,255]")
	private String account;

	/**
	 * カスタマーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "カスタマーID", required = false, position = 4, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ユーザーID", required = false, position = 5, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * ライセンス状態
	 */
	@ApiModelProperty(value = "ライセンス状態", required = false, position = 6, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private LicenseStatus licenseStatus;

	/**
	 * MoM企業ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MoM企業ID", required = false, position = 7, allowableValues = "range[0,255]")
	private String momCompanyId;

	/**
	 * 担当者氏名_姓
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者氏名_姓", required = false, position = 8, allowableValues = "range[0,255]")
	private String picNameSei;

	/**
	 * 担当者氏名_名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者氏名_名", required = false, position = 9, allowableValues = "range[0,255]")
	private String picNameMei;

	/**
	 * 担当者メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者メールアドレス", required = false, position = 10, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名", required = false, position = 11, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都道府県", required = false, position = 12, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "市区町村", required = false, position = 13, allowableValues = "range[0,255]")
	private String municipality;

	/**
	 * 初期パスワード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "初期パスワード", required = false, position = 14, allowableValues = "range[0,255]")
	private String initialPassword;

	/**
	 * リクエスト作成状態
	 */
	@ApiModelProperty(value = "リクエスト作成状態", required = false, position = 15, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")")
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "リクエスト作成日時", required = false, position = 16)
	private Date requestCreateDate;

	/**
	 * ライセンス情報
	 */
	@OneToMany(mappedBy = "licenseAccount")
	@OrderBy("id ASC")
	@ApiModelProperty(value = "ライセンス情報", required = false, position = 17)
	private List<LicenseInfo> licenseInfoList;

	/**
	 * ライセンスキー情報
	 */
	@OneToMany(mappedBy = "licenseAccount")
	@OrderBy("id ASC")
	@ApiModelProperty(value = "ライセンスキー情報", required = false, position = 18)
	private List<LicenseKeyInfo> licenseKeyInfoList;

}
