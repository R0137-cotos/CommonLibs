package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.ArcsPeriodSaleMainteProcStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.IsysoneProcStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentAdditionInfo.ChangeKbn;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentAdditionInfo.MigarateEquipmentDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ContractEquipmentAdditionInfoDto extends DtoBase {

	/**
	 * 変更区分
	 */
	@Schema(description = "変更区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "追加(\"1\"),変更(\"2\"),削除(\"3\")")
	private ChangeKbn changeKbn;

	/**
	 * MoM企事部ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM企事部ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * 企業名(漢字)
	 */
	@Size(max = 255)
	@Schema(description = "企業名(漢字)", required = false, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 事業所名(漢字)
	 */
	@Size(max = 255)
	@Schema(description = "事業所名(漢字)", required = false, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 企業名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "企業名(カナ)", required = false, allowableValues = "range[0,255]")
	private String companyNameKana;

	/**
	 * 事業所名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "事業所名(カナ)", required = false, allowableValues = "range[0,255]")
	private String officeNameKana;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@Schema(description = "住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String address;

	/**
	 * 番地(漢字)
	 */
	@Size(max = 255)
	@Schema(description = "番地(漢字)", required = false, allowableValues = "range[0,255]")
	private String streetBunch;
	/**
	 * 号名(漢字)
	 */
	@Size(max = 255)
	@Schema(description = "号名(漢字)", required = false, allowableValues = "range[0,255]")
	private String goumeiName;

	/**
	 * ビル名(漢字)
	 */
	@Size(max = 255)
	@Schema(description = "ビル名(漢字)", required = false, allowableValues = "range[0,255]")
	private String buildingName;

	/**
	 * フロア
	 */
	@Size(max = 255)
	@Schema(description = "フロア", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String floor;

	/**
	 * 法人前後区分
	 */
	@Size(max = 255)
	@Schema(description = "法人前後区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String crpStatusFrontRestKbn;

	/**
	 * 住所コ－ド
	 */
	@Size(max = 255)
	@Schema(description = "住所コ－ド", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String addrCd;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@Schema(description = "電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * 法人格コード
	 */
	@Size(max = 255)
	@Schema(description = "法人格コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String crpStatusKbn;

	/**
	 * 設置部課名(漢字)
	 */
	@Size(max = 255)
	@Schema(description = "設置部課名(漢字)", required = false, allowableValues = "range[0,255]")
	private String departmentName;

	/**
	 * 担当者名(漢字)
	 */
	@Size(max = 255)
	@Schema(description = "担当者名(漢字)", required = false, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 設置部課名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "設置部課名(カナ)", required = false, allowableValues = "range[0,255]")
	private String departmentNameKana;

	/**
	 * 担当者名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "担当者名(カナ)", required = false, allowableValues = "range[0,255]")
	private String picNameKana;

	/**
	 * 顧客番号
	 */
	@Size(max = 255)
	@Schema(description = "顧客番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerNumber;

	/**
	 * 申請者社員ｺｰﾄﾞ
	 */
	@Size(max = 255)
	@Schema(description = "申請者社員ｺｰﾄﾞ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String applicantUserNumber;

	/**
	 * 保守担当課所コード(MoM組織ID)
	 */
	@Size(max = 255)
	@Schema(description = "保守担当課所コード(MoM組織ID)", required = false, allowableValues = "range[0,255]")
	private String picMntMomOrgId;

	/**
	 * メンテ課所コード
	 */
	@Size(max = 255)
	@Schema(description = "メンテ課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String mntOrgCd;

	/**
	 * 保守担当CEコード
	 */
	@Size(max = 255)
	@Schema(description = "保守担当CEコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picMntCeCd;

	/**
	 * 保守担当MoM社員ID
	 */
	@Size(max = 255)
	@Schema(description = "保守担当MoM社員ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picMntMomEmployeeId;

	/**
	 * 保守部署名（漢字）
	 */
	@Size(max = 255)
	@Schema(description = "保守部署名（漢字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picMntSalesDepartmentName;

	/**
	 * 保守担当者名（漢字）
	 */
	@Size(max = 255)
	@Schema(description = "保守担当者名（漢字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picMntEmployeeName;

	/**
	 * 保守部署名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "保守部署名（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picMntSalesDepartmentNameKana;

	/**
	 * 保守担当者名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "保守担当者名（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picMntEmployeeNameKana;

	/**
	 * 導入担当課所コード(MoM組織ID)
	 */
	@Size(max = 255)
	@Schema(description = "導入担当課所コード(MoM組織ID)", required = false, allowableValues = "range[0,255]")
	private String picIntMomOrgId;

	/**
	 * 導入担当CEコード
	 */
	@Size(max = 255)
	@Schema(description = "導入担当CEコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picIntCeCd;

	/**
	 * 導入担当MoM社員ID
	 */
	@Size(max = 255)
	@Schema(description = "導入担当MoM社員ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picIntMomEmployeeId;

	/**
	 * 導入部署名（漢字）
	 */
	@Size(max = 255)
	@Schema(description = "導入部署名（漢字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picIntSalesDepartmentName;

	/**
	 * 導入担当者名（漢字）
	 */
	@Size(max = 255)
	@Schema(description = "導入担当者名（漢字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picIntEmployeeName;

	/**
	 * 導入部署名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "導入部署名（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picIntSalesDepartmentNameKana;

	/**
	 * 導入担当者名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "導入担当者名（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picIntEmployeeNameKana;

	/**
	 * Isys-One連携状態
	 */
	@Schema(description = "Isys-One連携状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"),CSV作成済み(\"1\"),連携済み(\"2\"),連携エラー(\"3\"),対象外(\"4\"),管理対象外(\"5\")")
	private IsysoneProcStatus isysoneProcStatus;

	/**
	 * IsysOne連携日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "IsysOne連携日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date isysoneLinkageAt;

	/**
	 * ARCS期間売保守処理状態
	 */
	@Schema(description = "ARCS期間売保守処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"),作成済み(\"1\"),作成不要(\"2\"),管理対象外(\"3\")")
	private ArcsPeriodSaleMainteProcStatus arcsPeriodSaleMainteProcStatus;

	/**
	 * ARCS期間売保守連携日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "ARCS期間売保守連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date arcsPeriodSaleMainteLinkageAt;

	/**
	 * 機器問い合わせ番号
	 */
	@Size(max = 255)
	@Schema(description = "機器問い合わせ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String equipmentContactNo;

	/**
	 * 累積契約機種ID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "累積契約機種ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long accumulationContractEquipmentId;

	/**
	 * 代表設置先フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "代表設置先フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer representationInstallationFlg;

	/**
	 * 取込フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "取込フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 契約形態
	 */
	@Size(max = 255)
	@Schema(description = "契約形態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractForm;

	/**
	 * 移行機種判別区分
	 */
	@Schema(description = "移行機種判別区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "移行_追加(\"1\"),移行_既存(\"2\"),移行_未確定(\"3\"),移行以外(\"4\")")
	private MigarateEquipmentDiv migarateEquipmentDiv;

	/**
	 * 点検診断月区分
	 */
	@Size(max = 255)
	@Schema(description = "点検診断月区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String inspectionMonthDiv;

	/**
	 * 点検診断月
	 */
	@Size(max = 255)
	@Schema(description = "点検診断月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String inspectionMonth;

	/**
	 * ARCS連携ファイル出力フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "ARCS連携ファイル出力フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer arcsLinkageFileOutputFlg;
}
