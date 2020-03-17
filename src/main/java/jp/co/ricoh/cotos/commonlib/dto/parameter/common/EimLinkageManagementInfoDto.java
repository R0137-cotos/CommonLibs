package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.common.AttachedFile;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EimLinkageManagementInfoDto extends DtoBase {

	/**
	 * 添付ファイル
	 */
	@NotNull
	@OneToOne(optional = false)
	@Valid
	@JoinColumn(name = "attached_file_id", referencedColumnName = "id")
	@ApiModelProperty(value = "添付ファイル", required = true, position = 2)
	private AttachedFile attachedFile;
}
