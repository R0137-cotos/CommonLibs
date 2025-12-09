package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * RJ人事組織マスタ
 */
@Entity
@Data
@Table(name = "v_pic_affiliate_master")
public class VPicAffiliateMaster {

	/**
	 * MoM組織ID
	 */
	@Id
	@Schema(description = "MoM組織ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,7]")
	private String orgId;

	/**
	 * MoM組織名称
	 */
	@Schema(description = "MoM組織名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String orgName;

	/**
	 * 組織階層レベル
	 */
	@Schema(description = "組織階層レベル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999]")
	private Integer orgHierarchyLevel;

	/**
	 * 上位MoM組織ID
	 */
	@Schema(description = "上位MoM組織ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,7]")
	private String highOrgId;

	/**
	 * 上位MoM組織名称
	 */
	@Schema(description = "上位MoM組織名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String highOrgName;
}