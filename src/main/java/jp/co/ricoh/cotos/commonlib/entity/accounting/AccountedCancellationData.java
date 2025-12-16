package jp.co.ricoh.cotos.commonlib.entity.accounting;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.OrderDiv;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 計上後注文キャンセルデータテーブル
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "accounted_cancellation_data")
public class AccountedCancellationData extends EntityBase {

	@Description(value = "計上キャンセルステータス")
	public enum AccountingCancellationStatus {

		未連携("0"), 連携準備中("1"), 連携済("2");

		private final String text;

		private AccountingCancellationStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AccountingCancellationStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounted_cancellation_data_seq")
	@SequenceGenerator(name = "accounted_cancellation_data_seq", sequenceName = "accounted_cancellation_data_seq", allocationSize = 1)
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Schema(description = "契約番号枝番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 費用種別
	 */
	@Schema(description = "費用種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\")")
	private CostType costType;

	/**
	 * 計上処理日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "計上処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date accountSalesDate;

	/**
	 * 契約明細ID
	 */
	@Schema(description = "契約明細ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long contractDetailId;

	/**
	 * 受注区分
	 */
	@Schema(description = "受注区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "訪販(\"1\"), WEB(\"2\")")
	private OrderDiv orderDiv;

	/**
	 * キャンセル処理日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "キャンセル処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date canceledDate;

	/**
	 * V-UP連携済フラグ
	 */
	@Schema(description = "V-UP連携済フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer vupLinkedFlg;

	/**
	 * 計上キャンセルステータス
	 */
	@Schema(description = "計上キャンセルステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未連携(\"0\"), 連携準備中(\"1\"), 連携済(\"2\")")
	private AccountingCancellationStatus accountingCancellationStatus;

	/**
	 * 見積番号
	 */
	@Size(max = 255)
	@Schema(description = "見積番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String estimationNumber;

	/**
	 * 見積番号枝番
	 */
	@Schema(description = "見積番号枝番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99]")
	private int estimationBranchNumber;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 検収日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "検収日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date acceptanceDate;

	/**
	 * 契約日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "契約日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date contractDate;

	/**
	 * サービス開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "サービス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date serviceTermStart;

	/**
	 * 恒久契約識別番号
	 */
	@Schema(description = "恒久契約識別番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String immutableContIdentNumber;

	/**
	 * お問い合わせ番号
	 */
	@Size(max = 255)
	@Schema(description = "お問い合わせ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * 得意先コード
	 */
	@Size(max = 255)
	@Schema(description = "得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String billingCustomerSpCode;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private int quantity;

	/**
	 * 単価
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 金額
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal amountSummary;

}
