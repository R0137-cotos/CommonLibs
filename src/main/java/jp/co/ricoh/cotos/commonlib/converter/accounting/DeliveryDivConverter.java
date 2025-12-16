package jp.co.ricoh.cotos.commonlib.converter.accounting;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.common.OsoResultsDataAbstractEntity.DeliveryDiv;

@Converter(autoApply = true)
public class DeliveryDivConverter implements AttributeConverter<DeliveryDiv, String> {
	@Override
	public String convertToDatabaseColumn(DeliveryDiv deliveryDiv) {
		if (deliveryDiv == null)
			return null;
		return deliveryDiv.toString();
	}

	@Override
	public DeliveryDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DeliveryDiv.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
