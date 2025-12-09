package jp.co.ricoh.cotos.commonlib.dto.result;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * アカウント検索結果をリスト取得するためのDtoです。<br/>
 * 一覧を取得するためには、こちらのクラスを使ってください。 <br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Entity
@Data
public class LicenseAccountListInfo {

	@Id
	@Schema(description = "連番", required = true)
	private long seqNo;

	/**
	 * アカウント
	 */
	@Schema(description = "アカウント", required = false, allowableValues = "range[0,18]")
	private String account;

	/**
	 * 担当者氏名
	 */
	@Schema(description = "担当者氏名", required = false, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * アカウント担当者メールアドレス
	 */
	@Schema(description = "アカウント担当者メールアドレス", required = false, allowableValues = "range[0,255]")
	private String accountPicMailAddress;

	/**
	 * アカウント企業名
	 */
	@Schema(description = "アカウント企業名", required = false, allowableValues = "range[0,255]")
	private String accountCompanyName;

	/**
	 * 都道府県
	 */
	@Schema(description = "都道府県", required = false, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * インストールURL
	 */
	@Schema(description = "インストールURL", required = false, allowableValues = "range[0,255]")
	private String installUrl;

	/**
	 * 費用種別
	 */
	@Schema(description = "費用種別", required = false, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\"), 違約金(\"5\")")
	private String costType;

	/**
	 * 数量
	 */
	@Schema(description = "数量", required = false, allowableValues = "range[-99999,99999]")
	private Integer licenseQuantity;

	/**
	 * 商品名
	 */
	@Schema(description = "商品名", required = false, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * MoM企業ID
	 */
	@Schema(description = "MoM企業ID", required = false, allowableValues = "range[0,255]")
	private String momCompanyId;

	/**
	 * 商品種類区分
	 */
	@Schema(description = "商品種類区分", required = false, allowableValues = "range[0,255]")
	private String productClassDiv;
}
