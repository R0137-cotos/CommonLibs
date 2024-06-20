package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.ArcsPeriodSaleMainteProcStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.IsysoneProcStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentAdditionInfo.ChangeKbn;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentAdditionInfo.MigarateEquipmentDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約機種付加情報の洗い替え履歴を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_equipment_addition_info_refresh_his")
public class ContractEquipmentAdditionInfoRefreshHis extends EntityBase {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_equipment_addition_info_refresh_his_seq")
	@SequenceGenerator(name = "contract_equipment_addition_info_refresh_his_seq", sequenceName = "contract_equipment_addition_info_refresh_his_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "契約", required = true, position = 2)
	private Contract contract;

	/**
	 * 変更区分
	 */
	@ApiModelProperty(value = "変更区分", required = false, position = 3, allowableValues = "追加(\"1\"),変更(\"2\"),削除(\"3\")")
	private ChangeKbn changeKbn;

	/**
	 * MoM企事部ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MoM企事部ID", required = false, position = 4, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * 企業名(漢字)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名(漢字)", required = false, position = 5, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 事業所名(漢字)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "事業所名(漢字)", required = false, position = 6, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 企業名(カナ)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名(カナ)", required = false, position = 7, allowableValues = "range[0,255]")
	private String companyNameKana;

	/**
	 * 事業所名(カナ)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "事業所名(カナ)", required = false, position = 8, allowableValues = "range[0,255]")
	private String officeNameKana;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "郵便番号", required = false, position = 9, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "住所", required = false, position = 10, allowableValues = "range[0,255]")
	private String address;

	/**
	 * 番地(漢字)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "番地(漢字)", required = false, position = 11, allowableValues = "range[0,255]")
	private String streetBunch;
	/**
	 * 号名(漢字)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "号名(漢字)", required = false, position = 12, allowableValues = "range[0,255]")
	private String goumeiName;

	/**
	 * ビル名(漢字)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ビル名(漢字)", required = false, position = 13, allowableValues = "range[0,255]")
	private String buildingName;

	/**
	 * フロア
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "フロア", required = false, position = 14, allowableValues = "range[0,255]")
	private String floor;

	/**
	 * 法人前後区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "法人前後区分", required = false, position = 15, allowableValues = "range[0,255]")
	private String crpStatusFrontRestKbn;

	/**
	 * 住所コ－ド
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "住所コ－ド", required = false, position = 16, allowableValues = "range[0,255]")
	private String addrCd;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "電話番号", required = false, position = 17, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * 法人格コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "法人格コード", required = false, position = 18, allowableValues = "range[0,255]")
	private String crpStatusKbn;

	/**
	 * 設置部課名(漢字)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置部課名(漢字)", required = false, position = 19, allowableValues = "range[0,255]")
	private String departmentName;

	/**
	 * 担当者名(漢字)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者名(漢字)", required = false, position = 20, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 設置部課名(カナ)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置部課名(カナ)", required = false, position = 21, allowableValues = "range[0,255]")
	private String departmentNameKana;

	/**
	 * 担当者名(カナ)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者名(カナ)", required = false, position = 22, allowableValues = "range[0,255]")
	private String picNameKana;

	/**
	 * 顧客番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客番号", required = false, position = 23, allowableValues = "range[0,255]")
	private String customerNumber;

	/**
	 * 申請者社員ｺｰﾄﾞ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "申請者社員ｺｰﾄﾞ", required = false, position = 24, allowableValues = "range[0,255]")
	private String applicantUserNumber;

	/**
	 * 保守担当課所コード(MoM組織ID)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "保守担当課所コード(MoM組織ID)", required = false, position = 25, allowableValues = "range[0,255]")
	private String picMntMomOrgId;

	/**
	 * メンテ課所コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メンテ課所コード", required = false, position = 26, allowableValues = "range[0,255]")
	private String mntOrgCd;

	/**
	 * 保守担当CEコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "保守担当CEコード", required = false, position = 27, allowableValues = "range[0,255]")
	private String picMntCeCd;

	/**
	 * 保守担当MoM社員ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "保守担当MoM社員ID", required = false, position = 28, allowableValues = "range[0,255]")
	private String picMntMomEmployeeId;

	/**
	 * 保守部署名（漢字）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "保守部署名（漢字）", required = false, position = 29, allowableValues = "range[0,255]")
	private String picMntSalesDepartmentName;

	/**
	 * 保守担当者名（漢字）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "保守担当者名（漢字）", required = false, position = 30, allowableValues = "range[0,255]")
	private String picMntEmployeeName;

	/**
	 * 保守部署名（カナ）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "保守部署名（カナ）", required = false, position = 31, allowableValues = "range[0,255]")
	private String picMntSalesDepartmentNameKana;

	/**
	 * 保守担当者名（カナ）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "保守担当者名（カナ）", required = false, position = 32, allowableValues = "range[0,255]")
	private String picMntEmployeeNameKana;

	/**
	 * 導入担当課所コード(MoM組織ID)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "導入担当課所コード(MoM組織ID)", required = false, position = 33, allowableValues = "range[0,255]")
	private String picIntMomOrgId;

	/**
	 * 導入担当CEコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "導入担当CEコード", required = false, position = 34, allowableValues = "range[0,255]")
	private String picIntCeCd;

	/**
	 * 導入担当MoM社員ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "導入担当MoM社員ID", required = false, position = 35, allowableValues = "range[0,255]")
	private String picIntMomEmployeeId;

	/**
	 * 導入部署名（漢字）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "導入部署名（漢字）", required = false, position = 36, allowableValues = "range[0,255]")
	private String picIntSalesDepartmentName;

	/**
	 * 導入担当者名（漢字）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "導入担当者名（漢字）", required = false, position = 37, allowableValues = "range[0,255]")
	private String picIntEmployeeName;

	/**
	 * 導入部署名（カナ）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "導入部署名（カナ）", required = false, position = 38, allowableValues = "range[0,255]")
	private String picIntSalesDepartmentNameKana;

	/**
	 * 導入担当者名（カナ）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "導入担当者名（カナ）", required = false, position = 39, allowableValues = "range[0,255]")
	private String picIntEmployeeNameKana;

	/**
	 * Isys-One連携状態
	 */
	@ApiModelProperty(value = "Isys-One連携状態", required = false, position = 40, allowableValues = "未処理(\"0\"),CSV作成済み(\"1\"),連携済み(\"2\"),連携エラー(\"3\"),対象外(\"4\"),管理対象外(\"5\")")
	private IsysoneProcStatus isysoneProcStatus;

	/**
	 * IsysOne連携日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "IsysOne連携日時", required = false, position = 41)
	private Date isysoneLinkageAt;

	/**
	 * ARCS期間売保守処理状態
	 */
	@ApiModelProperty(value = "ARCS期間売保守処理状態", required = false, position = 42, allowableValues = "未作成(\"0\"),作成済み(\"1\"),作成不要(\"2\"),管理対象外(\"3\")")
	private ArcsPeriodSaleMainteProcStatus arcsPeriodSaleMainteProcStatus;

	/**
	 * ARCS期間売保守連携日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "ARCS期間売保守連携日", required = false, position = 43)
	private Date arcsPeriodSaleMainteLinkageAt;

	/**
	 * 機器問い合わせ番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "機器問い合わせ番号", required = false, position = 44, allowableValues = "range[0,255]")
	private String equipmentContactNo;

	/**
	 * 累積契約機種ID
	 */
	@Min(0)
	@ApiModelProperty(value = "累積契約機種ID", required = false, position = 45, allowableValues = "range[0,9223372036854775807]")
	private Long accumulationContractEquipmentId;

	/**
	 * 代表設置先フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "代表設置先フラグ", required = false, position = 46, allowableValues = "range[0,9]")
	private Integer representationInstallationFlg;

	/**
	 * 取込フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "取込フラグ", required = false, position = 47, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 契約形態
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約形態", required = false, position = 48, allowableValues = "range[0,255]")
	private String contractForm;

	/**
	 * 移行機種判別区分
	 */
	@ApiModelProperty(value = "移行機種判別区分", required = false, position = 49, allowableValues = "移行_追加(\"1\"),移行_既存(\"2\"),移行_未確定(\"3\"),移行以外(\"4\")")
	private MigarateEquipmentDiv migarateEquipmentDiv;

	/**
	 * 点検診断月区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "点検診断月区分", required = false, position = 50, allowableValues = "range[0,255]")
	private String inspectionMonthDiv;

	/**
	 * 点検診断月
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "点検診断月", required = false, position = 51, allowableValues = "range[0,255]")
	private String inspectionMonth;

	/**
	 * 洗替日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "洗替日時", required = false, position = 52)
	private Date refreshedAt;

	/**
	 * 更新元バッチID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "更新元バッチID", required = false, position = 53, allowableValues = "range[0,255]")
	private String updateBatchId;

}
