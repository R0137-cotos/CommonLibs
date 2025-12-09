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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "トレンドマイクロ会社情報更新レスポンスWORK ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トレンドマイクロ会社情報更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "トレンドマイクロ会社情報更新リクエストWORK", requiredMode = Schema.RequiredMode.REQUIRED)
	private TmUpdateCustomerRequestWork requestWork;

	/**
	 * 会社
	 */
	@Size(max = 255)
	@Schema(description = "会社", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String name;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@Schema(description = "都道府県", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String state;

	/**
	 * 国
	 */
	@Size(max = 255)
	@Schema(description = "国", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String country;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@Schema(description = "市区町村", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String city;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@Schema(description = "住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String address;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String postalCode;

	/**
	 * 備考
	 */
	@Size(max = 255)
	@Schema(description = "備考", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String note;

	/**
	 * その他のメールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "その他のメールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String emergencyEmail;

}
