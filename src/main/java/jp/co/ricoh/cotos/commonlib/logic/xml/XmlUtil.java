package jp.co.ricoh.cotos.commonlib.logic.xml;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.bind.JAXB;

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
}
