package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

/**
 * サービス課所コードマスタ
 */
@Entity
@Data
@Table(name = "mv_tjmob260_org_service")
public class MvTjmob260OrgServiceMaster {

	private String orsCubicCorpId;

	private String orsCubicOrgId;

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
