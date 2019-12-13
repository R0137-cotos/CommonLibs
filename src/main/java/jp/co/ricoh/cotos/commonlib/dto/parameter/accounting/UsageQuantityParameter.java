package jp.co.ricoh.cotos.commonlib.dto.parameter.accounting;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 従量超過分使用量取込パラメーター
 *
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UsageQuantityParameter extends DtoBase {
	/**
	 * エラーID
	 * 従量超過分使用量取込パラメータ取得APIで設定
	 */
	@ApiModelProperty(value = "エラーID", required = false, position = 4, allowableValues = "range[0,255]")
	private String errorId;

	/**
	 * メッセージ
	 * 従量超過分使用量取込パラメータ取得APIで設定
	 */
	@ApiModelProperty(value = "メッセージ", required = false, position = 4, allowableValues = "range[0,4000]")
	private String errorMessage;

	/**
	 * RJ管理番号
	 * CSVから取得、従量超過分使用量取込パラメータ取得APIで設定
	 */
	@ApiModelProperty(value = "RJ管理番号", required = true, position = 5, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約ID
	 * 従量超過分使用量取込パラメータ取得APIで設定
	 */
	@ApiModelProperty(value = "契約ID", required = true, position = 6, allowableValues = "range[0,9999999999999999999]")
	private Long contractId;

	/**
	 * 契約明細ID
	 * 従量超過分使用量取込パラメータ取得APIで設定
	 */
	@ApiModelProperty(value = "契約明細ID", required = true, position = 7, allowableValues = "range[0,9999999999999999999]")
	private Long contractDetailId;

	/**
	 * 品種コード
	 * CSVから取得、従量超過分使用量取込パラメータ取得APIで設定
	 */
	@ApiModelProperty(value = "品種コード", required = true, position = 8, allowableValues = "range[0,255]")
	private String itemCode;

	/**
	 * 品種名
	 * 従量超過分使用量取込パラメータ取得APIで設定
	 */
	@ApiModelProperty(value = "品種名", required = false, position = 9, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * 使用年月
	 * CSVから取得、従量超過分使用量取込パラメータ取得APIで設定
	 */
	@ApiModelProperty(value = "使用年月", required = false, position = 10)
	private String usageDate;

	/**
	 * 超過使用量
	 * CSVから取得、従量超過分使用量取込パラメータ取得APIで設定
	 */
	@ApiModelProperty(value = "超過使用量", required = false, position = 11, allowableValues = "range[0,99999]")
	private Integer overuseQuantity;
}
