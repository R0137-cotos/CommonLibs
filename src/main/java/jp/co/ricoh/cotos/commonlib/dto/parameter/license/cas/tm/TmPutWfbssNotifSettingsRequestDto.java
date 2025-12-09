package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * トレンドマイクロWfbss通知設定更新リクエストDTO
 * 
 */
@Data
public class TmPutWfbssNotifSettingsRequestDto implements AbstractTmRequestDto {

	@JsonProperty("notif_setting")
	private TmPutWfbssNotifSettingsRequestDtoNotifSetting notifSetting;

	@Data
	public static class TmPutWfbssNotifSettingsRequestDtoNotifSetting {

		/**
		 * 通知メールの件名の先頭の文字列
		 */
		@JsonProperty("subject_preface")
		private String subjectPreface;

		/**
		 * 通知メールの受信者
		 */
		private String[] recipients;

		private TmPutWfbssNotifSettingsRequestDtoWtp wtp;

		@Data
		public static class TmPutWfbssNotifSettingsRequestDtoWtp {

			private TmPutWfbssNotifSettingsRequestDtoWtpThreshold threshold;

			@Data
			public static class TmPutWfbssNotifSettingsRequestDtoWtpThreshold {

				/**
				 * [Webレピュテーション：URL違反が次の条件を超えた時]
				 * 警告しきい値とする件数
				 * 警告しきい値をはかる期間（秒）
				 */
				@JsonProperty("0")
				private TmPutWfbssNotifSettingsRequestDtoThreshold0 replaceTarget0;

			}

			/**
			 * [Webレピュテーション：URL違反が次の条件を超えた時]
			 * 警告しきい値を超えたときに通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_incident_exceed")
			private Integer notifyIncidentExceed;

		}

		private TmPutWfbssNotifSettingsRequestDtoLicense license;

		@Data
		public static class TmPutWfbssNotifSettingsRequestDtoLicense {

			/**
			 * [ライセンス：ライセンスの使用期限が残り60日未満の時]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_license_expire_warn")
			private Integer notifyLicenseExpireWarn;

			/**
			 * [ライセンス：ライセンスの使用期限が切れた時]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_license_expired")
			private Integer notifyLicenseExpired;

			/**
			 * [ライセンス：使用中のシート数が契約数の100%を超えた時]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_seat_count_exceed_warn")
			private Integer notifySeatCountExceedWarn;

			/**
			 * [ライセンス：使用中のシート数が契約数の110%を超えた時]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_seat_count_exceed_crit")
			private Integer notifySeatCountExceedCrit;
		}

		private TmPutWfbssNotifSettingsRequestDtoUpdate update;

		@Data
		public static class TmPutWfbssNotifSettingsRequestDtoUpdate {

			/**
			 * [コンポーネントのアップデート：警告：アップデート通知開始後のアップデート率が次の値未満の時:]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_deployment_rate_warn")
			private Integer notifyDeploymentRateWarn;

			/**
			 * [コンポーネントのアップデート：要確認：アップデート通知開始後のアップデート率が次の値未満の時:]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_deployment_rate_crit")
			private Integer notifyDeploymentRateCrit;

		}

		@JsonProperty("smart_scan")
		private TmPutWfbssNotifSettingsRequestDtoSmartScan smartScan;

		@Data
		public static class TmPutWfbssNotifSettingsRequestDtoSmartScan {

			/**
			 * [スマートスキャン：クライアントがスマートスキャンサーバから切断された時:]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_cloudscan_connect")
			private Integer notifyCloudscanConnect;
		}

		@JsonProperty("behavior_monitoring")
		private TmPutWfbssNotifSettingsRequestDtoBehaviorMonitoring behaviorMonitoring;

		@Data
		public static class TmPutWfbssNotifSettingsRequestDtoBehaviorMonitoring {

			private TmPutWfbssNotifSettingsRequestDtoBehaviorMonitoringThreshold threshold;

			@Data
			public static class TmPutWfbssNotifSettingsRequestDtoBehaviorMonitoringThreshold {

				/**
				 * [挙動監視：挙動監視違反が条件を超えた時]
				 * 警告しきい値とする件数
				 * 警告しきい値をはかる期間（秒）
				 */
				@JsonProperty("0")
				private TmPutWfbssNotifSettingsRequestDtoThreshold0 replaceTarget0;

			}

			/**
			 * [挙動監視：挙動監視違反が条件を超えた時]
			 * 警告しきい値を超えたときに通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_incident_exceed")
			private Integer notifyIncidentExceed;

		}

		@JsonProperty("url_filtering")
		private TmPutWfbssNotifSettingsRequestDtoUrlFiltering urlFiltering;

		@Data
		public static class TmPutWfbssNotifSettingsRequestDtoUrlFiltering {

			private TmPutWfbssNotifSettingsRequestDtoUrlFilteringThreshold threshold;

			@Data
			public static class TmPutWfbssNotifSettingsRequestDtoUrlFilteringThreshold {

				/**
				 * [URLフィルタ：URL違反が次の条件を超えた時]
				 * 警告しきい値とする件数
				 * 警告しきい値をはかる期間（秒）
				 */
				@JsonProperty("0")
				private TmPutWfbssNotifSettingsRequestDtoThreshold0 replaceTarget0;

			}

			/**
			 * [URLフィルタ：URL違反が次の条件を超えた時]
			 * 警告しきい値を超えたときに通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_incident_exceed")
			private Integer notifyIncidentExceed;

		}

		private TmPutWfbssNotifSettingsRequestDtoAntispyware antispyware;

		@Data
		public static class TmPutWfbssNotifSettingsRequestDtoAntispyware {

			/**
			 * [スパイウェア対策：スパイウェアが検出され、デバイスを再起動する必要があるとき]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_need_restart")
			private Integer notifyNeedRestart;

			private TmPutWfbssNotifSettingsRequestDtoAntispywareThreshold threshold;

			@Data
			public static class TmPutWfbssNotifSettingsRequestDtoAntispywareThreshold {

				/**
				 * [スパイウェア対策：スパイウェア/グレーウェアの検出数が次の条件を超えた時]
				 * 警告しきい値とする件数
				 * 警告しきい値をはかる期間（秒）
				 */
				@JsonProperty("0")
				private TmPutWfbssNotifSettingsRequestDtoThreshold0 replaceTarget0;

			}

			/**
			 * [スパイウェア対策：スパイウェア/グレーウェアの検出数が次の条件を超えた時]
			 * 警告しきい値を超えたときに通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_incident_exceed")
			private Integer notifyIncidentExceed;

		}

		private TmPutWfbssNotifSettingsRequestDtoAntivirus antivirus;

		@Data
		public static class TmPutWfbssNotifSettingsRequestDtoAntivirus {

			/**
			 * [ウイルス/不正プログラム対策：検出されたウイルスの処理に失敗した時]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_action_failure")
			private Integer notifyActionFailure;

			private TmPutWfbssNotifSettingsRequestDtoAntivirusThreshold threshold;

			@Data
			public static class TmPutWfbssNotifSettingsRequestDtoAntivirusThreshold {

				/**
				 * [ウイルス/不正プログラム対策：ウイルス数が次の条件を超えた時]
				 * 警告しきい値とする件数
				 * 警告しきい値をはかる期間（秒）
				 */
				@JsonProperty("0")
				private TmPutWfbssNotifSettingsRequestDtoThreshold0 replaceTarget0;

			}

			/**
			 * [ウイルス/不正プログラム対策：ウイルス数が次の条件を超えた時]
			 * 警告しきい値を超えたときに通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_incident_exceed")
			private Integer notifyIncidentExceed;

			/**
			 * [ウイルス/不正プログラム対策：リアルタイム検索が無効になった時]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_realtime_scan_disabled")
			private Integer notifyRealtimeScanDisabled;
		}

		private TmPutWfbssNotifSettingsRequestDtoOutbreak outbreak;

		@Data
		public static class TmPutWfbssNotifSettingsRequestDtoOutbreak {

			/**
			 * [大規模感染予防： レッドアラート発令時]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_red_alert")
			private Integer notifyRedAlert;

			/**
			 * [大規模感染予防： イエローアラート発令時]
			 * 通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_yellow_alert")
			private Integer notifyYellowAlert;
		}

		@JsonProperty("network_virus")
		private TmPutWfbssNotifSettingsRequestDtoNetworkVirus networkVirus;

		@Data
		public static class TmPutWfbssNotifSettingsRequestDtoNetworkVirus {

			private TmPutWfbssNotifSettingsRequestDtoNetworkVirusThreshold threshold;

			@Data
			public static class TmPutWfbssNotifSettingsRequestDtoNetworkVirusThreshold {

				/**
				 * [ネットワークウイルス：ネットワークウイルス数が次の条件を超えた時]
				 * 警告しきい値とする件数
				 * 警告しきい値をはかる期間（秒）
				 */
				@JsonProperty("0")
				private TmPutWfbssNotifSettingsRequestDtoThreshold0 replaceTarget0;

			}

			/**
			 * [ネットワークウイルス：ネットワークウイルス数が次の条件を超えた時]
			 * 警告しきい値を超えたときに通知メールを送信するかどうか
			 *  1 = True, 0 = False
			 */
			@JsonProperty("notify_incident_exceed")
			private Integer notifyIncidentExceed;
		}
	}
}
