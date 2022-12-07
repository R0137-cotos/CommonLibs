package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;
import java.util.Date;

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
 * モバイル機器情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "mobile_equipment")
public class MobileEquipment extends EntityBase {

	@Description(value = "端末状態区分")
	public enum TerminalStatusDiv {

		契約中("1"), 返却("2"), 紛失("3"), 破損水没("4");

		private final String text;

		private TerminalStatusDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TerminalStatusDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "処理区分")
	public enum ProcessDiv {

		処理対象("1"), 処理済("2"), 対象外("3");

		private final String text;

		private ProcessDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ProcessDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * モバイル機器ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mobile_equipment_seq")
	@SequenceGenerator(name = "mobile_equipment_seq", sequenceName = "mobile_equipment_seq", allocationSize = 1)
	@ApiModelProperty(value = "モバイル機器ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 見積ID
	 */
	@Min(0)
	@ApiModelProperty(value = "見積ID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long estimationId;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 3, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "解約フラグ", required = false, position = 4, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 5, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 端末種別
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "端末種別", required = false, position = 6, allowableValues = "range[0,255]")
	private String terminalType;

	/**
	 * サービスコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービスコード", required = false, position = 7, allowableValues = "range[0,255]")
	private String serviceCd;

	/**
	 * 端末電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "端末電話番号", required = false, position = 8, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * シリアル番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "シリアル番号", required = false, position = 9, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * モバイル利用開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "モバイル利用開始日", required = false, position = 10)
	private Date mobileUsageStartDate;

	/**
	 * モバイル課金開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "モバイル課金開始日", required = false, position = 11)
	private Date mobileBillingStartDate;

	/**
	 * モバイル伝票番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "モバイル伝票番号", required = false, position = 12, allowableValues = "range[0,255]")
	private String mobileSlipNumber;

	/**
	 * 端末状態区分
	 */
	@ApiModelProperty(value = "端末状態区分", required = false, position = 13, allowableValues = "契約中(\"1\"), 返却(\"2\"), 紛失(\"3\"), 破損水没(\"4\")")
	private TerminalStatusDiv terminalStatusDiv;

	/**
	 * 端末解約送信日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "端末解約送信日", required = false, position = 14)
	private Date cancelSendDate;

	/**
	 * 端末解約契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "端末解約契約ID", required = false, position = 15, allowableValues = "range[0,9223372036854775807]")
	private Long cancelContractId;

	/**
	 * 処理区分
	 */
	@ApiModelProperty(value = "処理区分", required = false, position = 16, allowableValues = "処理対象(\"1\"), 処理済(\"2\"), 対象外(\"3\")")
	private ProcessDiv processDiv;
}
