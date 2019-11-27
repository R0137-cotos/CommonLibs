package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.common.EimLinkageManagementInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EimLinkageDocumentDto extends DtoBase {

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
