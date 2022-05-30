package jp.co.ricoh.cotos.commonlib.entity.accounting;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 計上期間明細
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "accounting_term_detail")
public class AccountingTermDetail extends EntityBase {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounting_term_detail_seq")
	@SequenceGenerator(name = "accounting_term_detail_seq", sequenceName = "accounting_term_detail_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 契約明細ID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "契約明細ID", required = true, position = 2, allowableValues = "range[0,9999999999999999999]")
	private Long contractDetailId;

	/**
	 * 計上開始日
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "計上開始日", required = true, position = 3)
	@Temporal(TemporalType.DATE)
	private Date accountingTermStart;

	/**
	 * 計上終了日
	 */
	@ApiModelProperty(value = "計上終了日", required = false, position = 4)
	@Temporal(TemporalType.DATE)
	private Date accountingTermEnd;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "数量", required = false, position = 5, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * 単価
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "単価", required = false, position = 6, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 金額
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "金額", required = false, position = 7, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal amountSummary;

}
