package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.LicenseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンスキー情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_key_info")
public class LicenseKeyInfo extends EntityBase {

	/**
	 * ライセンスキー情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_key_info_seq")
	@SequenceGenerator(name = "license_key_info_seq", sequenceName = "license_key_info_seq", allocationSize = 1)
	@Schema(description = "ライセンスキー情報ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンスアカウント
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "license_account_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "ライセンスアカウント", requiredMode = Schema.RequiredMode.REQUIRED)
	private LicenseAccount licenseAccount;

	/**
	 * ライセンスサービスID
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスサービスID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String licenseServiceId;

	/**
	 * ライセンスキー
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "ライセンスキー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String licenseKey;

	/**
	 * ライセンス状態
	 */
	@Schema(description = "ライセンス状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private LicenseStatus licenseStatus;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date licenseTermEnd;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer quantity;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * ライセンス区分マスタID
	 */
	@Min(0)
	@Schema(description = "ライセンス区分マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;
}
