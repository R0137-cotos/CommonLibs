package jp.co.ricoh.cotos.commonlib.entity.common;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoDealerFlow;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoProcessingStatus;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSO実績データエンティティー共通項目
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class OsoResultsDataAbstractEntity extends EntityBase {

	@Description(value = "手数料データ作成状態")
	public enum CommissionDataCreateStatus {

		未作成("0"), 作成済("1"), 作成対象外("9"), 処理エラー("E");

		private final String text;

		private CommissionDataCreateStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CommissionDataCreateStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "納品場所識別")
	public enum DeliveryDiv {

		届け先マスタの住所情報を利用("0"), 納品場所を指定("1");

		private final String text;

		private DeliveryDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DeliveryDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * OSO申込明細ID
	 */
	@Column(nullable = false)
	@Schema(description = "OSO申込明細ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long osoRequestDetailId;

	/**
	 * 処理状態
	 */
	@Schema(description = "処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0")
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
	 * RTS管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RTS管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rtsManageNumber;

	/**
	 * RTS管理番号枝番
	 */
	@Max(999)
	@Min(0)
	@Schema(description = "RTS管理番号枝番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[1,999]")
	private Integer rtsManageBranchNumber;

	/**
	 * OSO管理番号
	 */
	@Size(max = 255)
	@Schema(description = "OSO管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String osoManageNumber;

	/**
	 * 売上年月
	 */
	@Schema(description = "売上年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date salesDate;

	/**
	 * 手数料データ作成状態
	 */
	@Schema(description = "手数料データ作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 作成対象外(\"9\"), 処理エラー(\"E\")", example = "0")
	private CommissionDataCreateStatus commissionDataCreateStatus;

	/**
	 * 品種コード
	 */
	@Size(max = 255)
	@Schema(description = "品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemCode;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * OSO商流区分
	 */
	@Schema(description = "OSO商流区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "販社売上(\"1\"), 販売店売上(\"2\"), リコー売上(\"3\")", example = "1")
	private OsoDealerFlow osoDealerFlow;

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
	 * 数量
	 */
	@Max(99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 納品日
	 */
	@Schema(description = "納品日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date deliveryDate;

	/**
	 * リコー仕切単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "リコー仕切単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal ricohPartitionPrice;

	/**
	 * リコー売上単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "リコー売上単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal ricohSalesPrice;

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
	 * 納品場所識別
	 */
	@Schema(description = "納品場所識別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "届け先マスタの住所情報を利用(\"0\"), 納品場所を指定(\"1\")", example = "0")
	private DeliveryDiv deliveryDiv;

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
	 * 記事１（カナ）
	 */
	@Column(name = "article1_kana")
	@Size(max = 255)
	@Schema(description = "記事１（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String article1Kana;

	/**
	 * 記事２（カナ）
	 */
	@Column(name = "article2_kana")
	@Size(max = 255)
	@Schema(description = "記事２（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String article2Kana;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@Schema(description = "仕入先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vendorCode;

	/**
	 * 販社仕入金額
	 */
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販社仕入金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesCompanyPurchaseAmount;

	/**
	 * 販社売上金額
	 */
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販社売上金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesCompanySalesAmount;

	/**
	 * 接点店手数料金額
	 */
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "接点店手数料金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal contactStoreCommissionAmount;

	/**
	 * 母店手数料金額
	 */
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "接点店手数料金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal motherStoreCommissionAmount;

	/**
	 * 費用種別
	 */
	@Schema(description = "費用種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "初期費(\"1\"), 月額定額(\"2\"), 年額(\"3\"), 月額従量(\"4\")", example = "1")
	private CostType cotosCostType;

	/**
	 * 品種区分
	 */
	@Schema(description = "品種区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", example = "0")
	private ItemType cotosItemType;
}
