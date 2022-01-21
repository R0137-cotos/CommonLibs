package jp.co.ricoh.cotos.commonlib.dto.json.license;

import java.util.Arrays;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * ライセンス情報拡張項目DTO（SSec）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseInfoExtendsParameterSsecDto {
	@Description(value = " 導入代行区分")
	public enum IntActingDiv {

		導入代行なし("1"), リモート導入("2"), オンサイト導入("3");

		private final String text;

		private IntActingDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static IntActingDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 導入代行区分
	 */
	private IntActingDiv intActingDiv;
}
