package jp.co.ricoh.cotos.commonlib.dto.result;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

/**
 * MoM組織情報を取得するためのDTOです。
 */
@Entity
@Data
public class MomInfoSearchResult {

	/** MOM組織ID */
	@Id
	private String orgId;

	/** 組織長 */
	private String orgLeaderEmpId;

	/** 組織正式名称 */
	private String orgName;
}
