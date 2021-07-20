package jp.co.ricoh.cotos.commonlib.dto.json.license;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * ライセンス明細拡張項目DTO（GSP）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseDetailExtendsParameterGspDto {

	public enum ReceptionStatusFlg {

		未("0"), 受理("1"), 不受理("2");

		private final String text;

		private ReceptionStatusFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ReceptionStatusFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 受付状況フラグ
	 */
	private ReceptionStatusFlg receptionStatusFlg;

	/**
	 * 不受理理由
	 */
	private String notAcceptReason;

	/**
	 * 利用期間開始日
	 */
	private String usePeriodStart;

	/**
	 * 利用期間終了日
	 */
	private String usePeriodEnd;

	/**
	 * サービス種別
	 */
	private String serviceType;

	/**
	 * 商品区分
	 */
	private String productType;

	/**
	 * 既存契約商品区分
	 */
	private String oldProductType;
}
