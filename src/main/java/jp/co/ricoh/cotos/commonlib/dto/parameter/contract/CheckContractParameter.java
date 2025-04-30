package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.validation.Valid;

import io.swagger.annotations.ApiParam;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import lombok.Data;

/**
 * 契約情報確認のためのパラメータクラスを表します。
 */
@Data
public class CheckContractParameter {
	@Valid
	@ApiParam(value = "契約", required = true)
	private Contract contract;

	@ApiParam(value = "日付項目チェックフラグ", required = true, allowableValues = "true, false")
	private boolean checkDateFlg;

}
