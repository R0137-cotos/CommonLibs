package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 社員グループ管理マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "emp_grp_management_master")
public class EmpGrpManagementMaster extends EntityBaseMaster {

	/**
	 * 社員グループ管理マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_grp_management_master_seq")
	@SequenceGenerator(name = "emp_grp_management_master_seq", sequenceName = "emp_grp_management_master_seq", allocationSize = 1)
	@Schema(description = "社員グループ管理マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * グループコード
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "グループコード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String groupCode;

	/**
	 * グループ名
	 */
	@Size(max = 255)
	@Schema(description = "グループ名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String groupName;

	/**
	 * MoM社員ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "MoM社員ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String momEmpId;
}
