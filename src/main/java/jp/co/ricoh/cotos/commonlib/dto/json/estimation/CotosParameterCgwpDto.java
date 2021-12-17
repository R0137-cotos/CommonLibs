package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（見積用）拡張項目COTOS商品固有項目DTO（Cgwp）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CotosParameterCgwpDto {

	/**
	 * MerakiスマートサービスUTM/ルータプラン契約ID
	 */
	private String mssLinkageRjManageNumber;

	/**
	 * MerakiスマートサービスオーガニゼーションID
	 */
	private String mssLinkageOrganizationId;
}
