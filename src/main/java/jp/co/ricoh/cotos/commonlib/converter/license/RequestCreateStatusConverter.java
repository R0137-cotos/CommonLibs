package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.RequestCreateStatus;

public class RequestCreateStatusConverter implements AttributeConverter<RequestCreateStatus, String> {

	@Override
	public String convertToDatabaseColumn(RequestCreateStatus receptionStatusFlg) {
		if (receptionStatusFlg == null)
			return null;
		return receptionStatusFlg.toString();
	}

	@Override
	public RequestCreateStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return RequestCreateStatus.fromString(value); // IllegalArgumentExceptionはAllocationDiv.fromString側で投げている
	}
}
