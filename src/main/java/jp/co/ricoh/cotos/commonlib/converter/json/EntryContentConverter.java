package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.EntryContent;

@Converter(autoApply = true)
public class EntryContentConverter implements AttributeConverter<EntryContent, String> {
	@Override
	public String convertToDatabaseColumn(EntryContent entryContent) {
		if (entryContent == null)
			return null;
		return entryContent.toString();
	}

	@Override
	public EntryContent convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return EntryContent.fromString(value);
	}
}