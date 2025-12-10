package jp.co.ricoh.cotos.commonlib.entity.common;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
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
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * OSO申込ID
	 */
	@Column(nullable = false)
	@Schema(description = "OSO申込ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long osoRequestId;

	/**
	 * 契約明細ID
	 */
	@Column(nullable = false)
	@Schema(description = "契約明細ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long contractDetailId;

	/**
	 * 処理状態
	 */
	@Schema(description = "処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0")
	private OsoProcessingStatus processingStatus;

	/**
	 * 処理日時
	 */
	@Schema(description = "処理日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processingAt;

	/**
	 * メッセージ
	 */
	@Column(name = "message")
	@Size(max = 4000)
	@Schema(description = "メッセージ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,4000]")
	private String osoMessage;

	/**
	 * RTS管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RTS管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rtsManageNumber;

	/**
	 * RTS管理番号枝番
	 */
	@Max(999)
	@Min(0)
	@Schema(description = "RTS管理番号枝番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[1,999]")
	private Integer rtsManageBranchNumber;

	/**
	 * OSO管理番号
	 */
	@Size(max = 255)
	@Schema(description = "OSO管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String osoManageNumber;

	/**
	 * 処理区分
	 */
	@Schema(description = "処理区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "追加(\"1\"), 変更(\"2\")", example = "1")
	private ProcessingDiv processingDiv;

	/**
	 * 品種コード
	 */
	@Size(max = 255)
	@Schema(description = "品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemCode;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * 種別
	 */
	@Schema(description = "種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "初期費用(\"1\"), 月額(\"2\")", example = "1")
	private OsoCostType osoCostType;

	/**
	 * 自動売上
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "自動売上", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer automaticSalesFlg;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * リコー仕切り価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "リコー仕切り価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal ricohPartitionPrice;

	/**
	 * リコー売上価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "リコー売上価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal ricohSalesPrice;

	/**
	 * 販社売上価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販社売上価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesCompanySalesPrice;

	/**
	 * 販売店売上価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "販売店売上価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal dealerSalesPrice;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@Schema(description = "仕入先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vendorCode;
}
