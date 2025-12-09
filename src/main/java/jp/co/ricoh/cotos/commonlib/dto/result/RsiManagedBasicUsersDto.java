package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * RSI 基本ユーザー情報APIの管理対象テナントの基本ユーザー情報検索API レスポンスのDTO
 */

@Data

public class RsiManagedBasicUsersDto {
	/**
	 * 取得結果1ページあたりの件数
	 */
	@Schema(description = "取得結果1ページあたりの件数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer itemsPerPage;

	/**
	 * 現在のページ番号
	 */
	@Schema(description = "現在のページ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer page;

	/**
	 * 総取得結果数
	 */
	@Schema(description = "総取得結果数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer totalResults;

	/**
	 * 管理対象テナントの基本ユーザー情報一覧
	 */
	@Schema(description = "お客様テナント情報一覧", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<RsiManagedBasicUsersResultsDto> results;

}

