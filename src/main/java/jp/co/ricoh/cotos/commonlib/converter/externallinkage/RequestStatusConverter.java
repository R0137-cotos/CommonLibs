package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.LaitLinkageInfo.RequestStatus;

@Converter(autoApply = true)
public class RequestStatusConverter implements AttributeConverter<RequestStatus, String> {

	@Override
	public String convertToDatabaseColumn(RequestStatus RequestStatus) {
		if (RequestStatus == null)
			return null;
		return RequestStatus.toString();
	}

	@Override
	public RequestStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return RequestStatus.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
