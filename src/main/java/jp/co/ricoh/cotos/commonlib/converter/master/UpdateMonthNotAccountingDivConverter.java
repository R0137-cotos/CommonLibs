package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.UpdateMonthNotAccountingDiv;

@Converter(autoApply = true)
public class UpdateMonthNotAccountingDivConverter implements AttributeConverter<UpdateMonthNotAccountingDiv, String> {
	@Override
	public String convertToDatabaseColumn(UpdateMonthNotAccountingDiv updateMonthNotAccountingDiv) {
		if (updateMonthNotAccountingDiv == null)
			return null;
		return updateMonthNotAccountingDiv.toString();
	}

	@Override
	public UpdateMonthNotAccountingDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return UpdateMonthNotAccountingDiv.fromString(value); //IllegalArgumentExceptionはPaymentCycleType.fromString側で投げている
	}
}
