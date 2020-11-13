package jp.co.ricoh.cotos.commonlib.entity.contract;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約機種品種紐づけを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_equipment_item_link")
public class ContractEquipmentItemLink extends EntityBase {
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_equipment_item_link_seq")
	@SequenceGenerator(name = "contract_equipment_item_link_seq", sequenceName = "contract_equipment_item_link_seq", allocationSize = 1)
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
	 * 品種(契約用)
	 */
	@NotNull
	@OneToOne(optional = false)
	@JoinColumn(name = "item_contract_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "品種(契約用)", required = true, position = 4)
	private ItemContract itemContract;


}
