package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文サービス固有情報DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OrderServiceInnerInfoDto {

	/**
	 * 固有項目１
	 */
	@Size(max = 255)
	@Schema(description = "固有項目１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item1;

	/**
	 * 固有項目２
	 */
	@Size(max = 255)
	@Schema(description = "固有項目２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item2;

	/**
	 * 固有項目３
	 */
	@Size(max = 255)
	@Schema(description = "固有項目３", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item3;

	/**
	 * 固有項目４
	 */
	@Size(max = 255)
	@Schema(description = "固有項目４", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item4;

	/**
	 * 固有項目５
	 */
	@Size(max = 255)
	@Schema(description = "固有項目５", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item5;

	/**
	 * 固有項目６
	 */
	@Size(max = 255)
	@Schema(description = "固有項目６", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item6;

	/**
	 * 固有項目７
	 */
	@Size(max = 255)
	@Schema(description = "固有項目７", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item7;

	/**
	 * 固有項目８
	 */
	@Size(max = 255)
	@Schema(description = "固有項目８", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item8;

	/**
	 * 固有項目９
	 */
	@Size(max = 255)
	@Schema(description = "固有項目９", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item9;

	/**
	 * 固有項目１０
	 */
	@Size(max = 255)
	@Schema(description = "固有項目１０", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item10;

	/**
	 * 固有項目１１
	 */
	@Size(max = 255)
	@Schema(description = "固有項目１１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item11;

	/**
	 * 固有項目１２
	 */
	@Size(max = 255)
	@Schema(description = "固有項目１２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item12;

	/**
	 * 固有項目１３
	 */
	@Size(max = 255)
	@Schema(description = "固有項目１３", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item13;

	/**
	 * 固有項目１４
	 */
	@Size(max = 255)
	@Schema(description = "固有項目１４", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item14;

	/**
	 * 固有項目１５
	 */
	@Size(max = 255)
	@Schema(description = "固有項目１５", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item15;

	/**
	 * 固有項目１６
	 */
	@Size(max = 255)
	@Schema(description = "固有項目１６", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item16;

	/**
	 * 固有項目１７
	 */
	@Size(max = 255)
	@Schema(description = "固有項目１７", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item17;

	/**
	 * 固有項目１８
	 */
	@Size(max = 255)
	@Schema(description = "固有項目１８", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item18;

	/**
	 * 固有項目１９
	 */
	@Size(max = 255)
	@Schema(description = "固有項目１９", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item19;

	/**
	 * 固有項目２０
	 */
	@Size(max = 255)
	@Schema(description = "固有項目２０", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item20;

	/**
	 * 固有項目２１
	 */
	@Size(max = 255)
	@Schema(description = "固有項目２１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item21;

	/**
	 * 固有項目２２
	 */
	@Size(max = 255)
	@Schema(description = "固有項目２２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item22;

	/**
	 * 固有項目２３
	 */
	@Size(max = 255)
	@Schema(description = "固有項目２３", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item23;

	/**
	 * 固有項目２４
	 */
	@Size(max = 255)
	@Schema(description = "固有項目２４", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item24;

	/**
	 * 固有項目２５
	 */
	@Size(max = 255)
	@Schema(description = "固有項目２５", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item25;

	/**
	 * 固有項目２６
	 */
	@Size(max = 255)
	@Schema(description = "固有項目２６", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item26;

	/**
	 * 固有項目２７
	 */
	@Size(max = 255)
	@Schema(description = "固有項目２７", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item27;

	/**
	 * 固有項目２８
	 */
	@Size(max = 255)
	@Schema(description = "固有項目２８", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item28;

	/**
	 * 固有項目２９
	 */
	@Size(max = 255)
	@Schema(description = "固有項目２９", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item29;

	/**
	 * 固有項目３０
	 */
	@Size(max = 255)
	@Schema(description = "固有項目３０", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String item30;

}
