package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "チェックアラート対象マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * チェックアラートマスタID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "check_alert_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "チェックアラートマスタID", required = true, position = 2)
	private CheckAlertMaster checkAlertMaster;

	/**
	 * 対象マスタID
	 */
	@ApiModelProperty(value = "対象マスタID", required = true, position = 3, allowableValues = "range[0,9999999999999999999]")
	private Long targetMasterId;

}
