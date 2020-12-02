package jp.co.ricoh.cotos.commonlib.logic.json;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON共通クラス
 */
@Component
public class JsonUtil {

	@Autowired
	ObjectMapper mapper;

	/**
	 *
	 * JSONテキストをオブジェクトに変換します。
	 *
	 * <pre>
	 * Exceptionが発生した場合、Nullを返却します。
	 * エラー処理をしたい場合は、呼び出し元でNull判定を行ってください。
	 * </pre>
	 *
	 * @param jsonText
	 * @param obj
	 * @return <T> T
	 */
	public <T> T convertToDto(String jsonText, Class<T> obj) {

		// JSONテキストが設定されていない場合、Nullを返却
		if (StringUtils.isBlank(jsonText)) {
			return null;
		}

		// オブジェクトクラスが設定されていない場合、Nullを返却
		if (null == obj) {
			return null;
		}

		try {
			// JSONテキストをオブジェクトに変換
			return mapper.readValue(jsonText, obj);
		} catch (IOException ioe) {
			// エラーの場合、Nullを返却
			return null;
		}
	}

	/**
	 * オブジェクトからJSONテキストに変換します。
	 *
	 *<pre>
	 * Exceptionが発生した場合、Nullを返却します。
	 * エラー処理をしたい場合は、呼び出し元でNull判定を行ってください。
	 * </pre>
	 *
	 * @param obj
	 * @return String
	 */
	public String convertToStr(Object obj) {

		// オブジェクトが設定されていない場合、Nullを返却
		if (null == obj) {
			return null;
		}

		try {
			// オブジェクトをJSONテキストに変換
			return mapper.writeValueAsString(obj);
		} catch (IOException ioe) {
			// エラーの場合、Nullを返却
			return null;
		}
	}
}
