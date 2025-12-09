package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DetailStatus;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationDetail.IncreaseDecreaseDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "managed_estimation_detail")
public class ManagedEstimationDetail extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "managed_estimation_detail_seq")
	@SequenceGenerator(name = "managed_estimation_detail_seq", sequenceName = "managed_estimation_detail_seq", allocationSize = 1)
	@Schema(description = "見積明細管理ID(作成時不要)", readOnly = true)
	private long id;

	/**
	 * 見積
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonIgnore
	private Contract contract;

	/**
	 * 状態
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "NOUPDATE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")", example = "1")
	private DetailStatus state;

	/**
	 * 変更前数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "変更前数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer beforeQuantity;

	/**
	 * 数量
	 */
	@Column(nullable = false)
	@Max(99999)
	@Min(-99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[-99999,99999]")
	private int quantity;

	/**
	 * 見積単価
	 */
	@Column(nullable = false)
	@NotNull
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "見積単価", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal estimationUnitPrice;

	/**
	 * 見積金額
	 */
	@Column(nullable = false)
	@NotNull
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "見積金額", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal estimationAmountSummary;

	/**
	 * 摘要
	 */
	@Size(max = 255)
	@Schema(description = "摘要", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String detailAbstract;

	/**
	 * 拡張項目
	 */
	@Lob
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String extendsParameter;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * リコー品種コード
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * メーカー商品コード
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "メーカー商品コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String makerItemCode;

	/**
	 * 変更前単価
	 */
	@Column(nullable = true)
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "変更前単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal beforeUnitPrice;

	/**
	 * 増減区分
	 */
	@Schema(description = "増減区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "増数(\"1\"), 減数(\"2\")", example = "1")
	private IncreaseDecreaseDiv increaseDecreaseDiv;
}
