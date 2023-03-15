package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * BPN SACM設定変更情報取得API レスポンスのSACM手配業務のDTO
 */

@Data
public class SacmChangeInfoArrangementWorkDto {

	/**
	 * 手配業務ID
	 */
	@ApiModelProperty(value = "手配業務ID", required = false, position = 1)
	private String arrangementWorkId;

	/**
	 * 文書番号
	 */
	@ApiModelProperty(value = "文書番号", required = false, position = 2)
	private String estimationNumber;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = false, position = 3)
	private String rjManageNumber;

	/**
	 * 手配業務明細リスト
	 */
	@ApiModelProperty(value = "手配業務明細リスト", required = false, position = 4)
	private List<ArrangementWorkDetail> arrangementWorkDetailList;

}