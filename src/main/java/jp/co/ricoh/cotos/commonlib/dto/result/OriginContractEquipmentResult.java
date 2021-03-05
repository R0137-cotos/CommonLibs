package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment;
import lombok.Data;

/**
 * 登録済み契約機種取得APIの実施結果用パラメーター
 */

@Data
public class OriginContractEquipmentResult {

	/**
	 * 契約機種を表すEntity
	 */
	@ApiModelProperty(value = "契約機種を表すEntity", required = true, position = 1)
	private ContractEquipment contractEquipment;

	/**
	 * 削除フラグ
	 */
	@ApiModelProperty(value = "削除フラグ", required = false, position = 2)
	private Integer deleteFlg;
}