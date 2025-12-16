
package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster.ElectronicContractDesiredStartDateDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster.EquipmentItemLinkProcDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster.PriceRevisionProcDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster.SsWorkRequestRootDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster.TransferHeaderSettingDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 商品グループマスタDto
 * 商品グループマスタに紐づくエンティティは持たない
 */
@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_grp_master")
public class ProductGrpMasterDto extends EntityBaseMaster {

	/**
	 * 商品グループマスタID
	 */
	@Id
	@Schema(description = "商品グループマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 見積承認ルートグループID
	 */
	@Column(name = "estimation_approval_route_grp_id")
	@Schema(description = "見積承認ルートグループID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long estimationApprovalRouteGrpId;

	/**
	 * 契約承認ルートグループID
	 */
	@Column(name = "contract_approval_route_grp_id")
	@Schema(description = "契約承認ルートグループID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long contractApprovalRouteGrpId;

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
	 * 商品グループ識別子マスタID
	 */
	@Column(name = "product_grp_identifier_master_id")
	@Schema(description = "商品グループ識別子マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long productGrpIdentifierMasterId;

	/**
	 * EDW大塚商会専用商品フラグ
	 */
	@Schema(description = "EDW大塚商会専用商品フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer edwOtsukaProductFlg;

	/**
	 * 解約予定日差分
	 */
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
	@Schema(description = "期間売外部請求対象フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer periodSellingExternalBillingFlg;

	/**
	 * 契約承認後キャンセルフラグ
	 */
	@Schema(description = "契約承認後キャンセルフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer contractAfterApprovalCancelFlg;

	/**
	 * 満了解約後手配作成フラグ
	 */
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
