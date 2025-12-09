package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "対象データの総件数", required = true, allowableValues = "range[0,99999]")
	private Integer totalNum;

	/**
	 * 取得開始行
	 */
	@Schema(description = "取得開始行", required = true, allowableValues = "range[0,99999]")
	private Integer startLine;

	/**
	 * 取得行数
	 */
	@Schema(description = "取得行数", required = true, allowableValues = "range[0,99999]")
	private Integer offset;
}
