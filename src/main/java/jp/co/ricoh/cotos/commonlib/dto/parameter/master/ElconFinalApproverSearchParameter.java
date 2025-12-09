package jp.co.ricoh.cotos.commonlib.dto.parameter.master;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "企業ID", required = true, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@Schema(description = "事業所名", required = false, allowableValues = "range[0,255]")
	private String likeSearchOfficeName;

	/**
	 * 氏名
	 */
	@Size(max = 255)
	@Schema(description = "氏名", required = false, allowableValues = "range[0,255]")
	private String likeSearchApprovalName;

}
