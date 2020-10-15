package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * ヤマト便作業対象一覧取得APIの検索結果用パラメーター
 */

@Data
public class YamatoSearchResult {

	/**
	 * サービス利用希望日
	 */
	@ApiModelProperty(value = "サービス利用希望日", required = false, position = 1)
	@Temporal(TemporalType.DATE)
	private Date conclusionPreferredDate;

	/**
	 * 業務受付日時
	 */
	@ApiModelProperty(value = "業務受付日時", required = false, position = 2)
	@Temporal(TemporalType.TIMESTAMP)
	private Date businessAcceptDateTime;

	/**
	 * 業務受付枝番
	 */
	@Max(99)
	@Min(0)
	@ApiModelProperty(value = "業務受付枝番", required = false, position = 3, allowableValues = "range[1,99]")
	private Integer businessAcceptBranchNumber;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名(作成時不要)", required = false, position = 4, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * お問い合わせ番号
	 */
	@ApiParam(value = "お問い合わせ番号", required = false)
	@ApiModelProperty(value = "お問い合わせ番号", required = false, allowableValues = "range[0,255]", position = 5)
	private String contactNo;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "郵便番号", required = false, position = 6, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 住所
	 */
	@Size(max = 1000)
	@ApiModelProperty(value = "住所", required = false, position = 7, allowableValues = "range[0,1000]")
	private String address;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "電話番号", required = false, position = 8, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * 社員名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "社員名", required = false, position = 8, allowableValues = "range[0,255]")
	private String employeeName;

}
