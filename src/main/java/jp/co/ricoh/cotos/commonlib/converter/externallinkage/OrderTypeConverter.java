package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ConstructionEimApplyInfo.OrderType;

@Converter(autoApply = true)
public class OrderTypeConverter implements AttributeConverter<OrderType, String> {

	@Override
	public String convertToDatabaseColumn(OrderType orderType) {
		if (orderType == null)
			return null;
		return orderType.toString();
	}

	@Override
	public OrderType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OrderType.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
