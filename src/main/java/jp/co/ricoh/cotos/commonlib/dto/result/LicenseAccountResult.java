package jp.co.ricoh.cotos.commonlib.dto.result;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
public class LicenseAccountResult {

	/**
	 * ライセンスアカウントID
	 */
	@Id
	@ApiModelProperty(value = "ライセンスアカウントID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * アカウント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アカウント", required = false, position = 2, allowableValues = "range[0,255]")
	private String account;

	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ユーザーID", required = false, position = 3, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名", required = false, position = 4, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都道府県", required = false, position = 5, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * 初期パスワード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "初期パスワード", required = false, position = 6, allowableValues = "range[0,255]")
	private String initialPassword;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 7)
	private String extendsParameter;

}
