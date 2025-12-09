package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeCgwp.AgentInstallationDivCgwp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeCgwp.MssLinkageDivCgwp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeCgwp.MssLinkageStopDivCgwp;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（CGWP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueCgwpDto {

	/**
	 * 見積種別詳細
	 */
	private EstimationTypeDetails estimationTypeDetails;

	/**
	 * エージェントインストール積上げ区分
	 */
	private AgentInstallationDivCgwp agentInstallationDiv;

	/**
	 * Merakiルータ連携積上げ区分
	 */
	private MssLinkageDivCgwp mssLinkageDiv;

	/**
	 * Merakiルータ連携解除積上げ区分
	 */
	private MssLinkageStopDivCgwp mssLinkageStopDiv;
}
