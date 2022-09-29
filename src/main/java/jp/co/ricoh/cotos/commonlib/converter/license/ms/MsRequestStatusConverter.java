package jp.co.ricoh.cotos.commonlib.converter.license.ms;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.AbstractMsRequestWork.MsRequestStatus;

@Converter(autoApply = true)
public class MsRequestStatusConverter implements AttributeConverter<MsRequestStatus, String> {
	@Override
	public String convertToDatabaseColumn(MsRequestStatus msRequestStatus) {
		if (msRequestStatus == null)
			return null;
		return msRequestStatus.toString();
	}

	@Override
	public MsRequestStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MsRequestStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
