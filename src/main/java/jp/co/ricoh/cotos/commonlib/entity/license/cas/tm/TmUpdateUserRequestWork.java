package jp.co.ricoh.cotos.commonlib.entity.license.cas.tm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ユーザーアカウント更新リクエストWORK", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールアドレス", required = false, position = 7, allowableValues = "range[0,255]")
	private String email;
	
	/**
	 * ユーザーアカウント更新レスポンスWORK
	 */
	@Valid
	@OneToOne(mappedBy = "requestWork")
	@ApiModelProperty(value = "ユーザーアカウント更新レスポンスWORK", required = false, position = 17)
	private TmUpdateUserResponseWork responseWork;

}
