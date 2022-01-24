package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス情報操作履歴を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(LicenseInfoOperationLogListener.class)
@Data
@Table(name = "license_info_operation_log")
public class LicenseInfoOperationLog extends EntityBase {

	@Description(value = "操作内容")
	public enum Operation {
		新規作成, 更新
	}

	/**
	 * ライセンス情報操作履歴ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_info_operation_log_seq")
	@SequenceGenerator(name = "license_info_operation_log_seq", sequenceName = "license_info_operation_log_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンス情報操作履歴ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス情報
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "license_info_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ライセンス情報", required = true, position = 2)
	private LicenseInfo licenseInfo;

	/**
	 * 操作内容
	 */
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "操作内容", required = false, position = 3, allowableValues = "新規作成、更新", readOnly = false)
	private Operation operation;

	/**
	 * 操作者MoM社員ID
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "操作者MoM社員ID", required = true, position = 4, allowableValues = "range[0,255]")
	private String operatorEmpId;

	/**
	 * 操作者氏名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "操作者氏名", required = false, position = 5, allowableValues = "range[0,255]", readOnly = false)
	private String operatorName;

	/**
	 * 操作者組織名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "操作者組織名", required = false, position = 6, allowableValues = "range[0,255]", readOnly = false)
	private String operatorOrgName;

	/**
	 * 実施日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "実施日時", required = false, position = 7, readOnly = false)
	private Date operatedAt;

	@PrePersist
	public void prePersist() {
		super.prePersist();
		this.operatedAt = super.getCreatedAt();
	}

}
