package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ServicePreferredSettingPossibleType;


@Converter(autoApply = true)
public class ServicePreferredSettingPossibleTypeConverter implements AttributeConverter<ServicePreferredSettingPossibleType, String> {

	@Override
	public String convertToDatabaseColumn(ServicePreferredSettingPossibleType itemType) {
		if (itemType == null)
			return null;
		return itemType.toString();
	}

	@Override
	public ServicePreferredSettingPossibleType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ServicePreferredSettingPossibleType.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}