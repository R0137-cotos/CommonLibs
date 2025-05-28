package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * チェックアラートマスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "check_alert_master")
public class CheckAlertMaster extends EntityBaseMaster {

	/**
	 * チェックアラートマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_alert_master_seq")
	@SequenceGenerator(name = "check_alert_master_seq", sequenceName = "check_alert_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "チェックアラートマスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * チェックコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "チェックコード", required = true, position = 2, allowableValues = "range[0,255]")
	private String checkCode;

	/**
	 * 対象ドメイン
	 */
	@ApiModelProperty(value = "対象ドメイン", required = true, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")", example = "1", position = 3)
	private ServiceCategory serviceCategory;

	/**
	 * 共通チェックフラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "共通チェックフラグ", required = true, position = 4, allowableValues = "range[0,255]")
	private Integer commonCheckFlg;

	/**
	 * メッセージ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メッセージ", required = false, position = 5, allowableValues = "range[0,255]")
	private String message;

	/**
	 * チェックアラート対象マスタ
	 */
	@OneToMany(mappedBy = "checkAlertMaster")
	@ApiModelProperty(value = "チェックアラート対象マスタ", required = false, position = 6)
	private List<CheckAlertTargetMaster> checkAlertTargetMasterList;
}
