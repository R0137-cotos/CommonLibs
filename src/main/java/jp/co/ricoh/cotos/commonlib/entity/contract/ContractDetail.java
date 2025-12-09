package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DetailStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.InitialAccountSalesStatus;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationDetail.IncreaseDecreaseDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 契約明細を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true, exclude={"itemContract"})
@ToString(callSuper = true, exclude={"itemContract"})
@Data
@Table(name = "contract_detail")
public class ContractDetail extends EntityBase {

	@Description(value = "FFM内部振替処理状態")
	public enum FfmInsideTransStatus {
		未処理("0"), CSV作成済み("1"), 連携済み("2"), 対象外("3");

		private final String text;

		private FfmInsideTransStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static FfmInsideTransStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "統合契約内部振替処理状態")
	public enum AbsConInsideTransStatus {
		未処理("0"), 連携済み("1"), 対象外("4");

		private final String text;

		private AbsConInsideTransStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AbsConInsideTransStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "ランニング売上計上処理状態")
	public enum RunningAccountSalesStatus {
		正常("0"), 処理エラー("1"), 処理不要("2");

		private final String text;

		private RunningAccountSalesStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static RunningAccountSalesStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "FFM検収連携状態")
	public enum FfmAcceptanceLinkingStatus {
		未作成("0"), 作成済み("1"), 作成エラー("2"), 対象外("3");

		private final String text;

		private FfmAcceptanceLinkingStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static FfmAcceptanceLinkingStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "統合契約外部請求処理状態")
	public enum AbsConExternalBillingStatus {
		未処理("0"), CSV作成済み("1"), 対象外("2");

		private final String text;

		private AbsConExternalBillingStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AbsConExternalBillingStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_detail_seq")
	@SequenceGenerator(name = "contract_detail_seq", sequenceName = "contract_detail_seq", allocationSize = 1)
	@Schema(description = "契約明細ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

	/**
	 * 状態
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "NONE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")", example = "")
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
	 * 単価
	 */
	@Column(nullable = false)
	@NotNull
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "単価", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 金額
	 */
	@Column(nullable = false)
	@NotNull
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "金額", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal amountSummary;

	/**
	 * 摘要
	 */
	@Size(max = 255)
	@Schema(description = "摘要", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String detailAbstract;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * イニシャル売上計上処理状態
	 */
	@Schema(description = "イニシャル売上計上処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), CSV作成済み(\"1\"), 連携済み(\"2\"), 対象外(\"3\")")
	private InitialAccountSalesStatus initialAccountSalesStatus;

	/**
	 * イニシャル売上計上処理日
	 */
	@Schema(description = "イニシャル売上計上処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date initialAccountSalesDate;

	/**
	 * 品種(契約用)
	 */
	@Valid
	@OneToOne(mappedBy = "contractDetail")
	@Schema(description = "品種(契約用)")
	private ItemContract itemContract;

	/**
	 * 注文番号
	 */
	@Size(max = 255)
	@Schema(description = "注文番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String orderNo;

	/**
	 * FFM内部振替処理状態
	 */
	@Schema(description = "FFM内部振替処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理中(\"0\"), 作成完了(\"1\"), 連携済み(\"2\"), 対象外(\"3\")")
	private FfmInsideTransStatus ffmInsideTransStatus;

	/**
	 * FFM内部振替連携日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "FFM内部振替連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date ffmInsideLinkDate;

	/**
	 * 統合契約内部振替処理状態
	 */
	@Schema(description = "統合契約内部振替処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 連携済み(\"1\"), 対象外(\"4\")")
	private AbsConInsideTransStatus absConInsideTransStatus;

	/**
	 * 統合契約内部振替連携日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "統合契約内部振替連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date absConInsideLinkDate;

	/**
	 * ランニング売上計上処理状態
	 */
	@Schema(description = "ランニング売上計上処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "正常(\"0\"), 処理エラー(\"1\"), 処理不要(\"2\")")
	private RunningAccountSalesStatus runningAccountSalesStatus;

	/**
	 * ランニング売上計上処理日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ランニング売上計上処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date runningAccountSalesDate;

	/**
	 * FFM検収連携状態
	 */
	@Schema(description = "FFM検収連携状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"), 作成済み(\"1\"), 作成エラー(\"2\")")
	private FfmAcceptanceLinkingStatus ffmAcceptanceLinkingStatus;

	/**
	 * 契約期間
	 */
	@Size(max = 255)
	@Schema(description = "契約期間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractSpan;

	/**
	 * 品種追加フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "品種追加フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer itemAddFlg;

	/**
	 * 統合契約外部請求処理状態
	 */
	@Schema(description = "統合契約外部請求処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), CSV作成済み(\"1\"), 対象外(\"2\")")
	private AbsConExternalBillingStatus absConExternalBillingStatus;

	/**
	 * 統合契約外部請求連携日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "統合契約外部請求連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date absConExternalBillingDate;

	/**
	 * 発送物あり明細
	 */
	@Valid
	@OneToMany(mappedBy = "contractDetail")
	@Schema(description = "発送物あり明細", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ShippingThingDetail> shippingThingDetailList;

	/**
	 * 契約数
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "契約数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer contractAmount;

	/**
	 * FFM検収予定日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "FFM検収予定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date acceptanceScheduledDate;

	/**
	 * 増減区分
	 */
	@Schema(description = "増減区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "増数(\"1\"), 減数(\"2\")", example = "1")
	private IncreaseDecreaseDiv increaseDecreaseDiv;
}