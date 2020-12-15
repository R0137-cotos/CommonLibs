package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス区分構成マスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_div_comp_master")
public class LicenseDivCompMaster extends EntityBase {


	/**
	 * ライセンス区分構成マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_div_comp_master_seq")
	@SequenceGenerator(name = "license_div_comp_master_seq", sequenceName = "license_div_comp_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンス区分構成マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 商品マスタID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "商品マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;
}
