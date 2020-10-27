package jp.co.ricoh.cotos.commonlib.converter;

import javax.persistence.AttributeConverter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.WorkflowType;

//@Converter
public class WorkflowTypeConverter implements AttributeConverter<WorkflowType, String> {
	@Override
	public String convertToDatabaseColumn(WorkflowType workflowType) {
		if (workflowType == null)
			return null;
		return workflowType.toString();
	}

	@Override
	public WorkflowType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return WorkflowType.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
