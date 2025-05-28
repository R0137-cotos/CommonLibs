package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロ会社情報更新レスポンスWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_update_customer_response_work")
public class TmUpdateCustomerResponseWork extends AbstractTmResponseWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_update_customer_response_work_seq")
	@SequenceGenerator(name = "tm_update_customer_response_work_seq", sequenceName = "tm_update_customer_response_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "トレンドマイクロ会社情報更新レスポンスWORK ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トレンドマイクロ会社情報更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロ会社情報更新リクエストWORK", required = true, position = 2)
	private TmUpdateCustomerRequestWork requestWork;

	/**
	 * 会社
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "会社", required = false, position = 6, allowableValues = "range[0,255]")
	private String name;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都道府県", required = false, position = 7, allowableValues = "range[0,255]")
	private String state;

	/**
	 * 国
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "国", required = false, position = 8, allowableValues = "range[0,255]")
	private String country;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "市区町村", required = false, position = 9, allowableValues = "range[0,255]")
	private String city;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "住所", required = false, position = 10, allowableValues = "range[0,255]")
	private String address;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "郵便番号", required = false, position = 11, allowableValues = "range[0,255]")
	private String postalCode;

	/**
	 * 備考
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "備考", required = false, position = 12, allowableValues = "range[0,255]")
	private String note;

	/**
	 * その他のメールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "その他のメールアドレス", required = false, position = 13, allowableValues = "range[0,255]")
	private String emergencyEmail;

}
