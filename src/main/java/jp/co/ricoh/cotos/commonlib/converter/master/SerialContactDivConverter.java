package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ProductMaster.SerialContactDiv;

@Converter(autoApply = true)
public class SerialContactDivConverter implements AttributeConverter<SerialContactDiv, String> {

	@Override
	public String convertToDatabaseColumn(SerialContactDiv serialContactDiv) {
		if (serialContactDiv == null)
			return null;
		return serialContactDiv.toString();
	}

	@Override
	public SerialContactDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return SerialContactDiv.fromString(value); //IllegalArgumentExceptionはSerialContactDiv.fromString側で投げている
	}
}