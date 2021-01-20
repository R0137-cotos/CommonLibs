package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import java.util.List;

import lombok.Data;

@Data
public class CreateZipParameter {

	/**
	 * Zip対象パスリスト
	 */
	private List<String> inputPathList;

	/**
	 * 出力Zipファイルパス
	 */
	private String outputPath;

	/**
	 * 暗号化する際のパスワード(暗号化が必要なければ空文字をセットする)
	 */
	private String password;

	/**
	 * ファイル名文字コード
	 */
	private String fileNameCharset;

	/**
	 * 出力後ソースファイル削除フラグ
	 */
	private boolean inputFileDeleteFlg;
}
