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
	private String id;

	/**
	 * 契約種別詳細
	 */
	@ApiModelProperty(value = "契約種別詳細", required = false, position = 2)
	private String contract_type_detail;

	/**
	 * 販売店様管理番号
	 */
	@ApiModelProperty(value = "販売店様管理番号", required = false, position = 3)
	private String dealer_management_number;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = false, position = 4)
	private String contract_id;

	/**
	 * EU様会社名
	 */
	@ApiModelProperty(value = "EU様会社名", required = false, position = 5)
	private String eu_company_name;

	/**
	 * EU様会社名カナ
	 */
	@ApiModelProperty(value = "EU様会社名カナ", required = false, position = 6)
	private String eu_company_name_kana;

	/**
	 * EU様担当者名
	 */
	@ApiModelProperty(value = "EU様担当者名", required = false, position = 7)
	private String eu_pic_name;

	/**
	 * EU様ログインID送付先
	 */
	@ApiModelProperty(value = "EU様ログインID送付先", required = false, position = 8)
	private String eu_login_id_send_to;

	/**
	 * 月次REPORT送付先1
	 */
	@ApiModelProperty(value = "月次REPORT送付先1", required = false, position = 9)
	private String monthly_report_send_1;

	/**
	 * 月次REPORT送付先2
	 */
	@ApiModelProperty(value = "月次REPORT送付先2", required = false, position = 10)
	private String monthly_report_send_2;

	/**
	 * 月次REPORT送付先3
	 */
	@ApiModelProperty(value = "月次REPORT送付先3", required = false, position = 11)
	private String monthly_report_send_3;

	/**
	 * 月次REPORT送付先4
	 */
	@ApiModelProperty(value = "月次REPORT送付先4", required = false, position = 12)
	private String monthly_report_send_4;

	/**
	 * EU様電話番号
	 */
	@ApiModelProperty(value = "EU様電話番号", required = false, position = 13)
	private String eu_phone_number;

	/**
	 * EU様郵便番号
	 */
	@ApiModelProperty(value = "EU様郵便番号", required = false, position = 14)
	private String eu_post_number;

	/**
	 * EU様住所
	 */
	@ApiModelProperty(value = "EU様住所", required = false, position = 15)
	private String eu_address;

	/**
	 * シリアル番号1
	 */
	@ApiModelProperty(value = "シリアル番号1", required = false, position = 16)
	private String serial_number_1;

	/**
	 * 機種名1
	 */
	@ApiModelProperty(value = "機種名1", required = false, position = 17)
	private String model_name_1;

	/**
	 * OSバージョン1
	 */
	@ApiModelProperty(value = "OSバージョン1", required = false, position = 18)
	private String os_version_1;

	/**
	 * シリアル番号2
	 */
	@ApiModelProperty(value = "シリアル番号2", required = false, position = 19)
	private String serial_number_2;

	/**
	 * 機種名2
	 */
	@ApiModelProperty(value = "機種名2", required = false, position = 20)
	private String model_name_2;

	/**
	 * OSバージョン2
	 */
	@ApiModelProperty(value = "OSバージョン2", required = false, position = 21)
	private String os_version_2;

	/**
	 * 機器名
	 */
	@ApiModelProperty(value = "機器名", required = false, position = 22)
	private String machine_name;

	/**
	 * 内部グローバルIP1
	 */
	@ApiModelProperty(value = "内部グローバルIP1", required = false, position = 23)
	private String internal_global_ip_1;

	/**
	 * 内部グローバルIP2
	 */
	@ApiModelProperty(value = "内部グローバルIP2", required = false, position = 24)
	private String internal_global_ip_2;

	/**
	 * 内部グローバルIP3
	 */
	@ApiModelProperty(value = "内部グローバルIP3", required = false, position = 25)
	private String internal_global_ip_3;

	/**
	 * 内部グローバルIP4
	 */
	@ApiModelProperty(value = "内部グローバルIP4", required = false, position = 26)
	private String internal_global_ip_4;

	/**
	 * 内部グローバルIP5
	 */
	@ApiModelProperty(value = "内部グローバルIP5", required = false, position = 27)
	private String internal_global_ip_5;

	/**
	 * 内部グローバルIP6
	 */
	@ApiModelProperty(value = "内部グローバルIP6", required = false, position = 28)
	private String internal_global_ip_6;

	/**
	 * 内部グローバルIP7
	 */
	@ApiModelProperty(value = "内部グローバルIP7", required = false, position = 29)
	private String internal_global_ip_7;

	/**
	 * 内部グローバルIP8
	 */
	@ApiModelProperty(value = "内部グローバルIP8", required = false, position = 30)
	private String internal_global_ip_8;

	/**
	 * 担当者様名
	 */
	@ApiModelProperty(value = "担当者様名", required = false, position = 31)
	private String pic_name;

	/**
	 * 担当者様電話番号
	 */
	@ApiModelProperty(value = "担当者様電話番号", required = false, position = 32)
	private String pic_phone_number;

	/**
	 * 開通メール送付先
	 */
	@ApiModelProperty(value = "開通メール送付先", required = false, position = 33)
	private String opening_email_send_to;

	/**
	 * 月次REPORT同報先1
	 */
	@ApiModelProperty(value = "月次REPORT同報先1", required = false, position = 34)
	private String monthly_report_broadcast_to_1;

	/**
	 * 月次REPORT同報先2
	 */
	@ApiModelProperty(value = "月次REPORT同報先2", required = false, position = 35)
	private String monthly_report_broadcast_to_2;

	/**
	 * 月次REPORT同報先3
	 */
	@ApiModelProperty(value = "月次REPORT同報先3", required = false, position = 36)
	private String monthly_report_broadcast_to_3;

	/**
	 * 月次REPORT同報先4
	 */
	@ApiModelProperty(value = "月次REPORT同報先4", required = false, position = 37)
	private String monthly_report_broadcast_to_4;

	/**
	 * サービス開始日(予定)
	 */
	@ApiModelProperty(value = "サービス開始日(予定)", required = false, position = 38)
	private Date service_term_start_plans;

	/**
	 * サービス終了日(予定)
	 */
	@ApiModelProperty(value = "サービス終了日(予定)", required = false, position = 39)
	private Date service_term_end_plans;

	/**
	 * 契約種別
	 */
	@ApiModelProperty(value = "契約種別", required = false, position = 40)
	private String contract_type;

	/**
	 * 契約内容
	 */
	@ApiModelProperty(value = "契約内容", required = false, position = 41)
	private String contract_contents;

	/**
	 * 元契約内容
	 */
	@ApiModelProperty(value = "元契約内容", required = false, position = 42)
	private String original_contract_contents;

	/**
	 * REQUEST_STATUS
	 */
	@ApiModelProperty(value = "REQUEST_STATUS", required = false, position = 43)
	private String request_status;

	/**
	 * ACCEPT_STATUS
	 */
	@ApiModelProperty(value = "ACCEPT_STATUS", required = false, position = 44)
	private String accept_status;

	/**
	 * SOC_CONNECTION_CONFIRM_DATE
	 */
	@ApiModelProperty(value = "SOC_CONNECTION_CONFIRM_DATE", required = false, position = 45)
	private Date soc_connection_confirm_date;

	/**
	 * SCSK_REGISTERED_FLG
	 */
	@ApiModelProperty(value = "SCSK_REGISTERED_FLG", required = false, position = 46)
	private String scsk_registered_flg;

}
