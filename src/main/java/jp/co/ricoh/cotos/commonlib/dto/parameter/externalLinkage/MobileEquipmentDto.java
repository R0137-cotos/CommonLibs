package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.MobileEquipment.ProcessDiv;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.MobileEquipment.TerminalStatusDiv;
import lombok.Data;

/**
 * モバイル機器を設定するためのキー項目クラスを表します。
 */

@Data
public class MobileEquipmentDto extends DtoBase {

	/**
	 * 見積ID
	 */
	@Min(0)
	@Schema(description = "見積ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long estimationId;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", required = false, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", required = false, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 端末種別
	 */
	@Size(max = 255)
	@Schema(description = "端末種別", required = false, allowableValues = "range[0,255]")
	private String terminalType;

	/**
	 * サービスコード
	 */
	@Size(max = 255)
	@Schema(description = "サービスコード", required = false, allowableValues = "range[0,255]")
	private String serviceCd;

	/**
	 * 端末電話番号
	 */
	@Size(max = 255)
	@Schema(description = "端末電話番号", required = false, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * シリアル番号
	 */
	@Size(max = 255)
	@Schema(description = "シリアル番号", required = false, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * モバイル利用開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "モバイル利用開始日", required = false)
	private Date mobileUsageStartDate;

	/**
	 * モバイル課金開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "モバイル課金開始日", required = false)
	private Date mobileBillingStartDate;

	/**
	 * モバイル伝票番号
	 */
	@Size(max = 255)
	@Schema(description = "モバイル伝票番号", required = false, allowableValues = "range[0,255]")
	private String mobileSlipNumber;

	/**
	 * 端末状態区分
	 */
	@Schema(description = "端末状態区分", required = false, allowableValues = "契約中(\"1\"), 返却(\"2\"), 紛失(\"3\"), 破損水没(\"4\")")
	private TerminalStatusDiv terminalStatusDiv;

	/**
	 * 端末解約送信日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "端末解約送信日", required = false)
	private Date cancelSendDate;

	/**
	 * 端末解約契約ID
	 */
	@Min(0)
	@Schema(description = "端末解約契約ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long cancelContractId;

	/**
	 * 処理区分
	 */
	@Schema(description = "処理区分", required = false, allowableValues = "未処理(\"0\"), 処理済み(\"1\"), 対象外(\"2\")")
	private ProcessDiv processDiv;

}
