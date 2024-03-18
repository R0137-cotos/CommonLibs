package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロ顧客情報作成レスポンスWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_create_customer_response_work")
public class TmCreateCustomerResponseWork extends AbstractTmResponseWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_create_customer_response_work_seq")
	@SequenceGenerator(name = "tm_create_customer_response_work_seq", sequenceName = "tm_create_customer_response_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "トレンドマイクロ顧客情報作成レスポンスWORK ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トレンドマイクロ顧客情報作成リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロ顧客情報作成リクエストWORK", required = true, position = 2)
	private TmCreateCustomerRequestWork requestWork;

	/**
	 * 会社ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "会社ID", required = false, position = 6, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ユーザーID", required = false, position = 7, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * ログインアカウント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ログインアカウント", required = false, position = 8, allowableValues = "range[0,255]")
	private String userLoginName;

	/**
	 * ログインパスワード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ログインパスワード", required = false, position = 9, allowableValues = "range[0,255]")
	private String userPassword;

	/**
	 * リセット用パスワードURL
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "リセット用パスワードURL", required = false, position = 10, allowableValues = "range[0,255]")
	private String userResetpasswordurl;
}
