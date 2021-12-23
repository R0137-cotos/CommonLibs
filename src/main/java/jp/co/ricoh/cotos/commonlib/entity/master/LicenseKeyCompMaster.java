package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
@Table(name = "license_key_comp_master")
public class LicenseKeyCompMaster extends EntityBase {

	/**
	 * ライセンスサービス構成マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_key_comp_master_seq")
	@SequenceGenerator(name = "license_key_comp_master_seq", sequenceName = "license_key_comp_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンスサービス構成マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種コードマスタID
	 */
	@ApiModelProperty(value = "品種コードマスタID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long itemCodeMasterId;

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
