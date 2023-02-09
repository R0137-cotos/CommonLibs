package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.HandoverFlg;

@Converter(autoApply = true)
public class HandoverFlgConverter implements AttributeConverter<HandoverFlg, String> {
	@Override
	public String convertToDatabaseColumn(HandoverFlg handoverFlg) {
		if (handoverFlg == null)
			return null;
		return handoverFlg.toString();
	}

	@Override
	public HandoverFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return HandoverFlg.fromString(value);
	}
}
