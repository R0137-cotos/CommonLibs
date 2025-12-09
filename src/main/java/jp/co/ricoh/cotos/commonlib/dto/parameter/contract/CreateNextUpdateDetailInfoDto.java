package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.List;

import jakarta.validation.Valid;

import lombok.Data;

/**
 * 次回更新明細登録Dto
 */
@Data
public class CreateNextUpdateDetailInfoDto {

	/**
	 * 次回更新明細登録Dtoリスト
	 */
	@Valid
	private List<NextUpdateDetailInfoDto> nextUpdateDetailInfoDtoList;

}
