package jp.co.ricoh.cotos.commonlib.dto.json;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * MSS共通区分定義
 */
public class JsonEnumTypeMss {

	public enum NewExistingAccountTypeMss {

		新規("0"), 既存("1");

		private final String text;

		private NewExistingAccountTypeMss(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static NewExistingAccountTypeMss fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}
}
