package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * RSI 基本ユーザー情報APIの管理対象テナントの基本ユーザー情報検索API レスポンスのDTO
 */

@Data

public class RsiManagedBasicUsersDto {
	/**
	 * 取得結果1ページあたりの件数
	 */
	@ApiModelProperty(value = "取得結果1ページあたりの件数", required = false, position = 1)
	private Integer itemsPerPage;

	/**
	 * 現在のページ番号
	 */
	@ApiModelProperty(value = "現在のページ番号", required = false, position = 2)
	private Integer page;

	/**
	 * 総取得結果数
	 */
	@ApiModelProperty(value = "総取得結果数", required = false, position = 3)
	private Integer totalResults;

	/**
	 * 管理対象テナントの基本ユーザー情報一覧
	 */
	@ApiModelProperty(value = "お客様テナント情報一覧", required = false, position = 4)
	private List<RsiManagedBasicUsersResultsDto> results;

}

