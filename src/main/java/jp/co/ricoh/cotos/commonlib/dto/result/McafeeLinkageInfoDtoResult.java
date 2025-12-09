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
	@Schema(description = "契約ID", required = true)
	private long contractId;

	/**
	 * 契約状態
	 */
	@Schema(description = "契約状態", required = false)
	private String customerStatus;

	/**
	 * ノード数
	 */
	@Schema(description = "ノード数", required = false)
	private Integer nodeNum;

	/**
	 * 注文送信日
	 */
	@Schema(description = "注文送信日", required = false)
	private Date orderSendDate;

	/**
	 * 顧客参照番号
	 */
	@Schema(description = "顧客参照番号", required = false)
	private String customerReferenceNo;

	/**
	 * メールアドレス
	 */
	@Schema(description = "メールアドレス", required = false)
	private String mailAddress;

	/**
	 * McAfee連携情報明細
	 */
	@Schema(description = "McAfee連携情報明細", required = false)
	private List<McafeeLinkageInfoDetailDtoResult> mcAfeeLinkInfoDetail;
}
