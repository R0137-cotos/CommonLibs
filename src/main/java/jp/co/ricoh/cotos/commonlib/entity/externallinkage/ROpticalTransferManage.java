package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	public enum OpticalTransferResultLinkage {

		未処理("0"), 連携継続("1"), 連携済み("2"), ファイル作成エラー("3"), 連携対象外("4");

		private final String text;

		private OpticalTransferResultLinkage(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OpticalTransferResultLinkage fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * リコーひかり異動情報取込管理ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_optical_transfer_manage_seq")
	@SequenceGenerator(name = "r_optical_transfer_manage_seq", sequenceName = "r_optical_transfer_manage_seq", allocationSize = 1)
	@ApiModelProperty(value = "リコーひかり異動情報取込管理ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 見積ID
	 */
	@Min(0)
	@ApiModelProperty(value = "見積ID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long estimationId;

	/**
	 * 工事調整EIM管理番号
	 */
	@ApiModelProperty(value = "工事調整EIM管理番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String constructEimNumber;

	/**
	 * 注文種別
	 */
	@ApiModelProperty(value = "注文種別", required = false, position = 4, allowableValues = "range[0,255]")
	private String orderType;

	/**
	 * NTTお客様ID
	 */
	@ApiModelProperty(value = "NTTお客様ID", required = false, position = 5, allowableValues = "range[0,255]")
	private String nttCustomerId;

	/**
	 * アクセスキー
	 */
	@ApiModelProperty(value = "アクセスキー", required = false, position = 5, allowableValues = "range[0,255]")
	private String accessKey;

	/**
	 * 契約確定異動情報区分
	 */
	@ApiModelProperty(value = "契約確定異動情報区分", required = false, allowableValues = "ひかり回線(\"1\"), ひかり電話(\"2\"), 両方(\"3\")", example = "1", position = 6)
	private OpticalConfirmTransferDiv confirmTransferDiv;

	/**
	 * ひかり回線異動情報取込状態
	 */
	@ApiModelProperty(value = "契約確定異動情報区分", required = false, allowableValues = "未処理(\"0\"), 取込済み(\"1\"), 取込エラー(\"2\"), 未確定(\"3\"), 取込対象外(\"4\")", example = "1", position = 7)
	private OpticalTransferStatus opticalLineTransferStatus;

	/**
	 * ひかり回線異動情報取込日
	 */
	@ApiModelProperty(value = "ひかり回線異動情報取込日", required = false, position = 8)
	@Temporal(TemporalType.DATE)
	private Date opticalLineTransferDate;

	/**
	 * ひかり電話異動情報取込状態
	 */
	@ApiModelProperty(value = "ひかり電話異動情報取込状態", required = false, allowableValues = "未処理(\"0\"), 取込済み(\"1\"), 取込エラー(\"2\"), 未確定(\"3\"), 取込対象外(\"4\")", example = "1", position = 9)
	private OpticalTransferStatus opticalPhoneTransferStatus;

	/**
	 * ひかり電話異動情報取込日
	 */
	@ApiModelProperty(value = "ひかり電話異動情報取込日", required = false, position = 10)
	@Temporal(TemporalType.DATE)
	private Date opticalPhoneTransferDate;

	/**
	 * 異動情報取込結果連携状態
	 */
	@ApiModelProperty(value = "異動情報取込結果連携状態", required = false, allowableValues = "未処理(\"0\"), 連携継続(\"1\"), 連携済み(\"2\"), ファイル作成エラー(\"3\"), 連携対象外(\"4\")", example = "1", position = 11)
	private OpticalTransferResultLinkage transferResultLinkageState;

	/**
	 * 異動情報取込結果連携日
	 */
	@ApiModelProperty(value = "異動情報取込結果連携日", required = false, position = 12)
	@Temporal(TemporalType.DATE)
	private Date transferResultLinkageDate;

	/**
	 * リコーひかり異動情報
	 */
	@Valid
	@OneToMany(mappedBy = "rOpticalTransferManage")
	@ApiModelProperty(value = "リコーひかり異動情報", required = false, position = 13, readOnly = true)
	private List<ROpticalTransfer> rOpticalTransferList;

}
