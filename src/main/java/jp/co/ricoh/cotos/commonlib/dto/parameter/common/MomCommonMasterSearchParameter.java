package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import java.util.List;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MomCommonMasterSearchParameter {

	/**
	 * 汎用マスタIDリスト
	 */
	@Parameter(description = "汎用マスタIDリスト")
	private List<String> commonArticleCdList;

	/**
	 * 空行追加フラグ
	 */
	@Parameter(description = "空行追加フラグ", schema = @Schema(allowableValues = "true, false"))
	private boolean addBlankRowFlg = false;
}
