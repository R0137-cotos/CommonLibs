package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 汎用チェック事項マスタを表すEntity
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "gp_check_matter_master")
public class GpCheckMatterMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gp_check_matter_master_seq")
	@SequenceGenerator(name = "gp_check_matter_master_seq", sequenceName = "gp_check_matter_master_seq", allocationSize = 1)
	@Schema(description = "汎用チェック事項マスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * チェック事項コード
	 */
	@Column(nullable = false)
	@Schema(description = "チェック事項コード", required = true, allowableValues = "range[0,255]")
	private String checkMatterCode;

	/**
	 * チェック事項文面
	 */
	@Column(nullable = false)
	@Schema(description = "チェック事項文面", required = true, allowableValues = "range[0,255]")
	private String checkMatterText;

	/**
	 * 手配チェックリスト構成マスタ
	 */
	@OneToMany(mappedBy = "gpCheckMatterMaster")
	@Schema(description = "手配チェックリスト構成マスタ", required = false)
	private List<ArrangementChecklistCompMaster> arrangementChecklistCompMasterList;

	/**
	 * 見積チェックリスト構成マスタ
	 */
	@OneToMany(mappedBy = "gpCheckMatterMaster")
	@Schema(description = "見積チェックリスト構成マスタ", required = false)
	private List<EstimationChecklistCompMaster> estimationChecklistCompMasterList;

	/**
	 * 契約チェックリスト構成マスタ
	 */
	@OneToMany(mappedBy = "gpCheckMatterMaster")
	@Schema(description = "契約チェックリスト構成マスタ", required = false)
	private List<ContractChecklistCompMaster> contractChecklistCompMasterList;

}
