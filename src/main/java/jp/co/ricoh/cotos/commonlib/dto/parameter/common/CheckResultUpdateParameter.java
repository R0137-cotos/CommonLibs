package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import jakarta.validation.constraints.Min;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * 汎用チェック結果更新時のパラメーターを表します。
 */
@Data
public class CheckResultUpdateParameter {

	/**
	 * チェック結果ID
	 */
	@Min(0)
	@Parameter(description = "チェック結果ID", allowableValues = "range[0,9223372036854775807]", required = true)
	private long checkResultId;

	/**
	 * 更新ステータス
	 */
	@Parameter(description = "更新ステータス", required = true)
	private boolean updateStatus;

}
