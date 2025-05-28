package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.LaitLinkageInfo.ReceiptStatus;

@Converter(autoApply = true)
public class ReceiptStatusConverter implements AttributeConverter<ReceiptStatus, String> {

	@Override
	public String convertToDatabaseColumn(ReceiptStatus ReceiptStatus) {
		if (ReceiptStatus == null)
			return null;
		return ReceiptStatus.toString();
	}

	@Override
	public ReceiptStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ReceiptStatus.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
