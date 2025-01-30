package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * ARCS期間売保守データのCSVを出力する条件のキー項目クラスを表します。
 */
@Data
public class ExternalCooperationCsvOutputParameterDto {

	/**
	 * ファイル種別管理マスタID
	 */
	@ApiParam(value = "ファイル種別管理マスタID", required = false)
	@ApiModelProperty(value = "ファイル種別管理マスタID", required = false, position = 1)
	private long fileKindManagementMasterId;

	/**
	 * 商品種類区分
	 */
	@ApiParam(value = "商品種類区分", required = false)
	@ApiModelProperty(value = "商品種類区分", required = false, position = 2)
	private String productClassDiv;

	/**
	 * 出力日
	 */
	@JsonFormat(pattern = "yyyyMMdd", timezone = "Asia/Tokyo")
	@ApiParam(value = "出力日", required = false)
	@ApiModelProperty(value = "出力日", required = false, position = 3)
	private Date outputDate;

	/**
	 * 商品マスタID
	 */
	@ApiParam(value = "商品マスタID", required = false)
	@ApiModelProperty(value = "商品マスタID", required = false, position = 4)
	private long productMasterId;

}
