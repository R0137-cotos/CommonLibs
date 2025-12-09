package jp.co.ricoh.cotos.commonlib.logic.csv;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * カスタムデシリアライザの設定
 */
public class EmptyToNullDeserializer extends JsonDeserializer<String> {

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String value = p.getText();
		return value.isEmpty() ? null : value;
	}
}