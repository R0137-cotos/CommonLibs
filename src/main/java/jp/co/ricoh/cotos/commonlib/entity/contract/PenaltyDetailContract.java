package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
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
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 違約金明細(契約用)を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "penalty_detail_contract")
public class PenaltyDetailContract extends EntityBase {

	@Description(value = "計上先区分")
	public enum SalesToType {

		エンドユーザ("1"), 課所止め("2");

		private final String text;

		private SalesToType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static SalesToType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "違約金売上計上処理状態")
	public enum PenaltyAccountSalesStatus {

		未計上("0"), 計上済み("1"), 処理不要("2"), 処理不可("3");

		private final String text;

		private PenaltyAccountSalesStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static PenaltyAccountSalesStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 違約金明細ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "penalty_detail_contract_seq")
	@SequenceGenerator(name = "penalty_detail_contract_seq", sequenceName = "penalty_detail_contract_seq", allocationSize = 1)
	@Schema(description = "違約金明細ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@NotNull
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@Size(max = 255)
	@Schema(description = "品種区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemType;

	/**
	 * 違約金単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "違約金単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyUnitPrice;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 違約金金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "違約金金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyAmountSummary;

	/**
	 * 計上先区分
	 */
	@Schema(description = "計上先区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "エンドユーザ(\"1\"), 課所止め(\"2\")", example = "1")
	private SalesToType salesToType;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "削除フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * 違約金売上計上処理状態
	 */
	@Schema(description = "違約金売上計上処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未計上(\"0\"), 計上済み(\"1\"), 処理不要(\"2\"), 処理不可(\"3\")", example = "1")
	private PenaltyAccountSalesStatus penaltyAccountSalesStatus;

	/**
	 * 違約金売上計上処理日
	 */
	@Schema(description = "違約金売上計上処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date penaltyAccountSalesDate;

	/**
	 * 違約金明細振替
	 */
	@Valid
	@OneToMany(mappedBy = "penaltyDetailContract")
	@Schema(description = "違約金明細振替", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<PenaltyDetailTrans> penaltyDetailTransList;

}
