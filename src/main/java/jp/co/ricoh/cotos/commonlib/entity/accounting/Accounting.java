package jp.co.ricoh.cotos.commonlib.entity.accounting;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
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

	/**計上ID*/
	@ApiModelProperty(value = "計上ID", required = true, position = 1)
	private long id;

	/**RJ管理番号*/
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 2)
	private String rjManageNumber;

	/**契約ID*/
	@ApiModelProperty(value = "契約ID", required = false, position = 3)
	private long contractId;

	/**契約明細ID*/
	@ApiModelProperty(value = "契約明細ID", required = false, position = 4)
	private long contractDetailId;

	/**取引年月日*/
	@ApiModelProperty(value = "取引年月日", required = false, position = 5)
	private Date transactionDate;

	/**締日*/
	@ApiModelProperty(value = "締日", required = false, position = 6)
	private Date closingDate;

	/**商流区分*/
	@ApiModelProperty(value = "商流区分", required = false, position = 7)
	private String dealerFlow;

	/**費用種別*/
	@ApiModelProperty(value = "費用種別", required = false, position = 8)
	private CostType costType;

	/**品種区分*/
	@ApiModelProperty(value = "品種区分", required = false, position = 9)
	private ItemType itemType;

	/**請求年月*/
	@ApiModelProperty(value = "請求年月", required = false, position = 10)
	private Date billingDate;

	/**サービス期間開始日*/
	@ApiModelProperty(value = "サービス期間開始日", required = false, position = 11)
	private Date srvStartDate;

	/**サービス期間終了日*/
	@ApiModelProperty(value = "サービス期間終了日", required = false, position = 12)
	private Date srvEndDate;

	/**注文番号*/
	@ApiModelProperty(value = "注文番号", required = false, position = 13)
	private String webOrderNo;

	/**品種コード*/
	@ApiModelProperty(value = "品種コード", required = false, position = 14)
	private String productTypeCd;

	/**品種名*/
	@ApiModelProperty(value = "品種名", required = false, position = 15)
	private String productTypeName;

	/**FFM計上処理フラグ*/
	@ApiModelProperty(value = "FFM計上処理フラグ", required = false, position = 16)
	private int ffmFlag;

	/**CUBIC計上処理フラグ*/
	@ApiModelProperty(value = "CUBIC計上処理フラグ", required = false, position = 17)
	private int cubicFlag;

	/**データ作成日*/
	@ApiModelProperty(value = "データ作成日", required = false, position = 18)
	private String ffmDataCreateDate;

	/**データ作成時間*/
	@ApiModelProperty(value = "データ作成時間", required = false, position = 19)
	private Date ffmDataCreateTime;

	/**FFM会社コード*/
	@ApiModelProperty(value = "FFM会社コード", required = false, position = 20)
	private String ffmCompanyCd;

	/**契約種類区分*/
	@ApiModelProperty(value = "契約種類区分", required = false, position = 21)
	private String ffmContractTypeKbn;

	/**作成データパターン*/
	@ApiModelProperty(value = "作成データパターン", required = false, position = 22)
	private String ffmDataPtn;

	/**勘定識別*/
	@ApiModelProperty(value = "勘定識別", required = false, position = 23)
	private String ffmAccountType;

	/**データ種別*/
	@ApiModelProperty(value = "データ種別", required = false, position = 24)
	private String ffmDataType;

	/**赤黒区分*/
	@ApiModelProperty(value = "赤黒区分", required = false, position = 25)
	private String ffmRedBlackType;

	/**債権債務照合キー*/
	@ApiModelProperty(value = "債権債務照合キー", required = false, position = 26)
	private String ffmMatchingKey;

	/**NSPユニークキー*/
	@ApiModelProperty(value = "NSPユニークキー", required = false, position = 27)
	private String ffmNspKey;

	/**案件番号*/
	@ApiModelProperty(value = "案件番号", required = false, position = 28)
	private String ffmProjectNo;

	/**契約書番号*/
	@ApiModelProperty(value = "契約書番号", required = false, position = 29)
	private String ffmContractDocNo;

	/**契約番号*/
	@ApiModelProperty(value = "契約番号", required = false, position = 30)
	private String ffmContractNo;

	/**契約明細番号*/
	@ApiModelProperty(value = "契約明細番号", required = false, position = 31)
	private String ffmContractDetailNo;

	/**請求明細番号*/
	@ApiModelProperty(value = "請求明細番号", required = false, position = 32)
	private String ffmBillingDetailNo;

	/**お問合せ番号*/
	@ApiModelProperty(value = "お問合せ番号", required = false, position = 33)
	private String ffmInqNo;

	/**お問合せ明細番号*/
	@ApiModelProperty(value = "お問合せ明細番号", required = false, position = 34)
	private String ffmInqDetailNo;

	/**手配時の案件番号*/
	@ApiModelProperty(value = "手配時の案件番号", required = false, position = 35)
	private String ffmArrProjectNo;

	/**手配時の問合せ番号*/
	@ApiModelProperty(value = "手配時の問合せ番号", required = false, position = 36)
	private String ffmArrInqNo;

	/**赤伝理由*/
	@ApiModelProperty(value = "赤伝理由", required = false, position = 37)
	private String ffmCancelReason;

	/**元契約番号*/
	@ApiModelProperty(value = "元契約番号", required = false, position = 38)
	private String ffmOrgContractCd;

	/**元請求明細番号*/
	@ApiModelProperty(value = "元請求明細番号", required = false, position = 39)
	private String ffmOrgContractDetailNo;

	/**請求条件*/
	@ApiModelProperty(value = "請求条件", required = false, position = 40)
	private String ffmBillingCondition;

	/**請求分割回数*/
	@ApiModelProperty(value = "請求分割回数", required = false, position = 41)
	private int ffmTotalBillingCount;

	/**契約締結日*/
	@ApiModelProperty(value = "契約締結日", required = false, position = 42)
	private String ffmContractDate;

	/**契約期間（開始）*/
	@ApiModelProperty(value = "契約期間（開始）", required = false, position = 43)
	private String ffmContractPeriodStart;

	/**契約期間（終了）*/
	@ApiModelProperty(value = "契約期間（終了）", required = false, position = 44)
	private String ffmContractPeriodEnd;

	/**契約ＳＳコード*/
	@ApiModelProperty(value = "契約ＳＳコード", required = false, position = 45)
	private String ffmContractSscd;

	/**契約ＳＳ社員コード*/
	@ApiModelProperty(value = "契約ＳＳ社員コード", required = false, position = 46)
	private String ffmContractSspiccd;

	/**振替先課所コード*/
	@ApiModelProperty(value = "振替先課所コード", required = false, position = 47)
	private String ffmTrnsLocationCd;

	/**振替先社員コード*/
	@ApiModelProperty(value = "振替先社員コード", required = false, position = 48)
	private String ffmTrnsPicCd;

	/**保守契約／リース/レンタルＮｏ*/
	@ApiModelProperty(value = "保守契約／リース/レンタルＮｏ", required = false, position = 49)
	private String ffmMntLeaseNo;

	/**契約金額*/
	@ApiModelProperty(value = "契約金額", required = false, position = 50)
	private BigDecimal ffmContractPrice;

	/**仕切前計上金額*/
	@ApiModelProperty(value = "仕切前計上金額", required = false, position = 51)
	private BigDecimal ffmPriceBeforeInvoice;

	/**仕切前消費税額*/
	@ApiModelProperty(value = "仕切前消費税額", required = false, position = 52)
	private BigDecimal ffmTaxPriceBeforeInvoice;

	/**商品コード*/
	@ApiModelProperty(value = "商品コード", required = false, position = 53)
	private String ffmProdactCd;

	/**機種略号*/
	@ApiModelProperty(value = "機種略号", required = false, position = 54)
	private String ffmModelId;

	/**機番*/
	@ApiModelProperty(value = "機番", required = false, position = 55)
	private String ffmSerialId;

	/**見積時の入力商品名*/
	@ApiModelProperty(value = "見積時の入力商品名", required = false, position = 56)
	private String ffmQuotationProdactName;

	/**原価計上商品コード*/
	@ApiModelProperty(value = "原価計上商品コード", required = false, position = 57)
	private String ffmCostProdactName;

	/**仕入区分*/
	@ApiModelProperty(value = "仕入区分", required = false, position = 58)
	private String ffmPurchaseType;

	/**仕入値引区分*/
	@ApiModelProperty(value = "仕入値引区分", required = false, position = 59)
	private String ffmPurchaseDiscntType;

	/**仕入購買区分*/
	@ApiModelProperty(value = "仕入購買区分", required = false, position = 60)
	private String ffmPurchaseClassType;

	/**仕入取引日*/
	@ApiModelProperty(value = "仕入取引日", required = false, position = 61)
	private String ffmPurchaseDate;

	/**他社商品区分*/
	@ApiModelProperty(value = "他社商品区分", required = false, position = 62)
	private String ffmNonRItemCd;

	/**仕入取引先コード*/
	@ApiModelProperty(value = "仕入取引先コード", required = false, position = 63)
	private String ffmSupplierCd;

	/**仕入課所設定区分*/
	@ApiModelProperty(value = "仕入課所設定区分", required = false, position = 64)
	private String ffmDeptAssortType;

	/**仕入課所コード*/
	@ApiModelProperty(value = "仕入課所コード", required = false, position = 65)
	private String ffmPurchaseLocationCd;

	/**仕入責任得意先コード*/
	@ApiModelProperty(value = "仕入責任得意先コード", required = false, position = 66)
	private String ffmPurchaseRespClientCd;

	/**在庫区コード*/
	@ApiModelProperty(value = "在庫区コード", required = false, position = 67)
	private String ffmRdStrctInventoryCd;

	/**特価番号*/
	@ApiModelProperty(value = "特価番号", required = false, position = 68)
	private String ffmDealsNo;

	/**仕入先請求ＮＯ*/
	@ApiModelProperty(value = "仕入先請求ＮＯ", required = false, position = 69)
	private String ffmSupplierBillingNo;

	/**商品名（支払通知書用）*/
	@ApiModelProperty(value = "商品名（支払通知書用）", required = false, position = 70)
	private String ffmPaymentProdactName;

	/**売上区分*/
	@ApiModelProperty(value = "売上区分", required = false, position = 71)
	private String ffmSalesType;

	/**売上値引区分*/
	@ApiModelProperty(value = "売上値引区分", required = false, position = 72)
	private String ffmSalesDiscountType;

	/**売上取引日（納品日）*/
	@ApiModelProperty(value = "売上取引日（納品日）", required = false, position = 73)
	private String ffmSalesTradeDate;

	/**得意先コード*/
	@ApiModelProperty(value = "得意先コード", required = false, position = 74)
	private String ffmClientCd;

	/**売上課所設定区分*/
	@ApiModelProperty(value = "売上課所設定区分", required = false, position = 75)
	private String ffmSalesLocationType;

	/**売上課所コード*/
	@ApiModelProperty(value = "売上課所コード", required = false, position = 76)
	private String ffmSalesLocationCd;

	/**売上社員設定区分*/
	@ApiModelProperty(value = "売上社員設定区分", required = false, position = 77)
	private String ffmSalesEmpType;

	/**売上社員コード*/
	@ApiModelProperty(value = "売上社員コード", required = false, position = 78)
	private String ffmSalesEmpCd;

	/**値引番号*/
	@ApiModelProperty(value = "値引番号", required = false, position = 79)
	private String ffmDiscntNo;

	/**伝票番号*/
	@ApiModelProperty(value = "伝票番号", required = false, position = 80)
	private String ffmSlipNo;

	/**契約区分*/
	@ApiModelProperty(value = "契約区分", required = false, position = 81)
	private String ffmContractType;

	/**売上原価金額*/
	@ApiModelProperty(value = "売上原価金額", required = false, position = 82)
	private BigDecimal ffmRevenueCostprice;

	/**振替先振替金額*/
	@ApiModelProperty(value = "振替先振替金額", required = false, position = 83)
	private BigDecimal ffmTrnsPrice;

	/**代直区分（販売店データリンク・売上用）*/
	@ApiModelProperty(value = "代直区分（販売店データリンク・売上用）", required = false, position = 84)
	private String ffmDistType;

	/**売上数量*/
	@ApiModelProperty(value = "売上数量", required = false, position = 85)
	private int ffmUserSalesCnt;

	/**ユーザ売上単価*/
	@ApiModelProperty(value = "ユーザ売上単価", required = false, position = 86)
	private BigDecimal ffmUserSalesPrice;

	/**ユーザ売上単価（税込）*/
	@ApiModelProperty(value = "ユーザ売上単価（税込）", required = false, position = 87)
	private BigDecimal ffmUserSalesPriceInTax;

	/**ユーザ売上金額*/
	@ApiModelProperty(value = "ユーザ売上金額", required = false, position = 88)
	private BigDecimal ffmUserSalesAmt;

	/**ユーザ売上金額（税込）*/
	@ApiModelProperty(value = "ユーザ売上金額（税込）", required = false, position = 89)
	private BigDecimal ffmUserSalesAmtInTax;

	/**ユーザ売上消費税区分*/
	@ApiModelProperty(value = "ユーザ売上消費税区分", required = false, position = 90)
	private String ffmUserSalesTaxType;

	/**ユーザ売上消費税率区分*/
	@ApiModelProperty(value = "ユーザ売上消費税率区分", required = false, position = 91)
	private String ffmUserSalesTaxRate;

	/**ユーザ売上消費税額*/
	@ApiModelProperty(value = "ユーザ売上消費税額", required = false, position = 92)
	private BigDecimal ffmUserSalesTaxPrice;

	/**RJ売上数量*/
	@ApiModelProperty(value = "RJ売上数量", required = false, position = 93)
	private int ffmRjSalesCnt;

	/**RJ売上単価*/
	@ApiModelProperty(value = "RJ売上単価", required = false, position = 94)
	private BigDecimal ffmRjSalesPrice;

	/**RJ売上単価（税込）*/
	@ApiModelProperty(value = "RJ売上単価（税込）", required = false, position = 95)
	private BigDecimal ffmRjSalesPriceInTax;

	/**RJ売上金額*/
	@ApiModelProperty(value = "RJ売上金額", required = false, position = 96)
	private BigDecimal ffmRjSalesAmt;

	/**RJ売上金額（税込）*/
	@ApiModelProperty(value = "RJ売上金額（税込）", required = false, position = 97)
	private BigDecimal ffmRjSalesAmtInTax;

	/**RJ売上消費税区分*/
	@ApiModelProperty(value = "RJ売上消費税区分", required = false, position = 98)
	private String ffmRjSalesTaxType;

	/**RJ売上消費税率区分*/
	@ApiModelProperty(value = "RJ売上消費税率区分", required = false, position = 99)
	private String ffmRjSalesTaxRate;

	/**RJ売上消費税額*/
	@ApiModelProperty(value = "RJ売上消費税額", required = false, position = 100)
	private BigDecimal ffmRjSalesTaxPrice;

	/**RJ仕入数量*/
	@ApiModelProperty(value = "RJ仕入数量", required = false, position = 101)
	private int ffmRjPurchaseCnt;

	/**RJ仕入単価*/
	@ApiModelProperty(value = "RJ仕入単価", required = false, position = 102)
	private BigDecimal ffmRjPurchasePrice;

	/**RJ仕入単価（税込）*/
	@ApiModelProperty(value = "RJ仕入単価（税込）", required = false, position = 103)
	private BigDecimal ffmRjPurchasePriceInTax;

	/**RJ仕入金額*/
	@ApiModelProperty(value = "RJ仕入金額", required = false, position = 104)
	private BigDecimal ffmRjPurchaseAmt;

	/**RJ仕入金額（税込）*/
	@ApiModelProperty(value = "RJ仕入金額（税込）", required = false, position = 105)
	private BigDecimal ffmRjPurchaseAmtInTax;

	/**RJ仕入消費税区分*/
	@ApiModelProperty(value = "RJ仕入消費税区分", required = false, position = 106)
	private String ffmRjPurchaseTaxType;

	/**RJ仕入消費税率区分*/
	@ApiModelProperty(value = "RJ仕入消費税率区分", required = false, position = 107)
	private String ffmRjPurchaseTaxRate;

	/**RJ仕入消費税額*/
	@ApiModelProperty(value = "RJ仕入消費税額", required = false, position = 108)
	private BigDecimal ffmRjPurchaseTaxPrice;

	/**販売店売上数量*/
	@ApiModelProperty(value = "販売店売上数量", required = false, position = 109)
	private int ffmShopSalesCnt;

	/**販売店売上単価*/
	@ApiModelProperty(value = "販売店売上単価", required = false, position = 110)
	private BigDecimal ffmShopSalesPrice;

	/**販売店売上単価（税込）*/
	@ApiModelProperty(value = "販売店売上単価（税込）", required = false, position = 111)
	private BigDecimal ffmShopSalesPriceInTax;

	/**販売店売上金額*/
	@ApiModelProperty(value = "販売店売上金額", required = false, position = 112)
	private BigDecimal ffmShopSalesAmt;

	/**販売店売上金額（税込）*/
	@ApiModelProperty(value = "販売店売上金額（税込）", required = false, position = 113)
	private BigDecimal ffmShopSalesAmtInTax;

	/**販売店売上消費税区分*/
	@ApiModelProperty(value = "販売店売上消費税区分", required = false, position = 114)
	private String ffmShopSalesTaxType;

	/**販売店売上消費税率区分*/
	@ApiModelProperty(value = "販売店売上消費税率区分", required = false, position = 115)
	private String ffmShopSalesTaxRate;

	/**販売店売上消費税額*/
	@ApiModelProperty(value = "販売店売上消費税額", required = false, position = 116)
	private BigDecimal ffmShopSalesTaxPrice;

	/**R原価数量*/
	@ApiModelProperty(value = "R原価数量", required = false, position = 117)
	private int ffmRCostCnt;

	/**R原価単価*/
	@ApiModelProperty(value = "R原価単価", required = false, position = 118)
	private BigDecimal ffmRCostPrice;

	/**R原価単価（税込）*/
	@ApiModelProperty(value = "R原価単価（税込）", required = false, position = 119)
	private BigDecimal ffmRCostPriceInTax;

	/**R原価金額*/
	@ApiModelProperty(value = "R原価金額", required = false, position = 120)
	private BigDecimal ffmRCostAmt;

	/**R原価金額（税込）*/
	@ApiModelProperty(value = "R原価金額（税込）", required = false, position = 121)
	private BigDecimal ffmRCostAmtInTax;

	/**R原価消費税区分*/
	@ApiModelProperty(value = "R原価消費税区分", required = false, position = 122)
	private String ffmRCostTaxType;

	/**R原価消費税率区分*/
	@ApiModelProperty(value = "R原価消費税率区分", required = false, position = 123)
	private String ffmRCostTaxRate;

	/**R原価消費税額*/
	@ApiModelProperty(value = "R原価消費税額", required = false, position = 124)
	private BigDecimal ffmRCostTaxPrice;

	/**手数料数量*/
	@ApiModelProperty(value = "手数料数量", required = false, position = 125)
	private int ffmCommissionCnt;

	/**手数料単価*/
	@ApiModelProperty(value = "手数料単価", required = false, position = 126)
	private BigDecimal ffmCommissionPrice;

	/**手数料単価（税込）*/
	@ApiModelProperty(value = "手数料単価（税込）", required = false, position = 127)
	private BigDecimal ffmCommissionPriceInTax;

	/**手数料金額*/
	@ApiModelProperty(value = "手数料金額", required = false, position = 128)
	private BigDecimal ffmCommissionAmt;

	/**手数料金額（税込）*/
	@ApiModelProperty(value = "手数料金額（税込）", required = false, position = 129)
	private BigDecimal ffmCommissionAmtInTax;

	/**手数料消費税区分*/
	@ApiModelProperty(value = "手数料消費税区分", required = false, position = 130)
	private String ffmCommissionTaxType;

	/**手数料消費税率区分*/
	@ApiModelProperty(value = "手数料消費税率区分", required = false, position = 131)
	private String ffmCommissionTaxRate;

	/**手数料消費税額*/
	@ApiModelProperty(value = "手数料消費税額", required = false, position = 132)
	private BigDecimal ffmCommissionTaxPrice;

	/**請求書明細識別コード*/
	@ApiModelProperty(value = "請求書明細識別コード", required = false, position = 133)
	private String ffmBillDetailCd;

	/**納品書要否区分*/
	@ApiModelProperty(value = "納品書要否区分", required = false, position = 134)
	private String ffmBillOutputFlag;

	/**納品書出力パターン*/
	@ApiModelProperty(value = "納品書出力パターン", required = false, position = 135)
	private String ffmBillOutputPtn;

	/**納品書出力形式*/
	@ApiModelProperty(value = "納品書出力形式", required = false, position = 136)
	private String ffmBillOutputFmt;

	/**請求書発行システム*/
	@ApiModelProperty(value = "請求書発行システム", required = false, position = 137)
	private String ffmBillOutputSystem;

	/**商品名パターン番号（納品書・請求書用）*/
	@ApiModelProperty(value = "商品名パターン番号（納品書・請求書用）", required = false, position = 138)
	private String ffmProdactPtnNo;

	/**商品名（納品書・請求書用）*/
	@ApiModelProperty(value = "商品名（納品書・請求書用）", required = false, position = 139)
	private String ffmProdactNameForBill;

	/**業務への連絡事項*/
	@ApiModelProperty(value = "業務への連絡事項", required = false, position = 140)
	private String ffmMessageForBiz;

	/**備考（納品書・請求書用）*/
	@ApiModelProperty(value = "備考（納品書・請求書用）", required = false, position = 141)
	private String ffmRemarkForBill;

	/**請求期間（開始）*/
	@ApiModelProperty(value = "請求期間（開始）", required = false, position = 142)
	private String ffmRBillingPeriodStart;

	/**請求期間（終了）*/
	@ApiModelProperty(value = "請求期間（終了）", required = false, position = 143)
	private String ffmRBillingPeriodEnd;

	/**請求月*/
	@ApiModelProperty(value = "請求月", required = false, position = 144)
	private String ffmBillingYm;

	/**今回の請求回数*/
	@ApiModelProperty(value = "今回の請求回数", required = false, position = 145)
	private int ffmThisBillingCnt;

	/**カウンター*/
	@ApiModelProperty(value = "カウンター", required = false, position = 146)
	private String ffmCounter;

	/**コメント１*/
	@ApiModelProperty(value = "コメント１", required = false, position = 147)
	private String ffmOutputComment1;

	/**コメント２*/
	@ApiModelProperty(value = "コメント２", required = false, position = 148)
	private String ffmOutputComment2;

	/**強制フラグ*/
	@ApiModelProperty(value = "強制フラグ", required = false, position = 149)
	private int ffmForcedFlag;

	/**機器設置先名*/
	@ApiModelProperty(value = "機器設置先名", required = false, position = 150)
	private String ffmInstalltionName;

	/**機器設置先部課名*/
	@ApiModelProperty(value = "機器設置先部課名", required = false, position = 151)
	private String ffmInstalltionDptName;

	/**RINGS届先コード(3桁）*/
	@ApiModelProperty(value = "RINGS届先コード(3桁）", required = false, position = 152)
	private String ffmRingsDstCd;

	/**OE届先コード(11桁）*/
	@ApiModelProperty(value = "OE届先コード(11桁）", required = false, position = 153)
	private String ffmOeDstCd;

	/**納品場所識別*/
	@ApiModelProperty(value = "納品場所識別", required = false, position = 154)
	private String ffmDstType;

	/**届先名１（会社名）*/
	@ApiModelProperty(value = "届先名１（会社名）", required = false, position = 155)
	private String ffmDstName1;

	/**届先名２（会社部課名）*/
	@ApiModelProperty(value = "届先名２（会社部課名）", required = false, position = 156)
	private String ffmDstName2;

	/**顧客名*/
	@ApiModelProperty(value = "顧客名", required = false, position = 157)
	private String ffmDstClientName;

	/**届先住所１*/
	@ApiModelProperty(value = "届先住所１", required = false, position = 158)
	private String ffmDstAddr1;

	/**届先住所２*/
	@ApiModelProperty(value = "届先住所２", required = false, position = 159)
	private String ffmDstAddr2;

	/**届先住所３*/
	@ApiModelProperty(value = "届先住所３", required = false, position = 160)
	private String ffmDstAddr3;

	/**届先郵便番号*/
	@ApiModelProperty(value = "届先郵便番号", required = false, position = 161)
	private String ffmDstZipCd;

	/**届先電話番号*/
	@ApiModelProperty(value = "届先電話番号", required = false, position = 162)
	private String ffmDstTel;

	/**届先ＦＡＸ番号*/
	@ApiModelProperty(value = "届先ＦＡＸ番号", required = false, position = 163)
	private String ffmDstFax;

	/**届先名（カナ）*/
	@ApiModelProperty(value = "届先名（カナ）", required = false, position = 164)
	private String ffmDstNameKana;

	/**得意先コード（二次店）*/
	@ApiModelProperty(value = "得意先コード（二次店）", required = false, position = 165)
	private String ffmClientCdSec;

	/**届先コード（二次店）*/
	@ApiModelProperty(value = "届先コード（二次店）", required = false, position = 166)
	private String ffmDstCdSec;

	/**支払利息相当額*/
	@ApiModelProperty(value = "支払利息相当額", required = false, position = 167)
	private BigDecimal ffmInterestExpensePrice;

	/**受取利息相当額*/
	@ApiModelProperty(value = "受取利息相当額", required = false, position = 168)
	private BigDecimal ffmInterestIncomePrice;

	/**見積番号*/
	@ApiModelProperty(value = "見積番号", required = false, position = 169)
	private String ffmQuotationCd;

	/**見積明細番号*/
	@ApiModelProperty(value = "見積明細番号", required = false, position = 170)
	private String ffmQuotationDetailCd;

	/**本体見積明細番号*/
	@ApiModelProperty(value = "本体見積明細番号", required = false, position = 171)
	private String ffmMainQuotationDetailCd;

	/**R請求書摘要*/
	@ApiModelProperty(value = "R請求書摘要", required = false, position = 172)
	private String chgBillingText;

	/**一次店R会社コード*/
	@ApiModelProperty(value = "一次店R会社コード", required = false, position = 173)
	private String chgRCompanyCode1st;

	/**一次店R会社名*/
	@ApiModelProperty(value = "一次店R会社名", required = false, position = 174)
	private String chgRCompanyName1st;

	/**一次店販売店ID*/
	@ApiModelProperty(value = "一次店販売店ID", required = false, position = 175)
	private String chgShopId1st;

	/**一次店販売店名*/
	@ApiModelProperty(value = "一次店販売店名", required = false, position = 176)
	private String chgShopName1st;

	/**一次店販売店摘要*/
	@ApiModelProperty(value = "一次店販売店摘要", required = false, position = 177)
	private String chgShopText1st;

	/**二次店R会社コード*/
	@ApiModelProperty(value = "二次店R会社コード", required = false, position = 178)
	private String chgRCompanyCode2st;

	/**二次店R会社名*/
	@ApiModelProperty(value = "二次店R会社名", required = false, position = 179)
	private String chgRCompanyName2st;

	/**二次店販売店ID*/
	@ApiModelProperty(value = "二次店販売店ID", required = false, position = 180)
	private String chgShopId2st;

	/**二次店販売店名*/
	@ApiModelProperty(value = "二次店販売店名", required = false, position = 181)
	private String chgShopName2st;

	/**二次店販売店摘要*/
	@ApiModelProperty(value = "二次店販売店摘要", required = false, position = 182)
	private String chgShopText2st;

	/**フォーマット種別*/
	@ApiModelProperty(value = "フォーマット種別", required = false, position = 183)
	private String cubicFmtType;

	/**勘定科目コード*/
	@ApiModelProperty(value = "勘定科目コード", required = false, position = 184)
	private String cubicAccountingCd;

	/**貸借区分*/
	@ApiModelProperty(value = "貸借区分", required = false, position = 185)
	private String cubicLcType;

	/**システムコード*/
	@ApiModelProperty(value = "システムコード", required = false, position = 186)
	private String cubicSystemCd;

	/**CUBIC会社コード*/
	@ApiModelProperty(value = "CUBIC会社コード", required = false, position = 187)
	private String cubicCompanyCd;

	/**会計計上日*/
	@ApiModelProperty(value = "会計計上日", required = false, position = 188)
	private Date cubicAccountingDate;

	/**伝票ＮＯ*/
	@ApiModelProperty(value = "伝票ＮＯ", required = false, position = 189)
	private String cubicVoucherDate;

	/**伝票明細NO*/
	@ApiModelProperty(value = "伝票明細NO", required = false, position = 190)
	private String cubicVoucherDetailDate;

	/**計上部門コード*/
	@ApiModelProperty(value = "計上部門コード", required = false, position = 191)
	private String cubicAccountDeptCd;

	/**商品軸*/
	@ApiModelProperty(value = "商品軸", required = false, position = 192)
	private String cubicProductAxis;

	/**営業軸*/
	@ApiModelProperty(value = "営業軸", required = false, position = 193)
	private String cubicSalesAxis;

	/**決算識別子*/
	@ApiModelProperty(value = "決算識別子", required = false, position = 194)
	private String cubicFinancialIdentifier;

	/**CUBIC品種コード*/
	@ApiModelProperty(value = "CUBIC品種コード", required = false, position = 195)
	private String cubicProductTypeCd;

	/**増減理由*/
	@ApiModelProperty(value = "増減理由", required = false, position = 196)
	private String cubicInDecReason;

	/**環境会計コード*/
	@ApiModelProperty(value = "環境会計コード", required = false, position = 197)
	private String cubicEnvAccountCd;

	/**プロジェクトコード*/
	@ApiModelProperty(value = "プロジェクトコード", required = false, position = 198)
	private String cubicProjectCd;

	/**数量*/
	@ApiModelProperty(value = "数量", required = false, position = 199)
	private int cubicCount;

	/**取引金額*/
	@ApiModelProperty(value = "取引金額", required = false, position = 200)
	private BigDecimal cubicAmount;

	/**外貨取引金額*/
	@ApiModelProperty(value = "外貨取引金額", required = false, position = 201)
	private BigDecimal cubicAmountForForeign;

	/**通貨コード*/
	@ApiModelProperty(value = "通貨コード", required = false, position = 202)
	private String cubicCurrencyCd;

	/**通貨換算タイプ*/
	@ApiModelProperty(value = "通貨換算タイプ", required = false, position = 203)
	private String cubicCurrencyConvType;

	/**通貨換算レート*/
	@ApiModelProperty(value = "通貨換算レート", required = false, position = 204)
	private BigDecimal cubicCurrencyConvRate;

	/**通貨換算日*/
	@ApiModelProperty(value = "通貨換算日", required = false, position = 205)
	private Date cubicCurrencyConvDate;

	/**摘要*/
	@ApiModelProperty(value = "摘要", required = false, position = 206)
	private String cubicText;

	/**明細摘要*/
	@ApiModelProperty(value = "明細摘要", required = false, position = 207)
	private String cubicTextDetail;

	/**各社セグメント*/
	@ApiModelProperty(value = "各社セグメント", required = false, position = 208)
	private String cubicCoSegment;

	/**管理セグメント予備2*/
	@ApiModelProperty(value = "管理セグメント予備2", required = false, position = 209)
	private String cubicCoSegment1;

	/**管理セグメント予備3*/
	@ApiModelProperty(value = "管理セグメント予備3", required = false, position = 210)
	private String cubicCoSegment2;

	/**消し込みキー*/
	@ApiModelProperty(value = "消し込みキー", required = false, position = 211)
	private String cubicDeleteKey;

	/**扱い者コード*/
	@ApiModelProperty(value = "扱い者コード", required = false, position = 212)
	private String cubicOperatorCd;

	/**グリーン購買コード*/
	@ApiModelProperty(value = "グリーン購買コード", required = false, position = 213)
	private String cubicGreenBuyCd;

	/**案件ＮＯ*/
	@ApiModelProperty(value = "案件ＮＯ", required = false, position = 214)
	private String cubicProjectNo;

	/**Ｄ／Ｆ　ＮＯ*/
	@ApiModelProperty(value = "Ｄ／Ｆ　ＮＯ", required = false, position = 215)
	private String cubicDfNo;

	/**予算ＮＯ*/
	@ApiModelProperty(value = "予算ＮＯ", required = false, position = 216)
	private String cubicBudgetNo;

	/**顧客コード*/
	@ApiModelProperty(value = "顧客コード", required = false, position = 217)
	private String cubicClientCd;

	/**各社固有管理セグメント1*/
	@ApiModelProperty(value = "各社固有管理セグメント1", required = false, position = 218)
	private String cubicCoMgtSegment;

	/**取引日*/
	@ApiModelProperty(value = "取引日", required = false, position = 219)
	private Date cubicTransactionDate;

	/**請求先サイトコード*/
	@ApiModelProperty(value = "請求先サイトコード", required = false, position = 220)
	private String cubicBillDstSiteCd;

	/**国内／海外区分*/
	@ApiModelProperty(value = "国内／海外区分", required = false, position = 221)
	private String cubicDomesticForeignType;

	/**取引単価（税抜）*/
	@ApiModelProperty(value = "取引単価（税抜）", required = false, position = 222)
	private BigDecimal cubicSalesPriceNoTax;

	/**外貨取引単価*/
	@ApiModelProperty(value = "外貨取引単価", required = false, position = 223)
	private BigDecimal cubicSalesPriceForeign;

	/**回収条件名*/
	@ApiModelProperty(value = "回収条件名", required = false, position = 224)
	private String cubicRecoveryReqName;

	/**回収方法名*/
	@ApiModelProperty(value = "回収方法名", required = false, position = 225)
	private String cubicRecoveryMethodName;

	/**回収起算日*/
	@ApiModelProperty(value = "回収起算日", required = false, position = 226)
	private Date cubicRecoveryDate;

	/**請求分類名*/
	@ApiModelProperty(value = "請求分類名", required = false, position = 227)
	private String cubicBillingTypeName;

	/**CUBIC請求書明細識別コード*/
	@ApiModelProperty(value = "CUBIC請求書明細識別コード", required = false, position = 228)
	private String cubicBillDetailTypeCode;

	/**値引名称*/
	@ApiModelProperty(value = "値引名称", required = false, position = 229)
	private String cubicDiscountName;

	/**請求書発行区分*/
	@ApiModelProperty(value = "請求書発行区分", required = false, position = 230)
	private String cubicBillOutputType;

	/**請求書ＮＯ*/
	@ApiModelProperty(value = "請求書ＮＯ", required = false, position = 231)
	private String cubicBillingNo;

	/**荷為替手形ＮＯ*/
	@ApiModelProperty(value = "荷為替手形ＮＯ", required = false, position = 232)
	private String cubicDocumentaryBillNo;

	/**Ｐ／ＣキーＮＯ*/
	@ApiModelProperty(value = "Ｐ／ＣキーＮＯ", required = false, position = 233)
	private String cubicPcKeyNo;

	/**請求書出力用伝票ＮＯ*/
	@ApiModelProperty(value = "請求書出力用伝票ＮＯ", required = false, position = 234)
	private String cubicBillingOutputNo;

	/**受注ＮＯ*/
	@ApiModelProperty(value = "受注ＮＯ", required = false, position = 235)
	private String cubicReceivedOrderNo;

	/**発注ＮＯ*/
	@ApiModelProperty(value = "発注ＮＯ", required = false, position = 236)
	private String cubicOrderNo;

	/**前受管理ＮＯ*/
	@ApiModelProperty(value = "前受管理ＮＯ", required = false, position = 237)
	private String cubicBeforeManageNo;

	/**前受金消込額*/
	@ApiModelProperty(value = "前受金消込額", required = false, position = 238)
	private BigDecimal cubicBeforeCancelAmt;

	/**追加ＴＥＲＭ*/
	@ApiModelProperty(value = "追加ＴＥＲＭ", required = false, position = 239)
	private String cubicAddTerm;

	/**契約NO*/
	@ApiModelProperty(value = "契約NO", required = false, position = 240)
	private String cubicContractNo;

	/**品名*/
	@ApiModelProperty(value = "品名", required = false, position = 241)
	private String cubicProdactName;

	/**債権債務汎用照合キー*/
	@ApiModelProperty(value = "債権債務汎用照合キー", required = false, position = 242)
	private String cubicMatchingKey;

	/**汎用転送データ*/
	@ApiModelProperty(value = "汎用転送データ", required = false, position = 243)
	private String cubicGeneralTransferData;

	/**元伝票ＮＯ（赤伝時）*/
	@ApiModelProperty(value = "元伝票ＮＯ（赤伝時）", required = false, position = 244)
	private String cubicOrgSlipNoForRed;

	/**元伝票明細ＮＯ（赤伝時）*/
	@ApiModelProperty(value = "元伝票明細ＮＯ（赤伝時）", required = false, position = 245)
	private String cubicOrgSlipDetailNoForRed;

	/**元会計計上日（赤伝時）*/
	@ApiModelProperty(value = "元会計計上日（赤伝時）", required = false, position = 246)
	private Date cubicOrgAcctDateForRed;

	/**設置先サイトコード*/
	@ApiModelProperty(value = "設置先サイトコード", required = false, position = 247)
	private String cubicDstSiteCd;

	/**項目予備1*/
	@ApiModelProperty(value = "項目予備1", required = false, position = 248)
	private String cubicItem1;

	/**項目予備2*/
	@ApiModelProperty(value = "項目予備2", required = false, position = 249)
	private String cubicItem2;

	/**項目予備3*/
	@ApiModelProperty(value = "項目予備3", required = false, position = 250)
	private String cubicItem3;

	/**拡張項目*/
	@ApiModelProperty(value = "拡張項目", required = false, position = 251)
	private String extendItem;

}
