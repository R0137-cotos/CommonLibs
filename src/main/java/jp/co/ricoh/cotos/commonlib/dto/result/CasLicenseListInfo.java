package jp.co.ricoh.cotos.commonlib.dto.result;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * MVBアカウント検索結果をリスト取得するためのDtoです。<br/>
 * 一覧を取得するためには、こちらのクラスを使ってください。 <br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Entity
@Data
public class CasLicenseListInfo {

	/**
	 * MVBアカウント
	 */
	@Id
	@Schema(description = "MVBアカウント", required = false, allowableValues = "range[0,18]")
	private String mvbAccount;

	/**
	 * 担当者氏名
	 */
	@Schema(description = "担当者氏名", required = false, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 担当者メールアドレス
	 */
	@Schema(description = "担当者メールアドレス", required = false, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * TrendMicroアカウント企業名
	 */
	@Schema(description = "企業名", required = false, allowableValues = "range[0,255]")
	private String tmAccountCompanyName;

	/**
	 * 都道府県
	 */
	@Schema(description = "都道府県", required = false, allowableValues = "range[0,255]")
	private String prefectures;

}