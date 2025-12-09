package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品種を表すEntity
 */

@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(ItemEstimationListener.class)
@Data
@Table(name = "item_estimation")
public class ItemEstimation extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_estimation_seq")
	@SequenceGenerator(name = "item_estimation_seq", sequenceName = "item_estimation_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 商品マスタ
	 */
	@Min(0)
	@Schema(description = "商品マスタ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 品種名
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String itemEstimationName;

	/**
	 * リコー品種コード
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "リコー品種コード<br/>※POST時「品種マスタ」存在チェック実施", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@NotNull
	@Schema(description = "品種区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", example = "1")
	private ItemType itemType;

	/**
	 * 費用種別
	 */
	@NotNull
	@Schema(description = "費用種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "初期費(\"1\"), 月額(\"2\"), 年額(\"3\")", example = "1")
	private CostType costType;

	/**
	 * 仕切価格
	 */
	@NotNull
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "仕切価格", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal partitionPrice;

	/**
	 * 見積明細
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "estimation_detail_id", referencedColumnName = "id")
	@Schema(description = "見積明細", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonIgnore
	private EstimationDetail estimationDetail;

	/**
	 * Ｒ原価
	 */
	@Column
	@Schema(description = "Ｒ原価", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private BigDecimal rCost;

	/**
	 * ＳＡ仕切価格
	 */
	@Column
	@Schema(description = "ＳＡ仕切価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private BigDecimal rjPurchasePrice;

	/**
	 * ＲＪ仕切価格
	 */
	@Column
	@Schema(description = "ＲＪ仕切価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private BigDecimal rjDividingPrice;

	/**
	 * 母店売価(接点店仕切)
	 */
	@Column
	@Schema(description = "母店売価(接点店仕切)", required = false)
	private BigDecimal motherStorePrice;

	/**
	 * 標準価格
	 */
	@Column
	@Schema(description = "標準価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private BigDecimal standardPrice;

	/**
	 * メーカー商品コード
	 */
	@Size(max = 255)
	@Schema(description = "メーカー商品コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String makerItemCode;

	/**
	 * ＲＪ販事本仕入価格
	 */
	@Column
	@Schema(description = "ＲＪ販事本仕入価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private BigDecimal rjHanjihonPurchasePrice;

	/**
	 * 価格改定日マスタID
	 */
	@Schema(description = "価格改定日マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long priceRevisionDateMasterId;

	@PrePersist
	public void prePersist() {
		super.setCreatedAt(new Date());
	}
}
