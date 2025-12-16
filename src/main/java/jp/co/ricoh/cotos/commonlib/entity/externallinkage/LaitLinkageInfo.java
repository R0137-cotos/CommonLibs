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
	@Schema(description = "LaIT連携情報ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 管理拠点RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "管理拠点RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String baseRjManageNumber;

	/**
	 * 情報区分
	 */
	@Schema(description = "情報区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "新規(\"1\"), 追加(\"2\"), 解約(\"3\")")
	private InfoDiv infoDiv;

	/**
	 * ルータ/AP区分
	 */
	@Schema(description = "ルータ/AP区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "ルータ(\"1\"), AP(\"2\")")
	private RouterApDiv routerApDiv;

	/**
	 * SAラベル
	 */
	@Size(max = 255)
	@Schema(description = "SAラベル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String saLabel;

	/**
	 * SAコード
	 */
	@Size(max = 255)
	@Schema(description = "SAコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String saCode;

	/**
	 * LaITID
	 */
	@Size(max = 255)
	@Schema(description = "LaITID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String laitId;

	/**
	 * サービスコード
	 */
	@Size(max = 255)
	@Schema(description = "サービスコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serviceCode;

	/**
	 * パスワード
	 */
	@Size(max = 255)
	@Schema(description = "パスワード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String password;

	/**
	 * 申請状況
	 */
	@Schema(description = "申請状況", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未(\"0\"), 済(\"1\")")
	private RequestStatus requestStatus;

	/**
	 * 申請日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "申請日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date requestAt;

	/**
	 * 受付状況
	 */
	@Schema(description = "受付状況", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未(\"0\"), 済(\"1\")")
	private ReceiptStatus receiptStatus;

	/**
	 * 受付日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "受付日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date receiptAt;

	/**
	 * 解約予定日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "解約予定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cancelScheduledDate;

	/**
	 * 登録状況
	 */
	@Schema(description = "登録状況", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未登録(\"0\"), 登録済み(\"1\"), 解約済み(\"2\")")
	private EntryStatus entryStatus;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "削除フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * ディストリビューションID連携状態
	 */
	@Schema(description = "ディストリビューションID連携状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 連携済み(\"1\"), エラー(\"2\"), 処理対象外(\"3\")")
	private DistributionidLinkageState distributionidLinkageState;

	/**
	 * ディストリビューションID連携日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ディストリビューションID連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date distributionidLinkageDate;

	/**
	 * IIJ機器情報ID
	 */
	@Min(0)
	@Schema(description = "IIJ機器情報ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long iijMachineInfoId;
}
