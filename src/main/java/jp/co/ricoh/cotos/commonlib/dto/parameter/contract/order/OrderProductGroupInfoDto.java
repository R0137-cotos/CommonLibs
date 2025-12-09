package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文商品グループ情報DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OrderProductGroupInfoDto {

	/**
	 * 商品グループコード
	 */
	@Size(max = 255)
	@Schema(description = "商品グループコード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String productGroupCd;

	/**
	 * 商品グループ名
	 */
	@Size(max = 255)
	@Schema(description = "商品グループ名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productGroupName;

}
