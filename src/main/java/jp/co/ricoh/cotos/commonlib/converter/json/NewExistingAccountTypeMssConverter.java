package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMss.NewExistingAccountTypeMss;

@Converter(autoApply = true)
public class NewExistingAccountTypeMssConverter implements AttributeConverter<NewExistingAccountTypeMss, String> {
	@Override
	public String convertToDatabaseColumn(NewExistingAccountTypeMss newExistingAccountTypeMss) {
		if (newExistingAccountTypeMss == null)
			return null;
		return newExistingAccountTypeMss.toString();
	}

	@Override
	public NewExistingAccountTypeMss convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return NewExistingAccountTypeMss.fromString(value);
	}
}
