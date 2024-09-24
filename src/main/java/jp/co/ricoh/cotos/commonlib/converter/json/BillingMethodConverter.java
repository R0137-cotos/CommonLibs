package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.BillingMethod;

@Converter(autoApply = true)
public class BillingMethodConverter implements AttributeConverter<BillingMethod, String> {
	@Override
	public String convertToDatabaseColumn(BillingMethod billingMethod) {
		if (billingMethod == null)
			return null;
		return billingMethod.toString();
	}

	@Override
	public BillingMethod convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return BillingMethod.fromString(value);
	}
}