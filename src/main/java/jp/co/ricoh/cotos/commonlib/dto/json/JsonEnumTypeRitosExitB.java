package jp.co.ricoh.cotos.commonlib.dto.json;

import java.util.Arrays;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 脱RITOSカテゴリB共通区分定義
 */
public class JsonEnumTypeRitosExitB {

	@Description(value = "RtoRフラグ")
	public enum RtorFlg {

		通常契約("0"), RtoR("1");

		private final String text;

		private RtorFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static RtorFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "ベンダー区分")
	public enum VendorDiv {

		IIJ("1"), SoftBank("2");

		private final String text;

		private VendorDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static VendorDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "オプションRtoR区分")
	public enum OptionRtorDiv {

		対象外("0"), 引継ぎ先契約("1"), 引継ぎ元契約("2");

		private final String text;

		private OptionRtorDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OptionRtorDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "ホスティングRtoRフラグ")
	public enum HostingRtoRFlg {

		ホスティングRtoR無し("0"), ホスティングRtoRあり("1");

		private final String text;

		private HostingRtoRFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static HostingRtoRFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "情報変更表示フラグ")
	public enum InfoChangeDisplayFlg {

		しない("0"), する("1");

		private final String text;

		private InfoChangeDisplayFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static InfoChangeDisplayFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "情報変更スキップフラグ")
	public enum InfoChangeSkipFlg {

		しない("0"), する("1");

		private final String text;

		private InfoChangeSkipFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static InfoChangeSkipFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

}
