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
 * ユーザーアカウント更新レスポンスWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_update_user_response_work")
public class TmUpdateUserResponseWork extends AbstractTmResponseWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_update_user_response_work_seq")
	@SequenceGenerator(name = "tm_update_user_response_work_seq", sequenceName = "tm_update_user_response_work_seq", allocationSize = 1)
	@Schema(description = "ユーザーアカウント更新レスポンスWORK ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ユーザーアカウント更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "ユーザーアカウント更新リクエストWORK", requiredMode = Schema.RequiredMode.REQUIRED)
	private TmUpdateUserRequestWork requestWork;

	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@Schema(description = "ユーザーID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * 担当者名
	 */
	@Size(max = 255)
	@Schema(description = "担当者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String firstName;

	/**
	 * 担当者姓
	 */
	@Size(max = 255)
	@Schema(description = "担当者姓", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String lastName;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String email;

	/**
	 * タイムゾーン
	 */
	@Size(max = 255)
	@Schema(description = "タイムゾーン", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String timeZone;

	/**
	 * 言語
	 */
	@Size(max = 255)
	@Schema(description = "言語", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String language;

	/**
	 * 市外局番
	 */
	@Size(max = 255)
	@Schema(description = "市外局番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneAreaCode;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@Schema(description = "電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * 内線番号
	 */
	@Size(max = 255)
	@Schema(description = "内線番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneExtension;
}
