package jp.co.ricoh.cotos.commonlib.converter.license.cas;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork.TmRequestStatus;

@Converter(autoApply = true)
public class TmRequestStatusConverter implements AttributeConverter<TmRequestStatus, String> {
	@Override
	public String convertToDatabaseColumn(TmRequestStatus tmRequestStatus) {
		if (tmRequestStatus == null)
			return null;
		return tmRequestStatus.toString();
	}

	@Override
	public TmRequestStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return TmRequestStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
