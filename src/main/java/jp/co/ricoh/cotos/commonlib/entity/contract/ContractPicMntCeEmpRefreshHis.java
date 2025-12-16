package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.common.EmployeeAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約情報の中で保持する契約保守担当CE社員情報の洗い替え履歴を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_pic_mnt_ce_emp_refresh_his")
@Schema(description = "契約保守担当CE社員洗い替え履歴(作成時不要)")
public class ContractPicMntCeEmpRefreshHis extends EmployeeAbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_pic_mnt_ce_emp_refresh_his_seq")
	@SequenceGenerator(name = "contract_pic_mnt_ce_emp_refresh_his_seq", sequenceName = "contract_pic_mnt_ce_emp_refresh_his_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

	/**
	 * 洗替日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "洗替日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date refreshedAt;

	/**
	 * 更新元バッチID
	 */
	@Size(max = 255)
	@Schema(description = "更新元バッチID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String updateBatchId;
}
