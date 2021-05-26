package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.PenaltyDetailContract.SalesToType;

@Converter(autoApply = true)
public class SalesToTypeConverter implements AttributeConverter<SalesToType, String> {

	@Override
	public String convertToDatabaseColumn(SalesToType salesToType) {
		if (salesToType == null)
			return null;
		return salesToType.toString();
	}

	@Override
	public SalesToType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return SalesToType.fromString(value); // IllegalArgumentExceptionはSalesToType.fromString側で投げている
	}

}
