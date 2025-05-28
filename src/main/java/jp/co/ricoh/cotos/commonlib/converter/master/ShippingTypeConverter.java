package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ShippingThingMaster.ShippingType;

@Converter(autoApply = true)
public class ShippingTypeConverter implements AttributeConverter<ShippingType, String> {

	@Override
	public String convertToDatabaseColumn(ShippingType shippingType) {
		if (shippingType == null)
			return null;
		return shippingType.toString();
	}

	@Override
	public ShippingType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ShippingType.fromString(value); //IllegalArgumentExceptionはShippingType.fromString側で投げている
	}
}