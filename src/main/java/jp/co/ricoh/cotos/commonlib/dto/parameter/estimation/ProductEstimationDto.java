package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

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
public class ProductEstimationDto extends DtoBase {

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@Schema(description = "商品マスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 商品名
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "商品名", required = true, allowableValues = "range[0,255]")
	private String productEstimationName;

	/**
	 * 代表品種マスタID
	 */
	@Min(0)
	@Schema(description = "代表品種マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long repItemMasterId;

	/**
	 * サービス識別番号
	 */
	@Size(max = 255)
	@Schema(description = "サービス識別番号", required = false, allowableValues = "range[0,255]")
	private String serviceIdentNumber;

	/**
	 * 拡張項目
	 */
	@Lob
	@Schema(description = "拡張項目", required = false)
	private String extendsParameter;

	/**
	 * 拡張項目繰返
	 */
	@Schema(description = "拡張項目繰返", required = false)
	@Lob
	private String extendsParameterIterance;
}
