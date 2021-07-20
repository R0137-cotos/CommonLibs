package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRns.NewExistingAccountTypeRns;

@Converter(autoApply = true)
public class NewExistingAccountTypeRnsConverter implements AttributeConverter<NewExistingAccountTypeRns, String> {
	@Override
	public String convertToDatabaseColumn(NewExistingAccountTypeRns newExistingAccountTypeRns) {
		if (newExistingAccountTypeRns == null)
			return null;
		return newExistingAccountTypeRns.toString();
	}

	@Override
	public NewExistingAccountTypeRns convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return NewExistingAccountTypeRns.fromString(value);
	}
}
