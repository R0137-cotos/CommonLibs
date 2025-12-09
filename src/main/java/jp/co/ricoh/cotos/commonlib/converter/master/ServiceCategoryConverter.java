package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.MailAddressMaster.ServiceCategory;

@Converter(autoApply = true)
public class ServiceCategoryConverter implements AttributeConverter<ServiceCategory, String> {

	@Override
	public String convertToDatabaseColumn(ServiceCategory serviceCategory) {
		if (serviceCategory == null)
			return null;
		return serviceCategory.toString();
	}

	@Override
	public ServiceCategory convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ServiceCategory.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
