package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "McAfee連携情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 手配業務ID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "手配業務ID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long arrangementWorkId;

	/**
	 * 処理種別
	 */
	@ApiModelProperty(value = "処理種別", required = false, position = 4, allowableValues = "新規注文(\"N\"), 注文キャンセル(\"U\"), 顧客情報更新(\"C\")")
	private McafeeLinkageProcessDiv processDiv;

	/**
	 * 顧客参照番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客参照番号", required = false, position = 5, allowableValues = "range[0,255]")
	private String customerReferenceNo;

	/**
	 * オーダーNo
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "オーダーNo", required = false, position = 6, allowableValues = "range[0,255]")
	private String orderNo;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールアドレス", required = false, position = 7, allowableValues = "range[0,255]")
	private String mailAddress;

	/**
	 * パスワード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "パスワード", required = false, position = 8, allowableValues = "range[0,255]")
	private String password;

	/**
	 * 都道府県コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都道府県コード", required = false, position = 9, allowableValues = "range[0,255]")
	private String prefecturesCd;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "市区町村", required = false, position = 10, allowableValues = "range[0,255]")
	private String municipalities;

	/**
	 * 町名番地
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "市区町村", required = false, position = 11, allowableValues = "range[0,255]")
	private String streetBunch;

	/**
	 * 建物名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "建物名", required = false, position = 12, allowableValues = "range[0,255]")
	private String buildingName;

	/**
	 * 住所詳細情報
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "住所詳細情報", required = false, position = 13, allowableValues = "range[0,255]")
	private String addressDetailInfo;

	/**
	 * 注文日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "注文日", required = false, position = 14)
	private Date orderDate;

	/**
	 * ノード数
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "ノード数", required = false, position = 15, allowableValues = "range[-99999,99999]")
	private Integer quantity;

	/**
	 * 注文送信日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "注文送信日", required = false, position = 16)
	private Date orderSendDate;

	/**
	 * ePOCloud顧客ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ePOCloud顧客ID", required = false, position = 17, allowableValues = "range[0,255]")
	private String epocloudCustomerId;

	/**
	 * ePOCloud注文ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ePOCloud注文ID", required = false, position = 18, allowableValues = "range[0,255]")
	private String epocloudOrderId;

	/**
	 * アクティベーションURL
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アクティベーションURL", required = false, position = 19, allowableValues = "range[0,255]")
	private String activationUrl;

	/**
	 * オーダーCSV名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "オーダーCSV名称", required = false, position = 20, allowableValues = "range[0,255]")
	private String orderCsvName;

	/**
	 * オーダーCSV連携日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "オーダーCSV連携日", required = false, position = 21)
	private Date orderCsvLinkageDate;

	/**
	 * リプライCSV名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "リプライCSV名称", required = false, position = 22, allowableValues = "range[0,255]")
	private String replyCsvName;

	/**
	 * リプライCSV取込日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "リプライCSV取込日", required = false, position = 23)
	private Date replyCsvLinkageDate;

	/**
	 * 連携状況
	 */
	@ApiModelProperty(value = "連携状況", required = false, position = 24, allowableValues = "未処理(\"0\"), 連携済(\"1\"), 取込済(\"2\"), 破棄(\"3\")")
	private LinkageStatus linkageStatus;

	/**
	 * 解約フラグ
	 */
	@ApiModelProperty(value = "解約フラグ", required = false, position = 25, allowableValues = "通常時(\"0\"), 解約時(\"1\")")
	private DisengagementFlg disengagementFlg;
}
