package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeCgwp.AgentInstallationDivCgwp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeCgwp.MssLinkageDivCgwp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeCgwp.MssLinkageStopDivCgwp;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（Cgwp）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterCgwpDto {
	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

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

	/**
	 * MerakiスマートサービスUTM/ルータプラン契約ID
	 */
	private String mssLinkageRjManageNumber;

	/**
	 * MerakiスマートサービスオーガニゼーションID
	 */
	private String mssLinkageOrganizationId;
}
