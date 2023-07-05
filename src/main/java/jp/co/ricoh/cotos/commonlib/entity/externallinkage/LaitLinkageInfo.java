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
 * LaIT連携情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "lait_linkage_info")
public class LaitLinkageInfo extends EntityBase {

	@Description(value = "情報区分")
	public enum InfoDiv {

		新規("1"), 追加("2"), 解約("3");

		private final String text;

		private InfoDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static InfoDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "ルータ/AP区分")
	public enum RouterApDiv {

		ルータ("1"), AP("2");

		private final String text;

		private RouterApDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static RouterApDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "申請状況")
	public enum RequestStatus {

		未("0"), 済("1");

		private final String text;

		private RequestStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static RequestStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "受付状況")
	public enum ReceiptStatus {

		未("0"), 済("1");

		private final String text;

		private ReceiptStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ReceiptStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "登録状況")
	public enum EntryStatus {

		未登録("0"), 登録済("1"), 解約済("2");

		private final String text;

		private EntryStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static EntryStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "ディストリビューションID連携状態")
	public enum DistributionidLinkageState {

		未処理("0"), 連携済み("1"), エラー("2"), 処理対象外("3");

		private final String text;

		private DistributionidLinkageState(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DistributionidLinkageState fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * LaIT連携情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lait_linkage_info_seq")
	@SequenceGenerator(name = "lait_linkage_info_seq", sequenceName = "lait_linkage_info_seq", allocationSize = 1)
	@ApiModelProperty(value = "LaIT連携情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 管理拠点RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "管理拠点RJ管理番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String baseRjManageNumber;

	/**
	 * 情報区分
	 */
	@ApiModelProperty(value = "情報区分", required = false, position = 5, allowableValues = "新規(\"1\"), 追加(\"2\"), 解約(\"3\")")
	private InfoDiv infoDiv;

	/**
	 * ルータ/AP区分
	 */
	@ApiModelProperty(value = "ルータ/AP区分", required = false, position = 6, allowableValues = "ルータ(\"1\"), AP(\"2\")")
	private RouterApDiv routerApDiv;

	/**
	 * SAラベル
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "SAラベル", required = false, position = 7, allowableValues = "range[0,255]")
	private String saLabel;

	/**
	 * SAコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "SAコード", required = false, position = 8, allowableValues = "range[0,255]")
	private String saCode;

	/**
	 * LaITID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "LaITID", required = false, position = 9, allowableValues = "range[0,255]")
	private String laitId;

	/**
	 * サービスコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービスコード", required = false, position = 10, allowableValues = "range[0,255]")
	private String serviceCode;

	/**
	 * パスワード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "パスワード", required = false, position = 11, allowableValues = "range[0,255]")
	private String password;

	/**
	 * 申請状況
	 */
	@ApiModelProperty(value = "申請状況", required = false, position = 12, allowableValues = "未(\"0\"), 済(\"1\")")
	private RequestStatus requestStatus;

	/**
	 * 申請日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "申請日時", required = false, position = 13)
	private Date requestAt;

	/**
	 * 受付状況
	 */
	@ApiModelProperty(value = "受付状況", required = false, position = 14, allowableValues = "未(\"0\"), 済(\"1\")")
	private ReceiptStatus receiptStatus;

	/**
	 * 受付日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "受付日時", required = false, position = 15)
	private Date receiptAt;

	/**
	 * 解約予定日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "解約予定日", required = false, position = 16)
	private Date cancelScheduledDate;

	/**
	 * 登録状況
	 */
	@ApiModelProperty(value = "登録状況", required = false, position = 17, allowableValues = "未登録(\"0\"), 登録済み(\"1\"), 解約済み(\"2\")")
	private EntryStatus entryStatus;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "削除フラグ", required = false, position = 18, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * ディストリビューションID連携状態
	 */
	@ApiModelProperty(value = "ディストリビューションID連携状態", required = false, position = 19, allowableValues = "未処理(\"0\"), 連携済み(\"1\"), エラー(\"2\"), 処理対象外(\"3\")")
	private DistributionidLinkageState distributionidLinkageState;

	/**
	 * ディストリビューションID連携日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ディストリビューションID連携日", required = false, position = 20)
	private Date distributionidLinkageDate;

	/**
	 * IIJ機器情報ID
	 */
	@Min(0)
	@ApiModelProperty(value = "IIJ機器情報ID", required = false, position = 21, allowableValues = "range[0,9223372036854775807]")
	private long iijMachineInfoId;
}
