package jp.co.ricoh.cotos.commonlib.entity.accounting;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "accounting")
public class Accounting extends EntityBase {

	/** 計上ID */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounting_seq")
	@SequenceGenerator(name = "accounting_seq", sequenceName = "accounting_seq", allocationSize = 1)
	@Schema(description = "計上ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/** RJ管理番号 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/** 契約ID */
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long contractId;

	/** 契約明細ID */
	@Schema(description = "契約明細ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long contractDetailId;

	/** 取引年月日 */
	@Size(max = 255)
	@Schema(description = "取引年月日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String transactionDate;

	/** 締日 */
	@Size(max = 255)
	@Schema(description = "締日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String closingDate;

	/** 商流区分 */
	@Size(max = 255)
	@Schema(description = "商流区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dealerFlow;

	/** 費用種別 */
	@Schema(description = "費用種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\")", example = "1")
	private CostType costType;

	/** 品種区分 */
	@Schema(description = "品種区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", example = "1")
	private ItemType itemType;

	/** 請求年月 */
	@Size(max = 255)
	@Schema(description = "請求年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String billingDate;

	/** サービス期間開始日 */
	@Size(max = 255)
	@Schema(description = "サービス期間開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String srvStartDate;

	/** サービス期間終了日 */
	@Size(max = 255)
	@Schema(description = "サービス期間終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String srvEndDate;

	/** 注文番号 */
	@Size(max = 255)
	@Schema(description = "注文番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String webOrderNo;

	/** 品種コード */
	@Size(max = 255)
	@Schema(description = "品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productTypeCd;

	/** 品種名 */
	@Size(max = 255)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productTypeName;

	/** FFM計上処理フラグ */
	@Max(9)
	@Schema(description = "FFM計上処理フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer ffmFlg;

	/** CUBIC計上処理フラグ */
	@Max(9)
	@Schema(description = "CUBIC計上処理フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer cubicFlg;

	/** データ作成日 */
	@Size(max = 255)
	@Schema(description = "データ作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDataCreateDate;

	/** データ作成時間 */
	@Size(max = 255)
	@Schema(description = "データ作成時間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDataCreateTime;

	/** FFM会社コード */
	@Size(max = 255)
	@Schema(description = "FFM会社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmCompanyCd;

	/** 契約種類区分 */
	@Size(max = 255)
	@Schema(description = "契約種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmContractTypeKbn;

	/** 作成データパターン */
	@Size(max = 255)
	@Schema(description = "作成データパターン", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDataPtn;

	/** 勘定識別 */
	@Size(max = 255)
	@Schema(description = "勘定識別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmAccountType;

	/** データ種別 */
	@Size(max = 255)
	@Schema(description = "データ種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDataType;

	/** 赤黒区分 */
	@Size(max = 255)
	@Schema(description = "赤黒区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRedBlackType;

	/** 債権債務照合キー */
	@Size(max = 255)
	@Schema(description = "債権債務照合キー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmMatchingKey;

	/** NSPユニークキー */
	@Size(max = 255)
	@Schema(description = "NSPユニークキー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmNspKey;

	/** 案件番号 */
	@Size(max = 255)
	@Schema(description = "案件番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmProjectNo;

	/** 契約書番号 */
	@Size(max = 255)
	@Schema(description = "契約書番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmContractDocNo;

	/** 契約番号 */
	@Size(max = 255)
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmContractNo;

	/** 契約明細番号 */
	@Size(max = 255)
	@Schema(description = "契約明細番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmContractDetailNo;

	/** 請求明細番号 */
	@Size(max = 255)
	@Schema(description = "請求明細番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmBillingDetailNo;

	/** お問合せ番号 */
	@Size(max = 255)
	@Schema(description = "お問合せ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmInqNo;

	/** お問合せ明細番号 */
	@Size(max = 255)
	@Schema(description = "お問合せ明細番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmInqDetailNo;

	/** 手配時の案件番号 */
	@Size(max = 255)
	@Schema(description = "手配時の案件番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmArrProjectNo;

	/** 手配時の問合せ番号 */
	@Size(max = 255)
	@Schema(description = "手配時の問合せ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmArrInqNo;

	/** 赤伝理由 */
	@Size(max = 255)
	@Schema(description = "赤伝理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmCancelReason;

	/** 元契約番号 */
	@Size(max = 255)
	@Schema(description = "元契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmOrgContractCd;

	/** 元請求明細番号 */
	@Size(max = 255)
	@Schema(description = "元請求明細番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmOrgContractDetailNo;

	/** 請求条件 */
	@Size(max = 255)
	@Schema(description = "請求条件", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmBillingCondition;

	/** 請求分割回数 */
	@Max(99)
	@Schema(description = "請求分割回数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99]")
	private Integer ffmTotalBillingCount;

	/** 契約締結日 */
	@Size(max = 255)
	@Schema(description = "契約締結日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmContractDate;

	/** 契約期間（開始） */
	@Size(max = 255)
	@Schema(description = "契約期間（開始）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmContractPeriodStart;

	/** 契約期間（終了） */
	@Size(max = 255)
	@Schema(description = "契約期間（終了）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmContractPeriodEnd;

	/** 契約ＳＳコード */
	@Size(max = 255)
	@Schema(description = "契約ＳＳコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmContractSscd;

	/** 契約ＳＳ社員コード */
	@Size(max = 255)
	@Schema(description = "契約ＳＳ社員コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmContractSspiccd;

	/** 振替先課所コード */
	@Size(max = 255)
	@Schema(description = "振替先課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmTrnsLocationCd;

	/** 振替先社員コード */
	@Size(max = 255)
	@Schema(description = "振替先社員コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmTrnsPicCd;

	/** 保守契約／リース/レンタルＮｏ */
	@Size(max = 255)
	@Schema(description = "保守契約／リース/レンタルＮｏ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmMntLeaseNo;

	/** 契約金額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "契約金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmContractPrice;

	/** 仕切前計上金額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "仕切前計上金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmPriceBeforeInvoice;

	/** 仕切前消費税額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "仕切前消費税額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmTaxPriceBeforeInvoice;

	/** 商品コード */
	@Size(max = 255)
	@Schema(description = "商品コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmProdactCd;

	/** 機種略号 */
	@Size(max = 255)
	@Schema(description = "機種略号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmModelId;

	/** 機番 */
	@Size(max = 255)
	@Schema(description = "機番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmSerialId;

	/** 見積時の入力商品名 */
	@Size(max = 255)
	@Schema(description = "見積時の入力商品名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmQuotationProdactName;

	/** 原価計上商品コード */
	@Size(max = 255)
	@Schema(description = "原価計上商品コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmCostProdactName;

	/** 仕入区分 */
	@Size(max = 255)
	@Schema(description = "仕入区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmPurchaseType;

	/** 仕入値引区分 */
	@Size(max = 255)
	@Schema(description = "仕入値引区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmPurchaseDiscntType;

	/** 仕入購買区分 */
	@Size(max = 255)
	@Schema(description = "仕入購買区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmPurchaseClassType;

	/** 仕入取引日 */
	@Size(max = 255)
	@Schema(description = "仕入取引日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmPurchaseDate;

	/** 他社商品区分 */
	@Size(max = 255)
	@Column(name = "ffm_non_r_item_cd")
	@Schema(description = "他社商品区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmNonRItemCd;

	/** 仕入取引先コード */
	@Size(max = 255)
	@Schema(description = "仕入取引先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmSupplierCd;

	/** 仕入課所設定区分 */
	@Size(max = 255)
	@Schema(description = "仕入課所設定区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDeptAssortType;

	/** 仕入課所コード */
	@Size(max = 255)
	@Schema(description = "仕入課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmPurchaseLocationCd;

	/** 仕入責任得意先コード */
	@Size(max = 255)
	@Schema(description = "仕入責任得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmPurchaseRespClientCd;

	/** 在庫区コード */
	@Size(max = 255)
	@Schema(description = "在庫区コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRdStrctInventoryCd;

	/** 特価番号 */
	@Size(max = 255)
	@Schema(description = "特価番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDealsNo;

	/** 仕入先請求ＮＯ */
	@Size(max = 255)
	@Schema(description = "仕入先請求ＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmSupplierBillingNo;

	/** 商品名（支払通知書用） */
	@Size(max = 255)
	@Schema(description = "商品名（支払通知書用）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmPaymentProdactName;

	/** 売上区分 */
	@Size(max = 255)
	@Schema(description = "売上区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmSalesType;

	/** 売上値引区分 */
	@Size(max = 255)
	@Schema(description = "売上値引区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmSalesDiscountType;

	/** 売上取引日（納品日） */
	@Size(max = 255)
	@Schema(description = "売上取引日（納品日）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmSalesTradeDate;

	/** 得意先コード */
	@Size(max = 255)
	@Schema(description = "得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmClientCd;

	/** 売上課所設定区分 */
	@Size(max = 255)
	@Schema(description = "売上課所設定区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmSalesLocationType;

	/** 売上課所コード */
	@Size(max = 255)
	@Schema(description = "売上課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmSalesLocationCd;

	/** 売上社員設定区分 */
	@Size(max = 255)
	@Schema(description = "売上社員設定区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmSalesEmpType;

	/** 売上社員コード */
	@Size(max = 255)
	@Schema(description = "売上社員コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmSalesEmpCd;

	/** 値引番号 */
	@Size(max = 255)
	@Schema(description = "値引番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDiscntNo;

	/** 伝票番号 */
	@Size(max = 255)
	@Schema(description = "伝票番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmSlipNo;

	/** 契約区分 */
	@Size(max = 255)
	@Schema(description = "契約区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmContractType;

	/** 売上原価金額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "売上原価金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRevenueCostprice;

	/** 振替先振替金額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "振替先振替金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmTrnsPrice;

	/** 代直区分（販売店データリンク・売上用） */
	@Size(max = 255)
	@Schema(description = "代直区分（販売店データリンク・売上用）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDistType;

	/** 売上数量 */
	@Max(9999999999L)
	@Schema(description = "売上数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999999999]")
	private Long ffmUserSalesCnt;

	/** ユーザ売上単価 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "ユーザ売上単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmUserSalesPrice;

	/** ユーザ売上単価（税込） */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "ユーザ売上単価（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmUserSalesPriceInTax;

	/** ユーザ売上金額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "ユーザ売上金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmUserSalesAmt;

	/** ユーザ売上金額（税込） */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "ユーザ売上金額（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmUserSalesAmtInTax;

	/** ユーザ売上消費税区分 */
	@Size(max = 255)
	@Schema(description = "ユーザ売上消費税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmUserSalesTaxType;

	/** ユーザ売上消費税率区分 */
	@Size(max = 255)
	@Schema(description = "ユーザ売上消費税率区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmUserSalesTaxRate;

	/** ユーザ売上消費税額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "ユーザ売上消費税額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmUserSalesTaxPrice;

	/** RJ売上数量 */
	@Max(9999999999L)
	@Schema(description = "RJ売上数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999999999]")
	private Long ffmRjSalesCnt;

	/** RJ売上単価 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "RJ売上単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRjSalesPrice;

	/** RJ売上単価（税込） */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "RJ売上単価（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRjSalesPriceInTax;

	/** RJ売上金額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "RJ売上金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRjSalesAmt;

	/** RJ売上金額（税込） */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "RJ売上金額（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRjSalesAmtInTax;

	/** RJ売上消費税区分 */
	@Size(max = 255)
	@Schema(description = "RJ売上消費税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRjSalesTaxType;

	/** RJ売上消費税率区分 */
	@Size(max = 255)
	@Schema(description = "RJ売上消費税率区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRjSalesTaxRate;

	/** RJ売上消費税額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "RJ売上消費税額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRjSalesTaxPrice;

	/** RJ仕入数量 */
	@Max(9999999999L)
	@Schema(description = "RJ仕入数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999999999]")
	private Long ffmRjPurchaseCnt;

	/** RJ仕入単価 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "RJ仕入単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRjPurchasePrice;

	/** RJ仕入単価（税込） */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "RJ仕入単価（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRjPurchasePriceInTax;

	/** RJ仕入金額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "RJ仕入金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRjPurchaseAmt;

	/** RJ仕入金額（税込） */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "RJ仕入金額（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRjPurchaseAmtInTax;

	/** RJ仕入消費税区分 */
	@Size(max = 255)
	@Schema(description = "RJ仕入消費税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRjPurchaseTaxType;

	/** RJ仕入消費税率区分 */
	@Size(max = 255)
	@Schema(description = "RJ仕入消費税率区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRjPurchaseTaxRate;

	/** RJ仕入消費税額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "RJ仕入消費税額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRjPurchaseTaxPrice;

	/** 販売店売上数量 */
	@Max(9999999999L)
	@Schema(description = "販売店売上数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999999999]")
	private Long ffmShopSalesCnt;

	/** 販売店売上単価 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "販売店売上単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmShopSalesPrice;

	/** 販売店売上単価（税込） */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "販売店売上単価（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmShopSalesPriceInTax;

	/** 販売店売上金額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "販売店売上金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmShopSalesAmt;

	/** 販売店売上金額（税込） */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "販売店売上金額（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmShopSalesAmtInTax;

	/** 販売店売上消費税区分 */
	@Size(max = 255)
	@Schema(description = "販売店売上消費税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmShopSalesTaxType;

	/** 販売店売上消費税率区分 */
	@Size(max = 255)
	@Schema(description = "販売店売上消費税率区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmShopSalesTaxRate;

	/** 販売店売上消費税額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "販売店売上消費税額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmShopSalesTaxPrice;

	/** R原価数量 */
	@Column(name = "ffm_r_cost_cnt")
	@Max(9999999999L)
	@Schema(description = "R原価数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999999999]")
	private Long ffmRCostCnt;

	/** R原価単価 */
	@Column(name = "ffm_r_cost_price")
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "R原価単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRCostPrice;

	/** R原価単価（税込） */
	@Column(name = "ffm_r_cost_price_in_tax")
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "R原価単価（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRCostPriceInTax;

	/** R原価金額 */
	@Column(name = "ffm_r_cost_amt")
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "R原価金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRCostAmt;

	/** R原価金額（税込） */
	@Column(name = "ffm_r_cost_amt_in_tax")
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "R原価金額（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRCostAmtInTax;

	/** R原価消費税区分 */
	@Column(name = "ffm_r_cost_tax_type")
	@Size(max = 255)
	@Schema(description = "R原価消費税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRCostTaxType;

	/** R原価消費税率区分 */
	@Column(name = "ffm_r_cost_tax_rate")
	@Size(max = 255)
	@Schema(description = "R原価消費税率区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRCostTaxRate;

	/** R原価消費税額 */
	@Column(name = "ffm_r_cost_tax_price")
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "R原価消費税額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmRCostTaxPrice;

	/** 手数料数量 */
	@Max(99999999999L)
	@Schema(description = "手数料数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999999999]")
	private Long ffmCommissionCnt;

	/** 手数料単価 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "手数料単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmCommissionPrice;

	/** 手数料単価（税込） */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "手数料単価（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmCommissionPriceInTax;

	/** 手数料金額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "手数料金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmCommissionAmt;

	/** 手数料金額（税込） */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "手数料金額（税込）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmCommissionAmtInTax;

	/** 手数料消費税区分 */
	@Size(max = 255)
	@Schema(description = "手数料消費税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmCommissionTaxType;

	/** 手数料消費税率区分 */
	@Size(max = 255)
	@Schema(description = "手数料消費税率区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmCommissionTaxRate;

	/** 手数料消費税額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "手数料消費税額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmCommissionTaxPrice;

	/** 請求書明細識別コード */
	@Size(max = 255)
	@Schema(description = "請求書明細識別コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmBillDetailCd;

	/** 納品書要否区分 */
	@Size(max = 255)
	@Schema(description = "納品書要否区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmBillOutputFlg;

	/** 納品書出力パターン */
	@Size(max = 255)
	@Schema(description = "納品書出力パターン", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmBillOutputPtn;

	/** 納品書出力形式 */
	@Size(max = 255)
	@Schema(description = "納品書出力形式", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmBillOutputFmt;

	/** 請求書発行システム */
	@Size(max = 255)
	@Schema(description = "請求書発行システム", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmBillOutputSystem;

	/** 商品名パターン番号（納品書・請求書用） */
	@Size(max = 255)
	@Schema(description = "商品名パターン番号（納品書・請求書用）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmProdactPtnNo;

	/** 商品名（納品書・請求書用） */
	@Size(max = 255)
	@Schema(description = "商品名（納品書・請求書用）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmProdactNameForBill;

	/** 業務への連絡事項 */
	@Size(max = 255)
	@Schema(description = "業務への連絡事項", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmMessageForBiz;

	/** 備考（納品書・請求書用） */
	@Size(max = 255)
	@Schema(description = "備考（納品書・請求書用）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRemarkForBill;

	/** 請求期間（開始） */
	@Column(name = "ffm_r_billing_period_start")
	@Size(max = 255)
	@Schema(description = "請求期間（開始）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRBillingPeriodStart;

	/** 請求期間（終了） */
	@Column(name = "ffm_r_billing_period_end")
	@Size(max = 255)
	@Schema(description = "請求期間（終了）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRBillingPeriodEnd;

	/** 請求月 */
	@Size(max = 255)
	@Schema(description = "請求月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmBillingYm;

	/** 今回の請求回数 */
	@Max(99999)
	@Schema(description = "今回の請求回数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer ffmThisBillingCnt;

	/** カウンター */
	@Size(max = 255)
	@Schema(description = "カウンター", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmCounter;

	/** コメント１ */
	@Size(max = 255)
	@Schema(description = "コメント１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmOutputComment1;

	/** コメント２ */
	@Size(max = 255)
	@Schema(description = "コメント２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmOutputComment2;

	/** 強制フラグ */
	@Max(9)
	@Schema(description = "強制フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer ffmForcedFlg;

	/** 機器設置先名 */
	@Size(max = 255)
	@Schema(description = "機器設置先名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmInstalltionName;

	/** 機器設置先部課名 */
	@Size(max = 255)
	@Schema(description = "機器設置先部課名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmInstalltionDptName;

	/** RINGS届先コード(3桁） */
	@Size(max = 255)
	@Schema(description = "RINGS届先コード(3桁）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmRingsDstCd;

	/** OE届先コード(11桁） */
	@Size(max = 255)
	@Schema(description = "OE届先コード(11桁）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmOeDstCd;

	/** 納品場所識別 */
	@Size(max = 255)
	@Schema(description = "納品場所識別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstType;

	/** 届先名１（会社名） */
	@Size(max = 255)
	@Schema(description = "届先名１（会社名）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstName1;

	/** 届先名２（会社部課名） */
	@Size(max = 255)
	@Schema(description = "届先名２（会社部課名）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstName2;

	/** 顧客名 */
	@Size(max = 255)
	@Schema(description = "顧客名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstClientName;

	/** 届先住所１ */
	@Size(max = 255)
	@Schema(description = "届先住所１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstAddr1;

	/** 届先住所２ */
	@Size(max = 255)
	@Schema(description = "届先住所２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstAddr2;

	/** 届先住所３ */
	@Size(max = 255)
	@Schema(description = "届先住所３", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstAddr3;

	/** 届先郵便番号 */
	@Size(max = 255)
	@Schema(description = "届先郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstZipCd;

	/** 届先電話番号 */
	@Size(max = 255)
	@Schema(description = "届先電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstTel;

	/** 届先ＦＡＸ番号 */
	@Size(max = 255)
	@Schema(description = "届先ＦＡＸ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstFax;

	/** 届先名（カナ） */
	@Size(max = 255)
	@Schema(description = "届先名（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstNameKana;

	/** 得意先コード（二次店） */
	@Size(max = 255)
	@Schema(description = "得意先コード（二次店）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmClientCdSec;

	/** 届先コード（二次店） */
	@Size(max = 255)
	@Schema(description = "届先コード（二次店）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmDstCdSec;

	/** 支払利息相当額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "支払利息相当額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmInterestExpensePrice;

	/** 受取利息相当額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "受取利息相当額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal ffmInterestIncomePrice;

	/** 見積番号 */
	@Size(max = 255)
	@Schema(description = "見積番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmQuotationCd;

	/** 見積明細番号 */
	@Size(max = 255)
	@Schema(description = "見積明細番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmQuotationDetailCd;

	/** 本体見積明細番号 */
	@Size(max = 255)
	@Schema(description = "本体見積明細番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ffmMainQuotationDetailCd;

	/** R請求書摘要 */
	@Size(max = 255)
	@Schema(description = "R請求書摘要", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String chgBillingText;

	/** 一次店R会社コード */
	@Column(name = "chg_r_company_code_1st")
	@Size(max = 255)
	@Schema(description = "一次店R会社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String chgRCompanyCode1st;

	/** 一次店R会社名 */
	@Column(name = "chg_r_company_name_1st")
	@Size(max = 255)
	@Schema(description = "一次店R会社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String chgRCompanyName1st;

	/** 一次店販売店ID */
	@Column(name = "chg_shop_id_1st")
	@Size(max = 255)
	@Schema(description = "一次店販売店ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String chgShopId1st;

	/** 一次店販売店名 */
	@Column(name = "chg_shop_name_1st")
	@Size(max = 255)
	@Schema(description = "一次店販売店名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String chgShopName1st;

	/** 一次店販売店摘要 */
	@Column(name = "chg_shop_text_1st")
	@Size(max = 255)
	@Schema(description = "一次店販売店摘要", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String chgShopText1st;

	/** 二次店R会社コード */
	@Column(name = "chg_r_company_code_2st")
	@Size(max = 255)
	@Schema(description = "二次店R会社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String chgRCompanyCode2st;

	/** 二次店R会社名 */
	@Column(name = "chg_r_company_name_2st")
	@Size(max = 255)
	@Schema(description = "二次店R会社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String chgRCompanyName2st;

	/** 二次店販売店ID */
	@Column(name = "chg_shop_id_2st")
	@Size(max = 255)
	@Schema(description = "二次店販売店ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String chgShopId2st;

	/** 二次店販売店名 */
	@Column(name = "chg_shop_name_2st")
	@Size(max = 255)
	@Schema(description = "二次店販売店名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String chgShopName2st;

	/** 二次店販売店摘要 */
	@Column(name = "chg_shop_text_2st")
	@Size(max = 255)
	@Schema(description = "二次店販売店摘要", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String chgShopText2st;

	/** フォーマット種別 */
	@Size(max = 255)
	@Schema(description = "フォーマット種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicFmtType;

	/** 勘定科目コード */
	@Size(max = 255)
	@Schema(description = "勘定科目コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicAccountingCd;

	/** 貸借区分 */
	@Size(max = 255)
	@Schema(description = "貸借区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicLcType;

	/** システムコード */
	@Size(max = 255)
	@Schema(description = "システムコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicSystemCd;

	/** CUBIC会社コード */
	@Size(max = 255)
	@Schema(description = "CUBIC会社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicCompanyCd;

	/** 会計計上日 */
	@Size(max = 255)
	@Schema(description = "会計計上日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicAccountingDate;

	/** 伝票ＮＯ */
	@Size(max = 255)
	@Schema(description = "伝票ＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicVoucherDate;

	/** 伝票明細NO */
	@Size(max = 255)
	@Schema(description = "伝票明細NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicVoucherDetailDate;

	/** 計上部門コード */
	@Size(max = 255)
	@Schema(description = "計上部門コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicAccountDeptCd;

	/** 商品軸 */
	@Size(max = 255)
	@Schema(description = "商品軸", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicProductAxis;

	/** 営業軸 */
	@Size(max = 255)
	@Schema(description = "営業軸", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicSalesAxis;

	/** 決算識別子 */
	@Size(max = 255)
	@Schema(description = "決算識別子", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicFinancialIdentifier;

	/** CUBIC品種コード */
	@Size(max = 255)
	@Schema(description = "CUBIC品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicProductTypeCd;

	/** 増減理由 */
	@Size(max = 255)
	@Schema(description = "増減理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicInDecReason;

	/** 環境会計コード */
	@Size(max = 255)
	@Schema(description = "環境会計コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicEnvAccountCd;

	/** プロジェクトコード */
	@Size(max = 255)
	@Schema(description = "プロジェクトコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicProjectCd;

	/** 数量 */
	@Max(99999999999L)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999999999]")
	private Long cubicCount;

	/** 取引金額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "取引金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal cubicAmount;

	/** 外貨取引金額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "外貨取引金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal cubicAmountForForeign;

	/** 通貨コード */
	@Size(max = 255)
	@Schema(description = "通貨コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicCurrencyCd;

	/** 通貨換算タイプ */
	@Size(max = 255)
	@Schema(description = "通貨換算タイプ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicCurrencyConvType;

	/** 通貨換算レート */
	@DecimalMax("99999.99")
	@Schema(description = "通貨換算レート", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999.99]")
	private BigDecimal cubicCurrencyConvRate;

	/** 通貨換算日 */
	@Size(max = 255)
	@Schema(description = "通貨換算日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicCurrencyConvDate;

	/** 摘要 */
	@Size(max = 255)
	@Schema(description = "摘要", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicText;

	/** 明細摘要 */
	@Size(max = 255)
	@Schema(description = "明細摘要", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicTextDetail;

	/** 各社セグメント */
	@Size(max = 255)
	@Schema(description = "各社セグメント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicCoSegment;

	/** 管理セグメント予備2 */
	@Size(max = 255)
	@Schema(description = "管理セグメント予備2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicCoSegment1;

	/** 管理セグメント予備3 */
	@Size(max = 255)
	@Schema(description = "管理セグメント予備3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicCoSegment2;

	/** 消し込みキー */
	@Size(max = 255)
	@Schema(description = "消し込みキー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicDeleteKey;

	/** 扱い者コード */
	@Size(max = 255)
	@Schema(description = "扱い者コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicOperatorCd;

	/** グリーン購買コード */
	@Size(max = 255)
	@Schema(description = "グリーン購買コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicGreenBuyCd;

	/** 案件ＮＯ */
	@Size(max = 255)
	@Schema(description = "案件ＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicProjectNo;

	/** Ｄ／Ｆ ＮＯ */
	@Size(max = 255)
	@Schema(description = "Ｄ／Ｆ　ＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicDfNo;

	/** 予算ＮＯ */
	@Size(max = 255)
	@Schema(description = "予算ＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicBudgetNo;

	/** 顧客コード */
	@Size(max = 255)
	@Schema(description = "顧客コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicClientCd;

	/** 各社固有管理セグメント1 */
	@Size(max = 255)
	@Schema(description = "各社固有管理セグメント1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicCoMgtSegment;

	/** 取引日 */
	@Size(max = 255)
	@Schema(description = "取引日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicTransactionDate;

	/** 請求先サイトコード */
	@Size(max = 255)
	@Schema(description = "請求先サイトコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicBillDstSiteCd;

	/** 国内／海外区分 */
	@Size(max = 255)
	@Schema(description = "国内／海外区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicDomesticForeignType;

	/** 取引単価（税抜） */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "取引単価（税抜）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal cubicSalesPriceNoTax;

	/** 外貨取引単価 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "外貨取引単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal cubicSalesPriceForeign;

	/** 回収条件名 */
	@Size(max = 255)
	@Schema(description = "回収条件名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicRecoveryReqName;

	/** 回収方法名 */
	@Size(max = 255)
	@Schema(description = "回収方法名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicRecoveryMethodName;

	/** 回収起算日 */
	@Size(max = 255)
	@Schema(description = "回収起算日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicRecoveryDate;

	/** 請求分類名 */
	@Size(max = 255)
	@Schema(description = "請求分類名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicBillingTypeName;

	/** CUBIC請求書明細識別コード */
	@Size(max = 255)
	@Schema(description = "CUBIC請求書明細識別コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicBillDetailTypeCode;

	/** 値引名称 */
	@Size(max = 255)
	@Schema(description = "値引名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicDiscountName;

	/** 請求書発行区分 */
	@Size(max = 255)
	@Schema(description = "請求書発行区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicBillOutputType;

	/** 請求書ＮＯ */
	@Size(max = 255)
	@Schema(description = "請求書ＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicBillingNo;

	/** 荷為替手形ＮＯ */
	@Size(max = 255)
	@Schema(description = "荷為替手形ＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicDocumentaryBillNo;

	/** Ｐ／ＣキーＮＯ */
	@Size(max = 255)
	@Schema(description = "Ｐ／ＣキーＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicPcKeyNo;

	/** 請求書出力用伝票ＮＯ */
	@Size(max = 255)
	@Schema(description = "請求書出力用伝票ＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicBillingOutputNo;

	/** 受注ＮＯ */
	@Size(max = 255)
	@Schema(description = "受注ＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicReceivedOrderNo;

	/** 発注ＮＯ */
	@Size(max = 255)
	@Schema(description = "発注ＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicOrderNo;

	/** 前受管理ＮＯ */
	@Size(max = 255)
	@Schema(description = "前受管理ＮＯ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicBeforeManageNo;

	/** 前受金消込額 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "前受金消込額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal cubicBeforeCancelAmt;

	/** 追加ＴＥＲＭ */
	@Size(max = 255)
	@Schema(description = "追加ＴＥＲＭ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicAddTerm;

	/** 契約NO */
	@Size(max = 255)
	@Schema(description = "契約NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicContractNo;

	/** 品名 */
	@Size(max = 255)
	@Schema(description = "品名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicProdactName;

	/** 債権債務汎用照合キー */
	@Size(max = 255)
	@Schema(description = "債権債務汎用照合キー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicMatchingKey;

	/** 汎用転送データ */
	@Size(max = 255)
	@Schema(description = "汎用転送データ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicGeneralTransferData;

	/** 元伝票ＮＯ（赤伝時） */
	@Size(max = 255)
	@Schema(description = "元伝票ＮＯ（赤伝時）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicOrgSlipNoForRed;

	/** 元伝票明細ＮＯ（赤伝時） */
	@Size(max = 255)
	@Schema(description = "元伝票明細ＮＯ（赤伝時）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicOrgSlipDetailNoForRed;

	/** 元会計計上日（赤伝時） */
	@Size(max = 255)
	@Schema(description = "元会計計上日（赤伝時）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicOrgAcctDateForRed;

	/** 設置先サイトコード */
	@Size(max = 255)
	@Schema(description = "設置先サイトコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicDstSiteCd;

	/** 項目予備1 */
	@Size(max = 255)
	@Schema(description = "項目予備1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicItem1;

	/** 項目予備2 */
	@Size(max = 255)
	@Schema(description = "項目予備2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicItem2;

	/** 項目予備3 */
	@Size(max = 255)
	@Schema(description = "項目予備3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicItem3;

	/** 拡張項目 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendItem;

	/** 計上日制御用商品種類区分 */
	@Size(max = 255)
	@Schema(description = "計上日制御用商品種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String accountingProductClassDiv;

}
