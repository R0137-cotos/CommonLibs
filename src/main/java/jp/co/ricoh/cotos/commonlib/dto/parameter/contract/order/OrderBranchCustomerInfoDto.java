package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文担当支社情報
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OrderBranchCustomerInfoDto {

	/**
	 * 支社コード
	 */
	@Size(max = 255)
	@Schema(description = "支社コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String branchCustomerCd;

	/**
	 * 支社名
	 */
	@Size(max = 255)
	@Schema(description = "支社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String branchCustomerName;

	/**
	 * 課所コード
	 */
	@Size(max = 255)
	@Schema(description = "課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String officeCd;

	/**
	 * 課所名
	 */
	@Size(max = 255)
	@Schema(description = "課所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 営業コード
	 */
	@Size(max = 255)
	@Schema(description = "営業コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String employeeCd;

	/**
	 * 営業名
	 */
	@Size(max = 255)
	@Schema(description = "営業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String employeeName;

	/**
	 * 担当営業電話番号
	 */
	@Size(max = 255)
	@Schema(description = "担当営業電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String employeePhoneNumber;

	/**
	 * 担当営業メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "担当営業メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String employeeMailAddress;

}
