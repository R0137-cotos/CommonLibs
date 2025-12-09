package jp.co.ricoh.cotos.commonlib.entity.accounting;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 売上計上データID区分
	 */
	@Schema(description = "売上計上データID区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "OSO申込データ(\"1\"), OSO申込明細データ(\"2\"), OSO実績データ(\"3\")", example = "1")
	private OsoSalesDataIdDiv salesDataIdDiv;

	/**
	 * 売上計上データID
	 */
	@Column(nullable = false)
	@Schema(description = "売上計上データID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long salesDataId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long contractId;

	/**
	 * 契約明細ID
	 */
	@Min(0)
	@Schema(description = "契約明細ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long contractDetailId;

	/**
	 * 売上計上データID区分
	 */
	@Schema(description = "売上計上データID区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0")
	private OsoProcessingStatus processingStatus;

	/**
	 * 処理日時
	 */
	@Schema(description = "処理日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processingAt;

	/**
	 * メッセージ
	 */
	@Column(name = "message")
	@Size(max = 4000)
	@Schema(description = "メッセージ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,4000]")
	private String osoMessage;

	/**
	 * 商流区分
	 */
	@Schema(description = "商流区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "リコーなし代売売上１手数料（通常）(\"N\"), リコーなし代売売上１手数料（北海道）(\"P\"), リコーなし代売売上２手数料（通常）(\"T\"), リコーなし代売売上２手数料（北海道）(\"U\")", example = "N")
	private NspCommercialFlowDiv nspCommercialFlowDiv;

	/**
	 * サービスID
	 */
	@Size(max = 255)
	@Schema(description = "サービスID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serviceId;

	/**
	 * 販社コード
	 */
	@Size(max = 255)
	@Schema(description = "販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String salesCompanyCode;

	/**
	 * 販社得意先コード
	 */
	@Size(max = 255)
	@Schema(description = "販社得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String salesCompanyCustomerCode;

	/**
	 * 販社届先コード
	 */
	@Size(max = 255)
	@Schema(description = "販社届先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String salesCompanyDeliveryCode;

	/**
	 * 届け先コード
	 */
	@Size(max = 255)
	@Schema(description = "届け先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryCode;

	/**
	 * ローカル商品コード
	 */
	@Size(max = 255)
	@Schema(description = "ローカル商品コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String localProductCode;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 取引日
	 */
	@Schema(description = "取引日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date tradingDate;

	/**
	 * 販売元仕入単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販売元仕入単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal distributorPurchasePrice;

	/**
	 * 販売元売上単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販売元売上単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal distributorSalesPrice;

	/**
	 * 販社売上単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販社売上単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesCompanySalesPrice;

	/**
	 * 販売店売上単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販売店売上単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal dealerSalesPrice;

	/**
	 * 販売元仕入金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販売元仕入金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal distributorPurchaseAmount;

	/**
	 * 販売元売上金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販売元売上金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal distributorSalesAmount;

	/**
	 * 販社売上金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販社売上金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesCompanySalesAmount;

	/**
	 * 販売店売上金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販売店売上金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal dealerSalesAmount;

	/**
	 * 消費税区分
	 */
	@Schema(description = "消費税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "非課税(\"4\"), ５％外税(\"C\"), ８％外税(\"E\"), １０％外税(\"G\")", example = "4")
	private TaxRateDiv taxRateDiv;

	/**
	 * 販売二次店売上単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販売二次店売上単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal secondaryStoreSalesPrice;

	/**
	 * 販売二次店売上金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販売二次店売上金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal secondaryStoreSalesAmount;

	/**
	 * 販社二次店得意先コード
	 */
	@Size(max = 255)
	@Schema(description = "販社二次店得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String secondaryStoreCustomerCode;

	/**
	 * 販社二次店届先コード
	 */
	@Size(max = 255)
	@Schema(description = "販社二次店届先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String secondaryStoreDeliveryCode;

	/**
	 * 販売元仕入消費税
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販売元仕入消費税", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal distributorPurchaseTaxAmount;

	/**
	 * 納品：届先名１
	 */
	@Size(max = 255)
	@Schema(description = "納品：届先名１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryName1;

	/**
	 * 納品：届先名２
	 */
	@Size(max = 255)
	@Schema(description = "納品：届先名２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryName2;

	/**
	 * 納品：郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "納品：郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryPostNumber;

	/**
	 * 納品：届先住所１
	 */
	@Size(max = 255)
	@Schema(description = "納品：届先住所１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryAddress1;

	/**
	 * 納品：届先住所２
	 */
	@Size(max = 255)
	@Schema(description = "納品：届先住所２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryAddress2;

	/**
	 * 納品：届先住所３
	 */
	@Size(max = 255)
	@Schema(description = "納品：届先住所３", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryAddress3;

	/**
	 * 納品：届先電話番号
	 */
	@Size(max = 255)
	@Schema(description = "納品：届先電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryPhoneNumber;

	/**
	 * 記事１（漢字）
	 */
	@Column(name = "article1_kanji")
	@Size(max = 255)
	@Schema(description = "記事１（漢字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String article1Kanji;

	/**
	 * 記事２（漢字）
	 */
	@Column(name = "article2_kanji")
	@Size(max = 255)
	@Schema(description = "記事２（漢字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String article2Kanji;

	/**
	 * コメント１
	 */
	@Size(max = 255)
	@Schema(description = "コメント１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String comment1;

	/**
	 * コメント２
	 */
	@Size(max = 255)
	@Schema(description = "コメント２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String comment2;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@Schema(description = "仕入先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vendorCode;

	/**
	 * OSO管理番号
	 */
	@Size(max = 255)
	@Schema(description = "OSO管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String osoManageNumber;

	/**
	 * 債権債務照合キー
	 */
	@Size(max = 255)
	@Schema(description = "債権債務照合キー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String matchingKey;

	/**
	 * 予備
	 */
	@Size(max = 255)
	@Schema(description = "予備", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve;

	/**
	 * サービス種類区分
	 */
	@Size(max = 255)
	@Schema(description = "サービス種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serviceTypeDiv;

	/**
	 * サービス名
	 */
	@Size(max = 255)
	@Schema(description = "サービス名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serviceName;

	/**
	 * コメント使用タイプ
	 */
	@Size(max = 255)
	@Schema(description = "コメント使用タイプ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String commentUsageType;

	/**
	 * 手数料取引先コード
	 */
	@Size(max = 255)
	@Schema(description = "手数料取引先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String commissionSupplierCode;

	/**
	 * 手数料ローカル商品コード
	 */
	@Size(max = 255)
	@Schema(description = "手数料ローカル商品コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String commissionLocalProductCode;

	/**
	 * 担当ＳＡコード
	 */
	@Size(max = 255)
	@Schema(description = "担当ＳＡコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picSaCode;

	/**
	 * 値引適用フラグ
	 */
	@Size(max = 255)
	@Schema(description = "値引適用フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String discountApplyFlg;

	/**
	 * 都度値引名称
	 */
	@Size(max = 255)
	@Schema(description = "都度値引名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String discountName;

	/**
	 * 都度値引値
	 */
	@Size(max = 255)
	@Schema(description = "都度値引値", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String discountPrice;

	/**
	 * 値引種類(売上)
	 */
	@Size(max = 255)
	@Schema(description = "値引種類(売上)", allowableValues = "range[0,255]")
	private String discountTyprForSales;

	/**
	 * 値引種類(仕入)
	 */
	@Size(max = 255)
	@Schema(description = "値引種類(仕入)", allowableValues = "range[0,255]")
	private String discountTyprForPurchase;

	/**
	 * 発注No
	 */
	@Size(max = 255)
	@Schema(description = "発注No", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String orderNumber;

	/**
	 * 商品名
	 */
	@Size(max = 255)
	@Schema(description = "商品名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 得意先名
	 */
	@Size(max = 255)
	@Schema(description = "得意先名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerName;

	/**
	 * 傘下店名
	 */
	@Size(max = 255)
	@Schema(description = "傘下店名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dealerName;

	/**
	 * データ連携日付
	 */
	@Schema(description = "データ連携日付", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date cooperationDate;

	/**
	 * 計上部門コード
	 */
	@Size(max = 255)
	@Schema(description = "計上部門コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String accountingDepartmentCode;

	/**
	 * 経費データフラグ
	 */
	@Size(max = 255)
	@Schema(description = "経費データフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String expenseDataFlg;
}
