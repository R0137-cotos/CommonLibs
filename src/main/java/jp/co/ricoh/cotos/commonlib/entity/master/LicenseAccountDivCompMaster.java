package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
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

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンスアカウント区分構成マスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_account_div_comp_master")
public class LicenseAccountDivCompMaster extends EntityBase {

	/**
	 * ライセンスアカウント区分構成マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_account_div_comp_master_seq")
	@SequenceGenerator(name = "license_account_div_comp_master_seq", sequenceName = "license_account_div_comp_masterr_seq", allocationSize = 1)
	@Schema(description = "ライセンスアカウント区分構成マスタID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 商品マスタID
	 */
	@Column(nullable = false)
	@Schema(description = "商品マスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * ライセンスアカウント区分マスタ
	 */
	@Valid
	@NotNull
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "license_account_div_master_id", referencedColumnName = "id")
	@Schema(description = "ライセンスアカウント区分マスタ", required = true)
	private LicenseAccountDivMaster licenseAccountDivMaster;

}
