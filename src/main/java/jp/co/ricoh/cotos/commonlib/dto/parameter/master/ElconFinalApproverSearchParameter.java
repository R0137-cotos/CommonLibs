package jp.co.ricoh.cotos.commonlib.dto.parameter.master;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 電子契約決裁権限者一覧取得パラメータを表すDto
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ElconFinalApproverSearchParameter extends DtoBase {
	/**
	 * 企業ID
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "企業ID", required = true, position = 1, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "事業所名", required = false, position = 2, allowableValues = "range[0,255]")
	private String likeSearchOfficeName;

	/**
	 * 氏名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "氏名", required = false, position = 3, allowableValues = "range[0,255]")
	private String likeSearchApprovalName;

}
