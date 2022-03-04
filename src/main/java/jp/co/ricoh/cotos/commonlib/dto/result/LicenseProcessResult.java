package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess.MailSendResultDiv;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess.ProcessStatus;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessMaster.OperationDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessPatternMaster.MailDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス工程を取得するためのDTOです。
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class LicenseProcessResult extends DtoBase {

	/**
	 * 工程順
	 */
	@ApiModelProperty(value = "工程順", required = true, position = 3, allowableValues = "range[0,999]")
	private int processOrder;

	/**
	 * ライセンス工程マスタID
	 */
	@ApiModelProperty(value = "ライセンス工程マスタID", required = true, position = 4, allowableValues = "range[0,9223372036854775807]")
	private long processMasterId;

	/**
	 * 工程名称
	 */
	@ApiModelProperty(value = "工程名称", required = false, position = 5, allowableValues = "range[0,255]")
	private String processName;

	/**
	 * 工程完了名称
	 */
	@ApiModelProperty(value = "工程完了名称", required = false, position = 6, allowableValues = "range[0,255]")
	private String completeProcessName;

	/**
	 * 手配業務ID
	 */
	@ApiModelProperty(value = "手配業務ID", required = true, position = 7, allowableValues = "range[0,9223372036854775807]")
	private long arrangementWorkId;

	/**
	 * 操作区分
	 */
	@ApiModelProperty(value = "操作区分", required = true, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")", position = 8)
	private OperationDiv operationDiv;

	/**
	 * メール区分
	 */
	@ApiModelProperty(value = "メール区分", required = false, position = 9, allowableValues = "事前完了メール(\"1\"), Welcomeメール(\"2\")")
	private MailDiv mailDiv;

	/**
	 * メール到達チェックフラグ
	 */
	@ApiModelProperty(value = "メール到達チェックフラグ", required = false, position = 10, allowableValues = "range[0,9]")
	private Integer mailArrivalCheckFlg;

	/**
	 * メール送信日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "メール送信日", required = false, position = 11)
	private Date mailSendAt;

	/**
	 * 送信結果区分
	 */
	@ApiModelProperty(value = "送信結果区分", required = false, allowableValues = "未送信(\"0\"), 送信中(\"1\"), 送信済(\"2\"), 不達(\"3\"), 送信エラー(\"4\")", position = 12)
	private MailSendResultDiv mailSendResultDiv;

	/**
	 * 工程状態
	 */
	@ApiModelProperty(value = "工程状態", required = false, position = 13, allowableValues = "未処理(\"0\"), 完了(\"1\"), 破棄(\"2\")")
	private ProcessStatus processStatus;

	/**
	 * メールマスタID
	 */
	@ApiModelProperty(value = "メールマスタID", required = false, position = 14, allowableValues = "range[0,9223372036854775807]")
	private Long mailMasterId;

	/**
	 * メール到着チェック時間
	 */
	@ApiModelProperty(value = "メール到着チェック時間", required = false, position = 15, allowableValues = "range[-99999,99999]")
	private Integer mailArrivalCheckHour;

	/**
	 * 到着チェックメール制御マスタID
	 */
	@ApiModelProperty(value = "到着チェックメール制御マスタID", required = false, position = 16, allowableValues = "range[0,9223372036854775807]")
	private Long arrivalCheckMailControlMasterId;

	/**
	 * 実施日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "実施日時", required = false, position = 17)
	private Date operatedAt;
}
