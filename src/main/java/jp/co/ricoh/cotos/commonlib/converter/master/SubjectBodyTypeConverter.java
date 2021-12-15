package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.MailConvertValueMaster.SubjectBodyType;

@Converter(autoApply = true)
public class SubjectBodyTypeConverter implements AttributeConverter<SubjectBodyType, String> {

	@Override
	public String convertToDatabaseColumn(SubjectBodyType subjectBodyType) {
		if (subjectBodyType == null)
			return null;
		return subjectBodyType.toString();
	}

	@Override
	public SubjectBodyType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return SubjectBodyType.fromString(value); //IllegalArgumentExceptionはShippingType.fromString側で投げている
	}
}