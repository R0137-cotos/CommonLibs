package jp.co.ricoh.cotos.commonlib.dto.json;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * PCP共通区分定義
 */
public class JsonEnumTypePcp {

	// PCPの移行元のRITOSと同様に0から採番
	public enum MakerCodePcp {

		富士通("0"), NEC("1"), 東芝("2"), lenovo("3"), HP("4"), DELL("5");

		private final String text;

		private MakerCodePcp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MakerCodePcp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	// PCPの移行元のRITOSと同様に0から採番
	public enum ClassificationCodePcp {

		デスクトップ型("0"), ノート型("1"), モニター("2");

		private final String text;

		private ClassificationCodePcp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ClassificationCodePcp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum InstallationFlgPcp {

		開梱設置しない("0"), 開梱設置する("1");

		private final String text;

		private InstallationFlgPcp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static InstallationFlgPcp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum NetworkFlgPcp {

		設定しない("0"), 設定する("1");

		private final String text;

		private NetworkFlgPcp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static NetworkFlgPcp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum InstallFlgPcp {

		設定しない("0"), 設定する("1");

		private final String text;

		private InstallFlgPcp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static InstallFlgPcp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum ContinuePossibleFlgPcp {

		継続しない("0"), 継続する("1");

		private final String text;

		private ContinuePossibleFlgPcp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ContinuePossibleFlgPcp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum ReContractNotAllowedFlgPcp {

		再契約可("0"), 再契約不可("1");

		private final String text;

		private ReContractNotAllowedFlgPcp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ReContractNotAllowedFlgPcp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum ParameterEstimationEquipmentDeleteFlgPcp {

		未削除("0"), 削除済み("1");

		private final String text;

		private ParameterEstimationEquipmentDeleteFlgPcp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ParameterEstimationEquipmentDeleteFlgPcp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}
}
