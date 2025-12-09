package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス手配マスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_arrangement_master")
public class LicenseArrangementMaster extends EntityBase {

	/**
	 * ライセンス手配マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_arrangement_master_seq")
	@SequenceGenerator(name = "license_arrangement_master_seq", sequenceName = "license_arrangement_master_seq", allocationSize = 1)
	@Schema(description = "ライセンス手配マスタID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分マスタID
	 */
	@Min(0)
	@Schema(description = "ライセンス区分マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * 契約種別
	 */
	@Schema(description = "契約種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")")
	private ContractType contractType;

	/**
	 * 契約種別詳細
	 */
	@Size(max = 255)
	@Schema(description = "契約種別詳細", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractTypeDetail;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private int disengagementFlg;

	/**
	 * 手配業務タイプマスタID
	 */
	@Min(0)
	@Schema(description = "手配業務タイプマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * 優先順位
	 */
	@Min(0)
	@Max(999)
	@Schema(description = "優先順位", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,999]")
	private Long priorityOrder;
}
