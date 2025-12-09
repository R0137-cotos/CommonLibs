package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemDecomposeType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品種を表すEntity
 */

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "item_contract")
public class ItemContract extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_contract_seq")
	@SequenceGenerator(name = "item_contract_seq", sequenceName = "item_contract_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 品種名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String itemContractName;

	/**
	 * リコー品種コード
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "品種区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", example = "1")
	private ItemType itemType;

	/**
	 * 費用種別
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "費用種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\")", example = "1")
	private CostType costType;

	/**
	 * 仕切価格
	 */
	@Column(nullable = false)
	@NotNull
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "仕切価格", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal partitionPrice;

	/**
	 * 契約明細
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "contract_detail_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約明細", requiredMode = Schema.RequiredMode.REQUIRED)
	private ContractDetail contractDetail;

	/**
	 * 仕入取引先コード
	 */
	@Size(max = 255)
	@Schema(description = "仕入取引先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String bpCd;

	/**
	 * Ｒ原価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "Ｒ原価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rCost;

	/**
	 * ＳＡ仕切価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "ＳＡ仕切価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjPurchasePrice;

	/**
	 * ＲＪ仕切価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "ＲＪ仕切価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjDividingPrice;

	/**
	 * 母店売価(接点店仕切)
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "母店売価(接点店仕切)", allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal motherStorePrice;

	/**
	 * 消費税区分
	 */
	@Size(max = 255)
	@Schema(description = "消費税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String taxFlag;

	/**
	 * IFS連携フラグ
	 */
	@Schema(description = "IFS連携フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9]")
	private Integer ifsLinkageFlg;

	/**
	 * 品種明細(契約用)
	 */
	@Valid
	@OneToMany(mappedBy = "itemContract")
	@Schema(description = "品種明細(契約用)")
	private List<ItemDetailContract> itemDetailContractList;

	/**
	 * メーカー商品コード
	 */
	@Size(max = 255)
	@Schema(description = "メーカー商品コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String makerItemCode;

	/**
	 * 分解後品種区分
	 */
	@Schema(description = "分解後品種区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "通常(\"1\"), 分解前(\"2\"), 分解後(\"3\")")
	private ItemDecomposeType itemDecomposeType;

	/**
	 * 分解後品種名
	 */
	@Size(max = 255)
	@Schema(description = "分解後品種名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemDecomposeName;

	/**
	 * 分解後原価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "分解後原価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal itemDecomposePrice;

	/**
	 * 契約機種品種紐づけ
	 */
	@Valid
	@OneToMany(mappedBy = "itemContract")
	@Schema(description = "契約機種品種紐づけ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, readOnly = true)
	private List<ContractEquipmentItemLink> contractEquipmentItemLinkList;

	/**
	 * 契約機種品種紐づけ_自動更新
	 */
	@Valid
	@OneToMany(mappedBy = "autoUpdateItemContract")
	@Schema(description = "契約機種品種紐づけ_自動更新", requiredMode = Schema.RequiredMode.NOT_REQUIRED, readOnly = true)
	private List<ContractEquipmentItemLink> contractEquipmentItemAutoUpdateLinkList;

	/**
	 * ＲＪ販事本仕入価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "ＲＪ販事本仕入価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjHanjihonPurchasePrice;

	/**
	 * 標準価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "標準価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal standardPrice;

	/**
	 * 価格改定日マスタID
	 */
	@Schema(description = "価格改定日マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long priceRevisionDateMasterId;

	/**
	 * 年額明細計上フラグ
	 */
	@Schema(description = "年額明細計上フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer yearDetailAccountFlg;

	/**
	 * 価格改定処理グループID
	 */
	@Schema(description = "価格改定処理グループID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long priceRevisionProcessGrpId;
}
