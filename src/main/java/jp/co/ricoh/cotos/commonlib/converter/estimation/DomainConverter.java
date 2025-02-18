package jp.co.ricoh.cotos.commonlib.converter.estimation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.estimation.SeOperationHistory.Domain;

@Converter(autoApply = true)
public class DomainConverter implements AttributeConverter<Domain, String> {

	@Override
	public String convertToDatabaseColumn(Domain domain) {
		if (domain == null)
			return null;
		return domain.toString();
	}

	@Override
	public Domain convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return Domain.fromString(value);
	}
}