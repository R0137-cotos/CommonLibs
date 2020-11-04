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
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス工程パターンマスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_process_pattern_master")
public class LicenseProcessPatternMaster extends EntityBase {

	/**
	 * ライセンス工程パターンマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_process_pattern_master_seq")
	@SequenceGenerator(name = "license_process_pattern_master_seq", sequenceName = "license_process_pattern_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンス工程パターンマスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンス区分", required = true, position = 2, allowableValues = "range[0,255]")
	private String licenseDiv;

	/**
	 * 工程パターンID
	 */
	@NotNull
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "工程パターンID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private Long processPatternId;

	/**
	 * 工程順
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "工程順", required = true, position = 4, allowableValues = "range[0,999]")
	private int processOrder;

	/**
	 * 工程ID
	 */
	@NotNull
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "工程ID", required = true, position = 5, allowableValues = "range[0,9223372036854775807]")
	private Long processId;

	/**
	 * 手配業務タイプマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "手配業務タイプマスタID", required = false, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * メールテンプレートID
	 */
	@Min(0)
	@ApiModelProperty(value = "メールテンプレートID", required = false, position = 7, allowableValues = "range[0,9223372036854775807]")
	private Long mailTemplateId;

	/**
	 * メール到達チェックフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "メール到達チェックフラグ", required = false, position = 8, allowableValues = "range[0,9]")
	private Integer mailArrivalCheckFlg;

	/**
	 * メール区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メール区分", required = true, position = 2, allowableValues = "range[0,255]")
	private String mailDiv;

}
