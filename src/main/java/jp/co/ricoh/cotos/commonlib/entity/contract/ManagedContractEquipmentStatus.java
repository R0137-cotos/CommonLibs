package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

	/**
	 * 契約機種
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_equipment_id", referencedColumnName = "id")
	@Schema(description = "契約機種", requiredMode = Schema.RequiredMode.REQUIRED)
	private ContractEquipment contractEquipment;

	/**
	 * 継続フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "継続フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer continueFlg;

	/**
	 * 再契約不可フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "再契約不可フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer reContractNotAllowedFlg;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "削除フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * 契約年数
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "契約年数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer contractYears;

	@Valid
	@OneToMany(mappedBy = "managedContractEquipmentStatus")
	@Schema(description = "契約機種品種紐づけシーケンス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractEquipmentItemLink> contractEquipmentItemLinkList;

	/**
	 * 機器削除契約ID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "機器削除契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long equipmentDeletedContractId;
}
