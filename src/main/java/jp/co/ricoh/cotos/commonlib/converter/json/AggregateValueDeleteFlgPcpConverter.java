package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.AggregateValueDeleteFlgPcp;

@Converter(autoApply = true)
public class AggregateValueDeleteFlgPcpConverter implements AttributeConverter<AggregateValueDeleteFlgPcp, String> {
	@Override
	public String convertToDatabaseColumn(AggregateValueDeleteFlgPcp aggregateValueDeleteFlgPcp) {
		if (aggregateValueDeleteFlgPcp == null)
			return null;
		return aggregateValueDeleteFlgPcp.toString();
	}

	@Override
	public AggregateValueDeleteFlgPcp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return AggregateValueDeleteFlgPcp.fromString(value);
	}
}
