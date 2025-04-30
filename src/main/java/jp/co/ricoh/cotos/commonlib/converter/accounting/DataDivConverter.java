package jp.co.ricoh.cotos.commonlib.converter.accounting;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.common.OsoRequestDataAbstractEntity.DataDiv;

@Converter(autoApply = true)
public class DataDivConverter implements AttributeConverter<DataDiv, String> {
	@Override
	public String convertToDatabaseColumn(DataDiv dataDiv) {
		if (dataDiv == null)
			return null;
		return dataDiv.toString();
	}

	@Override
	public DataDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DataDiv.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}