package jp.co.ricoh.cotos.commonlib.dto.json;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 共通区分定義
 */
public class JsonEnumType {

	/**
	 * 見積種別詳細
	 *
	 * GSP：新規/契約更新/アップグレード/オプション（追加・増数）
	 * LANF2：新規/契約更新/オプション（追加・増数）
	 * MSS/RNS：新規/メニュー変更/プラン・オプション（減数・削除）
	 * PCパック：新規/再契約/基本プラン減数
	 * SVP：新規/契約更新/オプション（追加・増数）
	 *
	 */

	public enum EstimationTypeDetails {

		新規("1"), 契約更新("2"), 再契約("3"), アップグレード("4"), メニュー変更("5"), 基本プラン減数("6"), プラン_オプション_減数_削除("7"), オプション_追加_増数("8"), オプション_追加_減数("9"), 自動更新("10");

		private final String text;

		private EstimationTypeDetails(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static EstimationTypeDetails fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 契約種別詳細
	 *
	 * GSP：新規/契約更新/アップグレード/オプション（追加・増数）
	 * LANF2：新規/契約更新/オプション（追加・増数）
	 * MSS/RNS：新規/メニュー変更/プラン・オプション（減数・削除）
	 * PCパック：新規/再契約/基本プラン減数
	 * SVP：新規/契約更新/オプション（追加・増数）
	 * CAS：新規/オプション_追加_増数/基本プラン増数/基本プラン減数
	 * MVB：新規/オプション_追加_増数/基本プラン増数/基本プラン減数
	 * SSec：新規/オプション_追加_増数/基本プラン増数/基本プラン減数
	 * O365：新規//メニュー変更/転入
	 *
	 */
	public enum ContractTypeDetails {

		新規("1"), 契約更新("2"), 再契約("3"), アップグレード("4"), メニュー変更("5"), 基本プラン減数("6"), プラン_オプション_減数_削除("7"), オプション_追加_増数("8"), オプション_追加_減数("9"), 自動更新("10"), 基本プラン増数("11"), 転入("12");

		private final String text;

		private ContractTypeDetails(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ContractTypeDetails fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 移行区分
	 *
	 * 標準
	 *
	 */
	public enum MigrationDiv {

		RITOS移行("1");

		private final String text;

		private MigrationDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MigrationDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum NewExistingAccountType {

		新規("0"), 既存("1");

		private final String text;

		private NewExistingAccountType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static NewExistingAccountType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}
}
