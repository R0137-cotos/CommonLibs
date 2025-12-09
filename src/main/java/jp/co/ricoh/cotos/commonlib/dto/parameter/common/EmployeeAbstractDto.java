package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class EmployeeAbstractDto extends DtoBase {

	/**
	 * MoM社員ID
	 */
	@Size(max = 255)
	@NotNull
	@Schema(description = "MoM社員ID", required = true, allowableValues = "range[0,255]")
	private String momEmployeeId;

	/**
	 * 所属組織MoM組織ID
	 */
	@Size(max = 255)
	@Schema(description = "所属組織MoM組織ID", required = false, allowableValues = "range[0,255]")
	private String momOrgId;

	/**
	 * 所属組織階層レベル
	 */
	@Min(0)
	@Max(9)
	@Schema(description = "所属組織階層レベル", required = false, allowableValues = "range[0,9]")
	private Integer orgHierarchyLevel;

	/**
	 * 所属組織名
	 */
	@Size(max = 255)
	@Schema(description = "所属組織名", required = false, allowableValues = "range[0,255]")
	private String orgName;

	/**
	 * 販売会社名
	 */
	@Size(max = 255)
	@Schema(description = "販売会社名", required = false, allowableValues = "range[0,255]")
	private String salesCompanyName;

	/**
	 * 会社代表電話番号
	 */
	@Size(max = 255)
	@Schema(description = "会社代表電話番号", required = false, allowableValues = "range[0,255]")
	private String orgPhoneNumber;

	/**
	 * 社員名
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "社員名", required = true, allowableValues = "range[0,255]")
	private String employeeName;

	/**
	 * 部署名
	 */
	@Size(max = 255)
	@Schema(description = "部署名", required = false, allowableValues = "range[0,255]")
	private String salesDepartmentName;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "郵便番号", required = false, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 住所
	 */
	@Size(max = 1000)
	@Schema(description = "住所", required = false, allowableValues = "range[0,1000]")
	private String address;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@Schema(description = "電話番号", required = false, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * FAX番号
	 */
	@Size(max = 255)
	@Schema(description = "FAX番号", required = false, allowableValues = "range[0,255]")
	private String faxNumber;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "メールアドレス", required = false, allowableValues = "range[0,255]")
	private String mailAddress;
}
