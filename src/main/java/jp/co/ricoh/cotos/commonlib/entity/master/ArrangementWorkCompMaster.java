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
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DetailStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.TargetContractType;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationDetail.IncreaseDecreaseDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 手配業務構成マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = { "itemMaster" })
@Table(name = "arrangement_work_comp_master")
public class ArrangementWorkCompMaster extends EntityBaseMaster {

	/**
	 * 手配業務構成マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_work_comp_master_seq")
	@SequenceGenerator(name = "arrangement_work_comp_master_seq", sequenceName = "arrangement_work_comp_master_seq", allocationSize = 1)
	@Schema(description = "手配業務構成マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 品種マスタ
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "品種マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ItemMaster itemMaster;

	/**
	 * 対象契約種別
	 */
	@Column(nullable = false)
	@Schema(description = "対象契約種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "共通(\"1\"), 新規(\"2\"), 契約変更(\"3\"), 情報変更(\"4\"), 契約更新(\"5\")", example = "1")
	private TargetContractType targetContractType;

	/**
	 * 解約フラグ
	 */
	@Column(nullable = false)
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9]")
	private int disengagementFlg;

	/**
	 * 手配業務タイプマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_work_type_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "手配業務タイプマスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private ArrangementWorkTypeMaster arrangementWorkTypeMaster;

	/**
	 * 明細番号
	 */
	@Column(nullable = false)
	@Schema(description = "明細番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private int seqNumber;

	/**
	 * 明細状態
	 */
	@Column(nullable = true)
	@Schema(description = "明細状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "NOUPDATE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")", example = "1")
	private DetailStatus detailStatus;

	/**
	 * 対象契約種別詳細
	 */
	@Size(max = 255)
	@Schema(description = "対象契約種別詳細", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String targetContractTypeDetails;

	/**
	 * 増減区分
	 */
	@Schema(description = "増減区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "増数(\"1\"), 減数(\"2\")", example = "1")
	private IncreaseDecreaseDiv increaseDecreaseDiv;
}
