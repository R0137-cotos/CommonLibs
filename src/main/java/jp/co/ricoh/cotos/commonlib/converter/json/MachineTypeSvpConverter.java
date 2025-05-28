package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MachineTypeSvp;

@Converter(autoApply = true)
public class MachineTypeSvpConverter implements AttributeConverter<MachineTypeSvp, String> {
	@Override
	public String convertToDatabaseColumn(MachineTypeSvp machineTypeSvp) {
		if (machineTypeSvp == null)
			return null;
		return machineTypeSvp.toString();
	}

	@Override
	public MachineTypeSvp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MachineTypeSvp.fromString(value);
	}
}
