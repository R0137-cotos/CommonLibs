package jp.co.ricoh.cotos.commonlib.entity.license.cas;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "CASライセンス明細情報", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サブスクリプションID", required = true, position = 2, allowableValues = "range[0,25]")
	private String subscriptionId;

	/**
	 * サービスプランID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービスプランID", required = true, position = 3, allowableValues = "range[0,25]")
	private String servicePlanId;

	/**
	 * ライセンス状態
	 */
	@ApiModelProperty(value = "ライセンス状態", required = false, position = 4, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private CasLicenseStatus licenseStatus;

	/**
	 * ライセンス開始日
	 */
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 5)
	@Temporal(TemporalType.DATE)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@ApiModelProperty(value = "ライセンス終了日", required = false, position = 6)
	@Temporal(TemporalType.DATE)
	private Date licenseTermEnd;

	/**
	 * 数量
	 */
	@Min(0)
	@Max(99999)
	@ApiModelProperty(value = "数量", required = true, position = 7, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * CASライセンス基本情報
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "cas_license_basic_info_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "CASライセンス基本情報", required = true, position = 8)
	private CasLicenseBasicInfo casLicenseBasicInfo;

}
