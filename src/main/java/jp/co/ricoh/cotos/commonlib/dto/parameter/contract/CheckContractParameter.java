package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Parameter;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import lombok.Data;

/**
 * 契約情報確認のためのパラメータクラスを表します。
 */
@Data
public class CheckContractParameter {
	@Valid
	@Parameter(description = "契約", required = true)
	private Contract contract;

	@Parameter(description = "日付項目チェックフラグ", required = true, allowableValues = "true, false")
	private boolean checkDateFlg;

}
