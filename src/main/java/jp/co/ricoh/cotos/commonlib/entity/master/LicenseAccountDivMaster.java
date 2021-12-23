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
 * ライセンスアカウント区分マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_account_div_master")
public class LicenseAccountDivMaster extends EntityBase {

	/**
	 * ライセンスアカウント区分マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_account_div_master_seq")
	@SequenceGenerator(name = "license_account_div_master_seq", sequenceName = "license_account_div_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンスアカウント区分マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンスアカウント区分名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスアカウント区分名", required = false, position = 2, allowableValues = "range[0,255]")
	private String licenseAccountDivName;

	/**
	 * ライセンスアカウント種類区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスアカウント種類区分", required = false, position = 3, allowableValues = "range[0,255]")
	private String licenseAccountClassDiv;

	/**
	 * ライセンスアカウント区分構成マスタ
	 */
	@OneToMany(mappedBy = "licenseAccountDivMaster")
	@ApiModelProperty(value = "ライセンスアカウント区分マスタ", required = true, position = 4)
	private List<LicenseAccountDivCompMaster> licenseAccountDivCompMasterList;

}
