package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Entity
@Data
public class BaoLicenseInfoDto {

	/**
	 * ID
	 */
	@Id
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private long id;

	/**
	 * 契約種別詳細
	 */
	@Schema(description = "契約種別詳細", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String contractTypeDetail;

	/**
	 * 販売店様管理番号
	 */
	@Schema(description = "販売店様管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String dealerManagementNumber;

	/**
	 * 販売店様管理番号枝番
	 */
	@Schema(description = "販売店様管理番号枝番", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String dealerManagementBranchNumber;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String contractId;

	/**
	 * EU様会社名
	 */
	@Schema(description = "EU様会社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String euCompanyName;

	/**
	 * EU様会社名カナ
	 */
	@Schema(description = "EU様会社名カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String euCompanyNameKana;

	/**
	 * EU様担当者名
	 */
	@Schema(description = "EU様担当者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String euPicName;

	/**
	 * EU様ログインID送付先
	 */
	@Schema(description = "EU様ログインID送付先", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String euLoginIdSendTo;

	/**
	 * 月次REPORT送付先1
	 */
	@Schema(description = "月次REPORT送付先1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String monthlyReportSend1;

	/**
	 * 月次REPORT送付先2
	 */
	@Schema(description = "月次REPORT送付先2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String monthlyReportSend2;

	/**
	 * 月次REPORT送付先3
	 */
	@Schema(description = "月次REPORT送付先3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String monthlyReportSend3;

	/**
	 * 月次REPORT送付先4
	 */
	@Schema(description = "月次REPORT送付先4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String monthlyReportSend4;

	/**
	 * EU様電話番号
	 */
	@Schema(description = "EU様電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String euPhoneNumber;

	/**
	 * EU様郵便番号
	 */
	@Schema(description = "EU様郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String euPostNumber;

	/**
	 * EU様住所
	 */
	@Schema(description = "EU様住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String euAddress;

	/**
	 * シリアル番号1
	 */
	@Schema(description = "シリアル番号1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String serialNumber1;

	/**
	 * 機種名1
	 */
	@Schema(description = "機種名1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String modelName1;

	/**
	 * OSバージョン1
	 */
	@Schema(description = "OSバージョン1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String osVersion1;

	/**
	 * シリアル番号2
	 */
	@Schema(description = "シリアル番号2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String serialNumber2;

	/**
	 * 機種名2
	 */
	@Schema(description = "機種名2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String modelName2;

	/**
	 * OSバージョン2
	 */
	@Schema(description = "OSバージョン2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String osVersion2;

	/**
	 * 機器名
	 */
	@Schema(description = "機器名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String machineName;

	/**
	 * 内部グローバルIP1
	 */
	@Schema(description = "内部グローバルIP1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internalGlobalIp1;

	/**
	 * 内部グローバルIP2
	 */
	@Schema(description = "内部グローバルIP2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internalGlobalIp2;

	/**
	 * 内部グローバルIP3
	 */
	@Schema(description = "内部グローバルIP3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internalGlobalIp3;

	/**
	 * 内部グローバルIP4
	 */
	@Schema(description = "内部グローバルIP4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internalGlobalIp4;

	/**
	 * 内部グローバルIP5
	 */
	@Schema(description = "内部グローバルIP5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internalGlobalIp5;

	/**
	 * 内部グローバルIP6
	 */
	@Schema(description = "内部グローバルIP6", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internalGlobalIp6;

	/**
	 * 内部グローバルIP7
	 */
	@Schema(description = "内部グローバルIP7", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internalGlobalIp7;

	/**
	 * 内部グローバルIP8
	 */
	@Schema(description = "内部グローバルIP8", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internalGlobalIp8;

	/**
	 * 担当者様名
	 */
	@Schema(description = "担当者様名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String picName;

	/**
	 * 担当者様電話番号
	 */
	@Schema(description = "担当者様電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String picPhoneNumber;

	/**
	 * 開通メール送付先
	 */
	@Schema(description = "開通メール送付先", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String openingEmailSendTo;

	/**
	 * 月次REPORT同報先1
	 */
	@Schema(description = "月次REPORT同報先1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String monthlyReportBroadcastTo1;

	/**
	 * 月次REPORT同報先2
	 */
	@Schema(description = "月次REPORT同報先2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String monthlyReportBroadcastTo2;

	/**
	 * 月次REPORT同報先3
	 */
	@Schema(description = "月次REPORT同報先3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String monthlyReportBroadcastTo3;

	/**
	 * 月次REPORT同報先4
	 */
	@Schema(description = "月次REPORT同報先4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
	@Schema(description = "契約種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String contractType;

	/**
	 * 契約内容
	 */
	@Schema(description = "契約内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String contractContents;

	/**
	 * 元契約内容
	 */
	@Schema(description = "元契約内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String originalContractContents;

	/**
	 * REQUEST_STATUS
	 */
	@Schema(description = "REQUEST_STATUS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String requestStatus;

	/**
	 * ACCEPT_STATUS
	 */
	@Schema(description = "ACCEPT_STATUS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String acceptStatus;

	/**
	 * SOC_CONNECTION_CONFIRM_DATE
	 */
	@Schema(description = "SOC_CONNECTION_CONFIRM_DATE", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date socConnectionConfirmDate;

	/**
	 * SCSK_REGISTERED_FLG
	 */
	@Schema(description = "SCSK_REGISTERED_FLG", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String scskRegisteredFlg;

	/**
	 * サイバー脅威レポートサービス内包フラグ
	 */
	@Schema(description = "CYBER_THREAT_REPORTING_SERVICE", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private boolean cyberThreatReportingService;

}
