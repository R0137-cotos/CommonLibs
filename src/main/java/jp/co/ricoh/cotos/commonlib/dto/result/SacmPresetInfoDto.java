package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import lombok.Data;

/**
 * BPN SACM事前設定情報取得API レスポンスのDTO
 */

@Data
public class SacmPresetInfoDto {

	/**
	 * 手配業務リスト
	 */
	private List<SacmArrangementWorkDto> arrangementWorkList;

}
