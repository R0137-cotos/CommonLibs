package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeLanf2.ExternalHardStackDiv;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeLanf2.PcBackupStackDiv;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeLanf2.UpsStackDiv;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（LANF2）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueLanf2Dto {

	/**
	 * 見積種別詳細
	 */
	private EstimationTypeDetails estimationTypeDetails;

	/**
	 * 外付けHDD積上げ区分
	 */
	private ExternalHardStackDiv externalHardStackDiv;

	/**
	 * UPS積上げ区分
	 */
	private UpsStackDiv upsStackDiv;

	/**
	 * PCバックアップ積上げ区分
	 */
	private PcBackupStackDiv pcBackupStackDiv;
}
