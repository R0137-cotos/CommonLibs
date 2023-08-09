package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "契約保守担当CE社員洗い替え履歴(作成時不要)")
public class ContractPicMntCeEmpRefreshHis extends EmployeeAbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_pic_mnt_ce_emp_refresh_his_seq")
	@SequenceGenerator(name = "contract_pic_mnt_ce_emp_refresh_his_seq", sequenceName = "contract_pic_mnt_ce_emp_refresh_his_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "契約", required = true, position = 2)
	private Contract contract;

	/**
	 * 洗替日
	 */
	@ApiModelProperty(value = "洗替日", required = false, position = 3)
	private Date refreshedAt;
}
