package jp.co.ricoh.cotos.commonlib.dto.result;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * MVBアカウント検索結果をリスト取得するためのDtoです。<br/>
 * 一覧を取得するためには、こちらのクラスを使ってください。 <br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Entity
@Data
public class CasLicenseListInfo {

	@Id
	@ApiModelProperty(value = "連番", required = true, position = 1)
	private long seqNo;

	/**
	 * MVBアカウント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MVBアカウント", required = false, position = 2, allowableValues = "range[0,25]")
	private String mvbAccount;

	/**
	 * 担当者氏名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者氏名", required = false, position = 3, allowableValues = "range[0,25]")
	private String picName;

	/**
	 * 担当者メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者メールアドレス", required = false, position = 4, allowableValues = "range[0,25]")
	private String picMailAddress;

	/**
	 * TrendMicroアカウント企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名", required = false, position = 5, allowableValues = "range[0,25]")
	private String tmAccountCompanyName;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都道府県", required = false, position = 6, allowableValues = "range[0,25]")
	private String prefectures;

}