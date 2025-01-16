package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.EntryDiv;

@Converter(autoApply = true)
public class EntryDivConverter implements AttributeConverter<EntryDiv, String> {
	@Override
	public String convertToDatabaseColumn(EntryDiv entryDiv) {
		if (entryDiv == null)
			return null;
		return entryDiv.toString();
	}

	@Override
	public EntryDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return EntryDiv.fromString(value);
	}
}