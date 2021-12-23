package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンスサービスマスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_service_master")
public class LicenseServiceMaster extends EntityBase {

	/**
	 * ライセンスサービスマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_service_master_seq")
	@SequenceGenerator(name = "license_service_master_seq", sequenceName = "license_service_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンスサービスマスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンスサービスID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスサービスID", required = true, position = 2, allowableValues = "range[0,255]")
	private String licenseServiceId;

	/**
	 * ライセンスサービス名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスサービス名", required = false, position = 3, allowableValues = "range[0,255]")
	private String licenseServiceName;

	/**
	 * ライセンスサービス種類区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスサービス種類区分", required = false, position = 4, allowableValues = "range[0,255]")
	private String licenseServiceClassDiv;

	/**
	 * ライセンス区分構成マスタ
	 */
	@OneToMany(mappedBy = "licenseServiceMaster")
	@ApiModelProperty(value = "ライセンス区分構成マスタ", required = true, position = 5)
	private List<LicenseKeyCompMaster> licenseKeyCompMasterList;
}
