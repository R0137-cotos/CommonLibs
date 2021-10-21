package jp.co.ricoh.cotos.commonlib.entity.common;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoProcessingStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSO申込明細データエンティティー共通項目
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class OsoRequestDetailDataAbstractEntity extends EntityBase {

	@Description(value = "処理区分")
	public enum ProcessingDiv {

		追加("1"), 変更("2");

		private final String text;

		private ProcessingDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ProcessingDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "種別")
	public enum OsoCostType {

		初期費用("1"), 月額("2");

		private final String text;

		private OsoCostType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OsoCostType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * OSO申込ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "OSO申込ID", required = true, position = 2, allowableValues = "range[0,9999999999999999999]")
	private long osoRequestId;

	/**
	 * 契約明細ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約明細ID", required = true, position = 3, allowableValues = "range[0,9999999999999999999]")
	private long contractDetailId;

	/**
	 * 処理状態
	 */
	@ApiModelProperty(value = "処理状態", required = false, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0", position = 4)
	private OsoProcessingStatus processingStatus;

	/**
	 * 処理日時
	 */
	@ApiModelProperty(value = "処理日時", required = false, position = 5)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processingAt;

	/**
	 * メッセージ
	 */
	@Column(name = "message")
	@Size(max = 4000)
	@ApiModelProperty(value = "メッセージ", required = false, position = 6, allowableValues = "range[0,4000]")
	private String osoMessage;

	/**
	 * RTS管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RTS管理番号", required = false, position = 7, allowableValues = "range[0,255]")
	private String rtsManageNumber;

	/**
	 * RTS管理番号枝番
	 */
	@Max(999)
	@Min(0)
	@ApiModelProperty(value = "RTS管理番号枝番", required = false, position = 8, allowableValues = "range[1,999]")
	private Integer rtsManageBranchNumber;

	/**
	 * OSO管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "OSO管理番号", required = false, position = 9, allowableValues = "range[0,255]")
	private String osoManageNumber;

	/**
	 * 処理区分
	 */
	@ApiModelProperty(value = "処理区分", required = false, allowableValues = "追加(\"1\"), 変更(\"2\")", example = "1", position = 10)
	private ProcessingDiv processingDiv;

	/**
	 * 品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種コード", required = false, position = 11, allowableValues = "range[0,255]")
	private String itemCode;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種名", required = false, position = 12, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * 種別
	 */
	@ApiModelProperty(value = "種別", required = false, allowableValues = "初期費用(\"1\"), 月額(\"2\")", example = "1", position = 13)
	private OsoCostType osoCostType;

	/**
	 * 自動売上
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "自動売上", required = false, position = 14, allowableValues = "range[0,9]")
	private Integer automaticSalesFlg;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "数量", required = false, position = 15, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * リコー仕切り価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "リコー仕切り価格", required = false, position = 16, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal ricohPartitionPrice;

	/**
	 * リコー売上価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "リコー売上価格", required = false, position = 17, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal ricohSalesPrice;

	/**
	 * 販社売上価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販社売上価格", required = false, position = 18, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesCompanySalesPrice;

	/**
	 * 販売店売上価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販売店売上価格", required = false, position = 19, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal dealerSalesPrice;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "仕入先コード", required = false, position = 20, allowableValues = "range[0,255]")
	private String vendorCode;
}
