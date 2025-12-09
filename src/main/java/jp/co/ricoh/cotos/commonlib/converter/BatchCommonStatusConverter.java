package jp.co.ricoh.cotos.commonlib.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.BatchCommonStatus;

@Converter(autoApply = true)
public class BatchCommonStatusConverter implements AttributeConverter<BatchCommonStatus, String> {

	@Override
	public String convertToDatabaseColumn(BatchCommonStatus batchCommonStatus) {
		if (batchCommonStatus == null)
			return null;
		return batchCommonStatus.toString();
	}

	@Override
	public BatchCommonStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return BatchCommonStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
