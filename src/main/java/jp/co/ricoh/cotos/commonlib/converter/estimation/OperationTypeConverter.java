package jp.co.ricoh.cotos.commonlib.converter.estimation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.estimation.SeOperationHistory.OperationType;

@Converter(autoApply = true)
public class OperationTypeConverter implements AttributeConverter<OperationType, String> {
	@Override
	public String convertToDatabaseColumn(OperationType operationType) {
		if (operationType == null)
			return null;
		return operationType.toString();
	}

	@Override
	public OperationType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OperationType.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
