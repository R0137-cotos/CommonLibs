package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterPcpDto {
	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;
}
