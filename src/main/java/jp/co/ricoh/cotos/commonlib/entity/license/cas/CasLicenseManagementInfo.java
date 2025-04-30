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

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CASライセンス管理情報エンティティ
 *
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "cas_license_management_info")
public class CasLicenseManagementInfo extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cas_license_management_info_seq")
	@SequenceGenerator(name = "cas_license_management_info_seq", sequenceName = "cas_license_management_info_seq", allocationSize = 1)
	@ApiModelProperty(value = "CASライセンス管理情報", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = true, position = 3, allowableValues = "range[0,25]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約番号", required = true, position = 4, allowableValues = "range[0,25]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Min(0)
	@Max(99)
	@ApiModelProperty(value = "契約番号枝番", required = true, position = 5, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 最終契約同期日付
	 */
	@ApiModelProperty(value = "最終契約同期日付", required = false, position = 6)
	@Temporal(TemporalType.DATE)
	private Date lastContractSyncDate;

	/**
	 * CASライセンス基本情報
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "cas_license_basic_info_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "CASライセンス基本情報", required = true, position = 7)
	private CasLicenseBasicInfo casLicenseBasicInfo;

}
