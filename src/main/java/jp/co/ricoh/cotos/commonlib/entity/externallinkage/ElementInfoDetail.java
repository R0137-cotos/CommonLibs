package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部材情報明細を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "element_info_detail")
public class ElementInfoDetail extends EntityBase {

	/**
	 * 部材情報明細ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "element_info_detail_seq")
	@SequenceGenerator(name = "element_info_detail_seq", sequenceName = "element_info_detail_seq", allocationSize = 1)
	@Schema(description = "部材情報明細ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 部材情報
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "element_info_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "部材情報", requiredMode = Schema.RequiredMode.REQUIRED)
	private ElementInfo elementInfo;

	/**
	 * 部材マスタID
	 */
	@Min(0)
	@Schema(description = "部材マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long elementMasterId;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer quantity;

	/**
	 * 原価単価
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "原価単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 原価金額
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "原価金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal price;
}
