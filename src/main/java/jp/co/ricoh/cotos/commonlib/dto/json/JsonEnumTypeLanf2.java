package jp.co.ricoh.cotos.commonlib.dto.json;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * LANF2共通区分定義
 */
public class JsonEnumTypeLanf2 {

	public enum ExternalHardStackDiv {

		変更無し("0"), 追加("1"), 減数("2");

		private final String text;

		private ExternalHardStackDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ExternalHardStackDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum UpsStackDiv {

		変更無し("0"), 追加("1"), 減数("2");

		private final String text;

		private UpsStackDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static UpsStackDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum PcBackupStackDiv {

		変更無し("0"), 追加("1"), 減数("2");

		private final String text;

		private PcBackupStackDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static PcBackupStackDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}
}
