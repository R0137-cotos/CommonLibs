package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * IM電子契約決済権限者Mview
 */
@Entity
@Data
@Table(name = "mv_im_elcon_final_approver")
public class MvImElconFinalApprover {

	@Id
	private long id;

	/** 管理番号 */
	@Column(name = "imfr_sd_insert_id")
	private String imfrSdInsertId;

	/** 管理行番号 */
	@Column(name = "imfr_sd_row_no")
	private String imfrSdRowNo;

	/** 適応範囲（コメント） */
	@Column(name = "imfr_ud_gt1_scope")
	private String imfrUdGt1Scope;

	/** 一次承認者名 */
	@Column(name = "imfr_ud_gt1_primary_approver")
	private String imfrUdGt1PrimaryApprover;

	/** 一次承認者所属 */
	@Column(name = "imfr_ud_gt1_primary_dep")
	private String imfrUdGt1PrimaryDep;

	/** 一次承認者役職 */
	@Column(name = "imfr_ud_gt_primary_postname")
	private String imfrUdGtPrimaryPostname;

	/** 一次承認者メールアドレス */
	@Column(name = "imfr_ud_gt1_primary_email")
	private String imfrUdGt1PrimaryEmail;

	/** 決裁者名 */
	@Column(name = "imfr_ud_gt1_final_approver")
	private String imfrUdGt1FinalApprover;

	/** 決裁者所属 */
	@Column(name = "imfr_ud_gt1_final_dep")
	private String imfrUdGt1FinalDep;

	/** 決裁者役職 */
	@Column(name = "imfr_ud_gt1_final_postname")
	private String imfrUdGt1FinalPostname;

	/** 決裁者メールアドレス */
	@Column(name = "imfr_ud_gt1_final_email")
	private String imfrUdGt1FinalEmail;

	/** お客様同意署名欄 */
	@Column(name = "imfr_ud_gt1_consenter")
	private String imfrUdGt1Consenter;

	/** お客様同意年月日 */
	@Column(name = "imfr_ud_gt1_agree_date")
	private String imfrUdGt1AgreeDate;

	/** 一次承認者有無 */
	@Column(name = "imfr_ud_gt1_p_existence")
	private String imfrUdGt1PExistence;

	/** P/C契約可能フラグ */
	@Column(name = "imfr_ud_gt1_pc_flg")
	private String imfrUdGt1PcFlg;

	/** 企業ID */
	@Column(name = "imfr_ud_company_id")
	private String imfrUdCompanyId;

	/** 企業名 */
	@Column(name = "imfr_ud_company_name")
	private String imfrUdCompanyName;

	/** 事業所ID */
	@Column(name = "imfr_ud_office_id")
	private String imfrUdOfficeId;

	/** 事業所名 */
	@Column(name = "imfr_ud_office_name")
	private String imfrUdOfficeName;

}
