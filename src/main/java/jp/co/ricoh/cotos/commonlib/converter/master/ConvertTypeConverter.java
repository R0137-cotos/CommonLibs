package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.MailConvertValueMaster.ConvertType;

@Converter(autoApply = true)
public class ConvertTypeConverter implements AttributeConverter<ConvertType, String> {

	@Override
	public String convertToDatabaseColumn(ConvertType convertType) {
		if (convertType == null)
			return null;
		return convertType.toString();
	}

	@Override
	public ConvertType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ConvertType.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}