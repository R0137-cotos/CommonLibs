package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRtor.OptionRtorDiv;

@Converter(autoApply = true)
public class OptionRtorDivConverter implements AttributeConverter<OptionRtorDiv, String> {

	@Override
	public String convertToDatabaseColumn(OptionRtorDiv optionRtorDiv) {
		if (optionRtorDiv == null)
			return null;
		return optionRtorDiv.toString();
	}

	@Override
	public OptionRtorDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OptionRtorDiv.fromString(value); // IllegalArgumentExceptionはSaleDiv.fromString側で投げている
	}
}
