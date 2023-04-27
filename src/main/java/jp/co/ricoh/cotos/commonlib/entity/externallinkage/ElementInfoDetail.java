package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "部材情報明細ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 部材情報
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "element_info_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "部材情報", required = true, position = 2)
	private ElementInfo elementInfo;

	/**
	 * 部材マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "部材マスタID", required = false, position = 3, allowableValues = "range[0,9223372036854775807]")
	private Long elementMasterId;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "数量", required = false, position = 4, allowableValues = "range[-99999,99999]")
	private Integer quantity;

	/**
	 * 原価単価
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "原価単価", required = false, position = 5, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 原価金額
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "原価金額", required = false, position = 6, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal price;
}
