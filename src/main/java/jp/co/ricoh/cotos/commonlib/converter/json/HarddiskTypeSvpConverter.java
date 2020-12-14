package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.HarddiskTypeSvp;

@Converter(autoApply = true)
public class HarddiskTypeSvpConverter implements AttributeConverter<HarddiskTypeSvp, String> {
	@Override
	public String convertToDatabaseColumn(HarddiskTypeSvp harddiskTypeSvp) {
		if (harddiskTypeSvp == null)
			return null;
		return harddiskTypeSvp.toString();
	}

	@Override
	public HarddiskTypeSvp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return HarddiskTypeSvp.fromString(value);
	}
}
