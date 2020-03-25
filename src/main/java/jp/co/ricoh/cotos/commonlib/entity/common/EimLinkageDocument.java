package jp.co.ricoh.cotos.commonlib.entity.common;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * EIM連携文書
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "eim_linkage_document")
public class EimLinkageDocument extends EntityBase {

	/**
	 * EIM連携文書ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eim_linkage_document_seq")
	@SequenceGenerator(name = "eim_linkage_document_seq", sequenceName = "eim_linkage_document_seq", allocationSize = 1)
	@ApiModelProperty(value = "EIM連携文書ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 文書ID
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "文書ID", required = true, position = 2, allowableValues = "range[0,255]")
	private String documentId;

	/**
	 * EIM連携管理情報
	 */
	@Valid
	@OneToMany(mappedBy = "eimLinkageDocument")
	@ApiModelProperty(value = "契約添付ファイル連携先", required = false, position = 3)
	private List<EimLinkageManagementInfo> eimLinkageManagementInfoList;
}
