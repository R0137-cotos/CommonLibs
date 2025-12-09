package jp.co.ricoh.cotos.commonlib.entity.contract;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種(契約用)ID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "item_contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "品種(契約用)", required = true, allowableValues = "range[0,9223372036854775807]")
	private ItemContract itemContract;

	/**
	 * 契約機種状態管理
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "managed_contract_equipment_status_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約機種状態管理", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private ManagedContractEquipmentStatus managedContractEquipmentStatus;

	/**
	 * 品種(契約用)ID_自動更新
	 */
	@ManyToOne
	@JoinColumn(name = "auto_update_item_contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "品種(契約用)_自動更新", required = true, allowableValues = "range[0,9223372036854775807]")
	private ItemContract autoUpdateItemContract;
}
