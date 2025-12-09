package jp.co.ricoh.cotos.commonlib.logic.xml;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.xml.bind.JAXB;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;

/**
 * XML共通クラス
 */
@Component
public class XmlUtil {

	/**
	 * UTF8用BOMデータ（以下の実装より設定）
	 * @see jp.co.ricoh.cotos.commonlib.logic.csv.CsvUtil#createCsvData(java.util.List, jp.co.ricoh.cotos.commonlib.dto.parameter.common.CsvParameter)
	 */
	private static final byte[] BOM_BYTES = new byte[] { (byte) 0xef, (byte) 0xbb, (byte) 0xbf };

	@Autowired
	CheckUtil checkUtil;

	/**
	 * xml文字列を対象DTOクラスに変換する
	 * @param xmlText xml内容
	 * @param obj 変換対象クラス
	 * @return
	 */
	public <T> T convertToDto(String xmlText, Class<T> obj) {
		// xmlテキストが設定されていない場合
		if (StringUtils.isBlank(xmlText)) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "XML文字列" }));
		}

		// 変換対象クラスが設定されていない場合
		if (null == obj) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "変換対象クラス" }));
		}

		// JsonUtilと異なり「JAXB#unmarshal」はthrowの定義がないため、try-catchは不要
		return JAXB.unmarshal(new StringReader(xmlText), obj);
	}

	/**
	 * xmlファイルを対象DTOクラスに変換する
	 * @param xmlPath xmlファイルのpath
	 * @param obj 変換対象クラス
	 * @param charset xmlファイルの文字コード
	 * @return
	 * @throws IOException ファイル読み込み失敗時
	 */
	public <T> T convertToDto(Path xmlPath, Class<T> obj, Charset charset) throws IOException {
		// 文字コードの指定がない場合
		if (null == charset) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "文字コード" }));
		}

		byte[] xmlBytes = Files.readAllBytes(xmlPath);
		String xmlText = new String(xmlBytes, charset);

		// JsonUtilと異なり「JAXB#unmarshal」はthrowの定義がないため、try-catchは不要
		return convertToDto(xmlText, obj);
	}

	/**
	 * UTF-8（BOM付き/BOM無し）で記載されたxmlファイルを対象DTOクラスに変換する
	 * @param xmlPath xmlファイルのpath
	 * @param obj 変換対象クラス
	 * @param charset xmlファイルの文字コード
	 * @return
	 * @throws IOException ファイル読み込み失敗時
	 */
	public <T> T convertToDtoForUtf8(Path xmlPath, Class<T> obj) throws IOException {
		// 通常xmlは1行目に文字コードを指定するため、BOMは不要なはずだが、実際にBOM付きのファイルが存在したため
		// 本メソッドを使用して読み込むこととした。
		byte[] xmlBytes = Files.readAllBytes(xmlPath);

		String xmlText;
		if (isUtf8Bom(xmlBytes)) {
			xmlText = removeBomAndCreateString(xmlBytes);
		} else {
			xmlText = new String(xmlBytes, StandardCharsets.UTF_8);
		}

		// JsonUtilと異なり「JAXB#unmarshal」はthrowの定義がないため、try-catchは不要
		return convertToDto(xmlText, obj);
	}

	/**
	 * UTF-8用BOMが入っているかを確認する
	 * @param checkBytes
	 * @return
	 */
	private boolean isUtf8Bom(byte[] checkBytes) {
		return checkBytes[0] == BOM_BYTES[0] && checkBytes[1] == BOM_BYTES[1] && checkBytes[2] == BOM_BYTES[2];
	}

	/**
	 * UTF-8用BOMを削除した文字列を返却する
	 * @param bytesWithBom 対象文字列のbyte配列
	 * @return
	 */
	private String removeBomAndCreateString(byte[] bytesWithBom) {
		// 先頭3バイトを省いたbyte配列を作成する
		byte[] noBomBytes = Arrays.copyOfRange(bytesWithBom, 3, bytesWithBom.length);

		return new String(noBomBytes, StandardCharsets.UTF_8);
	}

	/*
	 * 以下はStackOverFlowに記載されていた作業方法。
	 * コード的にはわかりやすいが、作業内容的にわかりづらいため不採用とした。
	 * （ちなみに採用した方と↓の実行結果をequalsで比較し、どちらも一致していることを確認済）
	 * 今後のためにも念のため残しておく
	 * https://stackoverflow.com/questions/21891578/removing-bom-characters-using-java
	private String removeBomAndCreateString2(byte[] bytesWithBom) {
		String strWithBom = new String(bytesWithBom, StandardCharsets.UTF_8);
	
		return strWithBom.substring(1);
	}
	*/
}
