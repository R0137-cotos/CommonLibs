package jp.co.ricoh.cotos.commonlib.converter.estimation;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.estimation.ElectronicContractInfo.CustomerPicDiv;

@Converter(autoApply = true)
public class CustomerPicDivConverter implements AttributeConverter<CustomerPicDiv, String> {

	@Override
	public String convertToDatabaseColumn(CustomerPicDiv customerPicDiv) {
		if (customerPicDiv == null)
			return null;
		return customerPicDiv.toString();
	}

	@Override
	public CustomerPicDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CustomerPicDiv.fromString(value); //IllegalArgumentExceptionはContractSpanStartDateType.fromString側で投げている
	}
}