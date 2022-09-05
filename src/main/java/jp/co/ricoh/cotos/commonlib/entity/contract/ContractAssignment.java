package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約業務情報
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_assignment")
public class ContractAssignment extends EntityBase {

	/**
	 * 契約業務情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_assignment_seq")
	@SequenceGenerator(name = "contract_assignment_seq", sequenceName = "contract_assignment_seq", allocationSize = 1)
	@ApiModelProperty(value = "契約業務情報ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * メモ
	 */
	@ApiModelProperty(value = "メモ", required = false, position = 3)
	@Lob
	private String memo;

	/**
	 * 契約業務添付ファイル
	 */
	@Valid
	@OneToMany(mappedBy = "contractAssignment")
	@ApiModelProperty(value = "契約業務添付ファイル", required = false, position = 4)
	private List<ContractAssignmentAttachedFile> contractAssignmentAttachedFileList;

	/**
	 * 更新フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "更新フラグ", required = false, position = 5, allowableValues = "range[0,9]")
	private Integer updateFlg;
}
