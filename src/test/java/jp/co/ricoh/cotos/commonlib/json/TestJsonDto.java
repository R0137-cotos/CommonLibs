package jp.co.ricoh.cotos.commonlib.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * JsonUtilテスト用のDTOです。
 *
 * <pre>
 * テスト以外の使用不可
 * </pre>
 */
@Data
public class TestJsonDto {

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

	@JsonProperty("testObject")
	private TestJsonDetailDto testJsonDetailDto;
}
