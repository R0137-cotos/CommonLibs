package jp.co.ricoh.cotos.commonlib.entity.master;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

/**
 * IM電子契約決裁権限者Mview
 */
@Entity
@Data
@Table(name = "mv_im_elcon_final_approver")
public class MvImElconFinalApprover {

	@Embeddable
	@Data
	public static class Id implements Serializable {
		/**
		 * シリアルバージョンID
		 */
		private static final long serialVersionUID = 1L;

		/** 管理番号 */
		private String imfrSdInsertId;

		/** 管理行番号 */
		private String imfrSdRowNo;
	}

	@EmbeddedId
	private Id id;

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
	private Date imfrUdGt1AgreeDate;

	/** 一次承認者有無 */
	@Column(name = "imfr_ud_gt1_p_existence")
	private String imfrUdGt1PExistence;

	/** P/C契約可能フラグ */
	@Column(name = "imfr_ud_gt1_pc_flg")
	private String imfrUdGt1PcFlg;

	/** 企業ID */
	private String imfrUdCompanyId;

	/** 企業名 */
	private String imfrUdCompanyName;

	/** 事業所ID */
	private String imfrUdOfficeId;

	/** 事業所名 */
	private String imfrUdOfficeName;

	/** 申請者(担当SA)名 */
	private String imfrUdReqUserName;
}
