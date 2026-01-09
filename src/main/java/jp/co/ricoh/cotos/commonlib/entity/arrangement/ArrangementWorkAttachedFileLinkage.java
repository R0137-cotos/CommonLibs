package jp.co.ricoh.cotos.commonlib.entity.arrangement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.FileLinkageStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手配業務添付ファイル連携先
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "arrangement_work_attached_file_linkage")
public class ArrangementWorkAttachedFileLinkage extends EntityBase {

	/**
	 * 手配業務添付ファイル連携先ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_work_attached_file_linkage_seq")
	@SequenceGenerator(name = "arrangement_work_attached_file_linkage_seq", sequenceName = "arrangement_work_attached_file_linkage_seq", allocationSize = 1)
	@Schema(description = "手配業務添付ファイル連携先ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 手配業務添付ファイルID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_work_attached_file_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "手配業務添付ファイルID", requiredMode = Schema.RequiredMode.REQUIRED)
	private ArrangementWorkAttachedFile arrangementWorkAttachedFile;

	/**
	 * ファイル連携先ID
	 */
	@Schema(description = "ファイル連携先ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long attachedFileLinkageId;

	/**
	 * ファイル連携先
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "ファイル連携先", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String attachedFileLinkageName;

	/**
	 * 連携ステータス
	 */
	@NotNull
	@Schema(description = "連携ステータス", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "連携対象外(\"0\"), 未連携(\"1\"), 連携済(\"2\"), 送付済(\"3\")", example = "0")
	private FileLinkageStatus linkageStatus;
}
