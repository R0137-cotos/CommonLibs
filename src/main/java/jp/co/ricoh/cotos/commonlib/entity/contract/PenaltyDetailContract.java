package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "違約金明細ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@ApiModelProperty(value = "契約", required = true, position = 2)
	private Contract contract;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種名", required = false, position = 4, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@NotNull
	@ApiModelProperty(value = "リコー品種コード", required = true, position = 5, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種区分", required = false, position = 6, allowableValues = "range[0,255]")
	private String itemType;

	/**
	 * 違約金単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "違約金単価", required = false, position = 7, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyUnitPrice;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "数量", required = false, position = 8, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 違約金金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "違約金金額", required = false, position = 9, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyAmountSummary;

	/**
	 * 計上先区分
	 */
	@ApiModelProperty(value = "計上先区分", required = false, allowableValues = "エンドユーザ(\"1\"), 課所止め(\"2\")", example = "1", position = 10)
	private SalesToType salesToType;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "削除フラグ", required = false, position = 11, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * 違約金売上計上処理状態
	 */
	@ApiModelProperty(value = "違約金売上計上処理状態", required = false, allowableValues = "未計上(\"0\"), 計上済み(\"1\"), 処理不要(\"2\"), 処理不可(\"3\")", example = "1", position = 12)
	private PenaltyAccountSalesStatus penaltyAccountSalesStatus;

	/**
	 * 違約金売上計上処理日
	 */
	@ApiModelProperty(value = "違約金売上計上処理日", required = false, position = 13)
	@Temporal(TemporalType.DATE)
	private Date penaltyAccountSalesDate;

	/**
	 * 違約金明細振替
	 */
	@Valid
	@OneToMany(mappedBy = "penaltyDetailContract")
	@ApiModelProperty(value = "違約金明細振替", required = false, position = 14)
	private List<PenaltyDetailTrans> penaltyDetailTransList;

}
