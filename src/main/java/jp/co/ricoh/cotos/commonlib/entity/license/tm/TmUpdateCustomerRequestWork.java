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
 * トレンドマイクロ会社情報更新リクエストWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_update_customer_request_work")
public class TmUpdateCustomerRequestWork extends AbstractTmRequestWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_update_customer_request_work_seq")
	@SequenceGenerator(name = "tm_update_customer_request_work_seq", sequenceName = "tm_update_customer_request_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "トレンドマイクロ会社情報更新リクエストWORK ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 会社
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "会社", required = false, position = 7, allowableValues = "range[0,255]")
	private String name;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都道府県", required = false, position = 8, allowableValues = "range[0,255]")
	private String state;

	/**
	 * 国
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "国", required = false, position = 9, allowableValues = "range[0,255]")
	private String country;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "市区町村", required = false, position = 10, allowableValues = "range[0,255]")
	private String city;

	/**
	 * 会社ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "会社ID", required = false, position = 11, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * トレンドマイクロ会社情報更新レスポンスWORK
	 */
	@Valid
	@OneToOne(mappedBy = "requestWork")
	@ApiModelProperty(value = "トレンドマイクロ会社情報更新レスポンスWORK", required = false, position = 12)
	private TmUpdateCustomerResponseWork responseWork;

}
