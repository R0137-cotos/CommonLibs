package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文顧客情報DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OrderContractorInfoDto {

	/**
	 * 企業ＩＤ
	 */
	@Size(max = 255)
	@Schema(description = "企業ＩＤ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * 企事部ＩＤ
	 */
	@Size(max = 255)
	@Schema(description = "企事部ＩＤ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String kjbId;

	/**
	 * 会員基本ID
	 */
	@Size(max = 255)
	@Schema(description = "会員基本ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	@JsonProperty("netRicohAccount")
	private String netricohAccount;

	/**
	 * 得意先コード
	 */
	@Size(max = 255)
	@Schema(description = "得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerCd;

	/**
	 * 会社名
	 */
	@Size(max = 255)
	@Schema(description = "会社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractorCompanyName;

	/**
	 * 担当者漢字（姓＋名）
	 */
	@Size(max = 255)
	@Schema(description = "担当者漢字（姓＋名）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractorNameKanji;

	/**
	 * 担当者カナ（姓＋名）
	 */
	@Size(max = 255)
	@Schema(description = "担当者カナ（姓＋名）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractorNameKana;

	/**
	 * 担当者メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "担当者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractorMailAddress;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractorPostNumber;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@Schema(description = "事業所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractorOfficeName;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@Schema(description = "住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractorAddress;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@Schema(description = "電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractorPhoneNumber;

	/**
	 * 利用登録権限(NetRICOH)
	 */
	@Size(max = 255)
	@Schema(description = "利用登録権限(NetRICOH)", required = false, allowableValues = "range[0,255]")
	@JsonProperty("authorityForNetRicoh")
	private String authorityForNetricoh;

	/**
	 * サービス開始希望日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "サービス開始希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date desiredServiceStartDate;

}
