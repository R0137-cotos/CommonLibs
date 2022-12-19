package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * 商品（見積用）拡張項目 追加メールDTO
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterEstimationAddMailDto {

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

	public enum NotEditFlg {

		可("0"), 不可("1");

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

	/**
	 * No
	 */
	private String addMailNo;

	/**
	 * メールアドレス
	 */
	private String mailAddress;

	/**
	 * 削除フラグ
	 */
	private DeleteFlg deleteFlg;

	/**
	 * 編集不可フラグ
	 */
	private NotEditFlg notEditFlg;
}