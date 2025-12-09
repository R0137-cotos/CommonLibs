package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.CasLicenseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CASライセンス基本情報DTO
 * @author z00se03039
 *
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CasLicenseBasicInfoDto extends DtoBase {

	/**
	 * カスタマーID
	 */
	@Size(max = 255)
	@Schema(description = "カスタマーID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,25]")
	private String customerId;

	/**
	 * トレンドユーザーID
	 */
	@Size(max = 255)
	@Schema(description = "トレンドユーザーID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,25]")
	private String trendUserId;

	/**
	 * MVBアカウント
	 */
	@Size(max = 255)
	@Schema(description = "MVBアカウント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,25]")
	private String mvbAccount;

	/**
	 * ライセンス状態
	 */
	@Schema(description = "ライセンス状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private CasLicenseStatus licenseStatus;

	/**
	 * MoM企業ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,25]")
	private String momCompanyId;

	/**
	 * 担当者氏名_姓
	 */
	@Size(max = 255)
	@Schema(description = "担当者氏名_姓", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,25]")
	private String picNameSei;

	/**
	 * 担当者氏名_名
	 */
	@Size(max = 255)
	@Schema(description = "担当者氏名_名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,25]")
	private String picNameMei;

	/**
	 * 担当者メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "担当者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,25]")
	private String picMailAddress;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,25]")
	private String companyName;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@Schema(description = "都道府県", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,25]")
	private String prefectures;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@Schema(description = "市区町村", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,25]")
	private String municipality;

	/**
	 * 初期パスワード
	 */
	@Size(max = 255)
	@Schema(description = "初期パスワード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,25]")
	private String initialPassword;
	
	/**
	 * CASライセンス管理情報
	 */
	@Valid
	@Schema(description = "CASライセンス管理情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<CasLicenseManagementInfoDto> casLicenseManagementInfoList;
}
