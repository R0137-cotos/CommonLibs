package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.MigrationDiv;

@Converter(autoApply = true)
public class MigrationDivConverter implements AttributeConverter<MigrationDiv, String> {
	@Override
	public String convertToDatabaseColumn(MigrationDiv migrationDiv) {
		if (migrationDiv == null)
			return null;
		return migrationDiv.toString();
	}

	@Override
	public MigrationDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MigrationDiv.fromString(value);
	}
}
