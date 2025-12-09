package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.LicenseStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.RequestCreateStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LicenseAccountDto extends DtoBase {

	/**
	 * アカウント
	 */
	@Size(max = 255)
	@Schema(description = "アカウント", required = false, allowableValues = "range[0,255]")
	private String account;

	/**
	 * カスタマーID
	 */
	@Size(max = 255)
	@Schema(description = "カスタマーID", required = false, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@Schema(description = "ユーザーID", required = false, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * 初期パスワード
	 */
	@Size(max = 255)
	@Schema(description = "初期パスワード", required = false, allowableValues = "range[0,255]")
	private String initialPassword;

	/**
	 * ライセンス状態
	 */
	@Schema(description = "ライセンス状態", required = false, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private LicenseStatus licenseStatus;

	/**
	 * MoM企業ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM企業ID", required = false, allowableValues = "range[0,255]")
	private String momCompanyId;

	/**
	 * 担当者氏名_姓
	 */
	@Size(max = 255)
	@Schema(description = "担当者氏名_姓", required = false, allowableValues = "range[0,255]")
	private String picNameSei;

	/**
	 * 担当者氏名_名
	 */
	@Size(max = 255)
	@Schema(description = "担当者氏名_名", required = false, allowableValues = "range[0,255]")
	private String picNameMei;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名", required = false, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@Schema(description = "都道府県", required = false, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@Schema(description = "市区町村", required = false, allowableValues = "range[0,255]")
	private String municipality;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", required = false)
	@Lob
	private String extendsParameter;

	/**
	 * リクエスト作成状態
	 */
	@Schema(description = "リクエスト作成状態", required = false, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")")
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "リクエスト作成日時", required = false)
	private Date requestCreateDate;

	/**
	 * ライセンスキー情報
	 */
	@Schema(description = "ライセンスキー情報", required = false)
	@Valid
	private List<LicenseKeyInfoDto> licenseKeyInfoList;
}
