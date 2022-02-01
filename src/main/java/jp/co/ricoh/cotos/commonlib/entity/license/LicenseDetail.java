package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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

		新規("1"), 減数("2"), 増数("3"), 情報変更("4"), 解約("5"), 乗換("6"), 乗換え増数("7");

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
	@ApiModelProperty(value = "ライセンス明細ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス情報
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "license_info_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ライセンス情報", required = true, position = 2)
	private LicenseInfo licenseInfo;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * シーケンスNo
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "シーケンスNo", required = true, position = 4, allowableValues = "range[0,9223372036854775807]")
	private long seqNumber;

	/**
	 * 情報区分
	 */
	@ApiModelProperty(value = "情報区分", required = false, allowableValues = "新規(\"1\"), 減数(\"2\"), 増数(\"3\"), 情報変更(\"4\"), 解約(\"5\"), 乗換(\"6\"), 乗換え増数(\"7\")", position = 5)
	private InfoDiv infoDiv;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = false, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "商品マスタID", required = false, position = 7, allowableValues = "range[0,9223372036854775807]")
	private Long productMasterId;

	/**
	 * ハード・ソフト区分
	 */
	@ApiModelProperty(value = "ハード・ソフト区分", required = false, allowableValues = "ハード(\"1\"), ライセンス(\"2\")", position = 8)
	private HardSoftDiv hardSoftDiv;

	/**
	 * 機種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "機種コード", required = false, position = 9, allowableValues = "range[0,255]")
	private String equipmentCode;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "数量", required = false, position = 10, allowableValues = "range[-99999,99999]")
	private Integer quantity;

	/**
	 * 取込フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "取込フラグ", required = false, position = 11, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 取込日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "取込日時", required = false, position = 12)
	private Date captureAt;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 13)
	@Lob
	private String extendsParameter;

	/**
	 * ライセンスサービスID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスサービスID", required = true, position = 14, allowableValues = "range[0,255]")
	private String licenseServiceId;

	/**
	 * ライセンスサービス名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスサービス名", required = true, position = 15, allowableValues = "range[0,255]")
	private String licenseServiceName;

	/**
	 * 増減数量
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "増減数量", required = false, position = 16, allowableValues = "range[-99999,99999]")
	private Integer changeQuantity;

	/**
	 * リクエスト処理区分
	 */
	@ApiModelProperty(value = "リクエスト処理区分", required = false, allowableValues = "即時(\"1\"), 月次(\"2\")", position = 17)
	private RequestProcessDiv requestProcessDiv;

	/**
	 * リクエスト作成状態
	 */
	@ApiModelProperty(value = "リクエスト作成状態", required = false, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")", position = 18)
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "リクエスト作成日時", required = false, position = 19)
	private Date requestCreateDate;

}
