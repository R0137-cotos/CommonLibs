package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 履歴詳細情報をリスト取得するためのDtoです。<br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Data
public class HistoryDetailDto {

	/**
	 * 品種名
	 */
	@ApiModelProperty(value = "品種名", required = false, position = 1, allowableValues = "range[0,255]")
	private String itemContractName;

	/**
	 * リコー品種コード
	 */
	@ApiModelProperty(value = "リコー品種コード", required = false, position = 2, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 費用種別
	 */
	@ApiModelProperty(value = "費用種別", required = false, position = 3, allowableValues = "range[0,255]")
	private String costName;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量", required = false, position = 4, allowableValues = "range[0,255]")
	private String quantity;
}
