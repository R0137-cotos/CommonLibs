package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * リコーひかり異動情報取込管理を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "r_optical_transfer_manage")
public class ROpticalTransferManage extends EntityBase {

	@Description(value = "契約確定異動情報区分")
	public enum OpticalConfirmTransferDiv {

		ひかり回線("1"), ひかり電話("2"), 両方("3");

		private final String text;

		private OpticalConfirmTransferDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OpticalConfirmTransferDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "異動情報取込状態")
	public enum OpticalTransferStatus {

		未処理("0"), 取込済み("1"), 取込エラー("2"), 未確定("3"), 取込対象外("4");

		private final String text;

		private OpticalTransferStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OpticalTransferStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "異動情報取込結果連携状態")
	public enum OpticalTransferResultLinkageState {

		未処理("0"), 連携継続("1"), 連携済み("2"), ファイル作成エラー("3"), 連携対象外("4");

		private final String text;

		private OpticalTransferResultLinkageState(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OpticalTransferResultLinkageState fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * リコーひかり異動情報取込管理ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_optical_transfer_manage_seq")
	@SequenceGenerator(name = "r_optical_transfer_manage_seq", sequenceName = "r_optical_transfer_manage_seq", allocationSize = 1)
	@Schema(description = "リコーひかり異動情報取込管理ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 見積ID
	 */
	@Min(0)
	@Schema(description = "見積ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long estimationId;

	/**
	 * 工事調整EIM管理番号
	 */
	@Schema(description = "工事調整EIM管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String constructEimNumber;

	/**
	 * 注文種別
	 */
	@Schema(description = "注文種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String orderType;

	/**
	 * NTTお客様ID
	 */
	@Schema(description = "NTTお客様ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nttCustomerId;

	/**
	 * アクセスキー
	 */
	@Schema(description = "アクセスキー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String accessKey;

	/**
	 * 契約確定異動情報区分
	 */
	@Schema(description = "契約確定異動情報区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "ひかり回線(\"1\"), ひかり電話(\"2\"), 両方(\"3\")", example = "1")
	private OpticalConfirmTransferDiv confirmTransferDiv;

	/**
	 * ひかり回線異動情報取込状態
	 */
	@Schema(description = "契約確定異動情報区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 取込済み(\"1\"), 取込エラー(\"2\"), 未確定(\"3\"), 取込対象外(\"4\")", example = "1")
	private OpticalTransferStatus opticalLineTransferStatus;

	/**
	 * ひかり回線異動情報取込日
	 */
	@Schema(description = "ひかり回線異動情報取込日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date opticalLineTransferDate;

	/**
	 * ひかり電話異動情報取込状態
	 */
	@Schema(description = "ひかり電話異動情報取込状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 取込済み(\"1\"), 取込エラー(\"2\"), 未確定(\"3\"), 取込対象外(\"4\")", example = "1")
	private OpticalTransferStatus opticalPhoneTransferStatus;

	/**
	 * ひかり電話異動情報取込日
	 */
	@Schema(description = "ひかり電話異動情報取込日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date opticalPhoneTransferDate;

	/**
	 * 異動情報取込結果連携状態
	 */
	@Schema(description = "異動情報取込結果連携状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 連携継続(\"1\"), 連携済み(\"2\"), ファイル作成エラー(\"3\"), 連携対象外(\"4\")", example = "1")
	private OpticalTransferResultLinkageState transferResultLinkageState;

	/**
	 * 異動情報取込結果連携日
	 */
	@Schema(description = "異動情報取込結果連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date transferResultLinkageDate;

	/**
	 * リコーひかり異動情報
	 */
	@Valid
	@OneToMany(mappedBy = "rOpticalTransferManage")
	@Schema(description = "リコーひかり異動情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED, readOnly = true)
	private List<ROpticalTransfer> rOpticalTransferList;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * 工事調整完了番号
	 */
	@Schema(description = "工事調整完了番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String constructAdjustmentNumber;

	/**
	 * リモートサポート異動情報取込状態
	 */
	@Schema(description = "リモートサポート異動情報取込状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 取込済み(\"1\"), 取込エラー(\"2\"), 未確定(\"3\"), 取込対象外(\"4\")", example = "1")
	private OpticalTransferStatus opticalRemoteTransferStatus;

	/**
	 * リモートサポート異動情報取込日
	 */
	@Schema(description = "リモートサポート異動情報取込日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date opticalRemoteTransferDate;
}
