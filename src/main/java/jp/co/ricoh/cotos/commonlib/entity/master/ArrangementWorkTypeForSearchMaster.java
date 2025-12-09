package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 検索用手配業務タイプマスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "arrangement_work_type_for_search_master")
public class ArrangementWorkTypeForSearchMaster extends EntityBaseMaster {

	/**
	 * 検索用手配業務タイプマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_work_type_for_search_master_seq")
	@SequenceGenerator(name = "arrangement_work_type_for_search_master_seq", sequenceName = "arrangement_work_type_for_search_master_seq", allocationSize = 1)
	@Schema(description = "検索用手配業務タイプマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタID
	 */
	@Column(nullable = false)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long productMasterId;

	/**
	 * 手配業務タイプマスタID
	 */
	@Column(nullable = false)
	@Schema(description = "手配業務タイプマスタID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long arrangementWorkTypeMasterId;

}
