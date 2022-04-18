package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.NewExistingAccountType;

@Converter(autoApply = true)
public class NewExistingAccountTypeConverter implements AttributeConverter<NewExistingAccountType, String> {
	@Override
	public String convertToDatabaseColumn(NewExistingAccountType newExistingAccountTypeMss) {
		if (newExistingAccountTypeMss == null)
			return null;
		return newExistingAccountTypeMss.toString();
	}

	@Override
	public NewExistingAccountType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return NewExistingAccountType.fromString(value);
	}
}
