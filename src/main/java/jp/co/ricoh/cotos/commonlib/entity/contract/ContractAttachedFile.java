package jp.co.ricoh.cotos.commonlib.entity.contract;

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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.common.AttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.master.AttachedFileProductGrpCheckMaster.CheckTimingDiv;
import jp.co.ricoh.cotos.commonlib.serializer.UnixTimestampDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約添付ファイルを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(ContractAttachedFileListener.class)
@Data
@Table(name = "contract_attached_file")
public class ContractAttachedFile extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_attached_file_seq")
	@SequenceGenerator(name = "contract_attached_file_seq", sequenceName = "contract_attached_file_seq", allocationSize = 1)
	@Schema(description = "契約添付ファイルID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

	/**
	 * ファイル名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "ファイル名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * ファイル種類
	 */
	@Size(max = 255)
	@Schema(description = "ファイル種類", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String fileKind;

	/**
	 * 添付ファイル
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "attached_file_id", referencedColumnName = "id")
	@Schema(description = "添付ファイル", requiredMode = Schema.RequiredMode.REQUIRED)
	private AttachedFile attachedFile;

	/**
	 * コメント
	 */
	@Size(max = 1000)
	@Schema(description = "コメント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String attachedComment;

	/**
	 * 添付者MoM社員ID
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "添付者MoM社員ID<br/>※POST時「RJ社員情報マスタ」存在チェック実施", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String attachedEmpId;

	/**
	 * 添付者氏名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "添付者氏名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String attachedEmpName;

	/**
	 * 添付者組織名
	 */
	@Size(max = 255)
	@Schema(description = "添付者組織名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String attachedOrgName;

	/**
	 * 添付日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	@Schema(description = "添付日時(作成時不要)", readOnly = true)
	private Date attachedAt;

	/**
	 * 契約添付ファイル連携先
	 */
	@Valid
	@OneToMany(mappedBy = "contractAttachedFile")
	@Schema(description = "契約添付ファイル連携先", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractAttachedFileLinkage> contractAttachedFileLinkageList;

	/**
	 * 添付必須フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "添付必須フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer attachedRequiredFlg;

	/**
	 * チェックタイミング区分
	 */
	@Schema(description = "チェックタイミング区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "常時(\"0\"), 承認のみ(\"1\")")
	private CheckTimingDiv checkTimingDiv;

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
