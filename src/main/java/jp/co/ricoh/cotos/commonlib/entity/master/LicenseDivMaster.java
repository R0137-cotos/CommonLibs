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
 * ライセンス区分マスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_div_master")
public class LicenseDivMaster extends EntityBase {

	/**
	 * ライセンス区分マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_div_master_seq")
	@SequenceGenerator(name = "license_div_master_seq", sequenceName = "license_div_master_seq", allocationSize = 1)
	@Schema(description = "ライセンス区分マスタID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分名称
	 */
	@Size(max = 255)
	@Schema(description = "ライセンス区分名称", required = true, allowableValues = "range[0,255]")
	private String licenseDivName;

	/**
	 * ライセンス種類区分
	 */
	@Size(max = 255)
	@Schema(description = "ライセンス種類区分", required = false, allowableValues = "range[0,255]")
	private String licenseClassDiv;

	/**
	 * ライセンス区分構成マスタ
	 */
	@OneToMany(mappedBy = "licenseDivMaster")
	@Schema(description = "ライセンス区分マスタ", required = true)
	private List<LicenseDivCompMaster> licenseDivCompMasterList;
}
