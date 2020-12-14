package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
