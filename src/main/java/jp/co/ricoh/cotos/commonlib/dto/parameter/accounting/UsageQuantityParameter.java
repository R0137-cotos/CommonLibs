package jp.co.ricoh.cotos.commonlib.dto.parameter.accounting;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
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
	 * RJ管理番号
	 * CSVから取得、従量超過分使用量取込パラメータ取得APIで設定
	 */
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約ID
	 * 従量超過分使用量取込パラメータ取得APIで設定
	 */
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long contractId;

	/**
	 * 契約明細ID
	 * 従量超過分使用量取込パラメータ取得APIで設定
	 */
	@Schema(description = "契約明細ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long contractDetailId;

	/**
	 * 顧客名
	 * 従量超過分使用量取込パラメータ取得APIで設定
	 */
	@Schema(description = "顧客名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerName;

	/**
	 * 品種コード
	 * CSVから取得、従量超過分使用量取込パラメータ取得APIで設定
	 */
	@Schema(description = "品種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String itemCode;

	/**
	 * 品種名
	 * 従量超過分使用量取込パラメータ取得APIで設定
	 */
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * 使用年月
	 * CSVから取得、従量超過分使用量取込パラメータ取得APIで設定
	 */
	@Schema(description = "使用年月", requiredMode = Schema.RequiredMode.REQUIRED)
	private Date usageDate;

	/**
	 * 超過使用量
	 * CSVから取得、従量超過分使用量取込パラメータ取得APIで設定
	 */
	@Schema(description = "超過使用量", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99999]")
	private Integer overuseQuantity;

	/**
	 * エラーID
	 * 従量超過分使用量取込パラメータ取得APIで設定
	 */
	@Schema(description = "エラーID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String errorId;

	/**
	 * メッセージ
	 * 従量超過分使用量取込パラメータ取得APIで設定
	 */
	@Schema(description = "メッセージ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,4000]")
	private String errorMessage;

}
