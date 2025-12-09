package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロ顧客情報作成リクエストWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_create_customer_request_work")
public class TmCreateCustomerRequestWork extends AbstractTmRequestWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_create_customer_request_work_seq")
	@SequenceGenerator(name = "tm_create_customer_request_work_seq", sequenceName = "tm_create_customer_request_work_seq", allocationSize = 1)
	@Schema(description = "トレンドマイクロ顧客情報作成リクエストWORK ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 会社
	 */
	@Size(max = 255)
	@Schema(description = "会社", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@Schema(description = "都道府県", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyState;

	/**
	 * 国
	 */
	@Size(max = 255)
	@Schema(description = "国", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyCountry;

	/**
	 * アカウント
	 */
	@Size(max = 255)
	@Schema(description = "アカウント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String userLoginName;

	/**
	 * 担当者名
	 */
	@Size(max = 255)
	@Schema(description = "担当者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String userFirstName;

	/**
	 * 担当者姓
	 */
	@Size(max = 255)
	@Schema(description = "担当者姓", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String userLastName;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String userEmail;

	/**
	 * タイムゾーン
	 */
	@Size(max = 255)
	@Schema(description = "タイムゾーン", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String userTimeZone;

	/**
	 * 言語
	 */
	@Size(max = 255)
	@Schema(description = "言語", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String userLanguage;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@Schema(description = "市区町村", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyCity;

	/**
	 * トレンドマイクロ顧客情報作成レスポンスWORK
	 */
	@Valid
	@OneToOne(mappedBy = "requestWork")
	@Schema(description = "トレンドマイクロ顧客情報作成レスポンスWORK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private TmCreateCustomerResponseWork responseWork;
}
