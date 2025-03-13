package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class SeOperationHistoryDto extends DtoBase {

	/**
	 * 見積ID
	 */
	@Min(0)
	@ApiModelProperty(value = "見積ID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long estimationId;

	/**
	 * 処理区分
	 */
	@ApiModelProperty(value = "処理区分", required = true, allowableValues = "range[0,255]", position = 4)
	private String processingCategory;

	/**
	 * 処理内容
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "処理内容", required = true, position = 5, allowableValues = "range[0,255]")
	private String processingDetails;

}
