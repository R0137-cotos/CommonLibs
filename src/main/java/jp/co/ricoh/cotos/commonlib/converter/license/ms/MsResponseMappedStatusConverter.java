package jp.co.ricoh.cotos.commonlib.converter.license.ms;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.AbstractMsResponseWork.MsResponseMappedStatus;

@Converter(autoApply = true)
public class MsResponseMappedStatusConverter implements AttributeConverter<MsResponseMappedStatus, String> {
	@Override
	public String convertToDatabaseColumn(MsResponseMappedStatus msResponseMappedStatus) {
		if (msResponseMappedStatus == null)
			return null;
		return msResponseMappedStatus.toString();
	}

	@Override
	public MsResponseMappedStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MsResponseMappedStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
