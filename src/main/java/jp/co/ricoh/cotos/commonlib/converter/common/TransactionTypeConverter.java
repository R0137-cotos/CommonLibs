package jp.co.ricoh.cotos.commonlib.converter.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.common.TransactionDiscardingHistory.TransactionType;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {
	@Override
	public String convertToDatabaseColumn(TransactionType transactionType) {
		if (transactionType == null)
			return null;
		return transactionType.toString();
	}

	@Override
	public TransactionType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return TransactionType.fromString(value); // IllegalArgumentExceptionはTransactionType.fromString側で投げている
	}
}
