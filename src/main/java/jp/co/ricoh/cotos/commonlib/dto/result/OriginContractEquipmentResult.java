package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentAdditionInfo;
import lombok.Data;

/**
 * 登録済み契約機種取得APIの実施結果用パラメーター
 */

@Data
public class OriginContractEquipmentResult {

	/**
	 * 契約機種を表すEntity
	 */
	@Schema(description = "契約機種を表すEntity", requiredMode = Schema.RequiredMode.REQUIRED)
	private ContractEquipment contractEquipment;

	/**
	 * 削除フラグ
	 */
	@Schema(description = "削除フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer deleteFlg;

	/**
	 * 契約機種付加情報を表すEntity
	 */
	@Schema(description = "契約機種付加情報を表すEntity", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractEquipmentAdditionInfo contractEquipmentAdditionInfo;

	/**
	 * 機器削除済文書番号
	 */
	@Schema(description = "機器削除済文書番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String equipmentDeletedDocumentNumber;

	/**
	 * 機器削除チェックボックス活性化フラグ
	 */
	@Schema(description = "機器削除チェックボックス活性化フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer activateEquipmentDeleteCheckboxFlg;
}