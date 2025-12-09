package jp.co.ricoh.cotos.commonlib.entity.master;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
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

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 機種略号マスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "model_abbreviation_master")
public class ModelAbbreviationMaster extends EntityBaseMaster {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_abbreviation_master_seq")
	@SequenceGenerator(name = "model_abbreviation_master_seq", sequenceName = "model_abbreviation_master_seq", allocationSize = 1)
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999999999999]")
	private long id;

	/**
	 * 機種コード
	 */
	@Size(max = 255)
	@Schema(description = "機種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nModelCode;

	/**
	 * 機種群コード
	 */
	@Size(max = 255)
	@Schema(description = "機種群コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nModelCategoryCode;

	/**
	 * 機種名(漢字)
	 */
	@Size(max = 255)
	@Schema(description = "機種名(漢字)", required = false, allowableValues = "range[0,255]")
	private String nModelDesc;

	/**
	 * 機種名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "機種名(カナ)", required = false, allowableValues = "range[0,255]")
	private String nModelDescKana;

	/**
	 * 簡略機種名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "簡略機種名(カナ)", required = false, allowableValues = "range[0,255]")
	private String nSimpleModelDescKana;

	/**
	 * PM基準
	 */
	@Size(max = 255)
	@Schema(description = "PM基準", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nPmStandard;

	/**
	 * PM枚数
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "PM枚数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nPmSheets;

	/**
	 * PM枚数2
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "PM枚数2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nPmSheets2;

	/**
	 * PM期間
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "PM期間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nPmPeriod;

	/**
	 * PM期間2
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "PM期間2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nPmPeriod2;

	/**
	 * PMC
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "PMC", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nPmc;

	/**
	 * 分岐
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "分岐", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nTurning;

	/**
	 * PM回数
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "PM回数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nPmNum;

	/**
	 * 点検起算
	 */
	@Size(max = 255)
	@Schema(description = "点検起算", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nMaintenanceReckon;

	/**
	 * 点検分岐
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "点検分岐", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nMaintenanceTurning;

	/**
	 * 以下PMC
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "以下PMC", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nBelowPmc;

	/**
	 * 以上PMC
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "以上PMC", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nOverPmc;

	/**
	 * 以下枚数
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "以下枚数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nBelowSheets;

	/**
	 * 以上枚数
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "以上枚数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nOverSheets;

	/**
	 * 点検枚数
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "点検枚数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nInspectionSheets;

	/**
	 * アローワンス
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "アローワンス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nAllowance;

	/**
	 * 限界
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "限界", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nLimit;

	/**
	 * 保証期間
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "保証期間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nGuaranteeTerm;

	/**
	 * 換算係数
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "換算係数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer nConvFactor;

	/**
	 * PM標準作業時間(分)
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "PM標準作業時間(分)", required = false, allowableValues = "range[0,99999]")
	private Integer nPmStandardWorkMin;

	/**
	 * 点検標準作業(時間)
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "点検標準作業(時間)", required = false, allowableValues = "range[0,99999]")
	private Integer nMaintStandardWorkHour;

	/**
	 * シリーズコード
	 */
	@Size(max = 255)
	@Schema(description = "シリーズコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nSeriesCode;

	/**
	 * 重点機種区分
	 */
	@Size(max = 255)
	@Schema(description = "重点機種区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nImportantModeType;

	/**
	 * 感熱区分
	 */
	@Size(max = 255)
	@Schema(description = "感熱区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nThermalType;

	/**
	 * 委託料商品コード
	 */
	@Size(max = 255)
	@Schema(description = "委託料商品コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nTrustProdCode;

	/**
	 * 年間保守料
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "年間保守料", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal nMaintFeeYear;

	/**
	 * 月間保守料
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "月間保守料", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal nMaintFeeMonth;

	/**
	 * 初年度保証費(年)
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "初年度保証費(年)", required = false, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal nFirstGuaranteeYear;

	/**
	 * 初年度保証費(月)
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "初年度保証費(月)", required = false, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal nFirstGuaranteeMonth;

	/**
	 * 作業料
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "作業料", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal nWorkPrice;

	/**
	 * 基本料
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "基本料", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal nBasePrice;

	/**
	 * FAXキット料
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "FAXキット料", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal nFaxKitCharge;

	/**
	 * Hシリーズ請求区分
	 */
	@Size(max = 255)
	@Column(name = "n_h_series_inv_type")
	@Schema(description = "Hシリーズ請求区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nHSeriesInvType;

	/**
	 * OEM区分
	 */
	@Size(max = 255)
	@Schema(description = "OEM区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nOemType;

	/**
	 * システムデバイス区分
	 */
	@Size(max = 255)
	@Schema(description = "システムデバイス区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nSystemDeviceType;

	/**
	 * 更新区分
	 */
	@Size(max = 255)
	@Schema(description = "更新区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nModifyType;

	/**
	 * 更新年月日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "更新年月日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date nModifyDate;

	/**
	 * 登録年月日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "登録年月日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date nNewDate;

	/**
	 * リモート診断区分
	 */
	@Size(max = 255)
	@Schema(description = "リモート診断区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nRemoteType;

	/**
	 * 機種グループ
	 */
	@Size(max = 255)
	@Schema(description = "機種グループ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nModelGroup;

	/**
	 * 売掛機種群コード
	 */
	@Size(max = 255)
	@Schema(description = "売掛機種群コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nCreditModelCatCode;

	/**
	 * 機種群名(漢字)
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(漢字)", required = false, allowableValues = "range[0,255]")
	private String nModelCategoryDesc;

	/**
	 * 機種群名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(カナ)", required = false, allowableValues = "range[0,255]")
	private String nModelCategoryDescKana;

	/**
	 * 機種簡略
	 */
	@Size(max = 255)
	@Schema(description = "機種簡略", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nSimpleModel;

	/**
	 * PCリンク区分
	 */
	@Size(max = 255)
	@Schema(description = "PCリンク区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nPcLinkType;

	/**
	 * 可能保守形態
	 */
	@Size(max = 255)
	@Schema(description = "可能保守形態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nPossibleMaintType;

	/**
	 * 可能機番(始)
	 */
	@Size(max = 255)
	@Schema(description = "可能機番(始)", required = false, allowableValues = "range[0,255]")
	private String nPossMechNoFrom;

	/**
	 * 可能機番(終)
	 */
	@Size(max = 255)
	@Schema(description = "可能機番(終)", required = false, allowableValues = "range[0,255]")
	private String nPossMechNoTo;

	/**
	 * PPF基準
	 */
	@Size(max = 255)
	@Schema(description = "PPF基準", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nPpfStandard;

	/**
	 * 発売年月
	 */
	@Size(max = 255)
	@Schema(description = "発売年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nSellMonth;

	/**
	 * 出荷打切年月
	 */
	@Size(max = 255)
	@Schema(description = "出荷打切年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nCloseMonth;

	/**
	 * 打切告知予定年月
	 */
	@Size(max = 255)
	@Schema(description = "打切告知予定年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nClosePlanMonth;

	/**
	 * 打切告知開始年月
	 */
	@Size(max = 255)
	@Schema(description = "打切告知開始年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nCloseStartMonth;

	/**
	 * L保守開始年月
	 */
	@Size(max = 255)
	@Column(name = "n_l_maint_start_month")
	@Schema(description = "L保守開始年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nLMaintStartMonth;

	/**
	 * AS機フラグ
	 */
	@Size(max = 255)
	@Schema(description = "L保守開始年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nAsFlg;

	/**
	 * RSC機種群
	 */
	@Size(max = 255)
	@Schema(description = "RSC機種群", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nRscModelCategory;

	/**
	 * 経営分類1
	 */
	@Size(max = 255)
	@Schema(description = "経営分類1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nManageGroup1;

	/**
	 * 保証形態1
	 */
	@Size(max = 255)
	@Schema(description = "保証形態1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGuaranteeType1;

	/**
	 * 機種群名(カナ)1
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(カナ)1", required = false, allowableValues = "range[0,255]")
	private String nModeCatDescKana1;

	/**
	 * 保証形態2
	 */
	@Size(max = 255)
	@Schema(description = "保証形態2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGuaranteeType2;

	/**
	 * 機種群名(カナ)2
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(カナ)2", required = false, allowableValues = "range[0,255]")
	private String nModeCatDescKana2;

	/**
	 * 保証形態3
	 */
	@Size(max = 255)
	@Schema(description = "保証形態3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGuaranteeType3;

	/**
	 * 機種群名(カナ)3
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(カナ)3", required = false, allowableValues = "range[0,255]")
	private String nModeCatDescKana3;

	/**
	 * 保証形態4
	 */
	@Size(max = 255)
	@Schema(description = "保証形態4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGuaranteeType4;

	/**
	 * 機種群名(カナ)4
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(カナ)4", required = false, allowableValues = "range[0,255]")
	private String nModeCatDescKana4;

	/**
	 * 保証形態5
	 */
	@Size(max = 255)
	@Schema(description = "保証形態5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGuaranteeType5;

	/**
	 * 機種群名(カナ)5
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(カナ)5", required = false, allowableValues = "range[0,255]")
	private String nModeCatDescKana5;

	/**
	 * 保証形態6
	 */
	@Size(max = 255)
	@Schema(description = "保証形態6", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGuaranteeType6;

	/**
	 * 機種群名(カナ)6
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(カナ)6", required = false, allowableValues = "range[0,255]")
	private String nModeCatDescKana6;

	/**
	 * 保証形態7
	 */
	@Size(max = 255)
	@Schema(description = "保証形態7", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGuaranteeType7;

	/**
	 * 機種群名(カナ)7
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(カナ)7", required = false, allowableValues = "range[0,255]")
	private String nModeCatDescKana7;

	/**
	 * 保証形態8
	 */
	@Size(max = 255)
	@Schema(description = "保証形態8", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGuaranteeType8;

	/**
	 * 機種群名(カナ)8
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(カナ)8", required = false, allowableValues = "range[0,255]")
	private String nModeCatDescKana8;

	/**
	 * 保証形態9
	 */
	@Size(max = 255)
	@Schema(description = "保証形態9", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGuaranteeType9;

	/**
	 * 機種群名(カナ)9
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(カナ)9", required = false, allowableValues = "range[0,255]")
	private String nModeCatDescKana9;

	/**
	 * 保証形態10
	 */
	@Size(max = 255)
	@Schema(description = "保証形態10", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGuaranteeType10;

	/**
	 * 機種群名(カナ)10
	 */
	@Size(max = 255)
	@Schema(description = "機種群名(カナ)10", required = false, allowableValues = "range[0,255]")
	private String nModeCatDescKana10;

	/**
	 * 可能保証内契約形態
	 */
	@Size(max = 255)
	@Schema(description = "可能保証内契約形態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGuarantContractType;

	/**
	 * ISO対象機区分
	 */
	@Size(max = 255)
	@Schema(description = "ISO対象機区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nIsoTargetType;

	/**
	 * ISO対象設定年月日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "更新年月日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date nIsoTargetDate;

	/**
	 * 仮機種コード
	 */
	@Size(max = 255)
	@Schema(description = "仮機種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nTmpModelCode;

	/**
	 * 仮機種群コード
	 */
	@Size(max = 255)
	@Schema(description = "仮機種群コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nTmpModelCategoryCode;

	/**
	 * PA-PMフラグ
	 */
	@Size(max = 255)
	@Schema(description = "PA-PMフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nPaPmFlg;

	/**
	 * PA-PM予定日数
	 */
	@Size(max = 255)
	@Schema(description = "PA-PM予定日数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nPaPmPlanDays;
}
