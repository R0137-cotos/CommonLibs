package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;
import java.util.Date;

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

		未処理("0"), 処理済み("1"), 対象外("2");

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
	@Schema(description = "モバイル機器ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 見積ID
	 */
	@Min(0)
	@Schema(description = "見積ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long estimationId;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 端末種別
	 */
	@Size(max = 255)
	@Schema(description = "端末種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String terminalType;

	/**
	 * サービスコード
	 */
	@Size(max = 255)
	@Schema(description = "サービスコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serviceCd;

	/**
	 * 端末電話番号
	 */
	@Size(max = 255)
	@Schema(description = "端末電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * シリアル番号
	 */
	@Size(max = 255)
	@Schema(description = "シリアル番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * モバイル利用開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "モバイル利用開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date mobileUsageStartDate;

	/**
	 * モバイル課金開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "モバイル課金開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date mobileBillingStartDate;

	/**
	 * モバイル伝票番号
	 */
	@Size(max = 255)
	@Schema(description = "モバイル伝票番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String mobileSlipNumber;

	/**
	 * 端末状態区分
	 */
	@Schema(description = "端末状態区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "契約中(\"1\"), 返却(\"2\"), 紛失(\"3\"), 破損水没(\"4\")")
	private TerminalStatusDiv terminalStatusDiv;

	/**
	 * 端末解約送信日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "端末解約送信日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cancelSendDate;

	/**
	 * 端末解約契約ID
	 */
	@Min(0)
	@Schema(description = "端末解約契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long cancelContractId;

	/**
	 * 処理区分
	 */
	@Schema(description = "処理区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 処理済み(\"1\"), 対象外(\"2\")")
	private ProcessDiv processDiv;
}
