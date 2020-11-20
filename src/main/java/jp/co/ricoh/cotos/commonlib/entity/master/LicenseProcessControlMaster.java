package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ライセンス工程制御マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * 品種マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 契約種別
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "契約種別", required = true, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")", position = 4)
	private ContractType contractType;

	/**
	 * 契約種別詳細
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "契約種別詳細", required = true, position = 5, allowableValues = "range[0,255]")
	private String contractTypeDetail;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "解約フラグ", required = false, position = 6, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * 工程パターンID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "工程パターンID", required = true, position = 7, allowableValues = "range[0,9223372036854775807]")
	private long processPatternId;

	/**
	 * 明細状態
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "明細状態", required = true, position = 8, allowableValues = "range[0,255]")
	private String detailStatus;


}
