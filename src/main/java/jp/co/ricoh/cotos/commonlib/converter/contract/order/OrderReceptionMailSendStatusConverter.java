package jp.co.ricoh.cotos.commonlib.converter.contract.order;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.order.OrderManagementInfo.OrderReceptionMailSendStatus;

@Converter(autoApply = true)
public class OrderReceptionMailSendStatusConverter implements AttributeConverter<OrderReceptionMailSendStatus, String> {
	@Override
	public String convertToDatabaseColumn(OrderReceptionMailSendStatus orderReceptionMailSendStatus) {

		if (orderReceptionMailSendStatus == null)
			return null;

		return orderReceptionMailSendStatus.toString();
	}

	@Override
	public OrderReceptionMailSendStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OrderReceptionMailSendStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
