
package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 商品グループマスタ
 */
@Entity
@Data
@ToString(exclude = { "estimationApprovalRouteGrpMaster", "contractApprovalRouteGrpMaster" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_grp_master")
public class ProductGrpMaster extends EntityBaseMaster {

	@Description(value = "S&S作業依頼ルート区分")
	public enum SsWorkRequestRootDiv {

		CSV作成("1"), API連携("2");

		private final String text;

		private SsWorkRequestRootDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static SsWorkRequestRootDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "電子契約利用開始希望日制御区分")
	public enum ElectronicContractDesiredStartDateDiv {

		新規のみ入力可能_契約変更入力不可("1"), 新規_契約変更ともに入力可能("2"), 契約変更のみ入力可能_新規入力不可("3"), 新規_契約変更いずれも入力不可("4");
		private final String text;

		private ElectronicContractDesiredStartDateDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ElectronicContractDesiredStartDateDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 商品グループマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_grp_master_seq")
	@SequenceGenerator(name = "product_grp_master_seq", sequenceName = "product_grp_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "商品グループマスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 見積承認ルートグループマスタ
	 */
	@ManyToOne
	@JoinColumn(name = "estimation_approval_route_grp_id", referencedColumnName = "id")
	@ApiModelProperty(value = "承認ルートグループマスタ（見積）", required = false, position = 2)
	private ApprovalRouteGrpMaster estimationApprovalRouteGrpMaster;

	/**
	 * 契約承認ルートグループマスタ
	 */
	@ManyToOne
	@JoinColumn(name = "contract_approval_route_grp_id", referencedColumnName = "id")
	@ApiModelProperty(value = "承認ルートグループマスタ（契約）", required = false, position = 3)
	private ApprovalRouteGrpMaster contractApprovalRouteGrpMaster;

	/**
	 * 商品構成マスタ
	 */
	@OneToMany(mappedBy = "productGrpMaster")
	@JsonIgnore
	@ApiModelProperty(value = "商品構成マスタ", required = false, position = 4)
	private List<ProductCompMaster> productCompMasterList;

	/**
	 * 商品グループ名
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "商品グループ名", required = true, position = 5, allowableValues = "range[255]")
	private String productGrpName;

	/**
	 * 積上げ可能期間(開始日)
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "積上げ可能期間(開始日)", required = true, position = 6)
	private Date effectiveFrom;

	/**
	 * 積上げ可能期間(終了日)
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "積上げ可能期間(終了日)", required = true, position = 7)
	private Date effectiveTo;

	/**
	 * 商品グループコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商品グループコード", required = false, position = 8, allowableValues = "range[0,255]")
	private String productGroupCd;

	/**
	 * 初期費内部振替対象フラグ
	 */
	@ApiModelProperty(value = "初期費内部振替対象フラグ", required = false, position = 9, allowableValues = "range[0,9]")
	private Integer initialExpensesInsideTransFlg;

	/**
	 * 期間売内部振替対象フラグ
	 */
	@ApiModelProperty(value = "期間売内部振替対象フラグ", required = false, position = 10, allowableValues = "range[0,9]")
	private Integer periodSellingInsideTransFlg;

	/**
	 * VUP連携商品フラグ
	 */
	@ApiModelProperty(value = "VUP連携商品フラグ", required = false, position = 11, allowableValues = "range[0,9]")
	private Integer vupLinkageProductFlg;

	/**
	 * 解約確定条件フラグ
	 */
	@ApiModelProperty(value = "解約確定条件フラグ", required = false, position = 12, allowableValues = "range[0,9]")
	private Integer cancelDefinitionConditionsFlg;

	/**
	 * 商品グループ識別子マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "product_grp_identifier_master_id", referencedColumnName = "id")
	@ApiModelProperty(value = "商品グループ識別子マスタ", required = false, position = 13)
	private ProductGrpIdentifierMaster productGrpIdentifierMaster;

	/**
	 * EDW大塚商会専用商品フラグ
	 */
	@ApiModelProperty(value = "EDW大塚商会専用商品フラグ", required = false, position = 14, allowableValues = "range[0,9]")
	private Integer edwOtsukaProductFlg;

	/**
	 * 解約予定日差分
	 */
	@Max(99)
	@ApiModelProperty(value = "解約予定日差分", required = false, position = 15, allowableValues = "range[0,99]")
	private Integer cancelScheduledDateDifference;

	/**
	 * 情報変更承認時手配チェックフラグ
	 */
	@ApiModelProperty(value = "情報変更承認時手配チェックフラグ", required = false, position = 16, allowableValues = "range[0,9]")
	private Integer infoChangeApprovalCheckArrangementFlg;

	/**
	 * 最長納期日数
	 */
	@Max(999)
	@ApiModelProperty(value = "最長納期日数", required = false, position = 17, allowableValues = "range[0,999]")
	private Integer longestDeliveryDate;

	/**
	 * EDWOR年額契約商品フラグ
	 */
	@ApiModelProperty(value = "EDWOR年額契約商品フラグ", required = false, position = 18, allowableValues = "range[0,9]")
	private Integer edwOrAnnualAmountProductFlg;

	/**
	 * 期間売外部請求対象フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "期間売外部請求対象フラグ", required = false, position = 19, allowableValues = "range[0,9]")
	private Integer periodSellingExternalBillingFlg;

	/**
	 * 契約承認後キャンセルフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "契約承認後キャンセルフラグ", required = false, position = 20, allowableValues = "range[0,9]")
	private Integer contractAfterApprovalCancelFlg;

	/**
	 * 満了解約後手配作成フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "満了解約後手配作成フラグ", required = false, position = 21, allowableValues = "range[0,9]")
	private Integer createArrangementForExpirationCancellationFlg;

	/**
	 * S&S作業依頼ルート区分
	 */
	@ApiModelProperty(value = "S&S作業依頼ルート区分", required = false, position = 22, allowableValues = "CSV作成(\"1\"), API連携(\"2\")")
	private SsWorkRequestRootDiv ssWorkRequestRootDiv;

	/**
	 * 電子契約対応フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "電子契約対応フラグ", required = false, position = 23, allowableValues = "range[0,9]")
	private Integer electronicContractFlg;

	/**
	 * 電子契約利用開始希望日制御区分
	 */
	@ApiModelProperty(value = "電子契約利用開始希望日制御区分", required = false, position = 24, allowableValues = "新規のみ入力可能_契約変更入力不可(\"1\"), 新規_契約変更ともに入力可能(\"2\"), 契約変更のみ入力可能_新規入力不可(\"3\"), 新規_契約変更いずれも入力不可(\"4\")")
	private ElectronicContractDesiredStartDateDiv electronicContractDesiredStartDateDiv;
}
