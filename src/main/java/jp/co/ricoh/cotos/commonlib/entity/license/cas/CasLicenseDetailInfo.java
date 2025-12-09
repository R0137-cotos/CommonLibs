package jp.co.ricoh.cotos.commonlib.entity.license.cas;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
import jp.co.ricoh.cotos.commonlib.entity.EnumType.CasLicenseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CASライセンス明細情報エンティティ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "cas_license_detail_info")
public class CasLicenseDetailInfo extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cas_license_detail_info_seq")
	@SequenceGenerator(name = "cas_license_detail_info_seq", sequenceName = "cas_license_detail_info_seq", allocationSize = 1)
	@Schema(description = "CASライセンス明細情報", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "サブスクリプションID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,25]")
	private String subscriptionId;

	/**
	 * サービスプランID
	 */
	@Size(max = 255)
	@Schema(description = "サービスプランID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,25]")
	private String servicePlanId;

	/**
	 * ライセンス状態
	 */
	@Schema(description = "ライセンス状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private CasLicenseStatus licenseStatus;

	/**
	 * ライセンス開始日
	 */
	@Schema(description = "ライセンス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@Schema(description = "ライセンス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date licenseTermEnd;

	/**
	 * 数量
	 */
	@Min(0)
	@Max(99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * CASライセンス基本情報
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "cas_license_basic_info_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "CASライセンス基本情報", requiredMode = Schema.RequiredMode.REQUIRED)
	private CasLicenseBasicInfo casLicenseBasicInfo;

}
