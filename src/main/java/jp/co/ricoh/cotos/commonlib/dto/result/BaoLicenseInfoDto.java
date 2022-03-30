package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
public class BaoLicenseInfoDto {

	/**
	 * ID
	 */
	@Id
	@ApiModelProperty(value = "ID", required = false, position = 1)
	private long id;

	/**
	 * 契約種別詳細
	 */
	@ApiModelProperty(value = "契約種別詳細", required = false, position = 2)
	private String contractTypeDetail;

	/**
	 * 販売店様管理番号
	 */
	@ApiModelProperty(value = "販売店様管理番号", required = false, position = 3)
	private String dealerManagementNumber;

	/**
	 * 販売店様管理番号枝番
	 */
	@ApiModelProperty(value = "販売店様管理番号枝番", required = false, position = 4)
	private String dealerManagementBranchNumber;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = false, position = 5)
	private String contractId;

	/**
	 * EU様会社名
	 */
	@ApiModelProperty(value = "EU様会社名", required = false, position = 6)
	private String euCompanyName;

	/**
	 * EU様会社名カナ
	 */
	@ApiModelProperty(value = "EU様会社名カナ", required = false, position = 7)
	private String euCompanyNameKana;

	/**
	 * EU様担当者名
	 */
	@ApiModelProperty(value = "EU様担当者名", required = false, position = 8)
	private String euPicName;

	/**
	 * EU様ログインID送付先
	 */
	@ApiModelProperty(value = "EU様ログインID送付先", required = false, position = 9)
	private String euLoginIdSendTo;

	/**
	 * 月次REPORT送付先1
	 */
	@ApiModelProperty(value = "月次REPORT送付先1", required = false, position = 10)
	private String monthlyReportSend1;

	/**
	 * 月次REPORT送付先2
	 */
	@ApiModelProperty(value = "月次REPORT送付先2", required = false, position = 11)
	private String monthlyReportSend2;

	/**
	 * 月次REPORT送付先3
	 */
	@ApiModelProperty(value = "月次REPORT送付先3", required = false, position = 12)
	private String monthlyReportSend3;

	/**
	 * 月次REPORT送付先4
	 */
	@ApiModelProperty(value = "月次REPORT送付先4", required = false, position = 13)
	private String monthlyReportSend4;

	/**
	 * EU様電話番号
	 */
	@ApiModelProperty(value = "EU様電話番号", required = false, position = 14)
	private String euPhoneNumber;

	/**
	 * EU様郵便番号
	 */
	@ApiModelProperty(value = "EU様郵便番号", required = false, position = 15)
	private String euPostNumber;

	/**
	 * EU様住所
	 */
	@ApiModelProperty(value = "EU様住所", required = false, position = 16)
	private String euAddress;

	/**
	 * シリアル番号1
	 */
	@ApiModelProperty(value = "シリアル番号1", required = false, position = 17)
	private String serialNumber1;

	/**
	 * 機種名1
	 */
	@ApiModelProperty(value = "機種名1", required = false, position = 18)
	private String modelName1;

	/**
	 * OSバージョン1
	 */
	@ApiModelProperty(value = "OSバージョン1", required = false, position = 19)
	private String osVersion1;

	/**
	 * シリアル番号2
	 */
	@ApiModelProperty(value = "シリアル番号2", required = false, position = 20)
	private String serialNumber2;

	/**
	 * 機種名2
	 */
	@ApiModelProperty(value = "機種名2", required = false, position = 21)
	private String modelName2;

	/**
	 * OSバージョン2
	 */
	@ApiModelProperty(value = "OSバージョン2", required = false, position = 22)
	private String osVersion2;

	/**
	 * 機器名
	 */
	@ApiModelProperty(value = "機器名", required = false, position = 23)
	private String machineName;

	/**
	 * 内部グローバルIP1
	 */
	@ApiModelProperty(value = "内部グローバルIP1", required = false, position = 24)
	private String internalGlobalIp1;

	/**
	 * 内部グローバルIP2
	 */
	@ApiModelProperty(value = "内部グローバルIP2", required = false, position = 25)
	private String internalGlobalIp2;

	/**
	 * 内部グローバルIP3
	 */
	@ApiModelProperty(value = "内部グローバルIP3", required = false, position = 26)
	private String internalGlobalIp3;

	/**
	 * 内部グローバルIP4
	 */
	@ApiModelProperty(value = "内部グローバルIP4", required = false, position = 27)
	private String internalGlobalIp4;

	/**
	 * 内部グローバルIP5
	 */
	@ApiModelProperty(value = "内部グローバルIP5", required = false, position = 28)
	private String internalGlobalIp5;

	/**
	 * 内部グローバルIP6
	 */
	@ApiModelProperty(value = "内部グローバルIP6", required = false, position = 29)
	private String internalGlobalIp6;

	/**
	 * 内部グローバルIP7
	 */
	@ApiModelProperty(value = "内部グローバルIP7", required = false, position = 30)
	private String internalGlobalIp7;

	/**
	 * 内部グローバルIP8
	 */
	@ApiModelProperty(value = "内部グローバルIP8", required = false, position = 31)
	private String internalGlobalIp8;

	/**
	 * 担当者様名
	 */
	@ApiModelProperty(value = "担当者様名", required = false, position = 32)
	private String picName;

	/**
	 * 担当者様電話番号
	 */
	@ApiModelProperty(value = "担当者様電話番号", required = false, position = 33)
	private String picPhoneNumber;

	/**
	 * 開通メール送付先
	 */
	@ApiModelProperty(value = "開通メール送付先", required = false, position = 34)
	private String openingEmailSendTo;

	/**
	 * 月次REPORT同報先1
	 */
	@ApiModelProperty(value = "月次REPORT同報先1", required = false, position = 35)
	private String monthlyReportBroadcastTo1;

	/**
	 * 月次REPORT同報先2
	 */
	@ApiModelProperty(value = "月次REPORT同報先2", required = false, position = 36)
	private String monthlyReportBroadcastTo2;

	/**
	 * 月次REPORT同報先3
	 */
	@ApiModelProperty(value = "月次REPORT同報先3", required = false, position = 37)
	private String monthlyReportBroadcastTo3;

	/**
	 * 月次REPORT同報先4
	 */
	@ApiModelProperty(value = "月次REPORT同報先4", required = false, position = 38)
	private String monthlyReportBroadcastTo4;

	/**
	 * サービス開始日(予定)
	 */
	@ApiModelProperty(value = "サービス開始日(予定)", required = false, position = 39)
	private Date serviceTermStartPlans;

	/**
	 * サービス終了日(予定)
	 */
	@ApiModelProperty(value = "サービス終了日(予定)", required = false, position = 40)
	private Date serviceTermEndPlans;

	/**
	 * 契約種別
	 */
	@ApiModelProperty(value = "契約種別", required = false, position = 41)
	private String contractType;

	/**
	 * 契約内容
	 */
	@ApiModelProperty(value = "契約内容", required = false, position = 42)
	private String contractContents;

	/**
	 * 元契約内容
	 */
	@ApiModelProperty(value = "元契約内容", required = false, position = 43)
	private String originalContractContents;

	/**
	 * REQUEST_STATUS
	 */
	@ApiModelProperty(value = "REQUEST_STATUS", required = false, position = 44)
	private String requestStatus;

	/**
	 * ACCEPT_STATUS
	 */
	@ApiModelProperty(value = "ACCEPT_STATUS", required = false, position = 45)
	private String acceptStatus;

	/**
	 * SOC_CONNECTION_CONFIRM_DATE
	 */
	@ApiModelProperty(value = "SOC_CONNECTION_CONFIRM_DATE", required = false, position = 46)
	private Date socConnectionConfirmDate;

	/**
	 * SCSK_REGISTERED_FLG
	 */
	@ApiModelProperty(value = "SCSK_REGISTERED_FLG", required = false, position = 47)
	private String scskRegisteredFlg;

	/**
	 * サイバー脅威レポートサービス内包フラグ
	 */
	@ApiModelProperty(value = "CYBER_THREAT_REPORTING_SERVICE", required = false, position = 48)
	private boolean cyberThreatReportingService;

}
