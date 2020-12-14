package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * 商品（見積用）拡張項目COTOS商品固有項目DTO（RNS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqParameterRnsDto {

	public enum NewExistingAccountTypeRns {

		新規("0"), 既存("1");

		private final String text;

		private NewExistingAccountTypeRns(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static NewExistingAccountTypeRns fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 新規/既存アカウント区分
	 */
	private NewExistingAccountTypeRns newExistingAccountType;

	/**
	 * オーガニゼーションID
	 */
	private String organizationId;
}
