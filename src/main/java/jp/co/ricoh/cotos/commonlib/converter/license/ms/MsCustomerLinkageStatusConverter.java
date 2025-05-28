package jp.co.ricoh.cotos.commonlib.converter.license.ms;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsCustomerRegisterRequestWork.MsCustomerLinkageStatus;

@Converter(autoApply = true)
public class MsCustomerLinkageStatusConverter implements AttributeConverter<MsCustomerLinkageStatus, String> {
	@Override
	public String convertToDatabaseColumn(MsCustomerLinkageStatus customerLinkageStatus) {
		if (customerLinkageStatus == null)
			return null;
		return customerLinkageStatus.toString();
	}

	@Override
	public MsCustomerLinkageStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MsCustomerLinkageStatus.fromString(value); // IllegalArgumentExceptionはMsCustomerLinkageStatus.fromString側で投げている
	}
}
