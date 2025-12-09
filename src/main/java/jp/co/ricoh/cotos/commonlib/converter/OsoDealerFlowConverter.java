package jp.co.ricoh.cotos.commonlib.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoDealerFlow;

@Converter(autoApply = true)
public class OsoDealerFlowConverter implements AttributeConverter<OsoDealerFlow, String> {

	@Override
	public String convertToDatabaseColumn(OsoDealerFlow osoDealerFlow) {
		if (osoDealerFlow == null)
			return null;
		return osoDealerFlow.toString();
	}

	@Override
	public OsoDealerFlow convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OsoDealerFlow.fromString(value); // IllegalArgumentExceptionはInitialRunningDiv.fromString側で投げている
	}
}