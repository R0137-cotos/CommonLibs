package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Entity
@Data
public class BaoLicenseInfoResult {

	/**
	 * 販売店様管理番号
	 */
	@Id
	@Schema(description = "販売店様管理番号", required = false)
	private String dealerManagementNumber;

	/**
	 * 販売店様管理番号枝番
	 */
	@Schema(description = "販売店様管理番号枝番", required = false)
	private String dealerManagementBranchNumber;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", required = false)
	private String contractId;

	/**
	 * EU様会社名
	 */
	@Schema(description = "EU様会社名", required = false)
	private String euCompanyName;

	/**
	 * EU様会社名カナ
	 */
	@Schema(description = "EU様会社名カナ", required = false)
	private String euCompanyNameKana;

	/**
	 * EU様担当者名
	 */
	@Schema(description = "EU様担当者名", required = false)
	private String euPicName;

	/**
	 * EU様ログインID送付先
	 */
	@Schema(description = "EU様ログインID送付先", required = false)
	private String euLoginIdSendTo;

	/**
	 * 月次REPORT送付先1
	 */
	@Schema(description = "月次REPORT送付先1", required = false)
	private String monthlyReportSend1;

	/**
	 * 月次REPORT送付先2
	 */
	@Schema(description = "月次REPORT送付先2", required = false)
	private String monthlyReportSend2;

	/**
	 * 月次REPORT送付先3
	 */
	@Schema(description = "月次REPORT送付先3", required = false)
	private String monthlyReportSend3;

	/**
	 * 月次REPORT送付先4
	 */
	@Schema(description = "月次REPORT送付先4", required = false)
	private String monthlyReportSend4;

	/**
	 * EU様電話番号
	 */
	@Schema(description = "EU様電話番号", required = false)
	private String euPhoneNumber;

	/**
	 * EU様郵便番号
	 */
	@Schema(description = "EU様郵便番号", required = false)
	private String euPostNumber;

	/**
	 * EU様住所
	 */
	@Schema(description = "EU様住所", required = false)
	private String euAddress;

	/**
	 * シリアル番号1
	 */
	@Schema(description = "シリアル番号1", required = false)
	private String serialNumber1;

	/**
	 * 機種名1
	 */
	@Schema(description = "機種名1", required = false)
	private String modelName1;

	/**
	 * OSバージョン1
	 */
	@Schema(description = "OSバージョン1", required = false)
	private String osVersion1;

	/**
	 * シリアル番号2
	 */
	@Schema(description = "シリアル番号2", required = false)
	private String serialNumber2;

	/**
	 * 機種名2
	 */
	@Schema(description = "機種名2", required = false)
	private String modelName2;

	/**
	 * OSバージョン2
	 */
	@Schema(description = "OSバージョン2", required = false)
	private String osVersion2;

	/**
	 * 機器名
	 */
	@Schema(description = "機器名", required = false)
	private String machineName;

	/**
	 * 内部グローバルIP1
	 */
	@Schema(description = "内部グローバルIP1", required = false)
	private String internalGlobalIp1;

	/**
	 * 内部グローバルIP2
	 */
	@Schema(description = "内部グローバルIP2", required = false)
	private String internalGlobalIp2;

	/**
	 * 内部グローバルIP3
	 */
	@Schema(description = "内部グローバルIP3", required = false)
	private String internalGlobalIp3;

	/**
	 * 内部グローバルIP4
	 */
	@Schema(description = "内部グローバルIP4", required = false)
	private String internalGlobalIp4;

	/**
	 * 内部グローバルIP5
	 */
	@Schema(description = "内部グローバルIP5", required = false)
	private String internalGlobalIp5;

	/**
	 * 内部グローバルIP6
	 */
	@Schema(description = "内部グローバルIP6", required = false)
	private String internalGlobalIp6;

	/**
	 * 内部グローバルIP7
	 */
	@Schema(description = "内部グローバルIP7", required = false)
	private String internalGlobalIp7;

	/**
	 * 内部グローバルIP8
	 */
	@Schema(description = "内部グローバルIP8", required = false)
	private String internalGlobalIp8;

	/**
	 * 担当者様名
	 */
	@Schema(description = "担当者様名", required = false)
	private String picName;

	/**
	 * 担当者様電話番号
	 */
	@Schema(description = "担当者様電話番号", required = false)
	private String picPhoneNumber;

	/**
	 * 開通メール送付先
	 */
	@Schema(description = "開通メール送付先", required = false)
	private String openingEmailSendTo;

	/**
	 * 月次REPORT同報先1
	 */
	@Schema(description = "月次REPORT同報先1", required = false)
	private String monthlyReportBroadcastTo1;

	/**
	 * 月次REPORT同報先2
	 */
	@Schema(description = "月次REPORT同報先2", required = false)
	private String monthlyReportBroadcastTo2;

	/**
	 * 月次REPORT同報先3
	 */
	@Schema(description = "月次REPORT同報先3", required = false)
	private String monthlyReportBroadcastTo3;

	/**
	 * 月次REPORT同報先4
	 */
	@Schema(description = "月次REPORT同報先4", required = false)
	private String monthlyReportBroadcastTo4;

	/**
	 * サービス開始日(予定)
	 */
	@Schema(description = "サービス開始日(予定)", required = false)
	private Date serviceTermStartPlans;

	/**
	 * サービス終了日(予定)
	 */
	@Schema(description = "サービス終了日(予定)", required = false)
	private Date serviceTermEndPlans;

	/**
	 * 契約種別
	 */
	@Schema(description = "契約種別", required = false)
	private String contractType;

	/**
	 * 契約内容
	 */
	@Schema(description = "契約内容", required = false)
	private String contractContents;

	/**
	 * 元契約内容
	 */
	@Schema(description = "元契約内容", required = false)
	private String originalContractContents;

	/**
	 * REQUEST_STATUS
	 */
	@Schema(description = "REQUEST_STATUS", required = false)
	private String requestStatus;

	/**
	 * ACCEPT_STATUS
	 */
	@Schema(description = "ACCEPT_STATUS", required = false)
	private String acceptStatus;

	/**
	 * SOC_CONNECTION_CONFIRM_DATE
	 */
	@Schema(description = "SOC_CONNECTION_CONFIRM_DATE", required = false)
	private Date socConnectionConfirmDate;

	/**
	 * SCSK_REGISTERED_FLG
	 */
	@Schema(description = "SCSK_REGISTERED_FLG", required = false)
	private String scskRegisteredFlg;

	/**
	 * サイバー脅威レポートサービス内包フラグ
	 */
	@Schema(description = "CYBER_THREAT_REPORTING_SERVICE", required = false)
	private boolean cyberThreatReportingService;
}
