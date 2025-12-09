package jp.co.ricoh.cotos.commonlib.converter.accounting;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.common.OsoResultsDataAbstractEntity.CommissionDataCreateStatus;

@Converter(autoApply = true)
public class CommissionDataCreateStatusConverter implements AttributeConverter<CommissionDataCreateStatus, String> {
	@Override
	public String convertToDatabaseColumn(CommissionDataCreateStatus commissionDataCreateStatus) {
		if (commissionDataCreateStatus == null)
			return null;
		return commissionDataCreateStatus.toString();
	}

	@Override
	public CommissionDataCreateStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CommissionDataCreateStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
