package jp.co.ricoh.cotos.commonlib.logic.json;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * JSON共通クラス
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
public class JsonUtil {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	CheckUtil checkUtil;

	/**
	 *
	 * JSON文字列をオブジェクトに変換します。
	 *
	 * @param jsonText String JSON文字列
	 * @param obj Class<t> Object Class
	 * @return <T> T
	 */
	public <T> T convertToDto(String jsonText, Class<T> obj) {

		// JSONテキストが設定されていない場合
		if (StringUtils.isBlank(jsonText)) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "JSON文字列" }));
		}

		// オブジェクトクラスが設定されていない場合
		if (null == obj) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "Object" }));
		}

		try {
			// JSON文字列をオブジェクトに変換
			return mapper.readValue(jsonText, obj);
		} catch (IOException e) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "JsonConvertFormTextToObjectError"));
		}
	}

	/**
	 * オブジェクトからJSON文字列に変換します。
	 *
	 * @param obj Object
	 * @return String
	 */
	public String convertToStr(Object obj) {

		// オブジェクトが設定されていない場合
		if (null == obj) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "Object" }));
		}

		try {
			// オブジェクトをJSON文字列に変換
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "JsonConvertFromObjectToTextError"));
		}
	}
}
