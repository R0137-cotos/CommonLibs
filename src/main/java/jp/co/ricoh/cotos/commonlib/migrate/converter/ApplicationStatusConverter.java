package jp.co.ricoh.cotos.commonlib.migrate.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.migrate.entity.BasicContents.ApplicationStatus;

@Converter(autoApply = true)
public class ApplicationStatusConverter implements AttributeConverter<ApplicationStatus, String> {
	@Override
	public String convertToDatabaseColumn(ApplicationStatus applicationStatus) {
		if (applicationStatus == null)
			return null;
		return applicationStatus.toString();
	}

	@Override
	public ApplicationStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ApplicationStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
