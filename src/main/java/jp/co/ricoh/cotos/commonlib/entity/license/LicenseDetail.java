package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.RequestCreateStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス明細を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_detail")
public class LicenseDetail extends EntityBase {

	@Description(value = "情報区分")
	public enum InfoDiv {

		新規("1"), 減数("2"), 増数("3"), 情報変更("4"), 解約("5"), 乗換("6"), 乗換え増数("7"), 乗換え減数("8"), 支払周期変更("9");

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

	@Description(value = "ハード・ソフト区分")
	public enum HardSoftDiv {

		ハード("1"), ライセンス("2");

		private final String text;

		private HardSoftDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static HardSoftDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "リクエスト処理区分")
	public enum RequestProcessDiv {

		即時("1"), 月次("2");

		private final String text;

		private RequestProcessDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static RequestProcessDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ライセンス明細ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_detail_seq")
	@SequenceGenerator(name = "license_detail_seq", sequenceName = "license_detail_seq", allocationSize = 1)
	@Schema(description = "ライセンス明細ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス情報
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "license_info_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "ライセンス情報", requiredMode = Schema.RequiredMode.REQUIRED)
	private LicenseInfo licenseInfo;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス区分マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * シーケンスNo
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "シーケンスNo", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long seqNumber;

	/**
	 * 情報区分
	 */
	@Schema(description = "情報区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "新規(\"1\"), 減数(\"2\"), 増数(\"3\"), 情報変更(\"4\"), 解約(\"5\"), 乗換(\"6\"), 乗換え増数(\"7\"), 乗換え減数(\"8\"), 支払周期変更(\"9\")")
	private InfoDiv infoDiv;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long productMasterId;

	/**
	 * ハード・ソフト区分
	 */
	@Schema(description = "ハード・ソフト区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "ハード(\"1\"), ライセンス(\"2\")")
	private HardSoftDiv hardSoftDiv;

	/**
	 * 機種コード
	 */
	@Size(max = 255)
	@Schema(description = "機種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String equipmentCode;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer quantity;

	/**
	 * 取込フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "取込フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 取込日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "取込日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date captureAt;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * ライセンスサービスID
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスサービスID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String licenseServiceId;

	/**
	 * ライセンスサービス名
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスサービス名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String licenseServiceName;

	/**
	 * 増減数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "増減数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer changeQuantity;

	/**
	 * リクエスト処理区分
	 */
	@Schema(description = "リクエスト処理区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "即時(\"1\"), 月次(\"2\")")
	private RequestProcessDiv requestProcessDiv;

	/**
	 * リクエスト作成状態
	 */
	@Schema(description = "リクエスト作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")")
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "リクエスト作成日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date requestCreateDate;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date licenseTermEnd;

	/**
	 * リクエスト予定日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "リクエスト予定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date requestScheduleDate;

	/**
	 * ライセンスキー
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスキー", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String licenseKey;

}
