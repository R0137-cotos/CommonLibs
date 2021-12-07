package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * ライセンス情報拡張項目DTO（CGWP）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseInfoExtendsParameterCgwpDto {

	/**
	 * Merakiルータ連携契約ID
	 */
	private String mssLinkageRjManageNumber;

	/**
	 * Merakiルータ連携オーガニゼーションID
	 */
	private String mssLinkageOrganizationId;
}
