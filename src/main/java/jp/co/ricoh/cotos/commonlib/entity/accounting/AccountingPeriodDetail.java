package jp.co.ricoh.cotos.commonlib.entity.accounting;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail.RunningAccountSalesStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 計上期間明細
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "accounting_period_detail")
public class AccountingPeriodDetail extends EntityBase {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounting_period_detail_seq")
	@SequenceGenerator(name = "accounting_period_detail_seq", sequenceName = "accounting_period_detail_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "契約", required = true, position = 2)
	private Contract contract;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 計上開始日
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "計上開始日", required = true, position = 4)
	@Temporal(TemporalType.DATE)
	private Date accountingPeriodStart;

	/**
	 * 計上終了日
	 */
	@ApiModelProperty(value = "計上終了日", required = false, position = 5)
	@Temporal(TemporalType.DATE)
	private Date accountingPeriodEnd;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "数量", required = false, position = 6, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * 単価
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "単価", required = false, position = 7, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 金額
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "金額", required = false, position = 8, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal amountSummary;

	/**
	 * ランニング売上計上処理状態
	 */
	@ApiModelProperty(value = "ランニング売上計上処理状態", required = false, allowableValues = "正常(\"0\"), 処理エラー(\"1\"), 処理不要(\"2\")", position = 9)
	private RunningAccountSalesStatus runningAccountSalesStatus;

	/**
	 * ランニング売上計上処理日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ランニング売上計上処理日", required = false, position = 10)
	private Date runningAccountSalesDate;

}
