package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster.DepartmentDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CustomerAbstractDto extends DtoBase {

	/**
	 * MoM企事部システム連携ID
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "MoM企事部システム連携ID", required = true, allowableValues = "range[0,255]")
	private String momKjbSystemId;

	/**
	 * MoM企事部ID
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "MoM企事部ID", required = true, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * MoM企業ID
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "MoM企業ID", required = true, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * MoM事業所ID
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "MoM事業所ID", required = true, allowableValues = "range[0,255]")
	private String officeId;

	/**
	 * 企事部設定区分
	 */
	@NotNull
	@Schema(description = "企事部設定区分", required = true, allowableValues = "企事(\"1\"), 企事部(\"2\")", example = "1")
	private DepartmentDiv departmentDiv;

	/**
	 * 顧客名
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "顧客名", required = true, allowableValues = "range[0,255]")
	private String customerName;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名", required = false, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 企業名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "企業名（カナ）", required = false, allowableValues = "range[0,255]")
	private String companyNameKana;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@Schema(description = "事業所名", required = false, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 部門名
	 */
	@Size(max = 255)
	@Schema(description = "部門名", required = false, allowableValues = "range[0,255]")
	private String departmentName;

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
	 * 企業代表者名
	 */
	@Size(max = 255)
	@Schema(description = "企業代表者名", required = false, allowableValues = "range[0,255]")
	private String companyRepresentativeName;

	/**
	 * MoM非連携_担当者氏名
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_担当者氏名", required = false, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * MoM非連携_担当者氏名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_担当者氏名（カナ）", required = false, allowableValues = "range[0,255]")
	private String picNameKana;

	/**
	 * MoM非連携_担当者部署
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_担当者部署", required = false, allowableValues = "range[0,255]")
	private String picDeptName;

	/**
	 * MoM非連携_担当者電話番号
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_担当者電話番号", required = false, allowableValues = "range[0,255]")
	private String picPhoneNumber;

	/**
	 * MoM非連携_担当者FAX番号
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_担当者FAX番号", required = false, allowableValues = "range[0,255]")
	private String picFaxNumber;

	/**
	 * MoM非連携_担当者メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_担当者メールアドレス", required = false, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * MoM非連携_企業代表者名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_企業代表者名（カナ）", required = false, allowableValues = "range[0,255]")
	private String companyRepresentativeNameKana;

}
