package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.PaymentCycleType;

@Converter(autoApply = true)
public class PaymentCycleTypeConverter implements AttributeConverter<PaymentCycleType, String> {
	@Override
	public String convertToDatabaseColumn(PaymentCycleType paymentCycleType) {
		if (paymentCycleType == null)
			return null;
		return paymentCycleType.toString();
	}

	@Override
	public PaymentCycleType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return PaymentCycleType.fromString(value); //IllegalArgumentExceptionはPaymentCycleType.fromString側で投げている
	}
}
