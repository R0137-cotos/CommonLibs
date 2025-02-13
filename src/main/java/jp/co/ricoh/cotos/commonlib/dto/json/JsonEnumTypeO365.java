package jp.co.ricoh.cotos.commonlib.dto.json;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * O365共通区分定義
 */
public class JsonEnumTypeO365 {

	@Description(value = "転入フラグ")
	public enum TransferFlg {

		転入以外("0"), 転入("1");

		private final String text;

		private TransferFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TransferFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "支払周期変更フラグ")
	public enum PaymentCycleChangeFlg {

		通常("0"), 支払周期変更入("1");

		private final String text;

		private PaymentCycleChangeFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static PaymentCycleChangeFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "受注区分")
	public enum OrderDiv {

		訪販("1"), WEB("2");

		private final String text;

		private OrderDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OrderDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "契約期間")
	public enum ContractTerm {

		月契約("1"), 年契約("2");

		private final String text;

		private ContractTerm(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ContractTerm fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "キャンセル処理状態")
	public enum CancellationStatus {

		申込済("1"), 受付済("2"), 処理済("3"), 処理エラー("4");

		private final String text;

		private CancellationStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CancellationStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "更新期限変更パターン")
	public enum CustomTermPattern {

		暦月に合わせる("1"), 他サブスクリプションに合わせる("2"), 変更済みの内容を破棄する("3");

		private final String text;

		private CustomTermPattern(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CustomTermPattern fromString(String string) {
			if (StringUtils.isEmpty(string)) {
				return null;
			}
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}
}
