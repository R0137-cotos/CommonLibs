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
	 * @param jsonText
	 * @param obj
	 * @return <T> T
	 * @throws IOException
	 */
	public <T> T convertToDto(String jsonText, Class<T> obj) throws IOException {

		// JSONテキストが設定されていない場合、Nullを返却
		if (StringUtils.isBlank(jsonText)) {
			return null;
		}

		// オブジェクトクラスが設定されていない場合、Nullを返却
		if (null == obj) {
			return null;
		}

		// JSONテキストをオブジェクトに変換
		return mapper.readValue(jsonText, obj);
	}

	/**
	 * オブジェクトからJSONテキストに変換します。
	 *
	 * @param obj
	 * @return String
	 * @throws IOException
	 */
	public String convertToStr(Object obj) throws IOException {

		// オブジェクトが設定されていない場合、Nullを返却
		if (null == obj) {
			return null;
		}

		// オブジェクトをJSONテキストに変換
			return mapper.writeValueAsString(obj);
	}
}
