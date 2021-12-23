package jp.co.ricoh.cotos.commonlib.dto.result;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * アカウント検索結果をリスト取得するためのDtoです。<br/>
 * 一覧を取得するためには、こちらのクラスを使ってください。 <br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Entity
@Data
public class LicenseListInfo {

	/**
	 * アカウント
	 */
	@Id
	@ApiModelProperty(value = "アカウント", required = false, position = 1, allowableValues = "range[0,18]")
	private String account;

	/**
	 * 担当者氏名
	 */
	@ApiModelProperty(value = "担当者氏名", required = false, position = 2, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 担当者メールアドレス
	 */
	@ApiModelProperty(value = "担当者メールアドレス", required = false, position = 3, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * TrendMicroアカウント企業名
	 */
	@ApiModelProperty(value = "企業名", required = false, position = 4, allowableValues = "range[0,255]")
	private String tmAccountCompanyName;

	/**
	 * 都道府県
	 */
	@ApiModelProperty(value = "都道府県", required = false, position = 5, allowableValues = "range[0,255]")
	private String prefectures;
}
