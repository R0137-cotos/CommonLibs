package jp.co.ricoh.cotos.commonlib.entity.master;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * サービス課所コードマスタ
 */
@Entity
@Data
@Table(name = "mv_tjmob260_org_service")
public class MvTjmob260OrgServiceMaster {

	@Embeddable
	@Data
	public static class Id implements Serializable {
		/**
		 * シリアルバージョンID
		 */
		private static final long serialVersionUID = 1L;

		@Column(name = "ors_cubic_corp_id")
		private String orsCubicCorpId;

		@Column(name = "ors_cubic_org_id")
		private String orsCubicOrgId;
	}

	@EmbeddedId
	private Id id;

	private String orsServiceOrgId;

	private String orsServiceOrgIdHead;

	private String orsServiceOrgIdBody;

	private String updateCorpId;

	private String registerUserId;

	private String registerUserName;

	private String updatingUserId;

	private String updatingUserName;

	private Date registerDate;

	private Date updatingDate;

	private String registerProgramId;

	private String updatingProgramId;
}