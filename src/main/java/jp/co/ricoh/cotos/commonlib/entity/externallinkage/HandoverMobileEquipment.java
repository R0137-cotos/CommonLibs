package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

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

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.MobileEquipment.ProcessDiv;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.MobileEquipment.TerminalStatusDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 引継ぎ用モバイル機器情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "handover_mobile_equipment")
public class HandoverMobileEquipment extends EntityBase {

	/**
	 * 引継ぎ用モバイル機器ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "handover_mobile_equipment_seq")
	@SequenceGenerator(name = "handover_mobile_equipment_seq", sequenceName = "handover_mobile_equipment_seq", allocationSize = 1)
	@Schema(description = "引継ぎ用モバイル機器ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
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
	@Temporal(TemporalType.DATE)
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

	/**
	 * 引継ぎ元契約ID
	 */
	@Min(0)
	@Schema(description = "引継ぎ元契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long handoverContractId;

	/**
	 * 引継ぎ反映フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "引継ぎ反映フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer handoverMappedFlg;
}
