package jp.co.ricoh.cotos.commonlib.entity.accounting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 使用量ID
	 */
	@Column(nullable = false)
	@Schema(description = "使用量ID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long usageQuantityId;

	/**
	 * 売上計上データID区分
	 */
	@Schema(description = "売上計上データID区分", required = false, allowableValues = "OSO申込データ(\"1\"), OSO申込明細データ(\"2\"), OSO実績データ(\"3\")", example = "1")
	private OsoSalesDataIdDiv salesDataIdDiv;

	/**
	 * 売上計上データID
	 */
	@Schema(description = "売上計上データID", required = false, allowableValues = "range[0,9999999999999999999]")
	private Long salesDataId;
}
