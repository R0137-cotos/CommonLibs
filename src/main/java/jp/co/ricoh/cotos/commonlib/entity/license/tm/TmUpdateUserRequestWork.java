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
 * ユーザーアカウント更新リクエストWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_update_user_request_work")
public class TmUpdateUserRequestWork extends AbstractTmRequestWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_update_user_request_work_seq")
	@SequenceGenerator(name = "tm_update_user_request_work_seq", sequenceName = "tm_update_user_request_work_seq", allocationSize = 1)
	@Schema(description = "ユーザーアカウント更新リクエストWORK ID", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "メールアドレス", required = false, allowableValues = "range[0,255]")
	private String email;

	/**
	 * 会社ID
	 */
	@Size(max = 255)
	@Schema(description = "会社ID", required = false, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@Schema(description = "ユーザーID", required = false, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * ユーザーアカウント更新レスポンスWORK
	 */
	@Valid
	@OneToOne(mappedBy = "requestWork")
	@Schema(description = "ユーザーアカウント更新レスポンスWORK", required = false)
	private TmUpdateUserResponseWork responseWork;

}
