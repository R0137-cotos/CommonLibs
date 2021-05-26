package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail.AbsConExternalBillingStatus;

@Converter(autoApply = true)
public class AbsConExternalBillingStatusConverter implements AttributeConverter<AbsConExternalBillingStatus, String> {

	@Override
	public String convertToDatabaseColumn(AbsConExternalBillingStatus absConInsideTransStatus) {
		if (absConInsideTransStatus == null)
			return null;
		return absConInsideTransStatus.toString();
	}

	@Override
	public AbsConExternalBillingStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return AbsConExternalBillingStatus.fromString(value); // IllegalArgumentExceptionはAbsConInsideTransStatus.fromString側で投げている
	}

}
