package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MakerCodeSvp;

@Converter(autoApply = true)
public class MakerCodeSvpConverter implements AttributeConverter<MakerCodeSvp, String> {
	@Override
	public String convertToDatabaseColumn(MakerCodeSvp makerCodeSvp) {
		if (makerCodeSvp == null)
			return null;
		return makerCodeSvp.toString();
	}

	@Override
	public MakerCodeSvp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MakerCodeSvp.fromString(value);
	}
}
