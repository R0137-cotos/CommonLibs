package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractDateSettingMaster.ServiceTermStartSettingDate;

@Converter(autoApply = true)
public class ServiceTermStartSettingDateConverter implements AttributeConverter<ServiceTermStartSettingDate, String> {

	@Override
	public String convertToDatabaseColumn(ServiceTermStartSettingDate serviceTermStartSettingDate) {
		if (serviceTermStartSettingDate == null)
			return null;
		return serviceTermStartSettingDate.toString();
	}

	@Override
	public ServiceTermStartSettingDate convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ServiceTermStartSettingDate.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}