package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractDateSettingMaster.ServiceTermEndSettingType;

@Converter(autoApply = true)
public class ServiceTermEndSettingTypeConverter implements AttributeConverter<ServiceTermEndSettingType, String> {

	@Override
	public String convertToDatabaseColumn(ServiceTermEndSettingType serviceTermEndSettingType) {
		if (serviceTermEndSettingType == null)
			return null;
		return serviceTermEndSettingType.toString();
	}

	@Override
	public ServiceTermEndSettingType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ServiceTermEndSettingType.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}