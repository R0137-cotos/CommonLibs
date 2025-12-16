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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "チェックアラートマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * チェックコード
	 */
	@Size(max = 255)
	@Schema(description = "チェックコード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String checkCode;

	/**
	 * 対象ドメイン
	 */
	@Schema(description = "対象ドメイン", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")", example = "1")
	private ServiceCategory serviceCategory;

	/**
	 * 共通チェックフラグ
	 */
	@Max(9)
	@Schema(description = "共通チェックフラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private Integer commonCheckFlg;

	/**
	 * メッセージ
	 */
	@Size(max = 255)
	@Schema(description = "メッセージ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String message;

	/**
	 * チェックアラート対象マスタ
	 */
	@OneToMany(mappedBy = "checkAlertMaster")
	@Schema(description = "チェックアラート対象マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<CheckAlertTargetMaster> checkAlertTargetMasterList;
}
