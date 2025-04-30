package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "見積ID", required = false, position = 1, allowableValues = "range[0,9223372036854775807]")
	private Long estimationId;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "解約フラグ", required = false, position = 3, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 端末種別
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "端末種別", required = false, position = 5, allowableValues = "range[0,255]")
	private String terminalType;

	/**
	 * サービスコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービスコード", required = false, position = 6, allowableValues = "range[0,255]")
	private String serviceCd;

	/**
	 * 端末電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "端末電話番号", required = false, position = 7, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * シリアル番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "シリアル番号", required = false, position = 8, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * モバイル利用開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "モバイル利用開始日", required = false, position = 9)
	private Date mobileUsageStartDate;

	/**
	 * モバイル課金開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "モバイル課金開始日", required = false, position = 10)
	private Date mobileBillingStartDate;

	/**
	 * モバイル伝票番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "モバイル伝票番号", required = false, position = 11, allowableValues = "range[0,255]")
	private String mobileSlipNumber;

	/**
	 * 端末状態区分
	 */
	@ApiModelProperty(value = "端末状態区分", required = false, position = 12, allowableValues = "契約中(\"1\"), 返却(\"2\"), 紛失(\"3\"), 破損水没(\"4\")")
	private TerminalStatusDiv terminalStatusDiv;

	/**
	 * 端末解約送信日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "端末解約送信日", required = false, position = 13)
	private Date cancelSendDate;

	/**
	 * 端末解約契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "端末解約契約ID", required = false, position = 14, allowableValues = "range[0,9223372036854775807]")
	private Long cancelContractId;

	/**
	 * 処理区分
	 */
	@ApiModelProperty(value = "処理区分", required = false, position = 15, allowableValues = "未処理(\"0\"), 処理済み(\"1\"), 対象外(\"2\")")
	private ProcessDiv processDiv;

}
