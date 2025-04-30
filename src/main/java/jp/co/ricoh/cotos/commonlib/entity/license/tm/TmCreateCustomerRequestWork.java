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

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "トレンドマイクロ顧客情報作成リクエストWORK ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 会社
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "会社", required = false, position = 7, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都道府県", required = false, position = 8, allowableValues = "range[0,255]")
	private String companyState;

	/**
	 * 国
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "国", required = false, position = 9, allowableValues = "range[0,255]")
	private String companyCountry;

	/**
	 * アカウント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アカウント", required = false, position = 10, allowableValues = "range[0,255]")
	private String userLoginName;

	/**
	 * 担当者名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者名", required = false, position = 11, allowableValues = "range[0,255]")
	private String userFirstName;

	/**
	 * 担当者姓
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者姓", required = false, position = 12, allowableValues = "range[0,255]")
	private String userLastName;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールアドレス", required = false, position = 13, allowableValues = "range[0,255]")
	private String userEmail;

	/**
	 * タイムゾーン
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "タイムゾーン", required = false, position = 14, allowableValues = "range[0,255]")
	private String userTimeZone;

	/**
	 * 言語
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "言語", required = false, position = 15, allowableValues = "range[0,255]")
	private String userLanguage;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "市区町村", required = false, position = 16, allowableValues = "range[0,255]")
	private String companyCity;

	/**
	 * トレンドマイクロ顧客情報作成レスポンスWORK
	 */
	@Valid
	@OneToOne(mappedBy = "requestWork")
	@ApiModelProperty(value = "トレンドマイクロ顧客情報作成レスポンスWORK", required = false, position = 17)
	private TmCreateCustomerResponseWork responseWork;
}
