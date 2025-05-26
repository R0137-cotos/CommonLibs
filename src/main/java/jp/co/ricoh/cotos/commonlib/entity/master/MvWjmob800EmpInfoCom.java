package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name = "mv_wjmob800_emp_info_com")
public class MvWjmob800EmpInfoCom {

	/** Mom社員ID */
	@Id
	private String empId;

	/** Mom会社ID */
	private String corpId;

	/** SingleUserId */
	private String singleuserId;

	private String ringsHanshCd;

	/** RINGS社員コード */
	private String ringsEmpCd;

	/** 業務用指名(姓) */
	private String jobname1;

	/** 業務用指名(名) */
	private String jobname2;

	/** 業務用指名(姓カナ) */
	private String kanaJobname1;

	/** 業務用指名(名カナ) */
	private String kanaJobname2;

	/** Eメールアドレス */
	private String email;

	/** 電話番号 */
	private String tel;

	/** FAX番号 */
	private String fax;

	/** CEコード */
	private String ceCd;

	private String salesFlg;

	private String cueInfoFlg;

	private String totalSort;

	/** 所属組織_MoM組織ID */
	private String orgId;

	/** 退職区分 */
	private String retiredKbn;

	/** 社員番号 */
	private String embUserNumber;

	/** 業務用上司 */
	private String embJobreaderEmpId;

	/** 業務センターCD */
	private String embOperationCenterCd;

	/** 登録日時 */
	@Temporal(TemporalType.DATE)
	private Date registerDate;

	/** 更新日時 */
	@Temporal(TemporalType.DATE)
	private Date updatingDate;

	/** 登録者ID */
	private String registerUserId;

	/** 登録者名 */
	private String registerUserName;

	/** 更新者ID */
	private String updatingUserId;

	/** 更新者名 */
	private String updatingUserName;

	private String empNumber;

	/** 統合ID */
	private String integrateId;

}
