package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプション解約レスポンスWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_suspend_subscription_response_work")
public class TmSuspendSubscriptionResponseWork extends AbstractTmResponseWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_suspend_subscription_response_work_seq")
	@SequenceGenerator(name = "tm_suspend_subscription_response_work_seq", sequenceName = "tm_suspend_subscription_response_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション解約レスポンスWORK", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トレンドマイクロサブスクリプション解約リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション解約リクエストWORK", required = true, position = 2)
	private TmSuspendSubscriptionRequestWork requestWork;
}
