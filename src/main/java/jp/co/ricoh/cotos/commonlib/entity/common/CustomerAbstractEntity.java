package jp.co.ricoh.cotos.commonlib.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster.DepartmentDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * COTOS顧客エンティティー共通項目 COTOSの顧客情報を管理するエンティティーはこのクラスのサブクラスとしてください。
 * 当クラスに項目追加する場合は、以下のクラスにも同様の項目を追加してください。
 * jp.co.ricoh.cotos.commonlib.dto.parameter.common.CustomerAbstractDto
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class CustomerAbstractEntity extends EntityBase {

	/**
	 * MoM企事部システム連携ID
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "MoM企事部システム連携ID<br/>※POST時「企事部マスタ」存在チェック実施", required = true, allowableValues = "range[0,255]")
	private String momKjbSystemId;

	/**
	 * MoM企事部ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "MoM企事部ID(作成時不要)", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String momCustId;

	/**
	 * MoM企業ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "MoM企業ID(作成時不要)", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String companyId;

	/**
	 * MoM事業所ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "MoM事業所ID(作成時不要)", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String officeId;

	/**
	 * 企事部設定区分
	 */
	@Column(nullable = false)
	@Schema(description = "企事部設定区分(作成時不要)", required = true, allowableValues = "企事(\"1\"), 企事部(\"2\")", example = "1", readOnly = true)
	private DepartmentDiv departmentDiv;

	/**
	 * 顧客名
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "顧客名(作成時不要)", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String customerName;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String companyName;

	/**
	 * 企業名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "企業名（カナ）(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String companyNameKana;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@Schema(description = "事業所名(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String officeName;

	/**
	 * 部門名
	 */
	@Size(max = 255)
	@Schema(description = "部門名(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String departmentName;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "郵便番号(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String postNumber;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@Schema(description = "住所(作成時不要)", required = false, allowableValues = "range[0,1000]", readOnly = true)
	private String address;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@Schema(description = "電話番号(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String phoneNumber;

	/**
	 * FAX番号
	 */
	@Size(max = 255)
	@Schema(description = "FAX番号(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String faxNumber;

	/**
	 * 企業代表者名
	 */
	@Size(max = 255)
	@Schema(description = "企業代表者名(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
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
	@Column
	@Schema(description = "MoM非連携_企業代表者名（カナ）", required = false, allowableValues = "range[0,]")
	private String companyRepresentativeNameKana;

}
