package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class McafeeLinkageInfoDtoResult {

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = true, position = 1)
	private long contractId;

	/**
	 * 契約状態
	 */
	@ApiModelProperty(value = "契約状態", required = false, position = 2)
	private String customerStatus;

	/**
	 * ノード数
	 */
	@ApiModelProperty(value = "ノード数", required = false, position = 3)
	private Integer nodeNum;

	/**
	 * 注文送信日
	 */
	@ApiModelProperty(value = "注文送信日", required = false, position = 4)
	private Date orderSendDate;

	/**
	 * 顧客参照番号
	 */
	@ApiModelProperty(value = "顧客参照番号", required = false, position = 5)
	private String customerReferenceNo;

	/**
	 * メールアドレス
	 */
	@ApiModelProperty(value = "メールアドレス", required = false, position = 6)
	private String mailAddress;

	/**
	 * McAfee連携情報明細
	 */
	@ApiModelProperty(value = "McAfee連携情報明細", required = false, position = 7)
	private List<McafeeLinkageInfoDetailDtoResult> mcAfeeLinkInfoDetail;
}
