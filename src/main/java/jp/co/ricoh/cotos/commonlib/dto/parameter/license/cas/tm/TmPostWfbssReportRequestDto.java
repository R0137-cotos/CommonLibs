package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * トレンドマイクロWfbssレポート作成リクエストDTO
 * 週次レポート用に定義しているため「type」は必ず3:Weekly固定
 */
@Data
public class TmPostWfbssReportRequestDto implements AbstractTmRequestDto {

	/**
	 * レポートのスケジュール設定
	 * 3:Weekly 4:Monthly
	 * ※本Dtoは週報レポート用に定義しているため必ず3:Weeklyを設定する
	 */
	private Integer type = 3;

	private TmPostWfbssReportRequestDtoSetting setting;

	@Data
	public static class TmPostWfbssReportRequestDtoSetting {

		/**
		 * レポート名
		 */
		private String name;

		/**
		 * 有効/無効設定
		 * 1=True, 0=False
		 */
		private Integer enabled;

		private TmPostWfbssReportRequestDtoParameter parameter;

		@Data
		public static class TmPostWfbssReportRequestDtoParameter {

			/**
			 * レポートの生成開始時刻（秒数設定）
			 */
			private Integer time;

			private TmPostWfbssReportRequestDtoContents[] contents;

			@Data
			public static class TmPostWfbssReportRequestDtoContents {

				/**
				 * レポートの内容
				 * 設定値によりレポートの出力内容を決定する。設定値による出力内容は以下
				 *  100 = ウイルス概要
				 *  101 = ウイルスが検出されたデバイス (サーバを除く) 上位5件
				 *  102 = イルスが検出されたサーバ上位5件
				 *  200 = スパイウェア/グレーウェア概要
				 *  201 = スパイウェア/グレーウェアが検出されたデバイス (サーバを除く) 上位5件
				 *  202 = スパイウェア/グレーウェアが検出されたサーバ上位5件
				 *  300 = 検出されたネットワークウイルスの上位10件
				 *  301 = ネットワークウイルスによって攻撃されたデバイス上位10件
				 *  400 = Webレピュテーションポリシー違反のあったデバイス上位10件
				 *  500 = 挙動監視ポリシー違反のあったプログラムの上位5件
				 *  501 = 挙動監視ポリシー違反のあったデバイス上位10件
				 *  600 = 違反したURLカテゴリポリシーの上位10件
				 *  601 = URLカテゴリポリシー違反のあったデバイス上位10件
				 */
				@JsonProperty("tbl_type")
				private Integer tblType;
			}

			/**
			 * 週1回の場合の間隔の設定
			 * Weeklyレポートの場合は必須
			 *  0 = Sunday
			 *  1 = Monday
			 *  2 = Tuesday
			 *  3 = Wednesday
			 *  4 = Thursday
			 *  5 = Friday
			 *  6 = Saturday
			 */
			private Integer weekday;

			/**
			 * レポートをメール送信するかどうかの設定
			 * 1=True, 0=False
			 */
			private Integer email;

			/**
			 * レポートを送信するメールアドレスの設定
			 */
			private String[] address;
		}

	}
}
