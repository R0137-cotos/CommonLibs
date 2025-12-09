package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス工程制御マスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_process_control_master")
public class LicenseProcessControlMaster extends EntityBase {

	/**
	 * ライセンス工程制御マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_process_control_master_seq")
	@SequenceGenerator(name = "license_process_control_master_seq", sequenceName = "license_process_control_master_seq", allocationSize = 1)
	@Schema(description = "ライセンス工程制御マスタID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス区分マスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * 品種マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "品種マスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 契約種別
	 */
	@NotNull
	@Column(nullable = false)
	@Schema(description = "契約種別", required = true, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")")
	private ContractType contractType;

	/**
	 * 契約種別詳細
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "契約種別詳細", required = true, allowableValues = "range[0,255]")
	private String contractTypeDetail;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", required = false, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * 工程パターンID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "工程パターンID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long processPatternId;

	/**
	 * 明細状態
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "明細状態", required = true, allowableValues = "range[0,255]")
	private String detailStatus;


}
