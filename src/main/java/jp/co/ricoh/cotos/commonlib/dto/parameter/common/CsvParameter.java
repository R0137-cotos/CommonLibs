package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import java.nio.charset.Charset;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CsvParameter {

	/**
	 * ヘッダー行の有無 ヘッダー行がないCSV情報を生成する
	 */
	@Builder.Default
	private boolean header = true;

	/**
	 * セパレーター文字 セパレーターを指定文字でCSV情報を生成する
	 */
	@Builder.Default
	private char separator = ',';

	/**
	 * 文字コード CSV情報の文字コードを指定して生成する
	 */
	@Builder.Default
	private Charset charset = Charset.forName("UTF-8");

	/**
	 * 改行コード 指定した改行コードでCSV情報を生成する
	 */
	@Builder.Default
	private String lineSeparator = "\n";

	/**
	 * 文字列をダブルクォートで囲むか否か 文字列項目をダブルクォートで囲んでCSV情報を生成する。
	 * trueの場合：ダブルクォートで囲む
	 * falseの場合：ダブルクォートで囲まない
	 * ただし、Jacksonの仕様により下記の値ではダブルクォートが付与される。
	 * ・ASCIIコード表の44以下の文字が含まれる場合(半角スペース、!など)
	 * ・25文字以上の場合
	 */
	@Builder.Default
	private boolean quote = true;

	/**
	 * 文字列に囲み文字を付与しないフラグ。
	 * trueの場合：ダブルクォートで囲まない
	 * falseの場合：quoteの設定を優先する
	 * quoteの制約にかかわらず、全ての文字列で囲み文字を付与しない。
	 * quote = true, withoutQuoteChar = true は両立できず、IllegalArgumentExceptionが返されるので注意する
	 */
	@Builder.Default
	private boolean withoutQuoteChar = false;

	/**
	 * NULL項目の文字列 NULL項目を指定した文字列でCSV情報を生成する ダブルクォートで囲むか否かのパラメーター有効化対象外
	 */
	@Builder.Default
	private String nullValueString = "null";

	/**
	 * BOM付き設定をするか判定するフラグ
	 * trueの場合：BOM付き
	 * falseの場合：BOMなし
	 */
	@Builder.Default
	private boolean bomSettingFlg = false;
}
