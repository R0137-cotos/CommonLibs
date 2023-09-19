package jp.co.ricoh.cotos.commonlib.entity.accounting;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 2, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@ApiModelProperty(value = "契約番号枝番", required = false, position = 4, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "リコー品種コード", required = false, position = 5, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 費用種別
	 */
	@ApiModelProperty(value = "費用種別", required = false, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\")", position = 6)
	private CostType costType;

	/**
	 * 計上処理日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "計上処理日", required = false, position = 7)
	private Date accountSalesDate;

	/**
	 * 契約明細ID
	 */
	@ApiModelProperty(value = "契約明細ID", required = false, position = 8, allowableValues = "range[0,9999999999999999999]")
	private Long contractDetailId;

	/**
	 * 受注区分
	 */
	@ApiModelProperty(value = "受注区分", required = false, allowableValues = "訪販(\"1\"), WEB(\"2\")", position = 9)
	private OrderDiv orderDiv;

	/**
	 * キャンセル処理日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "キャンセル処理日", required = false, position = 10)
	private Date canceledDate;

	/**
	 * V-UP連携済フラグ
	 */
	@ApiModelProperty(value = "V-UP連携済フラグ", required = false, position = 11, allowableValues = "range[0,9]")
	private Integer vupLinkedFlg;

	/**
	 * 計上キャンセルステータス
	 */
	@ApiModelProperty(value = "計上キャンセルステータス", required = false, allowableValues = "未連携(\"0\"), 連携準備中(\"1\"), 連携済(\"2\")", position = 12)
	private AccountingCancellationStatus accountingCancellationStatus;

	/**
	 * 見積番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "見積番号", required = false, position = 13, allowableValues = "range[0,255]")
	private String estimationNumber;

	/**
	 * 見積番号枝番
	 */
	@ApiModelProperty(value = "見積番号枝番", required = false, position = 14, allowableValues = "range[0,99]")
	private int estimationBranchNumber;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名", required = false, position = 15, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 検収日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "検収日", required = false, position = 16)
	private Date acceptanceDate;

	/**
	 * 契約日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "契約日", required = false, position = 17)
	private Date contractDate;

	/**
	 * サービス開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "サービス開始日", required = false, position = 18)
	private Date serviceTermStart;

	/**
	 * 恒久契約識別番号
	 */
	@ApiModelProperty(value = "恒久契約識別番号", required = false, position = 19, allowableValues = "range[0,255]")
	private String immutableContIdentNumber;

	/**
	 * お問い合わせ番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "お問い合わせ番号", required = false, position = 20, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * 得意先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "得意先コード", required = false, position = 21, allowableValues = "range[0,255]")
	private String billingCustomerSpCode;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種名", required = false, position = 22, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "数量", required = false, position = 23, allowableValues = "range[-99999,99999]")
	private int quantity;

	/**
	 * 単価
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "単価", required = false, position = 24, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 金額
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "金額", required = false, position = 25, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal amountSummary;

}
