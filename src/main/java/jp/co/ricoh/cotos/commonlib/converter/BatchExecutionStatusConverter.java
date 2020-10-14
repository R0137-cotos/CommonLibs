package jp.co.ricoh.cotos.commonlib.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.common.FileImportManagement.BatchExecutionStatus;

@Converter(autoApply = true)
public class BatchExecutionStatusConverter implements AttributeConverter<BatchExecutionStatus, String> {

	@Override
	public String convertToDatabaseColumn(BatchExecutionStatus batchExecutionStatus) {
		if (batchExecutionStatus == null)
			return null;
		return batchExecutionStatus.toString();
	}

	@Override
	public BatchExecutionStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return BatchExecutionStatus.fromString(value); // IllegalArgumentExceptionはBatchExecutionStatus.fromString側で投げている
	}

}