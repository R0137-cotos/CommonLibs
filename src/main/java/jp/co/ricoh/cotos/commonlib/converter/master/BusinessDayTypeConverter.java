package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.MailControlMaster.BusinessDayType;

@Converter(autoApply = true)
public class BusinessDayTypeConverter implements AttributeConverter<BusinessDayType, String> {

	@Override
	public String convertToDatabaseColumn(BusinessDayType businessDayType) {
		if (businessDayType == null)
			return null;
		return businessDayType.toString();
	}

	@Override
	public BusinessDayType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return BusinessDayType.fromString(value); //IllegalArgumentExceptionはBusinessDayType.fromString側で投げている
	}
}