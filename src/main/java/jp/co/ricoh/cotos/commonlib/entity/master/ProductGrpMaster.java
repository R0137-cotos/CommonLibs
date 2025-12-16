
package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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

	@Description(value = "振替ヘッダー設定区分")
	public enum TransferHeaderSettingDiv {

		受付に受付担当ＣＥ設定("1"), 受注に統括コード設定("2"), 受注未設定("3");

		private final String text;

		private TransferHeaderSettingDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TransferHeaderSettingDiv fromString(String string) {
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

	@Description(value = "価格改定処理パターン区分")
	public enum PriceRevisionProcDiv {

		価格改定前品種を持つ明細を品種ごと価格改定後品種へ更新("1");

		private final String text;

		private PriceRevisionProcDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static PriceRevisionProcDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "契約機種品種紐づけ処理区分")
	public enum EquipmentItemLinkProcDiv {

		品種グループマスタで紐づける("1");

		private final String text;

		private EquipmentItemLinkProcDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static EquipmentItemLinkProcDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 商品グループマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_grp_master_seq")
	@SequenceGenerator(name = "product_grp_master_seq", sequenceName = "product_grp_master_seq", allocationSize = 1)
	@Schema(description = "商品グループマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 見積承認ルートグループマスタ
	 */
	@ManyToOne
	@JoinColumn(name = "estimation_approval_route_grp_id", referencedColumnName = "id")
	@Schema(description = "承認ルートグループマスタ（見積）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ApprovalRouteGrpMaster estimationApprovalRouteGrpMaster;

	/**
	 * 契約承認ルートグループマスタ
	 */
	@ManyToOne
	@JoinColumn(name = "contract_approval_route_grp_id", referencedColumnName = "id")
	@Schema(description = "承認ルートグループマスタ（契約）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ApprovalRouteGrpMaster contractApprovalRouteGrpMaster;

	/**
	 * 商品構成マスタ
	 */
	@OneToMany(mappedBy = "productGrpMaster")
	@JsonIgnore
	@Schema(description = "商品構成マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ProductCompMaster> productCompMasterList;

	/**
	 * 商品グループ名
	 */
	@Column(nullable = false)
	@Schema(description = "商品グループ名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[255]")
	private String productGrpName;

	/**
	 * 積上げ可能期間(開始日)
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "積上げ可能期間(開始日)", required = true)
	private Date effectiveFrom;

	/**
	 * 積上げ可能期間(終了日)
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "積上げ可能期間(終了日)", required = true)
	private Date effectiveTo;

	/**
	 * 商品グループコード
	 */
	@Size(max = 255)
	@Schema(description = "商品グループコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productGroupCd;

	/**
	 * 初期費内部振替対象フラグ
	 */
	@Schema(description = "初期費内部振替対象フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer initialExpensesInsideTransFlg;

	/**
	 * 期間売内部振替対象フラグ
	 */
	@Schema(description = "期間売内部振替対象フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer periodSellingInsideTransFlg;

	/**
	 * VUP連携商品フラグ
	 */
	@Schema(description = "VUP連携商品フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer vupLinkageProductFlg;

	/**
	 * 解約確定条件フラグ
	 */
	@Schema(description = "解約確定条件フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer cancelDefinitionConditionsFlg;

	/**
	 * 商品グループ識別子マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "product_grp_identifier_master_id", referencedColumnName = "id")
	@Schema(description = "商品グループ識別子マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ProductGrpIdentifierMaster productGrpIdentifierMaster;

	/**
	 * EDW大塚商会専用商品フラグ
	 */
	@Schema(description = "EDW大塚商会専用商品フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer edwOtsukaProductFlg;

	/**
	 * 解約予定日差分
	 */
	@Max(99)
	@Schema(description = "解約予定日差分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99]")
	private Integer cancelScheduledDateDifference;

	/**
	 * 情報変更承認時手配チェックフラグ
	 */
	@Schema(description = "情報変更承認時手配チェックフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer infoChangeApprovalCheckArrangementFlg;

	/**
	 * 最長納期日数
	 */
	@Max(999)
	@Schema(description = "最長納期日数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,999]")
	private Integer longestDeliveryDate;

	/**
	 * EDWOR年額契約商品フラグ
	 */
	@Schema(description = "EDWOR年額契約商品フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer edwOrAnnualAmountProductFlg;

	/**
	 * 期間売外部請求対象フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "期間売外部請求対象フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer periodSellingExternalBillingFlg;

	/**
	 * 契約承認後キャンセルフラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "契約承認後キャンセルフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer contractAfterApprovalCancelFlg;

	/**
	 * 満了解約後手配作成フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "満了解約後手配作成フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer createArrangementForExpirationCancellationFlg;

	/**
	 * S&S作業依頼ルート区分
	 */
	@Schema(description = "S&S作業依頼ルート区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "CSV作成(\"1\"), API連携(\"2\")")
	private SsWorkRequestRootDiv ssWorkRequestRootDiv;

	/**
	 * 振替ヘッダー設定区分
	 */
	@Schema(description = "振替ヘッダー設定区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "受付に受付担当ＣＥ設定(\"1\"), 受注に統括コード設定(\"2\"), 受注未設定(\"3\")")
	private TransferHeaderSettingDiv transferHeaderSettingDiv;

	/**
	 * 一括取込商品フラグ
	 */
	@Schema(description = "一括取込商品フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer batchImportProductFlg;

	/**
	 * 契約承認時確認API実行フラグ
	 */
	@Schema(description = "契約承認時確認API実行フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer contractApprovalCheckapiExecutionFlg;

	/**
	 * ISYSONE保守レポート対象外フラグ
	 */
	@Schema(description = "ISYSONE保守レポート対象外フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer isysoneMaintereportNoTargetFlg;

	/**
	 * 価格改定処理パターン区分
	 */
	@Schema(description = "価格改定処理パターン区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "価格改定前品種を持つ明細を品種ごと価格改定後品種へ更新(\"1\")")
	private PriceRevisionProcDiv priceRevisionProcDiv;

	/**
	 * 契約機種品種紐づけ処理区分
	 */
	@Schema(description = "契約機種品種紐づけ処理区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "品種グループマスタで紐づける(\"1\")")
	private EquipmentItemLinkProcDiv equipmentItemLinkProcDiv;

	/**
	 * 電子契約対応フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "電子契約対応フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer electronicContractFlg;

	/**
	 * 電子契約利用開始希望日制御区分
	 */
	@Schema(description = "電子契約利用開始希望日制御区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "新規のみ入力可能_契約変更入力不可(\"1\"), 新規_契約変更ともに入力可能(\"2\"), 契約変更のみ入力可能_新規入力不可(\"3\"), 新規_契約変更いずれも入力不可(\"4\")")
	private ElectronicContractDesiredStartDateDiv electronicContractDesiredStartDateDiv;
}
