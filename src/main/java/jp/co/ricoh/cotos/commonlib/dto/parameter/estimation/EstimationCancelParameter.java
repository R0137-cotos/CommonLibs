package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import jakarta.validation.constraints.Min;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EstimationCancelParameter {

	/**
	 * 再見積ID
	 */
	@Min(0)
	@Parameter(description = "再見積ID", required = false, schema = @Schema(allowableValues = "range[0,9223372036854775807]"))
	private Long reEstimationId;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Parameter(description = "契約ID", required = false, schema = @Schema(allowableValues = "range[0,9223372036854775807]"))
	private Long contractId;
}
