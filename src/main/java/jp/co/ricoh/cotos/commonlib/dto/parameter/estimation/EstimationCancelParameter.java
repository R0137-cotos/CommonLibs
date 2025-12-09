package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import jakarta.validation.constraints.Min;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class EstimationCancelParameter {

	/**
	 * 再見積ID
	 */
	@Min(0)
	@Parameter(description = "再見積ID", allowableValues = "range[0,9223372036854775807]", required = false)
	private Long reEstimationId;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Parameter(description = "契約ID", allowableValues = "range[0,9223372036854775807]", required = false)
	private Long contractId;
}
