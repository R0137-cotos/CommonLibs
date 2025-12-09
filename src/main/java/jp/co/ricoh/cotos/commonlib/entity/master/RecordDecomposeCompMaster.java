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
import jp.co.ricoh.cotos.commonlib.entity.EnumType.TargetContractType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 計上分解構成マスタを表すEntity
 */
@Entity
@Data
@ToString(exclude = { "itemMaster" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "record_decompose_comp_master")
public class RecordDecomposeCompMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record_decompose_comp_master_seq")
	@SequenceGenerator(name = "record_decompose_comp_master_seq", sequenceName = "record_decompose_comp_master_seq", allocationSize = 1)
	@Schema(description = "計上分解構成マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 品種マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "品種マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
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
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.REQUIRED)
	private int disengagementFlg;

	/**
	 * 計上分解マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "record_decompose_master_id", referencedColumnName = "id")
	@Schema(description = "計上分解マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private RecordDecomposeMaster recordDecomposeMaster;

	/**
	 * 明細番号
	 */
	@Column(nullable = false)
	@Schema(description = "明細番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private int seqNumber;

}
