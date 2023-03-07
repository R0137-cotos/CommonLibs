package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * BPN SACM事前設定情報取得API レスポンスのDTO
 */

@Data
public class SacmPresetInfoDto {

	/**
	 * 手配業務リスト
	 */
	@ApiModelProperty(value = "手配業務リスト", required = false, position = 1)
	private List<SacmArrangementWorkDto> arrangementWorkList;

}
