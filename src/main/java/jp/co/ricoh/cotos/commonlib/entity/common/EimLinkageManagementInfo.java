package jp.co.ricoh.cotos.commonlib.entity.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * EIM連携管理情報
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "eim_linkage_management_info")
public class EimLinkageManagementInfo extends EntityBase {

	/**
	 * EIM連携管理情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eim_linkage_management_info_seq")
	@SequenceGenerator(name = "eim_linkage_management_info_seq", sequenceName = "eim_linkage_management_info_seq", allocationSize = 1)
	@ApiModelProperty(value = "EIM連携管理情報ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * EIM連携文書ID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "eim_linkage_document_id", referencedColumnName = "id")
	@ApiModelProperty(value = "EIM連携文書ID", required = true, position = 2)
	@JsonIgnore
	private EimLinkageDocument eimLinkageDocument;

	/**
	 * 添付ファイル
	 */
	@NotNull
	@OneToOne(optional = false)
	@Valid
	@JoinColumn(name = "attached_file_id", referencedColumnName = "id")
	@ApiModelProperty(value = "添付ファイル", required = true, position = 3)
	private AttachedFile attachedFile;
}
