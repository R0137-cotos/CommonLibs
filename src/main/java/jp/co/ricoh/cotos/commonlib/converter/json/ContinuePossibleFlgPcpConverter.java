package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.ContinuePossibleFlgPcp;

@Converter(autoApply = true)
public class ContinuePossibleFlgPcpConverter implements AttributeConverter<ContinuePossibleFlgPcp, String> {
	@Override
	public String convertToDatabaseColumn(ContinuePossibleFlgPcp continuePossibleFlgPcp) {
		if (continuePossibleFlgPcp == null)
			return null;
		return continuePossibleFlgPcp.toString();
	}

	@Override
	public ContinuePossibleFlgPcp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ContinuePossibleFlgPcp.fromString(value);
	}
}
