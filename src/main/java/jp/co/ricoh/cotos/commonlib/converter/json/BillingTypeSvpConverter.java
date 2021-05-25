package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.BillingTypeSvp;

@Converter(autoApply = true)
public class BillingTypeSvpConverter implements AttributeConverter<BillingTypeSvp, String> {
	@Override
	public String convertToDatabaseColumn(BillingTypeSvp billingTypeSvp) {
		if (billingTypeSvp == null)
			return null;
		return billingTypeSvp.toString();
	}

	@Override
	public BillingTypeSvp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return BillingTypeSvp.fromString(value);
	}
}
