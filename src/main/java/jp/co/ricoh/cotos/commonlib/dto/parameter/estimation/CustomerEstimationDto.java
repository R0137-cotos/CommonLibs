package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.CustomerAbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CustomerEstimationDto extends CustomerAbstractDto {

	/**
	 * MoM非連携_企業代表者名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_企業代表者名(カナ)", allowableValues = "range[0,255]")
	private String companyRepresentativeNameKana;
}
