package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * チェックアラート対象マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "check_alert_target_master")
public class CheckAlertTargetMaster extends EntityBaseMaster {

	/**
	 * チェックアラート対象マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_alert_target_master_seq")
	@SequenceGenerator(name = "check_alert_target_master_seq", sequenceName = "check_alert_target_master_seq", allocationSize = 1)
	@Schema(description = "チェックアラート対象マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * チェックアラートマスタID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "check_alert_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "チェックアラートマスタID", requiredMode = Schema.RequiredMode.REQUIRED)
	private CheckAlertMaster checkAlertMaster;

	/**
	 * 対象マスタID
	 */
	@Schema(description = "対象マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long targetMasterId;

}
