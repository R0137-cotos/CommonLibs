package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.common.AttachedFile;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約業務添付ファイル
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(ContractAssignmentAttachedFileListener.class)
@Data
@Table(name = "contract_assignment_attached_file")
public class ContractAssignmentAttachedFile extends EntityBase {

	/**
	 * 契約添付ファイルID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_assignment_attached_file_seq")
	@SequenceGenerator(name = "contract_assignment_attached_file_seq", sequenceName = "contract_assignment_attached_file_seq", allocationSize = 1)
	@Schema(description = "契約添付ファイルID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 契約業務ID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_assignment_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約業務ID", required = true)
	private ContractAssignment contractAssignment;

	/**
	 * ファイル名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "ファイル名", required = true, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * ファイル種類
	 */
	@Size(max = 255)
	@Schema(description = "ファイル種類", required = false, allowableValues = "range[0,255]")
	private String fileKind;

	/**
	 * 添付ファイル
	 */
	@Valid
	@NotNull
	@OneToOne(optional = false)
	@JoinColumn(name = "attached_file_id", referencedColumnName = "id")
	@Schema(description = "添付ファイル", required = true)
	private AttachedFile attachedFile;

	/**
	 * 添付者MoM社員ID
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "添付者MoM社員ID<br/>※POST時「RJ社員情報マスタ」存在チェック実施", required = true, allowableValues = "range[0,255]")
	private String attachedEmpId;

	/**
	 * 添付者氏名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "添付者氏名", required = true, allowableValues = "range[0,255]")
	private String attachedEmpName;

	/**
	 * 添付者組織名
	 */
	@Size(max = 255)
	@Schema(description = "添付者組織名", required = false, allowableValues = "range[0,255]")
	private String attachedOrgName;

	/**
	 * 添付日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "添付日時(作成時不要)", required = true, readOnly = true)
	private Date attachedAt;

	/**
	 * 添付必須フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "添付必須フラグ", required = false, allowableValues = "range[0,9]")
	private Integer attachedRequiredFlg;

	/**
	 * ファイル情報
	 */
	@Transient
	@Schema(hidden = true)
	private MultipartFile multipartFile;

	@PrePersist
	public void prePersist() {
		super.prePersist();
		this.attachedAt = super.getCreatedAt();
	}

}
