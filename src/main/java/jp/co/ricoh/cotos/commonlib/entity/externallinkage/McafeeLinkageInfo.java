package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * McAfee連携情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "mcafee_linkage_info")
public class McafeeLinkageInfo extends EntityBase {

	@Description(value = "処理種別")
	public enum McafeeLinkageProcessDiv {

		新規注文("N"), 注文キャンセル("U"), 顧客情報更新("C");

		private final String text;

		private McafeeLinkageProcessDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static McafeeLinkageProcessDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "連携状況")
	public enum LinkageStatus {

		未処理("0"), 連携済("1"), 取込済("2"), 破棄("3");

		private final String text;

		private LinkageStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static LinkageStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "解約フラグ")
	public enum DisengagementFlg {

		通常時("0"), 解約時("1");

		private final String text;

		private DisengagementFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DisengagementFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * McAfee連携情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mcafee_linkage_info_seq")
	@SequenceGenerator(name = "mcafee_linkage_info_seq", sequenceName = "mcafee_linkage_info_seq", allocationSize = 1)
	@Schema(description = "McAfee連携情報ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "契約ID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 手配業務ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "手配業務ID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long arrangementWorkId;

	/**
	 * 処理種別
	 */
	@Schema(description = "処理種別", required = false, allowableValues = "新規注文(\"N\"), 注文キャンセル(\"U\"), 顧客情報更新(\"C\")")
	private McafeeLinkageProcessDiv processDiv;

	/**
	 * 顧客参照番号
	 */
	@Size(max = 255)
	@Schema(description = "顧客参照番号", required = false, allowableValues = "range[0,255]")
	private String customerReferenceNo;

	/**
	 * オーダーNo
	 */
	@Size(max = 255)
	@Schema(description = "オーダーNo", required = false, allowableValues = "range[0,255]")
	private String orderNo;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "メールアドレス", required = false, allowableValues = "range[0,255]")
	private String mailAddress;

	/**
	 * パスワード
	 */
	@Size(max = 255)
	@Schema(description = "パスワード", required = false, allowableValues = "range[0,255]")
	private String password;

	/**
	 * 都道府県コード
	 */
	@Size(max = 255)
	@Schema(description = "都道府県コード", required = false, allowableValues = "range[0,255]")
	private String prefecturesCd;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@Schema(description = "市区町村", required = false, allowableValues = "range[0,255]")
	private String municipalities;

	/**
	 * 町名番地
	 */
	@Size(max = 255)
	@Schema(description = "市区町村", required = false, allowableValues = "range[0,255]")
	private String streetBunch;

	/**
	 * 建物名
	 */
	@Size(max = 255)
	@Schema(description = "建物名", required = false, allowableValues = "range[0,255]")
	private String buildingName;

	/**
	 * 住所詳細情報
	 */
	@Size(max = 255)
	@Schema(description = "住所詳細情報", required = false, allowableValues = "range[0,255]")
	private String addressDetailInfo;

	/**
	 * 注文日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "注文日", required = false)
	private Date orderDate;

	/**
	 * ノード数
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "ノード数", required = false, allowableValues = "range[0,99999]")
	private Integer nodeNum;

	/**
	 * 注文送信日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "注文送信日", required = false)
	private Date orderSendDate;

	/**
	 * ePOCloud顧客ID
	 */
	@Size(max = 255)
	@Schema(description = "ePOCloud顧客ID", required = false, allowableValues = "range[0,255]")
	private String epocloudCustomerId;

	/**
	 * ePOCloud注文ID
	 */
	@Size(max = 255)
	@Schema(description = "ePOCloud注文ID", required = false, allowableValues = "range[0,255]")
	private String epocloudOrderId;

	/**
	 * アクティベーションURL
	 */
	@Size(max = 255)
	@Schema(description = "アクティベーションURL", required = false, allowableValues = "range[0,255]")
	private String activationUrl;

	/**
	 * オーダーCSV名称
	 */
	@Size(max = 255)
	@Schema(description = "オーダーCSV名称", required = false, allowableValues = "range[0,255]")
	private String orderCsvName;

	/**
	 * オーダーCSV連携日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "オーダーCSV連携日", required = false)
	private Date orderCsvLinkageDate;

	/**
	 * リプライCSV名称
	 */
	@Size(max = 255)
	@Schema(description = "リプライCSV名称", required = false, allowableValues = "range[0,255]")
	private String replyCsvName;

	/**
	 * リプライCSV取込日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "リプライCSV取込日", required = false)
	private Date replyCsvLinkageDate;

	/**
	 * 連携状況
	 */
	@Schema(description = "連携状況", required = false, allowableValues = "未処理(\"0\"), 連携済(\"1\"), 取込済(\"2\"), 破棄(\"3\")")
	private LinkageStatus linkageStatus;

	/**
	 * 解約フラグ
	 */
	@Schema(description = "解約フラグ", required = false, allowableValues = "通常時(\"0\"), 解約時(\"1\")")
	private DisengagementFlg disengagementFlg;

	/**
	 * 注文参照番号
	 */
	@Size(max = 255)
	@Schema(description = "注文参照番号", required = false, allowableValues = "range[0,255]")
	private String orderReferenceNo;
}
