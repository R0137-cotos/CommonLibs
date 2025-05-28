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
	@ApiModelProperty(value = "ユーザーアカウント更新レスポンスWORK ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ユーザーアカウント更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ユーザーアカウント更新リクエストWORK", required = true, position = 2)
	private TmUpdateUserRequestWork requestWork;

	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ユーザーID", required = false, position = 6, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * 担当者名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者名", required = false, position = 7, allowableValues = "range[0,255]")
	private String firstName;

	/**
	 * 担当者姓
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者姓", required = false, position = 8, allowableValues = "range[0,255]")
	private String lastName;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールアドレス", required = false, position = 9, allowableValues = "range[0,255]")
	private String email;

	/**
	 * タイムゾーン
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "タイムゾーン", required = false, position = 10, allowableValues = "range[0,255]")
	private String timeZone;

	/**
	 * 言語
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "言語", required = false, position = 11, allowableValues = "range[0,255]")
	private String language;

	/**
	 * 市外局番
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "市外局番", required = false, position = 12, allowableValues = "range[0,255]")
	private String phoneAreaCode;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "電話番号", required = false, position = 13, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * 内線番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "内線番号", required = false, position = 14, allowableValues = "range[0,255]")
	private String phoneExtension;
}
