package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * BPN SACM事前設定情報取得API レスポンスのDTO
 */

@Data
public class SacmPresetInfoDto {

	/**
	 * 手配業務リスト
	 */
	@Schema(description = "手配業務リスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<SacmArrangementWorkDto> arrangementWorkList;

}
