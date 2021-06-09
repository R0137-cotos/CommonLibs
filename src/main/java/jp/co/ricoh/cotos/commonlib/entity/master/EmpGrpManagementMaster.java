package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "社員グループ管理マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * グループコード
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "グループコード", required = true, position = 2, allowableValues = "range[0,255]")
	private String groupCode;

	/**
	 * グループ名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "グループ名", required = false, position = 3, allowableValues = "range[0,255]")
	private String groupName;

	/**
	 * MoM社員ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "MoM社員ID", required = true, position = 4, allowableValues = "range[0,255]")
	private String momEmpId;
}
