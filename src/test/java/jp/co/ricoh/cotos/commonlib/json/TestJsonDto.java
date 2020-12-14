package jp.co.ricoh.cotos.commonlib.json;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * JsonUtilテスト用のDTOです。
 *
 * <pre>
 * テスト以外の使用不可
 * </pre>
 */
@Data
public class TestJsonDto {

	public enum TestEstimationTypeDetails {

		新規("1"), 契約更新("2"), オプション削除("3");

		private final String text;

		private TestEstimationTypeDetails(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TestEstimationTypeDetails fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 見積種別詳細
	 */
	private TestEstimationTypeDetails estimationTypeDetails;

	/**
	 * オーガニゼーションID
	 */
	private String organizationId;

	/**
	 * VPN
	 */
	private String vpn;

	/**
	 * RMA契約番号
	 */
	private String rmaContractNumber;

	/**
	 * RMA契約開始日
	 */
	private String rmaContractStart;

	/**
	 * RMA契約終了日
	 */
	private String rmaContractEnd;

	@JsonProperty("testObject")
	private TestJsonDetailDto testJsonDetailDto;
}
