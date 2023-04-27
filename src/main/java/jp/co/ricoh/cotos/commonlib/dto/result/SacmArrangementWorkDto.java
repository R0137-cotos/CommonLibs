package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * BPN SACM事前設定情報取得API レスポンスのSACM手配業務のDTO
 */

@Data
public class SacmArrangementWorkDto {

	/**
	 * 手配業務ID
	 */
	@ApiModelProperty(value = "手配業務ID", required = false, position = 1)
	private String arrangementWorkId;

	/**
	 * 文書番号
	 */
	@ApiModelProperty(value = "文書番号", required = false, position = 2)
	private String contractNumber;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = false, position = 3)
	private String rjManageNumber;

	/**
	 * CSV取込日
	 */
	@ApiModelProperty(value = "CSV取込日", required = false, position = 4)
	private String csvImportDate;

	/**
	 * コンフィグファイル名
	 */
	@ApiModelProperty(value = "コンフィグファイル名", required = false, position = 5)
	private String configFileName;

	/**
	 * LaITコンフィグデータリスト
	 */
	@ApiModelProperty(value = "LaITコンフィグデータリスト", required = false, position = 6)
	private List<LaitConfigData> laitConfigDataList;

}