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
public class LicenseAccountListInfo {

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
	 * アカウント担当者メールアドレス
	 */
	@ApiModelProperty(value = "アカウント担当者メールアドレス", required = false, position = 3, allowableValues = "range[0,255]")
	private String accountPicMailAddress;

	/**
	 * アカウント企業名
	 */
	@ApiModelProperty(value = "アカウント企業名", required = false, position = 4, allowableValues = "range[0,255]")
	private String accountCompanyName;

	/**
	 * 都道府県
	 */
	@ApiModelProperty(value = "都道府県", required = false, position = 5, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * インストールURL
	 */
	@ApiModelProperty(value = "インストールURL", required = false, position = 6, allowableValues = "range[0,255]")
	private String installUrl;

	/**
	 * 費用種別
	 */
	@ApiModelProperty(value = "費用種別", required = false, position = 7, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\"), 違約金(\"5\")")
	private String costType;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量", required = false, position = 8, allowableValues = "range[-99999,99999]")
	private Integer licenseQuantity;

	/**
	 * 商品名
	 */
	@ApiModelProperty(value = "商品名", required = false, position = 9, allowableValues = "range[0,255]")
	private String productName;
}
