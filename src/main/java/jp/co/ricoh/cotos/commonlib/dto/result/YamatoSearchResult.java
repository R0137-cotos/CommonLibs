package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ヤマト便作業対象一覧取得APIの検索結果用パラメーター
 */
@Entity
@Data
public class YamatoSearchResult {

	/**
	 * 手配業務ID
	 */
	@Id
	@ApiModelProperty(value = "手配業務ID", required = false, position = 1)
	private long arrangementWorkId;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = false, position = 2)
	private long contractId;

	/**
	 * サービス利用希望日
	 */
	@ApiModelProperty(value = "サービス利用希望日", required = false, position = 3)
	@Temporal(TemporalType.DATE)
	private Date conclusionPreferredDate;

	/**
	 * 業務受付日時
	 */
	@ApiModelProperty(value = "業務受付日時", required = false, position = 4)
	@Temporal(TemporalType.TIMESTAMP)
	private Date businessAcceptDateTime;

	/**
	 * 業務受付枝番
	 */
	@ApiModelProperty(value = "業務受付枝番", required = false, position = 5, allowableValues = "range[0,99]")
	private Integer businessAcceptBranchNumber;

	/**
	 * 企業名
	 */
	@ApiModelProperty(value = "企業名", required = false, position = 6, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * お問い合わせ番号
	 */
	@ApiModelProperty(value = "お問い合わせ番号", required = false, allowableValues = "range[0,255]", position = 7)
	private String contactNo;

	/**
	 * 郵便番号
	 */
	@ApiModelProperty(value = "郵便番号", required = false, position = 8, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 都道府県
	 */
	@ApiModelProperty(value = "都道府県", required = false, position = 9, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * 市区町村番地
	 */
	@ApiModelProperty(value = "市区町村番地", required = false, position = 10, allowableValues = "range[0,1000]")
	private String cityStreet;

	/**
	 * 建物名
	 */
	@ApiModelProperty(value = "建物名", required = false, position = 11, allowableValues = "range[0,255]")
	private String buildingName;

	/**
	 * 電話番号
	 */
	@ApiModelProperty(value = "電話番号", required = false, position = 12, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * 社員名
	 */
	@ApiModelProperty(value = "社員名", required = false, position = 13, allowableValues = "range[0,255]")
	private String employeeName;

}
