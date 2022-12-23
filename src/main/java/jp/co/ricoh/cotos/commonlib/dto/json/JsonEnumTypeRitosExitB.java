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
	public enum HostingRtorFlg {

		ホスティングRtoR無し("0"), ホスティングRtoRあり("1");

		private final String text;

		private HostingRtorFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static HostingRtorFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "サ実店フラグ")
	public enum SajitsutenFlg {

		サ実店以外("0"), サ実店("1");

		private final String text;

		private SajitsutenFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static SajitsutenFlg fromString(String string) {
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

	@Description(value = "追加メール削除フラグ")
	public enum AddMailDeleteFlg {

		なし("0"), あり("1");

		private final String text;

		private AddMailDeleteFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AddMailDeleteFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "削除フラグ")
	public enum DeleteFlg {

		未削除("0"), 削除("1");

		private final String text;

		private DeleteFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DeleteFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "編集不可フラグ")
	public enum NotEditFlg {

		不可("0"), 可("1");

		private final String text;

		private NotEditFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static NotEditFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "RtoRメールフラグ")
	public enum RtorMailFlg {

		引き継がない("0"), 引き継ぐ("1");

		private final String text;

		private RtorMailFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static RtorMailFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}
}
