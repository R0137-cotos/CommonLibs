package jp.co.ricoh.cotos.commonlib.entity.arrangement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "手配業務添付ファイル連携先ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 手配業務添付ファイルID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_work_attached_file_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "手配業務添付ファイルID", required = true, position = 2)
	private ArrangementWorkAttachedFile arrangementWorkAttachedFile;

	/**
	 * ファイル連携先ID
	 */
	@ApiModelProperty(value = "ファイル連携先ID", required = true, position = 3)
	private long attachedFileLinkageId;

	/**
	 * ファイル連携先
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル連携先", required = true, position = 4, allowableValues = "range[0,255]")
	private String attachedFileLinkageName;

	/**
	 * 連携ステータス
	 */
	@NotNull
	@ApiModelProperty(value = "連携ステータス", required = true, allowableValues = "連携対象外(\"0\"), 未連携(\"1\"), 連携済(\"2\"), 送付済(\"3\")", example = "0", position = 5)
	private FileLinkageStatus linkageStatus;
}
