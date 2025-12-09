package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * RJ社員情報マスタ
 */
@Entity
@Data
@Table(name = "mv_employee_master")
public class MvEmployeeMaster {

	/**
	 * MOM社員ID
	 */
	@Id
	@Column(length = 24, name = "emp_id")
	@Schema(description = "MOM社員ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,24]")
	private String momEmployeeId;

	/**
	 * MOM会社ID
	 */
	@Column(length = 18, name = "corp_id")
	@Schema(description = "MOM会社ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,18]")
	private String momCompanyId;

	/**
	 * SINGLE_USER_ID
	 */
	@Column(length = 300, name = "singleuser_id")
	@Schema(description = "SINGLE_USER_ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String singleUserId;

	/**
	 * 業務用氏名（姓）
	 */
	@Column(length = 300)
	@Schema(description = "業務用氏名（姓）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String jobname1;

	/**
	 * 業務用氏名（名）
	 */
	@Column(length = 300)
	@Schema(description = "業務用氏名（名）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String jobname2;

	/**
	 * 業務用氏名（姓カナ）
	 */
	@Column(length = 300)
	@Schema(description = "業務用氏名（姓カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String kanaJobname1;

	/**
	 * 業務用氏名（名カナ）
	 */
	@Column(length = 300)
	@Schema(description = "業務用氏名（名カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String kanaJobname2;

	/**
	 * EMAILアドレス
	 */
	@Column(length = 300, name = "email")
	@Schema(description = "EMAILアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String mailAddress;

	/**
	 * 電話番号
	 */
	@Column(length = 600, name = "tel")
	@Schema(description = "電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String phoneNumber;

	/**
	 * FAX番号
	 */
	@Column(length = 600, name = "fax")
	@Schema(description = "FAX番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String faxNumber;

	/**
	 * CEコード
	 */
	@Column(length = 15)
	@Schema(description = "CEコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String ceCd;

	/**
	 * 所属組織_MoM組織ID
	 */
	@Column(length = 21, name = "org_id")
	@Schema(description = "所属組織_MoM組織ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String momOrgId;

	/**
	 * 退職区分
	 */
	@Column(length = 3)
	@Schema(description = "退職区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String retiredKbn;

	/**
	 * 利用者区分
	 */
	@Column(length = 3)
	@Schema(description = "利用者区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String userType;

	/**
	 * RINGS販社コード
	 */
	@Column(length = 9)
	@Schema(description = "RINGS販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String ringsHanshCd;

	/**
	 * RINGS社員コード
	 */
	@Column(length = 15)
	@Schema(description = "RINGS社員コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String ringsEmpCd;

	/**
	 * 社員番号
	 */
	@Column(length = 300, name = "emb_user_number")
	@Schema(description = "社員番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String userNumber;

	/**
	 * 業務用上司
	 */
	@Column(length = 24)
	@Schema(description = "業務用上司", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String embJobreaderEmpId;

	/**
	 * 業務センターCD
	 */
	@Column(length = 9)
	@Schema(description = "業務センターCD", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String embOperationCenterCd;

	/**
	 * NotesID
	 */
	@Column(length = 300)
	@Schema(description = "NotesID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String notesId;

	/**
	 * 登録日時
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "登録日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date registerDate;

	/**
	 * 更新日時
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "更新日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date updatingDate;

	/**
	 * 登録者ID
	 */
	@Column(length = 24)
	@Schema(description = "登録者ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String registerUserId;

	/**
	 * 登録者名
	 */
	@Column(length = 300)
	@Schema(description = "登録者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String registerUserName;

	/**
	 * 更新者ID
	 */
	@Column(length = 24)
	@Schema(description = "更新者ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String updatingUserId;

	/**
	 * 更新者名
	 */
	@Column(length = 300)
	@Schema(description = "更新者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String updatingUserName;

	/**
	 * 販社コード
	 */
	@Column(length = 9)
	@Schema(description = "販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String hanshCd;

	/**
	 * 従業員番号
	 */
	@Column(length = 300, name = "emb_emp_number")
	@Schema(description = "従業員番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String empNumber;

	/**
	 * 専門資格コード
	 */
	@Column(length = 36)
	@Schema(description = "専門資格コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,36]")
	private String embCompetenceCd;

	/**
	 * 派遣区分コード
	 */
	@Column(length = 3)
	@Schema(description = "派遣区分コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String embVisitKbn;

	/**
	 * 入社年月日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "入社年月日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date embEnterDate;

	/**
	 * 有効開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "有効開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date startDate;

	/**
	 * 有効終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "有効終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date endDate;

	/**
	 * MOM組織名称
	 */
	@Column(length = 3000)
	@Schema(description = "MOM組織名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3000]")
	private String orgBaseName;

	/**
	 * MOM組織名称（カナ）
	 */
	@Column(length = 3000)
	@Schema(description = "MOM組織名称（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3000]")
	private String orgBaseKanaName;

	/**
	 * CUBIC会社コード
	 */
	@Column(length = 720)
	@Schema(description = "CUBIC会社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String cubicCorpId;

	/**
	 * CUBIC部門コード
	 */
	@Column(length = 75)
	@Schema(description = "CUBIC部門コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String cubicOrgId;

	/**
	 * RINGS販社コード
	 */
	@Column(length = 9)
	@Schema(description = "RINGS販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String orgRingsHanshCd;

	/**
	 * RINGS課所コード
	 */
	@Column(length = 12)
	@Schema(description = "RINGS課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,12]")
	private String ringsSectCd;

	/**
	 * 郵便番号
	 */
	@Column(length = 21, name = "post_no")
	@Schema(description = "郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String postNumber;

	/**
	 * 住所コード
	 */
	@Column(length = 33)
	@Schema(description = "住所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,33]")
	private String addrCd;

	/**
	 * 番地
	 */
	@Column(length = 90)
	@Schema(description = "番地", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,90]")
	private String street;

	/**
	 * ビル名
	 */
	@Column(length = 90)
	@Schema(description = "ビル名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,90]")
	private String building;

	/**
	 * 電話番号
	 */
	@Column(length = 54, name = "org_tel")
	@Schema(description = "電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,54]")
	private String orgPhoneNumber;

	/**
	 * FAX番号
	 */
	@Column(length = 54, name = "org_fax")
	@Schema(description = "FAX番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,54]")
	private String orgFaxNumber;

	/**
	 * 組織長
	 */
	@Column(length = 24)
	@Schema(description = "組織長", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String orgLeaderEmpId;

	/**
	 * 組織区分
	 */
	@Column(length = 6)
	@Schema(description = "組織区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String orgKbn;

	/**
	 * 登録ステータス区分
	 */
	@Column(length = 6)
	@Schema(description = "登録ステータス区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String registerKbn;

	/**
	 * 組織改編後MOM組織ID
	 */
	@Column(length = 21)
	@Schema(description = "組織改編後MOM組織ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String repairOrgId;

	/**
	 * メイン在庫区
	 */
	@Column(length = 21)
	@Schema(description = "メイン在庫区", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String stokOrgId;

	/**
	 * 経費負担組織
	 */
	@Column(length = 21)
	@Schema(description = "経費負担組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String experOrgId;

	/**
	 * 原価調整組織
	 */
	@Column(length = 21)
	@Schema(description = "原価調整組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String costOrgId;

	/**
	 * MOM設置届先サイトID
	 */
	@Column(length = 45)
	@Schema(description = "MOM設置届先サイトID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,45]")
	private String sendSiteId;

	/**
	 * 預かり在庫区区分
	 */
	@Column(length = 6)
	@Schema(description = "預かり在庫区区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String stokKbn;

	/**
	 * 通過在庫区対象フラグ
	 */
	@Column(length = 3)
	@Schema(description = "通過在庫区対象フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String currencyFlg;

	/**
	 * 管理委託区分
	 */
	@Column(length = 6)
	@Schema(description = "管理委託区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String entrustKbn;

	/**
	 * 組織内共有設定フラグ
	 */
	@Column(length = 3)
	@Schema(description = "組織内共有設定フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String orgComFlg;

	/**
	 * 商流決定フラグ
	 */
	@Column(length = 3)
	@Schema(description = "商流決定フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String comMovFlg;

	/**
	 * MOM組織ソート順
	 */
	@Schema(description = "MOM組織ソート順", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer momSort;

	/**
	 * NS1組織コード1
	 */
	@Column(length = 60)
	@Schema(description = "NS1組織コード1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,60]")
	private String orgCd1;

	/**
	 * NS1組織コード2
	 */
	@Column(length = 60)
	@Schema(description = "NS1組織コード2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,60]")
	private String orgCd2;

	/**
	 * NS1組織コード3
	 */
	@Column(length = 60)
	@Schema(description = "NS1組織コード3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,60]")
	private String orgCd3;

	/**
	 * NS1組織コード4
	 */
	@Column(length = 60)
	@Schema(description = "NS1組織コード4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,60]")
	private String orgCd4;

	/**
	 * NS1組織コード5
	 */
	@Column(length = 60)
	@Schema(description = "NS1組織コード5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,60]")
	private String orgCd5;

	/**
	 * 適用年月日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "適用年月日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date aplDate;

	/**
	 * 組織レベル
	 */
	@Column(name = "org_level")
	@Schema(description = "組織レベル", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer orgHierarchyLevel;

	/**
	 * 組織正式名称
	 */
	@Column(length = 3000, name = "org_name")
	@Schema(description = "組織正式名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3000]")
	private String orgFullname;

	/**
	 * 組織正式名称（カナ）
	 */
	@Column(length = 3000)
	@Schema(description = "組織正式名称（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3000]")
	private String orgKanaName;

	/**
	 * 組織簡略名称
	 */
	@Column(length = 3000, name = "abbr_name")
	@Schema(description = "組織簡略名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3000]")
	private String orgName;

	/**
	 * 正式名称1
	 */
	@Column(length = 600)
	@Schema(description = "正式名称1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String orgName1;

	/**
	 * 正式名称2
	 */
	@Column(length = 600)
	@Schema(description = "正式名称2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String orgName2;

	/**
	 * 正式名称3
	 */
	@Column(length = 600)
	@Schema(description = "正式名称3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String orgName3;

	/**
	 * 正式名称4
	 */
	@Column(length = 600)
	@Schema(description = "正式名称4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String orgName4;

	/**
	 * 正式名称5
	 */
	@Column(length = 600)
	@Schema(description = "正式名称5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String orgName5;

	/**
	 * 簡略名称1
	 */
	@Column(length = 600)
	@Schema(description = "簡略名称1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String abbrName1;

	/**
	 * 簡略名称2
	 */
	@Column(length = 600)
	@Schema(description = "簡略名称2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String abbrName2;

	/**
	 * 簡略名称3
	 */
	@Column(length = 600)
	@Schema(description = "簡略名称3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String abbrName3;

	/**
	 * 簡略名称4
	 */
	@Column(length = 600)
	@Schema(description = "簡略名称4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String abbrName4;

	/**
	 * 簡略名称5
	 */
	@Column(length = 600)
	@Schema(description = "簡略名称5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,600]")
	private String abbrName5;

	/**
	 * 経費コード
	 */
	@Column(length = 21)
	@Schema(description = "経費コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String experCd;

	/**
	 * 課所名称
	 */
	@Column(length = 60)
	@Schema(description = "課所名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,60]")
	private String orrRingsOrgName;

	/**
	 * 課所名称（カナ）
	 */
	@Column(length = 45)
	@Schema(description = "課所名称（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,45]")
	private String orrRingsOrgKanaName;

	/**
	 * 売上発生区分
	 */
	@Column(length = 6)
	@Schema(description = "売上発生区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String orrSalesOccurKbn;

	/**
	 * 在庫発生区分
	 */
	@Column(length = 6)
	@Schema(description = "在庫発生区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String orrStockOccurKbn;

	/**
	 * 経理発生区分
	 */
	@Column(length = 6)
	@Schema(description = "経理発生区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String orrManageOccurKbn;

	/**
	 * 売上集計区分
	 */
	@Column(length = 6)
	@Schema(description = "売上集計区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String orrSalesTotalKbn;

	/**
	 * 在庫集計区分
	 */
	@Column(length = 6)
	@Schema(description = "在庫集計区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String orrStockTotalKbn;

	/**
	 * 経理集計区分
	 */
	@Column(length = 6)
	@Schema(description = "経理集計区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String orrManageTotalKbn;

	/**
	 * 売上集計レベル
	 */
	@Column(length = 30)
	@Schema(description = "売上集計レベル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,30]")
	private String orrSalesTotalLevel;

	/**
	 * 在庫集計レベル
	 */
	@Column(length = 30)
	@Schema(description = "在庫集計レベル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,30]")
	private String orrStockTotalLevel;

	/**
	 * 経理集計レベル
	 */
	@Column(length = 30)
	@Schema(description = "経理集計レベル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,30]")
	private String orrManageTotalLevel;

	/**
	 * 売上集計組織
	 */
	@Column(length = 21)
	@Schema(description = "売上集計組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String orrSalesTotalOrg;

	/**
	 * 経理集計組織
	 */
	@Column(length = 21)
	@Schema(description = "経理集計組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String orrManageTotalOrg;

	/**
	 * 出納管理組織
	 */
	@Column(length = 21)
	@Schema(description = "出納管理組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String orrCbAdmOrg;

	/**
	 * 財源組織
	 */
	@Column(length = 21)
	@Schema(description = "財源組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String orrFinancialOrg;

	/**
	 * 登録日時
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "登録日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date orgRegisterDate;

	/**
	 * 更新日時
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "更新日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date orgUpdatingDate;

	/**
	 * 登録者ID
	 */
	@Column(length = 24)
	@Schema(description = "登録者ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String orgRegisterUserId;

	/**
	 * 登録者名
	 */
	@Column(length = 300)
	@Schema(description = "登録者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String orgRegisterUserName;

	/**
	 * 更新者ID
	 */
	@Column(length = 24)
	@Schema(description = "更新者ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String orgUpdatingUserId;

	/**
	 * 更新者名
	 */
	@Column(length = 300)
	@Schema(description = "更新者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String orgUpdatingUserName;

	/**
	 * 住所郵便番号
	 */
	@Column(length = 150)
	@Schema(description = "住所郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,150]")
	private String adsPostNum;

	/**
	 * 住所都道府県名_漢字
	 */
	@Column(length = 30)
	@Schema(description = "住所都道府県名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,30]")
	private String tdhknNmKnji;

	/**
	 * 住所市区郡町村名_漢字
	 */
	@Column(length = 78)
	@Schema(description = "住所市区郡町村名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,78]")
	private String skugnchosnKnji;

	/**
	 * 住所大字・通称名_漢字
	 */
	@Column(length = 114)
	@Schema(description = "住所大字・通称名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,114]")
	private String owaTusyoKnji;

	/**
	 * 住所字名・丁目_漢字
	 */
	@Column(length = 78)
	@Schema(description = "住所字名・丁目_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,78]")
	private String kowChomeKnji;

	/**
	 * 住所都道府県名_カナ
	 */
	@Column(length = 24)
	@Schema(description = "住所都道府県名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String tdhknNmKana;

	/**
	 * 住所市区郡町村名_カナ
	 */
	@Column(length = 72)
	@Schema(description = "住所市区郡町村名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,72]")
	private String skugnchosnKana;

	/**
	 * 住所大字・通称名_カナ
	 */
	@Column(length = 108)
	@Schema(description = "住所大字・通称名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,108]")
	private String owaTusyoKana;

	/**
	 * 住所字名・丁目_カナ
	 */
	@Column(length = 72)
	@Schema(description = "住所字名・丁目_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,72]")
	private String kowChomeKana;

	/**
	 * 住所コード大（都道府県）
	 */
	@Column(name = "ads_cd_l", length = 6)
	@Schema(description = "住所コード大（都道府県）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String adsCdL;

	/**
	 * 住所コード大中（市・群）
	 */
	@Column(length = 15)
	@Schema(description = "住所コード大中（市・群）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String adsCdLm;

	/**
	 * 住所コード大中小（町・村・大字）
	 */
	@Column(length = 24)
	@Schema(description = "住所コード大中小（町・村・大字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String adsCdLms;

	/**
	 * 無効フラグ
	 */
	@Column(length = 3)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String dltFlg;

	/**
	 * WEB作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date scWebCrtDt;

	/**
	 * WEB作成担当者ユーザID
	 */
	@Column(length = 192)
	@Schema(description = "WEB作成担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,192]")
	private String scWebCrtUserId;

	/**
	 * WEB作成担当者販社コード
	 */
	@Column(length = 9)
	@Schema(description = "WEB作成担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String scWebCrtHanshCd;

	/**
	 * WEB作成機能コード
	 */
	@Column(length = 9)
	@Schema(description = "WEB作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String scWebCrtKinoCd;

	/**
	 * WEB更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date scWebUpdtDt;

	/**
	 * WEB更新担当者ユーザID
	 */
	@Column(length = 192)
	@Schema(description = "WEB更新担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,192]")
	private String scWebUpdtUserId;

	/**
	 * WEB更新担当者販社コード
	 */
	@Column(length = 9)
	@Schema(description = "WEB更新担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String scWebUpdtHanshCd;

	/**
	 * WEB更新機能コード
	 */
	@Column(length = 9)
	@Schema(description = "WEB更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String scWebUpdtKinoCd;

	/**
	 * バッチ作成機能コード
	 */
	@Column(length = 9)
	@Schema(description = "バッチ作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String scBtCrtKinoCd;

	/**
	 * バッチ作成処理見做し日
	 */
	@Column(length = 24)
	@Schema(description = "バッチ作成処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String scBtCrtAsofDt;

	/**
	 * バッチ作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date scBtCrtDt;

	/**
	 * バッチ更新機能コード
	 */
	@Column(length = 9)
	@Schema(description = "バッチ更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String scBtUpdtKinoCd;

	/**
	 * バッチ更新処理見做し日
	 */
	@Column(length = 24)
	@Schema(description = "バッチ作成処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String scBtUpdtAsofDt;

	/**
	 * バッチ更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date scBtUpdtDt;

	/**
	 * システム管理対応日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "システム管理対応日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date scSysKnrDt;

	/**
	 * システム管理NO
	 */
	@Column(length = 15)
	@Schema(description = "システム管理NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String scSysKnrNo;

	/**
	 * MoM最終更新機能コード
	 */
	@Column(length = 9)
	@Schema(description = "MoM最終更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String scMomUpdtKinoCd;

	/**
	 * MoM最終更新日時時刻
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "MoM最終更新日時時刻", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date scMomUpdtDtTm;

	/**
	 * 郵便番号関連１
	 */
	@Column(name = "post_num_info_1", length = 3)
	@Schema(description = "郵便番号関連１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String postNumInfo1;

	/**
	 * 郵便番号関連２
	 */
	@Column(name = "post_num_info_2", length = 3)
	@Schema(description = "郵便番号関連２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String postNumInfo2;

	/**
	 * 登録年月
	 */
	@Column(length = 18)
	@Schema(description = "登録年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,18]")
	private String tourokuYearMs;

	/**
	 * 変更年月
	 */
	@Column(length = 18)
	@Schema(description = "変更年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,18]")
	private String henkouYearMs;

	/**
	 * 更新区分
	 */
	@Column(length = 3)
	@Schema(description = "更新区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String koushinKbn;

	/**
	 * 販社名
	 */
	@Column(length = 120, name = "hansh_nm")
	@Schema(description = "販社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,120]")
	private String salesCompanyName;

	/**
	 * 販社正式名称
	 */
	@Column(length = 120)
	@Schema(description = "販社正式名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,120]")
	private String hanshSeiskNm;

	/**
	 * 更新担当者コード
	 */
	@Column(length = 18)
	@Schema(description = "更新担当者コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,18]")
	private String updtTntoshCd;

	/**
	 * 更新端末コード
	 */
	@Column(length = 45)
	@Schema(description = "更新端末コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,45]")
	private String updtTermCd;

	/**
	 * 成元システムコード
	 */
	@Column(length = 6)
	@Schema(description = "成元システムコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String crtSysCd;

	/**
	 * 更新元システムコード
	 */
	@Column(length = 6)
	@Schema(description = "更新元システムコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String updtSysCd;

	/**
	 * 元システム作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "元システム作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date motoSysCrtdDt;

	/**
	 * 元システム更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "元システム更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date motoSysUpdtDt;

	/**
	 * バッチ処理做し日
	 */
	@Column(length = 24)
	@Schema(description = "バッチ処理做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String btPrcsAsofDt;

	/**
	 * バッチ処理日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date btPrcsDt;

	/**
	 * 販社種類区分
	 */
	@Column(length = 3)
	@Schema(description = "販社種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String hanshSyruiKbn;

	/**
	 * 連結会社コード
	 */
	@Column(length = 9)
	@Schema(description = "連結会社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String renktKishCd;

	/**
	 * MOM会社ID
	 */
	@Column(length = 18)
	@Schema(description = "MOM会社ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,18]")
	private String hanCorpId;

	/**
	 * RINGS連携対象フラグ
	 */
	@Column(length = 3)
	@Schema(description = "RINGS連携対象フラグ日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String ringsRelateFlg;

	/**
	 * CUIC連携対象フラグ
	 */
	@Column(length = 3)
	@Schema(description = "CUIC連携対象フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String cuicRelateFlg;

	/**
	 * P対策責任者
	 */
	@Column(length = 24)
	@Schema(description = "P対策責任者", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String directOrdEmpId;

	/**
	 * 日本OSフラグ
	 */
	@Column(length = 3)
	@Schema(description = "日本OSフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String osFlg;

	/**
	 * V-UP用販社種類区分
	 */
	@Column(length = 3)
	@Schema(description = "V-UP用販社種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String hanshSyruiKbnVup;

	/**
	 * 商流代表MoM組織ＩＤ
	 */
	@Column(length = 21)
	@Schema(description = "商流代表MoM組織ＩＤ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,21]")
	private String comDelegateOrgId;

	/**
	 * 有効開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "有効開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cubicStartDate;

	/**
	 * 有効終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "有効終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cubicEndDate;

	/**
	 * 部門カテゴリ
	 */
	@Column(length = 450)
	@Schema(description = "部門カテゴリ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,450]")
	private String departmentDivision;

	/**
	 * 部門カテゴリ名
	 */
	@Column(length = 720)
	@Schema(description = "部門カテゴリ名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String departmentDivisionNm;

	/**
	 * 部門/組織名
	 */
	@Column(length = 720, name = "department_name")
	@Schema(description = "部門/組織名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String salesDepartmentName;

	/**
	 * 部門カナ名称
	 */
	@Column(length = 720)
	@Schema(description = "部門カナ名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String departmentKana;

	/**
	 * 上位組織コード
	 */
	@Column(length = 75)
	@Schema(description = "上位組織コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String highOrgCd;

	/**
	 * 上位組織レベル
	 */
	@Column(length = 90)
	@Schema(description = "上位組織レベル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,90]")
	private String highOrgLevel;

	/**
	 * 管理P/L区分
	 */
	@Column(length = 450)
	@Schema(description = "管理P/L区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,450]")
	private String frontPl;

	/**
	 * 管理P/L区分名
	 */
	@Column(length = 720)
	@Schema(description = "管理P/L区分名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String frontPlNm;

	/**
	 * 初期値営業軸
	 */
	@Column(length = 75)
	@Schema(description = "初期値営業軸", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String salesMatrix;

	/**
	 * 初期値営業軸名
	 */
	@Column(length = 720)
	@Schema(description = "初期値営業軸名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,750]")
	private String salesMatrixNm;

	/**
	 * 営業軸配賦率グループ
	 */
	@Column(length = 75)
	@Schema(description = "営業軸配賦率グループ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String salesMatrixGrpCd;

	/**
	 * 初期値商品軸
	 */
	@Column(length = 75)
	@Schema(description = "初期値商品軸", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String commodityMatrix;

	/**
	 * 初期値商品軸名
	 */
	@Column(length = 720)
	@Schema(description = "初期値商品軸名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String commodityMatrixNm;

	/**
	 * 商品軸配賦率グループ
	 */
	@Column(length = 75)
	@Schema(description = "初期値商品軸名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String cmdtyMatrixGrpCd;

	/**
	 * 部門機能
	 */
	@Column(length = 450)
	@Schema(description = "部門機能", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,450]")
	private String departmentFunction;

	/**
	 * 部門機能名
	 */
	@Column(length = 720)
	@Schema(description = "部門機能名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String departmentFunctionNm;

	/**
	 * 経理単位
	 */
	@Column(length = 75)
	@Schema(description = "経理単位", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String changeDepartmentCd;

	/**
	 * 経理単位名
	 */
	@Column(length = 720)
	@Schema(description = "経理単位名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String changeDepartmentNm;

	/**
	 * 事業所・拠点
	 */
	@Column(length = 15)
	@Schema(description = "事業所・拠点", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String businessEstablishment;

	/**
	 * 事業所・拠点名
	 */
	@Column(length = 240)
	@Schema(description = "事業所・拠点名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,240]")
	private String businessEstablishmentNm;

	/**
	 * 在庫評価区分
	 */
	@Column(length = 450)
	@Schema(description = "在庫評価区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,450]")
	private String inventoryEstimation;

	/**
	 * 在庫評価区分名
	 */
	@Column(length = 720)
	@Schema(description = "在庫評価区分名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String inventoryEstimationNm;

	/**
	 * CUBIC部門有効開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "CUBIC部門有効開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cubicDateFrom;

	/**
	 * CUBIC部門有効終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "CUBIC部門有効終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cubicDateTo;

	/**
	 * CUBIC部門入力可能終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "CUBIC部門入力可能終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date enterableDateTo;

	/**
	 * 有効フラグ
	 */
	@Column(length = 3)
	@Schema(description = "有効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String validFlag;

	/**
	 * セキュリティルール
	 */
	@Column(length = 720)
	@Schema(description = "セキュリティルール", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String securityRule;

	/**
	 * リーダー社員NO
	 */
	@Column(length = 720)
	@Schema(description = "リーダー社員NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String leader;

	/**
	 * リーダー社員名
	 */
	@Column(length = 720)
	@Schema(description = "リーダー社員名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String leaderNm;

	/**
	 * CUBIC部門着手年月
	 */
	@Column(length = 720)
	@Schema(description = "CUBIC部門着手年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String beginTime;

	/**
	 * CUBIC部門完成予定年月
	 */
	@Column(length = 720)
	@Schema(description = "CUBIC部門完成予定年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String completePlanTime;

	/**
	 * CUBIC部門変更完成予定年月
	 */
	@Column(length = 720)
	@Schema(description = "CUBIC部門変更完成予定年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String changePlanTime;

	/**
	 * CUBIC部門完了年月
	 */
	@Column(length = 720)
	@Schema(description = "CUBIC部門完了年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String completeTime;

	/**
	 * 対応部門コード
	 */
	@Column(length = 720)
	@Schema(description = "対応部門コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String objDepartmentCd;

	/**
	 * 初期値各社セグメントコード
	 */
	@Column(length = 75)
	@Schema(description = "初期値各社セグメントコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String defaultOriginalSegmentCd;

	/**
	 * 配賦基準グループコード
	 */
	@Column(length = 75)
	@Schema(description = "配賦基準グループコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String distStandGroupCd;

	/**
	 * 部門階層レベル
	 */
	@Column(length = 90)
	@Schema(description = "部門階層レベル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,90]")
	private String hierarchyLevel;

	/**
	 * 組織階層_組織階層名称
	 */
	@Column(length = 720)
	@Schema(description = "組織階層_組織階層名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String hierarchyName;

	/**
	 * 組織階層_レベル1組織コード
	 */
	@Column(length = 75)
	@Schema(description = "組織階層_レベル1組織コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String structureCode1;

	/**
	 * 組織階層_レベル2組織コード
	 */
	@Column(length = 75)
	@Schema(description = "組織階層_レベル2組織コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String structureCode2;

	/**
	 * 組織階層_レベル3組織コード
	 */
	@Column(length = 75)
	@Schema(description = "組織階層_レベル3組織コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String structureCode3;

	/**
	 * 組織階層_レベル4組織コード
	 */
	@Column(length = 75)
	@Schema(description = "組織階層_レベル4組織コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String structureCode4;

	/**
	 * 組織階層_レベル5組織コード
	 */
	@Column(length = 75)
	@Schema(description = "組織階層_レベル5組織コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String structureCode5;

	/**
	 * 組織階層_レベル6組織コード
	 */
	@Column(length = 75)
	@Schema(description = "組織階層_レベル6組織コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String structureCode6;

	/**
	 * 請求書問合せ先部門名
	 */
	@Column(length = 720)
	@Schema(description = "請求書問合せ先部門名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String invDepartmentName;

	/**
	 * 請求書問合せ先郵便番号
	 */
	@Column(length = 30)
	@Schema(description = "求書問合せ先郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,30]")
	private String invZipCode;

	/**
	 * 請求書問合せ先都道府県
	 */
	@Column(length = 75)
	@Schema(description = "請求書問合せ先都道府県", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String invState;

	/**
	 * 請求書問合せ先住所
	 */
	@Column(length = 720)
	@Schema(description = "請求書問合せ先住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String invAddress;

	/**
	 * 請求書問合せ先有効フラグ
	 */
	@Column(length = 3)
	@Schema(description = "請求書問合せ先有効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String invEnabledFlag;

	/**
	 * 請求書問合せ先電話番号
	 */
	@Column(length = 42)
	@Schema(description = "請求書問合せ先電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,42]")
	private String invPhoneNum;

	/**
	 * 振込先自社銀行口座1銀行番号
	 */
	@Column(length = 90)
	@Schema(description = "振込先自社銀行口座1銀行番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,90]")
	private String bankNumber1;

	/**
	 * 振込先自社銀行口座1銀行名
	 */
	@Column(length = 180)
	@Schema(description = "振込先自社銀行口座1銀行名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,180]")
	private String bankName1;

	/**
	 * 振込先自社銀行口座1銀行支店番号
	 */
	@Column(length = 75)
	@Schema(description = "振込先自社銀行口座1銀行支店番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String bankNum1;

	/**
	 * 振込先自社銀行口座1銀行支店名
	 */
	@Column(length = 180)
	@Schema(description = "振込先自社銀行口座1銀行支店名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,180]")
	private String bankBranchName1;

	/**
	 * 振込先自社銀行口座1口座種別
	 */
	@Column(length = 75)
	@Schema(description = "振込先自社銀行口座1口座種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String bankAccountType1;

	/**
	 * 振込先自社銀行口座1口座種別名
	 */
	@Column(length = 95)
	@Schema(description = "振込先自社銀行口座1口座種別名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,95]")
	private String bankAccountNm1;

	/**
	 * 振込先自社銀行口座1通貨コード
	 */
	@Column(length = 45)
	@Schema(description = "振込先自社銀行口座1通貨コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,45]")
	private String currencyCode1;

	/**
	 * 振込先自社銀行口座1口座番号
	 */
	@Column(length = 90)
	@Schema(description = "振込先自社銀行口座1口座番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,90]")
	private String bankAccountNum1;

	/**
	 * 振込先自社銀行口座1銀行口座名
	 */
	@Column(length = 240)
	@Schema(description = "振込先自社銀行口座1銀行口座名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,240]")
	private String bankAccountName1;

	/**
	 * 振込先自社銀行口座1銀行口座名カナ
	 */
	@Column(length = 960)
	@Schema(description = "振込先自社銀行口座1銀行口座名カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,960]")
	private String bankAccountNameAlt1;

	/**
	 * 振込先自社銀行口座1銀行口座名義人
	 */
	@Column(length = 720)
	@Schema(description = "振込先自社銀行口座1銀行口座名義人", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String accountHolderName1;

	/**
	 * 振込先自社銀行口座1銀行口座名義人カナ
	 */
	@Column(length = 450)
	@Schema(description = "振込先自社銀行口座1銀行口座名義人カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,450]")
	private String accountHolderNameAlt1;

	/**
	 * 振込先自社銀行口座2銀行番号
	 */
	@Column(length = 90)
	@Schema(description = "振込先自社銀行口座2銀行番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,90]")
	private String bankNumber2;

	/**
	 * 振込先自社銀行口座2銀行名
	 */
	@Column(length = 180)
	@Schema(description = "振込先自社銀行口座2銀行名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,180]")
	private String bankName2;

	/**
	 * 振込先自社銀行口座2銀行支店番号
	 */
	@Column(length = 75)
	@Schema(description = "振込先自社銀行口座2銀行支店番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String bankNum2;

	/**
	 * 振込先自社銀行口座2銀行支店名
	 */
	@Column(length = 180)
	@Schema(description = "振込先自社銀行口座2銀行支店名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,180]")
	private String bankBranchName2;

	/**
	 * 振込先自社銀行口座2口座種別
	 */
	@Column(length = 75)
	@Schema(description = "振込先自社銀行口座2口座種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,75]")
	private String bankAccountType2;

	/**
	 * 振込先自社銀行口座2口座種別名
	 */
	@Column(length = 96)
	@Schema(description = "振込先自社銀行口座2口座種別名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,96]")
	private String bankAccountNm2;

	/**
	 * 振込先自社銀行口座2通貨コード
	 */
	@Column(length = 45)
	@Schema(description = "振込先自社銀行口座2通貨コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,45]")
	private String currencyCode2;

	/**
	 * 振込先自社銀行口座2口座番号
	 */
	@Column(length = 90)
	@Schema(description = "振込先自社銀行口座2口座番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,90]")
	private String bankAccountNum2;

	/**
	 * 振込先自社銀行口座2銀行口座名
	 */
	@Column(length = 240)
	@Schema(description = "振込先自社銀行口座2銀行口座名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,240]")
	private String bankAccountName2;

	/**
	 * 振込先自社銀行口座2銀行口座名カナ
	 */
	@Column(length = 960)
	@Schema(description = "振込先自社銀行口座2銀行口座名カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,960]")
	private String bankAccountNameAlt2;

	/**
	 * 振込先自社銀行口座2銀行口座名義人
	 */
	@Column(length = 720)
	@Schema(description = "振込先自社銀行口座2銀行口座名義人", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String accountHolderName2;

	/**
	 * 振込先自社銀行口座2銀行口座名義人カナ
	 */
	@Column(length = 450)
	@Schema(description = "振込先自社銀行口座2銀行口座名義人カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,450]")
	private String accountHolderNameAlt2;

	/**
	 * 第1請求書コメント有効開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "第1請求書コメント有効開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date firstInvDateFrom;

	/**
	 * 第1請求書コメント有効終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "第1請求書コメント有効終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date firstInvDateTo;

	/**
	 * 第1請求書コメント1
	 */
	@Column(length = 720)
	@Schema(description = "第1請求書コメント1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String firstInvComment1;

	/**
	 * 第1請求書コメント2
	 */
	@Column(length = 720)
	@Schema(description = "第1請求書コメント2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String firstInvComment2;

	/**
	 * 第1請求書コメント3
	 */
	@Column(length = 720)
	@Schema(description = "第1請求書コメント3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String firstInvComment3;

	/**
	 * 第2請求書コメント有効開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "第2請求書コメント有効開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date secondInvDateFrom;

	/**
	 * 第2請求書コメント有効終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "第2請求書コメント有効終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date secondInvDateTo;

	/**
	 * 第2請求書コメント1
	 */
	@Column(length = 720)
	@Schema(description = "第2請求書コメント1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String secondInvComment1;

	/**
	 * 第2請求書コメント2
	 */
	@Column(length = 720)
	@Schema(description = "第2請求書コメント2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String secondInvComment2;

	/**
	 * 第2請求書コメント3
	 */
	@Column(length = 720)
	@Schema(description = "第2請求書コメント3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,720]")
	private String secondInvComment3;

	/**
	 * ファイブリッジキャビネットID
	 */
	@Column(length = 96)
	@Schema(description = "ファイブリッジキャビネットID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,96]")
	private String fbDeptDistId;

	/**
	 * 登録日時
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "登録日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cubicRegisterDate;

	/**
	 * 更新日時
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "更新日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cubicUpdatingDate;

	/**
	 * 登録者ID
	 */
	@Column(length = 24)
	@Schema(description = "登録者ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String cubicRegisterUserId;

	/**
	 * 登録者名
	 */
	@Column(length = 300)
	@Schema(description = "登録者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String cubicRegisterUserName;

	/**
	 * 更新者ID
	 */
	@Column(length = 24)
	@Schema(description = "登録者ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String cubicUpdatingUserId;

	/**
	 * 更新者名
	 */
	@Column(length = 300)
	@Schema(description = "更新者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,300]")
	private String cubicUpdatingUserName;
}
