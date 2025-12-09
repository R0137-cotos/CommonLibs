package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約一覧情報詳細取得API用DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ContractListDetailInfoGetBplatsDto {

	/**
	 * 契約情報取得結果リスト
	 */
	@JsonProperty("contractInfoList")
	@Schema(description = "契約明細", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractForFindAllDetailsBplatsDto> contractForFindAllDetailsBplatsDtoList;

	/**
	 * ページング情報
	 */
	@JsonProperty("paging")
	@Schema(description = "ページング情報", requiredMode = Schema.RequiredMode.REQUIRED)
	private PagingDto pagingDto;
}
