package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手配業務権限制御マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "arrangement_work_auth_control_master")
public class ArrangementWorkAuthControlMaster extends EntityBaseMaster {

	/**
	 * 手配業務権限制御マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_work_auth_control_master_seq")
	@SequenceGenerator(name = "arrangement_work_auth_control_master_seq", sequenceName = "arrangement_work_accept_control_master_seq", allocationSize = 1)
	@Schema(description = "手配業務権限制御マスタID(作成時不要)", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 権限名称
	 */
	@Size(max = 255)
	@NotNull
	@Schema(description = "権限名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String authName;

	/**
	 * 権限パターンマスタ
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "auth_pattern_master_id", referencedColumnName = "authPatternId")
	@Schema(description = "権限パターンマスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private AuthPatternMaster authPatternMaster;

	/**
	 * 手配業務タイプマスタ
	 */
	@OneToMany(mappedBy = "arrangementWorkAuthControlMaster")
	@Schema(description = "手配業務タイプマスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<ArrangementWorkTypeMaster> arrangementWorkTypeMasterList;
}
