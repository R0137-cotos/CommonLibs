package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ライセンスアカウントID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンスアカウント区分マスタID
	 */
	@Min(0)
	@Schema(description = "ライセンスアカウント区分マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseAccountDivMasterId;

	/**
	 * アカウント
	 */
	@Size(max = 255)
	@Schema(description = "アカウント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String account;

	/**
	 * カスタマーID
	 */
	@Size(max = 255)
	@Schema(description = "カスタマーID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@Schema(description = "ユーザーID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * ライセンス状態
	 */
	@Schema(description = "ライセンス状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private LicenseStatus licenseStatus;

	/**
	 * MoM企業ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momCompanyId;

	/**
	 * 担当者氏名_姓
	 */
	@Size(max = 255)
	@Schema(description = "担当者氏名_姓", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picNameSei;

	/**
	 * 担当者氏名_名
	 */
	@Size(max = 255)
	@Schema(description = "担当者氏名_名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picNameMei;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@Schema(description = "住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String address;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@Schema(description = "都道府県", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@Schema(description = "市区町村", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String municipality;

	/**
	 * 初期パスワード
	 */
	@Size(max = 255)
	@Schema(description = "初期パスワード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String initialPassword;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * リクエスト作成状態
	 */
	@Schema(description = "リクエスト作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")")
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "リクエスト作成日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date requestCreateDate;

	/**
	 * ライセンス情報
	 */
	@OneToMany(mappedBy = "licenseAccount")
	@OrderBy("id ASC")
	@JsonIgnore
	@Schema(description = "ライセンス情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<LicenseInfo> licenseInfoList;

	/**
	 * ライセンスキー情報
	 */
	@OneToMany(mappedBy = "licenseAccount")
	@OrderBy("id ASC")
	@Schema(description = "ライセンスキー情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<LicenseKeyInfo> licenseKeyInfoList;

}
