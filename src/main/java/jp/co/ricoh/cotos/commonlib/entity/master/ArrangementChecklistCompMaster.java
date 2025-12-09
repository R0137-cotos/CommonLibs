package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手配チェックリスト構成マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "arrangement_checklist_comp_master")
public class ArrangementChecklistCompMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_checklist_comp_master_seq")
	@SequenceGenerator(name = "arrangement_checklist_comp_master_seq", sequenceName = "arrangement_checklist_comp_master_seq", allocationSize = 1)
	@Schema(description = "手配チェックリスト構成マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 手配業務タイプマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_work_type_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "手配業務タイプマスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private ArrangementWorkTypeMaster arrangementWorkTypeMaster;

	/**
	 * 汎用チェック事項マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "gp_check_matter_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "汎用チェック事項マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private GpCheckMatterMaster gpCheckMatterMaster;

	/**
	 * 表示順
	 */
	@Column(nullable = false)
	@Schema(description = "表示順", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private int displayOrder;

}
