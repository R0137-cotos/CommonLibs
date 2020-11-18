package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ライセンス区分マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンス区分名称", required = true, position = 2, allowableValues = "range[0,255]")
	private String licenseDivName;
}
