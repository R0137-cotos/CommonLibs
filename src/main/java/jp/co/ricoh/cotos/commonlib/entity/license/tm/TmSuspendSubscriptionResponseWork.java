package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "トレンドマイクロサブスクリプション解約レスポンスWORK ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トレンドマイクロサブスクリプション解約リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "トレンドマイクロサブスクリプション解約リクエストWORK", requiredMode = Schema.RequiredMode.REQUIRED)
	private TmSuspendSubscriptionRequestWork requestWork;
}
