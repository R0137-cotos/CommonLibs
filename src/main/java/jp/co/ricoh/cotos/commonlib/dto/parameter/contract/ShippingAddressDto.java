package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShippingAddressDto extends DtoBase {

	/**
	 * MoM社員ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "MoM社員ID<br/>※POST時「RJ社員情報マスタ」存在チェック実施", required = true, position = 2, allowableValues = "range[0,255]")
	private String momEmployeeId;

	/**
	 * 所属組織MoM組織ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "所属組織MoM組織ID", required = false, position = 3, allowableValues = "range[0,255]", readOnly = false)
	private String momOrgId;

	/**
	 * 所属組織階層レベル
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "所属組織階層レベル", required = false, position = 4, allowableValues = "range[0,9]", readOnly = false)
	private Integer orgHierarchyLevel;

	/**
	 * 所属組織名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "所属組織名", required = false, position = 5, allowableValues = "range[0,255]", readOnly = false)
	private String orgName;

	/**
	 * 販売会社名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "販売会社名", required = false, position = 6, allowableValues = "range[0,255]", readOnly = false)
	private String salesCompanyName;

	/**
	 * 会社代表電話番号
	 */
	@Size(max = 1000)
	@ApiModelProperty(value = "会社代表電話番号", required = false, position = 7, allowableValues = "range[0,1000]", readOnly = false)
	private String orgPhoneNumber;

	/**
	 * 社員名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "社員名", required = false, position = 8, allowableValues = "range[0,255]", readOnly = false)
	private String employeeName;

	/**
	 * 部署名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "部署名", required = false, position = 9, allowableValues = "range[0,255]", readOnly = false)
	private String salesDepartmentName;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "郵便番号", required = false, position = 10, allowableValues = "range[0,255]", readOnly = false)
	private String postNumber;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都道府県", required = false, position = 11, allowableValues = "range[0,255]", readOnly = false)
	private String prefectures;

	/**
	 * 市区町村番地
	 */
	@Size(max = 1000)
	@ApiModelProperty(value = "市区町村番地", required = false, position = 12, allowableValues = "range[0,1000]", readOnly = false)
	private String cityStreet;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "電話番号", required = false, position = 13, allowableValues = "range[0,255]", readOnly = false)
	private String phoneNumber;

	/**
	 * ヤマト便お問合せ伝票番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ヤマト便お問合せ伝票番号", required = false, position = 14, allowableValues = "range[0,255]", readOnly = false)
	private String yamatoSlipNumber;

	/**
	 * 建物名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "建物名", required = false, position = 16, allowableValues = "range[0,255]", readOnly = false)
	private String buildingName;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名", required = false, position = 17, allowableValues = "range[0,255]", readOnly = false)
	private String companyName;

}
