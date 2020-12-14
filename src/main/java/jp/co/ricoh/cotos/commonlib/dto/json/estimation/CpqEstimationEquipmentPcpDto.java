package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値 見積機種リストDTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqEstimationEquipmentPcpDto {

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

	/**
	 * 機種コード
	 */
	private String equipmentCode;

	/**
	 * 機種名
	 */
	private String equipmentName;

	/**
	 * 型番
	 */
	private String modelNumber;

	/**
	 * 購入日
	 */
	private Date purchaseDate;

	/**
	 * 保守終了日
	 */
	private Date maintenanceEndDate;

	/**
	 * CPQマッピングキー
	 */
	private String cpqMappingKey;

	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 契約年数
	 */
	private String contractYears;

	/**
	 * 品種コード
	 */
	private String itemCode;

	/**
	 * メーカーコード
	 */
	private MakerCodePcp makerCode;

	/**
	 * メーカー名
	 */
	private String makerName;

	/**
	 * 分類コード
	 */
	private ClassificationCodePcp classificationCode;

	/**
	 * 分類名
	 */
	private String classificationName;

	/**
	 * 保守可能年数
	 */
	private String maintenanceYears;

	/**
	 * 開梱設置フラグ
	 */
	private InstallationFlgPcp installationFlg;

	/**
	 * ネットワーク設定フラグ
	 */
	private NetworkFlgPcp networkFlg;

	/**
	 * ネットワーク設定品種コード
	 */
	private String networkItemCode;

	/**
	 * ソフトインストールフラグ
	 */
	private InstallFlgPcp installFlg;

	/**
	 * ソフトインストール品種コード
	 */
	private String installItemCode;
}
