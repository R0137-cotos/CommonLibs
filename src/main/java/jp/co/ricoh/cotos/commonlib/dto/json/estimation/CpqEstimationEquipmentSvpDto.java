package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値 見積機種リストDTO（SVP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqEstimationEquipmentSvpDto {

	public enum MachineTypeSvp {

		サーバー本体("0"), HWオプション("1"), SWオプション("2"), 監視サービスオプション("3");

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

	public enum MakerCodeSvp {

		hp("0"), IBM("1"), 日立("2"), 富士通("3"), イメーション("4"), DELL("6");

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

	public enum MaintenanceTimeTypeSvp {

		月土9時_21時("0"), 月金9時_17時("1"), H24D365("2");

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

	/**
	 * 機器区分
	 */
	private MachineTypeSvp machineType;

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
	 * 製品名
	 */
	private String offeringsName;

	/**
	 * 使用OS
	 */
	private String useOs;

	/**
	 * ハードウェア購入日
	 */
	private Date hardwarePurchaseDate;

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
	 * 請求方法区分
	 */
	private BillingTypeSvp billingType;

	/**
	 * メーカーコード
	 */
	private MakerCodeSvp makerCode;

	/**
	 * メーカー名
	 */
	private String makerName;

	/**
	 * メニュー区分
	 */
	private MenuTypeSvp menuType;

	/**
	 * メニュー名
	 */
	private String menuName;

	/**
	 * 保守対応時間区分
	 */
	private MaintenanceTimeTypeSvp maintenanceTimeType;
}
