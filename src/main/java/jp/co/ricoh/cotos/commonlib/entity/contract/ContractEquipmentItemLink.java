package jp.co.ricoh.cotos.commonlib.entity.contract;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約機種品種紐づけシーケンスを表すEntity
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
	 * 品種(契約用)ID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "item_contract_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "品種(契約用)", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private ItemContract itemContract;

	/**
	 * 契約機種状態管理
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "managed_contract_equipment_status_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "契約機種状態管理", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private ManagedContractEquipmentStatus managedContractEquipmentStatus;

	/**
	 * 品種(契約用)ID_自動更新
	 */
	@ManyToOne
	@JoinColumn(name = "auto_update_item_contract_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "品種(契約用)_自動更新", required = true, position = 4, allowableValues = "range[0,9223372036854775807]")
	private ItemContract autoUpdateItemContract;
}
