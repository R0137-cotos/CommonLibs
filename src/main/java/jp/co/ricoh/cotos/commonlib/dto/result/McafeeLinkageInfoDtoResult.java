package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class McafeeLinkageInfoDtoResult {

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long contractId;

	/**
	 * 契約状態
	 */
	@Schema(description = "契約状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerStatus;

	/**
	 * ノード数
	 */
	@Schema(description = "ノード数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer nodeNum;

	/**
	 * 注文送信日
	 */
	@Schema(description = "注文送信日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date orderSendDate;

	/**
	 * 顧客参照番号
	 */
	@Schema(description = "顧客参照番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerReferenceNo;

	/**
	 * メールアドレス
	 */
	@Schema(description = "メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String mailAddress;

	/**
	 * McAfee連携情報明細
	 */
	@Schema(description = "McAfee連携情報明細", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<McafeeLinkageInfoDetailDtoResult> mcAfeeLinkInfoDetail;
}
