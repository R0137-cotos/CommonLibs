package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * 商品（見積用）拡張項目COTOS商品固有項目 見積機種リストDTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterEstimationEquipmentPcpDto {

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

	/**
	 * 継続可能フラグ
	 */
	private ContinuePossibleFlgPcp continuePossibleFlg;

	/**
	 * 再契約不可フラグ
	 */
	private ReContractNotAllowedFlgPcp reContractNotAllowedFlg;

	/**
	 * 機種コード
	 */
	private String equipmentCode;

	/**
	 * 機種名
	 */
	private String equipmentName;

	/**
	 * 機番
	 */
	private String equipmentNumber;

	/**
	 * 型番
	 */
	private String modelNumber;

	/**
	 * 枝番
	 */
	private String branchesNumber;

	/**
	 * メーカー名
	 */
	private String makerName;

	/**
	 * 購入日
	 */
	private Date purchaseDate;

	/**
	 * 保守終了日
	 */
	private Date maintenanceEndDate;

	/**
	 * 本体フラグ
	 */
	private String bodyFlg;

	/**
	 * サービス機器フラグ
	 */
	private String serviceMachineFlg;

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
	private ParameterEstimationEquipmentDeleteFlgPcp deleteFlg;
}