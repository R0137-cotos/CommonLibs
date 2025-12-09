package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
		新規作成, 更新, 自動更新, ライセンス情報洗替
	}

	/**
	 * ライセンス情報操作履歴ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_info_operation_log_seq")
	@SequenceGenerator(name = "license_info_operation_log_seq", sequenceName = "license_info_operation_log_seq", allocationSize = 1)
	@Schema(description = "ライセンス情報操作履歴ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス情報
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "license_info_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "ライセンス情報", required = true)
	private LicenseInfo licenseInfo;

	/**
	 * 操作内容
	 */
	@Enumerated(EnumType.STRING)
	@Schema(description = "操作内容", required = false, allowableValues = "新規作成、更新、自動更新、ライセンス情報洗替", readOnly = false)
	private Operation operation;

	/**
	 * 操作者MoM社員ID
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "操作者MoM社員ID", required = true, allowableValues = "range[0,255]")
	private String operatorEmpId;

	/**
	 * 操作者氏名
	 */
	@Size(max = 255)
	@Schema(description = "操作者氏名", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String operatorName;

	/**
	 * 操作者組織名
	 */
	@Size(max = 255)
	@Schema(description = "操作者組織名", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String operatorOrgName;

	/**
	 * 実施日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "実施日時", required = false, readOnly = false)
	private Date operatedAt;

	@PrePersist
	public void prePersist() {
		super.prePersist();
		this.operatedAt = super.getCreatedAt();
	}

}
