package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.InitialRunningDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ItemDetailContractDto extends DtoBase {

	/**
	 * 原価
	 */
	@Digits(integer = 19, fraction = 2)
	@DecimalMin("-9999999999999999999.99")
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "原価", required = false, allowableValues = "range[-9999999999999999999.99,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 振替先課所コード
	 */
	@Size(max = 255)
	@Schema(description = "振替先課所コード", required = false, allowableValues = "range[0,255]")
	private String transToServiceOrgCode;

	/**
	 * 振替先課所名
	 */
	@Size(max = 255)
	@Schema(description = "振替先課所名", required = false, allowableValues = "range[0,255]")
	private String transToServiceOrgName;

	/**
	 * イニシャル/ランニング区分
	 */
	@Schema(description = "イニシャル/ランニング区分", required = false, allowableValues = "イニシャル(\"1\"), ランニング(\"2\"), 期間売(\"3\")")
	private InitialRunningDiv initialRunningDiv;

	/**
	 * 品種振替構成マスタID
	 */
	@Min(0)
	@Schema(description = "品種振替構成マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long itemTransCompMasterId;

	/**
	 * 一括登録フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "一括登録フラグ", required = false, allowableValues = "range[0,9]")
	private Integer batchImportFlg;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "数量", required = false, allowableValues = "range[-99999,99999]")
	private Integer quantity;
}
