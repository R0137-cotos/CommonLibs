package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ProductContractDto extends DtoBase {

	/**
	 * 商品マスタID
	 */
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
	 * 代表品種マスタID
	 */
	@Min(0)
	@Schema(description = "代表品種マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long repItemMasterId;

	/**
	 * サービス識別番号
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "サービス識別番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String serviceIdentNumber;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;
	
	/**
	 * 拡張項目繰返
	 */
	@Schema(description = "拡張項目繰返", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameterIterance;
}
