package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文販売店情報DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OrderDistributorInfoDto {

	/**
	 * 販売店コード
	 */
	@Size(max = 255)
	@Schema(description = "販売店コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorCd;

	/**
	 * 販売店名
	 */
	@Size(max = 255)
	@Schema(description = "販売店名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorName;

	/**
	 * OE届け先コード
	 */
	@Size(max = 255)
	@Schema(description = "OE届け先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String oeDeliveryCd;

	/**
	 * 販売店区分
	 */
	@Size(max = 255)
	@Schema(description = "販売店区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorCustomerType;

	/**
	 * 販売店担当営業
	 */
	@Size(max = 255)
	@Schema(description = "販売店担当営業", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorEmployeeName;

	/**
	 * 販売店担当営業メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "販売店担当営業メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorEmployeeMailAddress;

	/**
	 * Rings得意先コード
	 */
	@Size(max = 255)
	@Schema(description = "Rings得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ringsCustomerCd;

	/**
	 * 販売店郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "販売店郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorPostNumber;

	/**
	 * 販売店住所
	 */
	@Size(max = 255)
	@Schema(description = "販売店住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorAddress;

}
