package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.OrderDiv;

@Converter(autoApply = true)
public class OrderDivConverter implements AttributeConverter<OrderDiv, String> {
	@Override
	public String convertToDatabaseColumn(OrderDiv orderDiv) {
		if (orderDiv == null)
			return null;
		return orderDiv.toString();
	}

	@Override
	public OrderDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OrderDiv.fromString(value);
	}
}
