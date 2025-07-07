package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractDateSettingMaster.ServiceTermStartSettingType;

@Converter(autoApply = true)
public class ServiceTermStartSettingTypeConverter implements AttributeConverter<ServiceTermStartSettingType, String> {

	@Override
	public String convertToDatabaseColumn(ServiceTermStartSettingType serviceTermStartSettingType) {
		if (serviceTermStartSettingType == null)
			return null;
		return serviceTermStartSettingType.toString();
	}

	@Override
	public ServiceTermStartSettingType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ServiceTermStartSettingType.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}