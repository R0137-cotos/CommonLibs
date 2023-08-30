package jp.co.ricoh.cotos.commonlib.entity.accounting;

import java.math.BigDecimal;
import java.util.Arrays;
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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoProcessingStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoSalesDataIdDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手数料データ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "commission_data")
public class CommissionData extends EntityBase {

	@Description(value = "商流区分")
	public enum NspCommercialFlowDiv {

		リコーなし代売売上１手数料_通常("N"), リコーなし代売売上１手数料_北海道("P"), リコーなし代売売上２手数料_通常("T"), リコーなし代売売上２手数料_北海道("U");

		private final String text;

		private NspCommercialFlowDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static NspCommercialFlowDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "消費税区分")
	public enum TaxRateDiv {

		非課税("4"), 外税_5("C"), 外税_8("E"), 外税_10("G"), 適格請求書対応事業者以外("J");

		private final String text;

		private TaxRateDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TaxRateDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commission_data_seq")
	@SequenceGenerator(name = "commission_data_seq", sequenceName = "commission_data_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 売上計上データID区分
	 */
	@ApiModelProperty(value = "売上計上データID区分", required = false, allowableValues = "OSO申込データ(\"1\"), OSO申込明細データ(\"2\"), OSO実績データ(\"3\")", example = "1", position = 2)
	private OsoSalesDataIdDiv salesDataIdDiv;

	/**
	 * 売上計上データID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "売上計上データID", required = true, position = 3, allowableValues = "range[0,9999999999999999999]")
	private long salesDataId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 5, allowableValues = "range[0,9999999999999999999]")
	private Long contractId;

	/**
	 * 契約明細ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約明細ID", required = false, position = 6, allowableValues = "range[0,9999999999999999999]")
	private Long contractDetailId;

	/**
	 * 売上計上データID区分
	 */
	@ApiModelProperty(value = "売上計上データID区分", required = false, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0", position = 7)
	private OsoProcessingStatus processingStatus;

	/**
	 * 処理日時
	 */
	@ApiModelProperty(value = "処理日時", required = false, position = 8)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processingAt;

	/**
	 * メッセージ
	 */
	@Column(name = "message")
	@Size(max = 4000)
	@ApiModelProperty(value = "メッセージ", required = false, position = 9, allowableValues = "range[0,4000]")
	private String osoMessage;

	/**
	 * 商流区分
	 */
	@ApiModelProperty(value = "商流区分", required = false, allowableValues = "リコーなし代売売上１手数料（通常）(\"N\"), リコーなし代売売上１手数料（北海道）(\"P\"), リコーなし代売売上２手数料（通常）(\"T\"), リコーなし代売売上２手数料（北海道）(\"U\")", example = "N", position = 10)
	private NspCommercialFlowDiv nspCommercialFlowDiv;

	/**
	 * サービスID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービスID", required = false, position = 11, allowableValues = "range[0,255]")
	private String serviceId;

	/**
	 * 販社コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "販社コード", required = false, position = 12, allowableValues = "range[0,255]")
	private String salesCompanyCode;

	/**
	 * 販社得意先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "販社得意先コード", required = false, position = 13, allowableValues = "range[0,255]")
	private String salesCompanyCustomerCode;

	/**
	 * 販社届先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "販社届先コード", required = false, position = 14, allowableValues = "range[0,255]")
	private String salesCompanyDeliveryCode;

	/**
	 * 届け先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "届け先コード", required = false, position = 15, allowableValues = "range[0,255]")
	private String deliveryCode;

	/**
	 * ローカル商品コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ローカル商品コード", required = false, position = 16, allowableValues = "range[0,255]")
	private String localProductCode;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "数量", required = false, position = 17, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 取引日
	 */
	@ApiModelProperty(value = "取引日", required = false, position = 18)
	@Temporal(TemporalType.DATE)
	private Date tradingDate;

	/**
	 * 販売元仕入単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販売元仕入単価", required = false, position = 19, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal distributorPurchasePrice;

	/**
	 * 販売元売上単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販売元売上単価", required = false, position = 20, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal distributorSalesPrice;

	/**
	 * 販社売上単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販社売上単価", required = false, position = 21, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesCompanySalesPrice;

	/**
	 * 販売店売上単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販売店売上単価", required = false, position = 22, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal dealerSalesPrice;

	/**
	 * 販売元仕入金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販売元仕入金額", required = false, position = 23, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal distributorPurchaseAmount;

	/**
	 * 販売元売上金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販売元売上金額", required = false, position = 24, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal distributorSalesAmount;

	/**
	 * 販社売上金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販社売上金額", required = false, position = 25, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesCompanySalesAmount;

	/**
	 * 販売店売上金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販売店売上金額", required = false, position = 26, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal dealerSalesAmount;

	/**
	 * 消費税区分
	 */
	@ApiModelProperty(value = "消費税区分", required = false, allowableValues = "非課税(\"4\"), ５％外税(\"C\"), ８％外税(\"E\"), １０％外税(\"G\")", example = "4", position = 27)
	private TaxRateDiv taxRateDiv;

	/**
	 * 販売二次店売上単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販売二次店売上単価", required = false, position = 28, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal secondaryStoreSalesPrice;

	/**
	 * 販売二次店売上金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販売二次店売上金額", required = false, position = 29, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal secondaryStoreSalesAmount;

	/**
	 * 販社二次店得意先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "販社二次店得意先コード", required = false, position = 30, allowableValues = "range[0,255]")
	private String secondaryStoreCustomerCode;

	/**
	 * 販社二次店届先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "販社二次店届先コード", required = false, position = 31, allowableValues = "range[0,255]")
	private String secondaryStoreDeliveryCode;

	/**
	 * 販売元仕入消費税
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販売元仕入消費税", required = false, position = 32, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal distributorPurchaseTaxAmount;

	/**
	 * 納品：届先名１
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "納品：届先名１", required = false, position = 33, allowableValues = "range[0,255]")
	private String deliveryName1;

	/**
	 * 納品：届先名２
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "納品：届先名２", required = false, position = 34, allowableValues = "range[0,255]")
	private String deliveryName2;

	/**
	 * 納品：郵便番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "納品：郵便番号", required = false, position = 35, allowableValues = "range[0,255]")
	private String deliveryPostNumber;

	/**
	 * 納品：届先住所１
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "納品：届先住所１", required = false, position = 36, allowableValues = "range[0,255]")
	private String deliveryAddress1;

	/**
	 * 納品：届先住所２
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "納品：届先住所２", required = false, position = 37, allowableValues = "range[0,255]")
	private String deliveryAddress2;

	/**
	 * 納品：届先住所３
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "納品：届先住所３", required = false, position = 38, allowableValues = "range[0,255]")
	private String deliveryAddress3;

	/**
	 * 納品：届先電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "納品：届先電話番号", required = false, position = 39, allowableValues = "range[0,255]")
	private String deliveryPhoneNumber;

	/**
	 * 記事１（漢字）
	 */
	@Column(name = "article1_kanji")
	@Size(max = 255)
	@ApiModelProperty(value = "記事１（漢字）", required = false, position = 40, allowableValues = "range[0,255]")
	private String article1Kanji;

	/**
	 * 記事２（漢字）
	 */
	@Column(name = "article2_kanji")
	@Size(max = 255)
	@ApiModelProperty(value = "記事２（漢字）", required = false, position = 41, allowableValues = "range[0,255]")
	private String article2Kanji;

	/**
	 * コメント１
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "コメント１", required = false, position = 42, allowableValues = "range[0,255]")
	private String comment1;

	/**
	 * コメント２
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "コメント２", required = false, position = 43, allowableValues = "range[0,255]")
	private String comment2;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "仕入先コード", required = false, position = 44, allowableValues = "range[0,255]")
	private String vendorCode;

	/**
	 * OSO管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "OSO管理番号", required = false, position = 45, allowableValues = "range[0,255]")
	private String osoManageNumber;

	/**
	 * 債権債務照合キー
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "債権債務照合キー", required = false, position = 46, allowableValues = "range[0,255]")
	private String matchingKey;

	/**
	 * 予備
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "予備", required = false, position = 47, allowableValues = "range[0,255]")
	private String reserve;

	/**
	 * サービス種類区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービス種類区分", required = false, position = 48, allowableValues = "range[0,255]")
	private String serviceTypeDiv;

	/**
	 * サービス名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービス名", required = false, position = 49, allowableValues = "range[0,255]")
	private String serviceName;

	/**
	 * コメント使用タイプ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "コメント使用タイプ", required = false, position = 50, allowableValues = "range[0,255]")
	private String commentUsageType;

	/**
	 * 手数料取引先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "手数料取引先コード", required = false, position = 51, allowableValues = "range[0,255]")
	private String commissionSupplierCode;

	/**
	 * 手数料ローカル商品コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "手数料ローカル商品コード", required = false, position = 52, allowableValues = "range[0,255]")
	private String commissionLocalProductCode;

	/**
	 * 担当ＳＡコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当ＳＡコード", required = false, position = 53, allowableValues = "range[0,255]")
	private String picSaCode;

	/**
	 * 値引適用フラグ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "値引適用フラグ", required = false, position = 54, allowableValues = "range[0,255]")
	private String discountApplyFlg;

	/**
	 * 都度値引名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都度値引名称", required = false, position = 55, allowableValues = "range[0,255]")
	private String discountName;

	/**
	 * 都度値引値
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都度値引値", required = false, position = 56, allowableValues = "range[0,255]")
	private String discountPrice;

	/**
	 * 値引種類(売上)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "値引種類(売上)", required = false, position = 57, allowableValues = "range[0,255]")
	private String discountTyprForSales;

	/**
	 * 値引種類(仕入)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "値引種類(仕入)", required = false, position = 58, allowableValues = "range[0,255]")
	private String discountTyprForPurchase;

	/**
	 * 発注No
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "発注No", required = false, position = 59, allowableValues = "range[0,255]")
	private String orderNumber;

	/**
	 * 商品名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商品名", required = false, position = 60, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 得意先名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "得意先名", required = false, position = 61, allowableValues = "range[0,255]")
	private String customerName;

	/**
	 * 傘下店名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "傘下店名", required = false, position = 62, allowableValues = "range[0,255]")
	private String dealerName;

	/**
	 * データ連携日付
	 */
	@ApiModelProperty(value = "データ連携日付", required = false, position = 63)
	@Temporal(TemporalType.DATE)
	private Date cooperationDate;

	/**
	 * 計上部門コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "計上部門コード", required = false, position = 64, allowableValues = "range[0,255]")
	private String accountingDepartmentCode;

	/**
	 * 経費データフラグ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "経費データフラグ", required = false, position = 65, allowableValues = "range[0,255]")
	private String expenseDataFlg;
}
