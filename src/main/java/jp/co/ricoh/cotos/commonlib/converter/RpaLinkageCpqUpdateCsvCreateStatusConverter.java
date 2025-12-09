package jp.co.ricoh.cotos.commonlib.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.RpaLinkageCpqUpdateCsvCreateStatus;

@Converter(autoApply = true)
public class RpaLinkageCpqUpdateCsvCreateStatusConverter implements AttributeConverter<RpaLinkageCpqUpdateCsvCreateStatus, String> {

	@Override
	public String convertToDatabaseColumn(RpaLinkageCpqUpdateCsvCreateStatus rpaLinkageCpqUpdateCsvCreateStatus) {
		if (rpaLinkageCpqUpdateCsvCreateStatus == null)
			return null;
		return rpaLinkageCpqUpdateCsvCreateStatus.toString();
	}

	@Override
	public RpaLinkageCpqUpdateCsvCreateStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return RpaLinkageCpqUpdateCsvCreateStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}

}