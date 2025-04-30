package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンスサービス構成マスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_service_comp_master")
public class LicenseServiceCompMaster extends EntityBase {

	/**
	 * ライセンスサービス構成マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_service_comp_master_seq")
	@SequenceGenerator(name = "license_service_comp_master_seq", sequenceName = "license_service_comp_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンスサービス構成マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種マスタID
	 */
	@ApiModelProperty(value = "品種マスタID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * ライセンスサービスマスタ
	 */
	@Valid
	@NotNull
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "license_service_master_id", referencedColumnName = "id")
	@ApiModelProperty(value = "ライセンスサービスマスタ", required = true, position = 3)
	private LicenseServiceMaster licenseServiceMaster;

}
