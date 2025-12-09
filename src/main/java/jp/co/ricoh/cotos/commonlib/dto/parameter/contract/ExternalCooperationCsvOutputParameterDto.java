package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ARCS期間売保守データのCSVを出力する条件のキー項目クラスを表します。
 */
@Data
public class ExternalCooperationCsvOutputParameterDto {

	/**
	 * ファイル種別管理マスタID
	 */
	@Parameter(description = "ファイル種別管理マスタID")
	@Schema(description = "ファイル種別管理マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private long fileKindManagementMasterId;

	/**
	 * 商品種類区分
	 */
	@Parameter(description = "商品種類区分")
	@Schema(description = "商品種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String productClassDiv;

	/**
	 * 出力日
	 */
	@JsonFormat(pattern = "yyyyMMdd", timezone = "Asia/Tokyo")
	@Parameter(description = "出力日")
	@Schema(description = "出力日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date outputDate;

	/**
	 * 商品マスタID
	 */
	@Parameter(description = "商品マスタID")
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private long productMasterId;

}
