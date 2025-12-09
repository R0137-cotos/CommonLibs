package jp.co.ricoh.cotos.commonlib.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoSalesDataIdDiv;

@Converter(autoApply = true)
public class OsoSalesDataIdDivConverter implements AttributeConverter<OsoSalesDataIdDiv, String> {

	@Override
	public String convertToDatabaseColumn(OsoSalesDataIdDiv osoSalesDataIdDiv) {
		if (osoSalesDataIdDiv == null)
			return null;
		return osoSalesDataIdDiv.toString();
	}

	@Override
	public OsoSalesDataIdDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OsoSalesDataIdDiv.fromString(value); // IllegalArgumentExceptionはInitialRunningDiv.fromString側で投げている
	}
}
