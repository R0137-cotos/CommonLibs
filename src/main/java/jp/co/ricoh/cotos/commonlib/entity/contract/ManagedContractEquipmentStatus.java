package jp.co.ricoh.cotos.commonlib.entity.contract;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約機種状態管理を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "managed_contract_equipment_status")
public class ManagedContractEquipmentStatus extends EntityBase {
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "managed_contract_equipment_status_seq")
	@SequenceGenerator(name = "managed_contract_equipment_status_seq", sequenceName = "managed_contract_equipment_status_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "契約", required = true, position = 2)
	private Contract contract;

	/**
	 * 契約機種
	 */
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_equipment_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "契約機種", required = true, position = 3)
	private ContractEquipment contractEquipment;

	/**
	 * 継続フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "継続フラグ", required = false, position = 4, allowableValues = "range[0,9]")
	private Integer continueFlg;

	/**
	 * 再契約不可フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "再契約不可フラグ", required = false, position = 5, allowableValues = "range[0,9]")
	private Integer reContractNotAllowedFlg;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "削除フラグ", required = false, position = 6, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * 契約年数
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "契約年数", required = false, position = 7, allowableValues = "range[0,99999]")
	private Integer contractYears;
}
