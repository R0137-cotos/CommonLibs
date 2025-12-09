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
	@Schema(description = "トレンドマイクロ顧客情報作成レスポンスWORK ID", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トレンドマイクロ顧客情報作成リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "トレンドマイクロ顧客情報作成リクエストWORK", required = true)
	private TmCreateCustomerRequestWork requestWork;

	/**
	 * 会社ID
	 */
	@Size(max = 255)
	@Schema(description = "会社ID", required = false, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@Schema(description = "ユーザーID", required = false, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * ログインアカウント
	 */
	@Size(max = 255)
	@Schema(description = "ログインアカウント", required = false, allowableValues = "range[0,255]")
	private String userLoginName;

	/**
	 * ログインパスワード
	 */
	@Size(max = 255)
	@Schema(description = "ログインパスワード", required = false, allowableValues = "range[0,255]")
	private String userPassword;

	/**
	 * リセット用パスワードURL
	 */
	@Size(max = 255)
	@Schema(description = "リセット用パスワードURL", required = false, allowableValues = "range[0,255]")
	private String userResetpasswordurl;
}
