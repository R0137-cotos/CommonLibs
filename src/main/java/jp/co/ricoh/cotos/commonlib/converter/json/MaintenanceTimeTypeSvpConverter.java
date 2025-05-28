package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MaintenanceTimeTypeSvp;

@Converter(autoApply = true)
public class MaintenanceTimeTypeSvpConverter implements AttributeConverter<MaintenanceTimeTypeSvp, String> {
	@Override
	public String convertToDatabaseColumn(MaintenanceTimeTypeSvp maintenanceTimeTypeSvp) {
		if (maintenanceTimeTypeSvp == null)
			return null;
		return maintenanceTimeTypeSvp.toString();
	}

	@Override
	public MaintenanceTimeTypeSvp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MaintenanceTimeTypeSvp.fromString(value);
	}
}
