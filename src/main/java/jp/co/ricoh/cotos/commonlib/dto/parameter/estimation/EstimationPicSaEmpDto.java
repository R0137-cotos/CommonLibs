package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.EmployeeAbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class EstimationPicSaEmpDto extends EmployeeAbstractDto {

	/**
	 * MoM非連携_MoM企事部システム連携ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_MoM企事部システム連携ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momKjbSystemId;

	/**
	 * MoM非連携_MoM企事部ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_MoM企事部ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * 販売店名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "販売店名（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String salesCompanyNameKana;

	/**
	 * MoM非連携_企業代表者名
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_企業代表者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyRepresentativeName;

	/**
	 * MoM非連携_企業代表者名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_企業代表者名(カナ)", allowableValues = "range[0,255]")
	private String companyRepresentativeNameKana;
}
