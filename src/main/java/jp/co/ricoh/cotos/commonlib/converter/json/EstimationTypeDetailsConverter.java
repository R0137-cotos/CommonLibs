package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;

@Converter(autoApply = true)
public class EstimationTypeDetailsConverter implements AttributeConverter<EstimationTypeDetails, String> {
	@Override
	public String convertToDatabaseColumn(EstimationTypeDetails estimationTypeDetails) {
		if (estimationTypeDetails == null)
			return null;
		return estimationTypeDetails.toString();
	}

	@Override
	public EstimationTypeDetails convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return EstimationTypeDetails.fromString(value);
	}
}
