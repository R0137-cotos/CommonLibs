package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.MakerCodePcp;

@Converter(autoApply = true)
public class MakerCodePcpConverter implements AttributeConverter<MakerCodePcp, String> {
	@Override
	public String convertToDatabaseColumn(MakerCodePcp makerCodePcp) {
		if (makerCodePcp == null)
			return null;
		return makerCodePcp.toString();
	}

	@Override
	public MakerCodePcp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MakerCodePcp.fromString(value);
	}
}
