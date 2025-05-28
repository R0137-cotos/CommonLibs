package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.List;

import javax.validation.Valid;

import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;

/**
 * 次回更新明細登録Dto
 */
@Data
public class CreateNextUpdateDetailInfoDto extends DtoBase {

	/**
	 * 次回更新明細登録Dtoリスト
	 */
	@Valid
	private List<CreateNextUpdateDetailInfoDto> nextUpdateDetailInfoDtoList;

}
