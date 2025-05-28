package jp.co.ricoh.cotos.commonlib.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.json.TestJsonDto.TestEstimationTypeDetails;;

@Converter(autoApply = true)
public class TestEstimationTypeDetailsConverter implements AttributeConverter<TestEstimationTypeDetails, String> {
	@Override
	public String convertToDatabaseColumn(TestEstimationTypeDetails estimationTypeDetails) {
		if (estimationTypeDetails == null)
			return null;
		return estimationTypeDetails.toString();
	}

	@Override
	public TestEstimationTypeDetails convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return TestEstimationTypeDetails.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
