package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess.MailSendResultDiv;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess.ProcessStatus;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessMaster.OperationDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessPatternMaster.MailDiv;
import lombok.Data;

/**
 * ライセンス工程を取得するためのDTOです。
 */
@Entity
@Data
public class LicenseProcessResult {

	/**
	 * ライセンス工程ID
	 */
	@Id
	@Schema(description = "ライセンス工程ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * ライセンス工程version
	 */
	@Version
	@Schema(description = "ライセンス工程version(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long version;

	/**
	 * 工程順
	 */
	@Schema(description = "工程順", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private int processOrder;

	/**
	 * ライセンス工程マスタID
	 */
	@Schema(description = "ライセンス工程マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long processMasterId;

	/**
	 * 工程名称
	 */
	@Schema(description = "工程名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String processName;

	/**
	 * 工程完了名称
	 */
	@Schema(description = "工程完了名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String completeProcessName;

	/**
	 * 手配業務ID
	 */
	@Schema(description = "手配業務ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long arrangementWorkId;

	/**
	 * 操作区分
	 */
	@Schema(description = "操作区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")")
	private OperationDiv operationDiv;

	/**
	 * メール区分
	 */
	@Schema(description = "メール区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "事前完了メール(\"1\"), Welcomeメール(\"2\")")
	private MailDiv mailDiv;

	/**
	 * メール到達チェックフラグ
	 */
	@Schema(description = "メール到達チェックフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer mailArrivalCheckFlg;

	/**
	 * メール送信日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "メール送信日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date mailSendAt;

	/**
	 * 送信結果区分
	 */
	@Schema(description = "送信結果区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未送信(\"0\"), 送信中(\"1\"), 送信済(\"2\"), 不達(\"3\"), 送信エラー(\"4\")")
	private MailSendResultDiv mailSendResultDiv;

	/**
	 * 工程状態
	 */
	@Schema(description = "工程状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 完了(\"1\"), 破棄(\"2\")")
	private ProcessStatus processStatus;

	/**
	 * メールマスタID
	 */
	@Schema(description = "メールマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long mailMasterId;

	/**
	 * メール到着チェック時間
	 */
	@Schema(description = "メール到着チェック時間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer mailArrivalCheckHour;

	/**
	 * 到着チェックメール制御マスタID
	 */
	@Schema(description = "到着チェックメール制御マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long arrivalCheckMailControlMasterId;

	/**
	 * 実施日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "実施日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date operatedAt;
}
