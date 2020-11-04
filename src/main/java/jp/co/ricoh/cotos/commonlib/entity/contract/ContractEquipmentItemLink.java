package jp.co.ricoh.cotos.commonlib.entity.contract;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約機種シーケンスを表すEntity
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
	 * 契約ID
	 */
	@Min(0)
	@NotNull
	@ApiModelProperty(value = "契約ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 契約機種ID
	 */
	@Min(0)
	@NotNull
	@ApiModelProperty(value = "契約機種ID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private Long contractEquipmentId;

	/**
	 * 品種(契約用)ID
	 */
	@Min(0)
	@NotNull
	@ApiModelProperty(value = "契約機種ID", required = true, position = 4, allowableValues = "range[0,9223372036854775807]")
	private Long itemContractId;


}
