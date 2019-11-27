package jp.co.ricoh.cotos.commonlib.entity.accounting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoSalesDataIdDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "usage_quantity_related_management")
public class UsageQuantityRelatedManagement extends EntityBase {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usage_quantity_related_management_seq")
	@SequenceGenerator(name = "usage_quantity_related_management_seq", sequenceName = "usage_quantity_related_management_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 使用量ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "使用量ID", required = true, position = 2, allowableValues = "range[0,9999999999999999999]")
	private long usageQuantityId;

	/**
	 * 売上計上データID区分
	 */
	@ApiModelProperty(value = "売上計上データID区分", required = false, allowableValues = "OSO申込データ(\"1\"), OSO申込明細データ(\"2\"), OSO実績データ(\"3\")", example = "1", position = 3)
	private OsoSalesDataIdDiv salesDataIdDiv;

	/**
	 * 売上計上データID
	 */
	@ApiModelProperty(value = "売上計上データID", required = false, position = 4, allowableValues = "range[0,9999999999999999999]")
	private Long salesDataId;
}
