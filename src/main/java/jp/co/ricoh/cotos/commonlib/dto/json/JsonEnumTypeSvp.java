package jp.co.ricoh.cotos.commonlib.dto.json;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * SVP共通区分定義
 */
public class JsonEnumTypeSvp {

	public enum MachineTypeSvp {

		// SVPの移行元のRITOSと同様に0から採番
		サーバー本体("0"), HWオプション("1"), SWオプション("2");

		private final String text;

		private MachineTypeSvp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MachineTypeSvp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	// SVPの移行元のRITOSと同様に0から採番
	public enum BillingTypeSvp {

		一括("0"), 月額("1");

		private final String text;

		private BillingTypeSvp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static BillingTypeSvp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	// SVPの移行元のRITOSと同様に0から採番
	public enum MakerCodeSvp {

		hp("0"), Lenovo("1"), 日立("2"), 富士通("3"), イメーション("4"), NEC("5"), DELL("6");

		private final String text;

		private MakerCodeSvp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MakerCodeSvp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	// SVPの移行元のRITOSと同様に0から採番
	public enum MenuTypeSvp {

		導入保守ST("0"), 導入保守DX("1"), 保守ST("2"), 保守DX("3"), 保守HW("4");

		private final String text;

		private MenuTypeSvp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MenuTypeSvp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	// SVPの移行元のRITOSと同様に0から採番
	public enum MaintenanceTimeTypeSvp {

		月土9時_21時("0"), H24D365("1"), 月金9時_17時("2");

		private final String text;

		private MaintenanceTimeTypeSvp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MaintenanceTimeTypeSvp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	// SVPの移行元のRITOSと同様に0から採番
	public enum HarddiskTypeSvp {

		標準搭載("0"), 標準搭載_拡張("1"), ディスクレス("2");

		private final String text;

		private HarddiskTypeSvp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static HarddiskTypeSvp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}
}
