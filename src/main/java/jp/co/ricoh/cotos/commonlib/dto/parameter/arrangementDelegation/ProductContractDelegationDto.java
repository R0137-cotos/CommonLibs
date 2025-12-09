package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductContractDelegationDto {

	/**
	 * ID
	 */
	@NotNull
	@Min(0)
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * 商品マスタID
	 */
	@NotNull
	@Min(0)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 商品名
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "商品名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String productContractName;

	/**
	 * テナントID
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "テナントID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String tenantId;

	/**
	 * ZuoraアカウントId
	 */
	@Size(max = 255)
	@Schema(description = "ZuoraアカウントId", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String zuoraAccountId;

	/**
	 * ユーザID
	 */
	@Size(max = 255)
	@Schema(description = "ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * サブスクリプション番号
	 */
	@Size(max = 255)
	@Schema(description = "サブスクリプション番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subscriptionNumber;

}
