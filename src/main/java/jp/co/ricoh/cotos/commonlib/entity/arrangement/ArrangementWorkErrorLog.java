package jp.co.ricoh.cotos.commonlib.entity.arrangement;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手配業務エラー履歴を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "arrangement_work_error_log")
public class ArrangementWorkErrorLog extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_work_error_log_seq")
	@SequenceGenerator(name = "arrangement_work_error_log_seq", sequenceName = "arrangement_work_error_log_seq", allocationSize = 1)
	@Schema(description = "手配業務エラー履歴ID ", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_work_id", referencedColumnName = "id")
	@Schema(description = "手配業務", required = true)
	@JsonIgnore
	private ArrangementWork arrangementWork;

	@Size(max = 4000)
	@Schema(description = "エラー内容", required = false, allowableValues = "range[0,4000]")
	private String errorMessage;

	@Schema(description = "エラー発生日時(作成時不要)", required = false, readOnly = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date errorOccurredAt;

	@Size(max = 4000)
	@Schema(description = "APIリクエスト", required = false, allowableValues = "range[0,4000]")
	private String apiRequest;

	@PrePersist
	public void prePersist() {
		super.prePersist();
		this.errorOccurredAt = super.getCreatedAt();
	}

}
