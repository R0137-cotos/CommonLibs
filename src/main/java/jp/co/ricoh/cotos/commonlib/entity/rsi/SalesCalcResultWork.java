package jp.co.ricoh.cotos.commonlib.entity.rsi;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "sales_calc_result_work")
public class SalesCalcResultWork extends EntityBase {

	public enum CotosProcessingFlg {

		未処理("0"), 処理済み("1"), 処理不可("E1"), 計上不可("E2");

		private final String text;

		private CotosProcessingFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CotosProcessingFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_calc_result_work_seq")
	@SequenceGenerator(name = "sales_calc_result_work_seq", sequenceName = "sales_calc_result_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**請求情報 ID*/
	@ApiModelProperty(value = "請求情報 ID", required = true, position = 2, allowableValues = "range[0,9999999999999999999]")
	@JsonProperty("Invoice.Id")
	private String invoiceId;

	/**請求日*/
	@ApiModelProperty(value = "請求日", required = true, position = 3)
	@JsonProperty("Invoice.InvoiceDate")
	private Date invoicedate;

	/**請求金額（税抜き）*/
	@ApiModelProperty(value = "請求金額（税抜き）", required = true, position = 4, allowableValues = "range[0,9999999999999999999]")
	@JsonProperty("Invoice.AmountWithoutTax")
	private long amountwithouttax;

	/**請求情報作成日*/
	@ApiModelProperty(value = "請求情報作成日", required = true, position = 5)
	@JsonProperty("Invoice.CreatedDate")
	private Date createddate;

	/**請求明細 ID*/
	@ApiModelProperty(value = "請求明細 ID", required = true, position = 6, allowableValues = "range[0,9999999999999999999]")
	@JsonProperty("InvoiceItem.Id")
	private String invoiceitemId;

	/**請求明細 課金日*/
	@ApiModelProperty(value = "請求明細 課金日", required = true, position = 7)
	@JsonProperty("InvoiceItem.ChargeDate")
	private Date invoiceitemChargedate;

	/**請求明細 単価*/
	@ApiModelProperty(value = "請求明細 単価", required = true, position = 8, allowableValues = "range[0,9999999999999999999]")
	@JsonProperty("InvoiceItem.UnitPrice")
	private long invoiceitemUnitprice;

	/**請求明細 数量*/
	@ApiModelProperty(value = "請求明細 数量", required = false, position = 9, allowableValues = "range[0,99999]")
	@JsonProperty("InvoiceItem.Quantity")
	private int invoiceitemQuantity;

	/**請求明細 金額*/
	@ApiModelProperty(value = "請求明細 金額", required = true, position = 10, allowableValues = "range[0,9999999999999999999]")
	@JsonProperty("InvoiceItem.ChargeAmount")
	private long invoiceitemChargeamount;

	/**請求明細 作成日*/
	@ApiModelProperty(value = "請求明細 作成日", required = true, position = 11)
	@JsonProperty("InvoiceItem.CreatedDate")
	private Date invoiceitemCreateddate;

	/**品名*/
	@Size(max = 255)
	@ApiModelProperty(value = "品名", required = true, position = 12, allowableValues = "range[0,255]")
	@JsonProperty("ProductRatePlanCharge.Name")
	private String productrateplanchargeName;

	/**リコー品種コード*/
	@Size(max = 255)
	@JsonProperty("ProductRatePlanCharge.PRC_EDPCode__c")
	@Column(name = "productrateplancharge_prc_edpcode_c")
	@ApiModelProperty(value = "リコー品種コード", required = true, position = 13, allowableValues = "range[0,255]")
	private String productrateplanchargePrcEdpcodeC;

	/**サービス期間.開始日*/
	@ApiModelProperty(value = "サービス期間.開始日", required = true, position = 14)
	@JsonProperty("InvoiceItem.ServiceStartDate")
	private Date invoiceitemServicestartdate;

	/**サービス期間.終了日*/
	@ApiModelProperty(value = "サービス期間.終了日", required = true, position = 15)
	@JsonProperty("InvoiceItem.ServiceEndDate")
	private Date invoiceitemServiceenddate;

	/**月額DB　契約ID*/
	@Size(max = 255)
	@ApiModelProperty(value = "月額DB　契約ID", required = false, position = 16, allowableValues = "range[0,255]")
	@JsonProperty("Subscription.SUB_SubscriptionRegionalId__c")
	private String rjManagementNumber;

	/**使用量*/
	@ApiModelProperty(value = "使用量", required = false, position = 17, allowableValues = "range[0,99999]")
	@JsonProperty("Usage.Quantity")
	private int usageQuantity;

	/**COTOS処理フラグ*/
	@ApiModelProperty(value = "COTOS処理フラグ", required = false, position = 16, allowableValues = "range[0,255]")
	private CotosProcessingFlg cotosProcessingFlg;
	
	/**
	 * 契約情報と紐づけるためのキー。
	 * リコー品種コードとRJ管理番号をハイフンでつなぐ
	 * @return
	 */
	@JsonIgnore
	public String getContractMergeKey() {
		return getProductrateplanchargePrcEdpcodeC() + "-" + getRjManagementNumber();
	}

}
