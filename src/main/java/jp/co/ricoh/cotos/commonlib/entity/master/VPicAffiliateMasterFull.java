package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * RJ人事組織マスタ(全階層)
 */
@Entity
@Data
@Table(name = "v_pic_affiliate_master_full")
public class VPicAffiliateMasterFull {

	/**
	 * MoM組織ID
	 */
	@Id
	@Schema(description = "MoM組織ID", required = true, allowableValues = "range[0,7]")
	private String orgId;

	/**
	 * MoM組織名称
	 */
	@Schema(description = "MoM組織名称", required = false, allowableValues = "range[0,1000]")
	private String orgName;

	/**
	 * 組織階層レベル
	 */
	@Schema(description = "組織階層レベル", required = false, allowableValues = "range[0,9999999999]")
	private Integer orgHierarchyLevel;

	/**
	 * 上位MoM組織ID
	 */
	@Schema(description = "上位MoM組織ID", required = false, allowableValues = "range[0,7]")
	private String highOrgId;

	/**
	 * 上位MoM組織名称
	 */
	@Schema(description = "上位MoM組織名称", required = false, allowableValues = "range[0,1000]")
	private String highOrgName;
}