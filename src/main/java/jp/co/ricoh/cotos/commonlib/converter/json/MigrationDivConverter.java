package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
