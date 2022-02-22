package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約一覧情報詳細取得APIページング情報格納用DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PagingDto {

	/**
	 * 対象データの総件数
	 */
	@ApiModelProperty(value = "対象データの総件数", required = true, position = 1, allowableValues = "range[0,99999]")
	private Integer totalNum;

	/**
	 * 取得開始行
	 */
	@ApiModelProperty(value = "取得開始行", required = true, position = 2, allowableValues = "range[0,99999]")
	private Integer startLine;

	/**
	 * 取得行数
	 */
	@ApiModelProperty(value = "取得行数", required = true, position = 3, allowableValues = "range[0,99999]")
	private Integer offset;
}
