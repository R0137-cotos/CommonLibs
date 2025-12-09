package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.EmployeeAbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContractPicSaEmpDto extends EmployeeAbstractDto {

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", required = false)
	@Lob
	private String extendsParameter;

	/**
	 * MoM非連携_MoM企事部システム連携ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_MoM企事部システム連携ID", required = false, allowableValues = "range[0,255]")
	private String momKjbSystemId;

	/**
	 * MoM非連携_MoM企事部ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_MoM企事部ID", required = false, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * 販売店名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "販売店名（カナ）", required = false, allowableValues = "range[0,255]")
	private String salesCompanyNameKana;

	/**
	 * MoM非連携_企業代表者名
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_企業代表者名", required = false, allowableValues = "range[0,255]")
	private String companyRepresentativeName;

	/**
	 * MoM非連携_企業代表者名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_企業代表者名(カナ)", required = false, allowableValues = "range[0,255]")
	private String companyRepresentativeNameKana;
}
