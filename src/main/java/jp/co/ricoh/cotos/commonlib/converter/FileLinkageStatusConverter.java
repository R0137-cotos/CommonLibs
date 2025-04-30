package jp.co.ricoh.cotos.commonlib.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.FileLinkageStatus;

@Converter(autoApply = true)
public class FileLinkageStatusConverter implements AttributeConverter<FileLinkageStatus, String> {

	@Override
	public String convertToDatabaseColumn(FileLinkageStatus fileLinkageStatus) {
		if (fileLinkageStatus == null)
			return null;
		return fileLinkageStatus.toString();
	}

	@Override
	public FileLinkageStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return FileLinkageStatus.fromString(value); // IllegalArgumentExceptionはInitialRunningDiv.fromString側で投げている
	}
}
