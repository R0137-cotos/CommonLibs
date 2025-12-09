package jp.co.ricoh.cotos.commonlib.dto.json;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * CGWP共通区分定義
 */
public class JsonEnumTypeCgwp {

	public enum AgentInstallationDivCgwp {

		積上げなし("0"), 積上げあり("1");

		private final String text;

		private AgentInstallationDivCgwp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AgentInstallationDivCgwp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum MssLinkageDivCgwp {

		積上げなし("0"), 積上げあり("1");

		private final String text;

		private MssLinkageDivCgwp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MssLinkageDivCgwp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum MssLinkageStopDivCgwp {

		積上げなし("0"), 積上げあり("1");

		private final String text;

		private MssLinkageStopDivCgwp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MssLinkageStopDivCgwp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}
}
