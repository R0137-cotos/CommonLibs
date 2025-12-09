package jp.co.ricoh.cotos.commonlib.entity.contract.order;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文基本情報
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_basic_info")
public class OrderBasicInfo extends EntityBase {

	@Description(value = "注文タイプ")
	public enum OrdererType {

		新規("1"), 変更("2"), 解約("3"), キャンセル("4");

		private final String text;

		private OrdererType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OrdererType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "商品種別")
	public enum ProductType {

		RSI("1"), ROC("2"), O365("3"), MVB("4");

		private final String text;

		private ProductType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ProductType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "商流区分（代直区分）")
	public enum CommercialFlowDiv {

		代売("1"), 直売("2");

		private final String text;

		private CommercialFlowDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CommercialFlowDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_basic_info_seq")
	@SequenceGenerator(name = "order_basic_info_seq", sequenceName = "order_basic_info_seq", allocationSize = 1)
	@Schema(description = "ID", required = true)
	private long id;

	/**
	 * 注文番号
	 */
	@Column
	@Schema(description = "注文番号", required = false, allowableValues = "range[0,]")
	private String ordererNumber;

	/**
	 * 注文タイプ
	 */
	@Column
	@Schema(description = "注文タイプ", required = false, allowableValues = "新規(\"1\"), 変更(\"2\"), 解約(\"3\"), キャンセル(\"4\")")
	private OrdererType ordererType;

	/**
	 * 商品種別
	 */
	@Column
	@Schema(description = "商品種別", required = false, allowableValues = "RSI(\"1\"), ROC(\"2\"), O365(\"3\"), MVB(\"4\")")
	private ProductType productType;

	/**
	 * 契約番号
	 */
	@Column
	@Schema(description = "契約番号", required = false, allowableValues = "range[0,]")
	private String rjManageNumber;

	/**
	 * メーカー契約番号
	 */
	@Column
	@Schema(description = "メーカー契約番号", required = false, allowableValues = "range[0,]")
	private String makerManageNumber;

	/**
	 * 商流区分（代直区分）
	 */
	@Column
	@Schema(description = "商流区分（代直区分）", required = false, allowableValues = "代売(\"1\"), 直売(\"2\")")
	private CommercialFlowDiv commercialFlowDiv;

	/**
	 * 申込日時
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "申込日時", required = false)
	private Date ordererDateTime;

	/**
	 * 解約予定日
	 */
	@Column
	@Temporal(TemporalType.DATE)
	@Schema(description = "解約予定日", required = false)
	private Date cancelScheduledDate;

	/**
	 * 解約理由1
	 */
	@Column(name = "cancel_reason_1")
	@Schema(description = "解約理由1", required = false)
	private String cancelReason1;

	/**
	 * 解約理由2
	 */
	@Column(name = "cancel_reason_2")
	@Schema(description = "解約理由2", required = false)
	private String cancelReason2;

	/**
	 * 改善ポイント
	 */
	@Column
	@Schema(description = "改善ポイント", required = false)
	private String improvementPoint;

	/**
	 * 初期費合計
	 */
	@Column
	@Schema(description = "初期費合計", required = false)
	private BigDecimal initialTotalAmount;

	/**
	 * 月額費合計
	 */
	@Column
	@Schema(description = "月額費合計", required = false)
	private BigDecimal monthlyTotalAmount;

	/**
	 * 年額費合計
	 */
	@Column
	@Schema(description = "年額費合計", required = false)
	private BigDecimal yearlyTotalAmount;

	@OneToOne(mappedBy = "orderBasicInfo")
	@Schema(description = "注文商品グループ情報", required = false)
	private OrderProductGroupInfo orderProductGroupInfo;

	@OneToOne(mappedBy = "orderBasicInfo")
	@Schema(description = "注文サービス固有情報", required = false)
	private OrderServiceInnerInfo orderServiceInnerInfo;

	@OneToOne(mappedBy = "orderBasicInfo")
	@Schema(description = "注文販売店情報", required = false)
	private OrderDistributorInfo orderDistributorInfo;

	@OneToOne(mappedBy = "orderBasicInfo")
	@Schema(description = "注文母店情報", required = false)
	private OrderParentDistributorInfo orderParentDistributorInfo;

	@OneToOne(mappedBy = "orderBasicInfo")
	@Schema(description = "注文セットアップ先情報", required = false)
	private OrderSetupInfo orderSetupInfo;

	@OneToOne(mappedBy = "orderBasicInfo")
	@Schema(description = "注文者情報", required = false)
	private OrdererInfo ordererInfo;

	@OneToMany(mappedBy = "orderBasicInfo")
	@Schema(description = "注文商品情報", required = false)
	private List<OrderProductInfo> orderProductInfoList;

	@OneToOne(mappedBy = "orderBasicInfo")
	@Schema(description = "注文担当支社情報", required = false)
	private OrderBranchCustomerInfo orderBranchCustomerInfo;

	@OneToOne(mappedBy = "orderBasicInfo")
	@Schema(description = "注文顧客情報", required = false)
	private OrderContractorInfo orderContractorInfo;

	@OneToOne(mappedBy = "orderBasicInfo")
	@Schema(description = "注文管理情報", required = false)
	private OrderManagementInfo orderManagementInfo;

}