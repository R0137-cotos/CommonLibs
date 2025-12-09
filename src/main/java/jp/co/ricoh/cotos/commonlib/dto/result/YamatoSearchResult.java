package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "手配業務ID", required = false)
	private long arrangementWorkId;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", required = false)
	private long contractId;

	/**
	 * サービス利用希望日
	 */
	@Schema(description = "サービス利用希望日", required = false)
	@Temporal(TemporalType.DATE)
	private Date conclusionPreferredDate;

	/**
	 * 業務受付日時
	 */
	@Schema(description = "業務受付日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date businessAcceptDateTime;

	/**
	 * 業務受付枝番
	 */
	@Schema(description = "業務受付枝番", required = false, allowableValues = "range[0,99]")
	private Integer businessAcceptBranchNumber;

	/**
	 * 企業名
	 */
	@Schema(description = "企業名", required = false, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * お問い合わせ番号
	 */
	@Schema(description = "お問い合わせ番号", required = false, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * 郵便番号
	 */
	@Schema(description = "郵便番号", required = false, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 都道府県
	 */
	@Schema(description = "都道府県", required = false, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * 市区町村番地
	 */
	@Schema(description = "市区町村番地", required = false, allowableValues = "range[0,1000]")
	private String cityStreet;

	/**
	 * 建物名
	 */
	@Schema(description = "建物名", required = false, allowableValues = "range[0,255]")
	private String buildingName;

	/**
	 * 電話番号
	 */
	@Schema(description = "電話番号", required = false, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * 社員名
	 */
	@Schema(description = "社員名", required = false, allowableValues = "range[0,255]")
	private String employeeName;

}
