package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * RSI 契約管理APIのお客様テナント情報一覧取得API レスポンスのお客様テナント情報のDTO
 */

@Data
public class RsiCustomerAndContractorsDto {

	/**
	 * ID
	 */
	@ApiModelProperty(value = "ID", required = false, position = 1)
	private String id;

	/**
	 * テナントID
	 */
	@ApiModelProperty(value = "テナントID", required = false, position = 2)
	private String tenantId;

	/**
	 * 企業名
	 */
	@ApiModelProperty(value = "企業名", required = false, position = 3)
	private String customerName;

	/**
	 * メールアドレス
	 */
	@ApiModelProperty(value = "メールアドレス", required = false, position = 4)
	private String mailAddress;

	/**
	 * お客様テナント契約者情報一覧
	 */
	@ApiModelProperty(value = "お客様テナント契約者情報一覧", required = false, position = 5)
	private List<RsiContractorDto> contractors;

}
