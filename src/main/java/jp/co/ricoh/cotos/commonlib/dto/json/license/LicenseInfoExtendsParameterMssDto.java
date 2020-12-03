package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * ライセンス情報拡張項目DTO（MSS）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseInfoExtendsParameterMssDto {

	/**
	 * オーガニゼーションID
	 */
	private String organizationId;

	/**
	 * VPN
	 */
	private String vpn;

	/**
	 * RMA契約番号
	 */
	private String rmaContractNumber;

	/**
	 * RMA契約開始日
	 */
	private String rmaContractStart;

	/**
	 * RMA契約終了日
	 */
	private String rmaContractEnd;
}
