package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.PaymentCycleChangeFlg;

@Converter(autoApply = true)
public class PaymentCycleChangeFlgConverter implements AttributeConverter<PaymentCycleChangeFlg, String> {
	@Override
	public String convertToDatabaseColumn(PaymentCycleChangeFlg paymentCycleChangeFlg) {
		if (paymentCycleChangeFlg == null)
			return null;
		return paymentCycleChangeFlg.toString();
	}

	@Override
	public PaymentCycleChangeFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return PaymentCycleChangeFlg.fromString(value);
	}
}
