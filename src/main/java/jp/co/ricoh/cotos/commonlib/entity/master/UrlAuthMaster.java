package jp.co.ricoh.cotos.commonlib.entity.master;

import java.io.Serializable;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import org.springframework.context.annotation.Description;
import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.converter.HttpMethodConverter;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * URL権限マスタ
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "url_auth_master")
public class UrlAuthMaster extends EntityBaseMaster {

	@Description(value = "システムドメイン、外部参照ドメイン")
	public enum Domain {
		estimation, contract, arrangement, communication, master, arrangementDelegation;
	}

	@Description(value = "パラメータータイプ")
	public enum ParameterType {
		none("0"), path("1"), query("2"), json("3");

		private final String text;

		private ParameterType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ParameterType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "アクション区分")
	public enum ActionDiv {
		なし("00"), 照会("01"), 登録("02"), 更新("03"), 削除("04"), 印刷("05"), ダウンロード("06"), 集計("07");

		private final String text;

		private ActionDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ActionDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "権限区分")
	public enum AuthDiv {
		なし("0"), 見積_契約_手配("2200"), 請求_計上_本部("2210"), システム管理("2220"), 見積_契約_業務用検索("2230"), 業務管理("2240"), マネージド("2250"), リペア("2260");

		private final String text;

		private AuthDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AuthDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "参照種別")
	public enum AccessType {
		なし("0"), 参照("1"), 編集("2"), 承認("3");

		private final String text;

		private AccessType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AccessType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Embeddable
	@Data
	public static class Id implements Serializable {

		/**
		 * シリアルバージョンID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * URLパターン
		 */
		@Column(nullable = false)
		@Schema(description = "URLパターン", required = true)
		private String urlPattern;

		/**
		 * HTTPメソッド
		 */
		@Convert(converter = HttpMethodConverter.class)
		@Column(nullable = false)
		@Schema(description = "HTTPメソッド", required = true)
		@JsonSerialize(using = ToStringSerializer.class)
		private HttpMethod method;

		/**
		 * ドメイン
		 */
		@Column(nullable = false)
		@Enumerated(EnumType.STRING)
		@Schema(description = "システムドメイン", required = true)
		private Domain domain;
	}

	@EmbeddedId
	private Id id;

	/**
	 * 認可処理実施要否
	 */
	@Column(nullable = false)
	@Schema(description = "認可処理実施要否", required = true, allowableValues = "range[0,9]")
	private int requireAuthorize;

	/**
	 * 外部参照ドメイン
	 */
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	@Schema(description = "外部参照ドメイン", required = false)
	private Domain externalRefDomain;

	/**
	 * DBデータ存在有無
	 */
	@Column(nullable = false)
	@Schema(description = "DBデータ存在有無", required = true, allowableValues = "range[0,9]")
	private int existsDb;

	/**
	 * パラメータータイプ
	 */
	@Column(nullable = false)
	@Schema(description = "パラメータータイプ", required = true, allowableValues = "none(\"0\"), path(\"1\"), query(\"2\"), json(\"3\")", example = "1")
	private ParameterType paramType;

	/**
	 * パラメーターキー
	 */
	@Column(nullable = true)
	@Schema(description = "パラメーターキー", required = false, allowableValues = "range[0,255]")
	private String paramKey;

	/**
	 * アクション区分
	 */
	@Column(nullable = true)
	@Schema(description = "アクション区分", required = true, allowableValues = "なし(\"00\"), 照会(\"01\"), 登録(\"02\"), 更新(\"03\"), 削除(\"04\"), 印刷(\"05\"), ダウンロード(\"06\"), 集計(\"07\")", example = "01")
	private ActionDiv actionDiv;

	/**
	 * 権限区分
	 */
	@Column(nullable = true)
	@Schema(description = "権限区分", required = true, allowableValues = "なし(\"0\"), 見積_契約_手配(\"2200\"), 請求_計上_本部(\"2210\"), システム管理(\"2220\"), 見積_契約_業務用検索(\"2230\"), 業務管理(\"2240\")", example = "0")
	private AuthDiv authDiv;

	/**
	 * 参照種別
	 */
	@Column(nullable = true)
	@Schema(description = "参照種別", required = true, allowableValues = "なし(\"0\"), 参照(\"1\"), 編集(\"2\"), 承認(\"3\")", example = "1")
	private AccessType accessType;

	/**
	 * 処理概要
	 */
	@Column(nullable = true)
	@Schema(description = "処理概要", required = true, allowableValues = "range[0,255]")
	private String description;
}
