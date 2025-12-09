package jp.co.ricoh.cotos.commonlib.entity.arrangement;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
 * 手配業務に紐づく添付ファイル情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(ArrangementWorkAttachedFileListener.class)
@Data
@Table(name = "arrangement_work_attached_file")
public class ArrangementWorkAttachedFile extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_work_attached_file_seq")
	@SequenceGenerator(name = "arrangement_work_attached_file_seq", sequenceName = "arrangement_work_attached_file_seq", allocationSize = 1)
	@Schema(description = "手配業務添付ファイルID (作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 手配業務
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_work_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "手配業務", required = true)
	private ArrangementWork arrangementWork;

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
	@OneToOne(optional = false)
	@NotNull
	@Valid
	@JoinColumn(name = "attached_file_id", referencedColumnName = "id")
	@Schema(description = "添付ファイル", required = true)
	private AttachedFile attachedFile;

	/**
	 * コメント
	 */
	@Size(max = 1000)
	@Schema(description = "コメント", required = false, allowableValues = "range[0,1000]")
	private String attachedComment;

	/**
	 * 添付者MoM社員ID
	 */
	@Column(nullable = false)
	@Schema(description = "添付者MoM社員ID(作成時不要)<br/>※POST時「RJ社員情報マスタ」存在チェック実施", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String attachedEmpId;

	/**
	 * 添付者氏名
	 */
	@Column(nullable = false)
	@Schema(description = "添付者氏名(作成時不要)", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String attachedEmpName;

	/**
	 * 添付者組織名
	 */
	@Schema(description = "添付者組織名(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String attachedOrgName;

	/**
	 * 添付日時
	 */
	@Column(nullable = false)
	@Schema(description = "添付日時(作成時不要)", required = true, readOnly = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date attachedAt;

	/**
	 * 手配業務添付ファイル連携先
	 */
	@Valid
	@OneToMany(mappedBy = "arrangementWorkAttachedFile")
	@Schema(description = "手配業務添付ファイル連携先", required = false)
	private List<ArrangementWorkAttachedFileLinkage> arrangementWorkAttachedFileLinkageList;

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
