package jp.co.ricoh.cotos.commonlib.dto.json.master;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 帳票ページ管理マスタ 拡張項目DTO
 *
 * ページの出力条件を指定する
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportPageMasterExtendsParameterDto {

	/**
	 * 商流区分リスト
	 */
	private List<String> commercialFlowDivList;
}
