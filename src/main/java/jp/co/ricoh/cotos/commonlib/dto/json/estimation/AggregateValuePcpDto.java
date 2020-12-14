package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ連携集計値リストDTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregateValuePcpDto {

	public enum AggregateValueDeleteFlgPcp {

		未削除("0"), 削除済み("1");

		private final String text;

		private AggregateValueDeleteFlgPcp(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AggregateValueDeleteFlgPcp fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * CPQマッピングキー
	 */
	private String cpqMappingKey;

	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 削除フラグ
	 */
	private AggregateValueDeleteFlgPcp deleteFlg;
}
