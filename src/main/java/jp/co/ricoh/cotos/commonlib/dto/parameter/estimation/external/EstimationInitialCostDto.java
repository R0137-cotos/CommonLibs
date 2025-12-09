package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.external;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EstimationInitialCostDto {

	/**
	 * ログインMoM社員ID
	 */
	@NotNull
	@Size(max = 8)
	@Schema(description = "ログインMoM社員ID", required = true, allowableValues = "range[0,8]")
	private String loginMoMId;

	/**
	 * ログインシングルユーザID
	 */
	@NotNull
	@Size(max = 64)
	@Schema(description = "ログインシングルユーザID", required = true, allowableValues = "range[0,64]")
	private String loginSingleUserid;

	/**
	 * 商流
	 */
	@NotNull
	@Size(max = 2)
	@Schema(description = "商流", required = true, allowableValues = "range[0,2]")
	private String oeProductRoot;

	/**
	 * お客様企事部ID
	 */
	@NotNull
	@Size(max = 15)
	@Schema(description = "お客様企事部ID", required = true, allowableValues = "range[0,15]")
	private String custInfoCpmpanyId;

	/**
	 * RTS見積No
	 */
	@NotNull
	@Size(max = 25)
	@Schema(description = "RTS見積No", required = true, allowableValues = "range[0,25]")
	private String estimatedNoRts;

	/**
	 * 見積ID
	 */
	@Min(0)
	@Schema(description = "見積ID", allowableValues = "range[0,9223372036854775807]", required = true)
	private long estimationId;

	/**
	 * 初期費情報
	 */
	@Valid
	@Schema(description = "初期費情報", required = true)
	private List<EstimationInitialCostInfoDto> estimationInitialCostInfoDtoList;

	/**
	 * 販社CD
	 */
	@NotNull
	@Size(max = 3)
	@Schema(description = "販社CD", required = true, allowableValues = "range[0,3]")
	private String hanshCd;
}
