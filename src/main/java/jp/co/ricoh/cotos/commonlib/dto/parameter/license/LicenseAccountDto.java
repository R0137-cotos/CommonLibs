package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "アカウント", required = false, position = 3, allowableValues = "range[0,255]")
	private String account;

	/**
	 * カスタマーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "カスタマーID", required = false, position = 4, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ユーザーID", required = false, position = 5, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * 初期パスワード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "初期パスワード", required = false, position = 6, allowableValues = "range[0,255]")
	private String initialPassword;

	/**
	 * ライセンス状態
	 */
	@ApiModelProperty(value = "ライセンス状態", required = false, position = 7, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private LicenseStatus licenseStatus;

	/**
	 * MoM企業ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MoM企業ID", required = false, position = 8, allowableValues = "range[0,255]")
	private String momCompanyId;

	/**
	 * 担当者氏名_姓
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者氏名_姓", required = false, position = 9, allowableValues = "range[0,255]")
	private String picNameSei;

	/**
	 * 担当者氏名_名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者氏名_名", required = false, position = 10, allowableValues = "range[0,255]")
	private String picNameMei;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名", required = false, position = 11, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都道府県", required = false, position = 12, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "市区町村", required = false, position = 13, allowableValues = "range[0,255]")
	private String municipality;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 14)
	@Lob
	private String extendsParameter;

	/**
	 * リクエスト作成状態
	 */
	@ApiModelProperty(value = "リクエスト作成状態", required = false, position = 15, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")")
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "リクエスト作成日時", required = false, position = 16)
	private Date requestCreateDate;

	/**
	 * ライセンスキー情報
	 */
	@ApiModelProperty(value = "ライセンスキー情報", required = false, position = 17)
	@Valid
	private List<LicenseKeyInfoDto> licenseKeyInfoList;
}
