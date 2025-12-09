package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "契約業務情報ID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * メモ
	 */
	@Schema(description = "メモ", required = false)
	@Lob
	private String memo;

	/**
	 * 契約業務添付ファイル
	 */
	@Valid
	@OneToMany(mappedBy = "contractAssignment")
	@Schema(description = "契約業務添付ファイル", required = false)
	private List<ContractAssignmentAttachedFile> contractAssignmentAttachedFileList;

	/**
	 * 更新フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "更新フラグ", required = false, allowableValues = "range[0,9]")
	private Integer updateFlg;
}
