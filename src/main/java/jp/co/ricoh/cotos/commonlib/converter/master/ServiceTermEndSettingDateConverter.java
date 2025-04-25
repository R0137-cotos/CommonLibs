package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractDateSettingMaster.ServiceTermEndSettingDate;

@Converter(autoApply = true)
public class ServiceTermEndSettingDateConverter implements AttributeConverter<ServiceTermEndSettingDate, String> {

	@Override
	public String convertToDatabaseColumn(ServiceTermEndSettingDate serviceTermEndSettingDate) {
		if (serviceTermEndSettingDate == null)
			return null;
		return serviceTermEndSettingDate.toString();
	}

	@Override
	public ServiceTermEndSettingDate convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ServiceTermEndSettingDate.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}