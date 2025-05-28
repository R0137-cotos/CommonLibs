package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.ParameterEstimationEquipmentDeleteFlgPcp;

@Converter(autoApply = true)
public class ParameterEstimationEquipmentDeleteFlgPcpConverter implements AttributeConverter<ParameterEstimationEquipmentDeleteFlgPcp, String> {
	@Override
	public String convertToDatabaseColumn(ParameterEstimationEquipmentDeleteFlgPcp parameterEstimationEquipmentDeleteFlgPcp) {
		if (parameterEstimationEquipmentDeleteFlgPcp == null)
			return null;
		return parameterEstimationEquipmentDeleteFlgPcp.toString();
	}

	@Override
	public ParameterEstimationEquipmentDeleteFlgPcp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ParameterEstimationEquipmentDeleteFlgPcp.fromString(value);
	}
}
