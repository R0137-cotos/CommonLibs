package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ItemContractDelegationDto {

	/**
	 * ID
	 */
	@NotNull
	@Min(0)
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * 品種マスタID
	 */
	@NotNull
	@Min(0)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 商品マスタID
	 */
	@NotNull
	@Min(0)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 品種名
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String itemContractName;

	/**
	 * リコー品種コード
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

}
