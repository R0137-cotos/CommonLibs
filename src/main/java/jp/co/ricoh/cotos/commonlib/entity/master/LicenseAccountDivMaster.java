package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ライセンスアカウント区分マスタID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンスアカウント区分名
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスアカウント区分名", required = false, allowableValues = "range[0,255]")
	private String licenseAccountDivName;

	/**
	 * ライセンスアカウント種類区分
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスアカウント種類区分", required = false, allowableValues = "range[0,255]")
	private String licenseAccountClassDiv;

	/**
	 * ライセンスアカウント区分構成マスタ
	 */
	@OneToMany(mappedBy = "licenseAccountDivMaster")
	@Schema(description = "ライセンスアカウント区分マスタ", required = true)
	private List<LicenseAccountDivCompMaster> licenseAccountDivCompMasterList;

}
