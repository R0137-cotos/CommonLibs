package jp.co.ricoh.cotos.commonlib.converter.license.tm;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmTransitionSubscriptionRequestWork.CrossGradeDiv;

@Converter(autoApply = true)
public class CrossGradeDivConverter implements AttributeConverter<CrossGradeDiv, String> {
	@Override
	public String convertToDatabaseColumn(CrossGradeDiv crossGradeDiv) {
		if (crossGradeDiv == null)
			return null;
		return crossGradeDiv.toString();
	}

	@Override
	public CrossGradeDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CrossGradeDiv.fromString(value); // IllegalArgumentExceptionはCrossGradeDiv.fromString側で投げている
	}
}
