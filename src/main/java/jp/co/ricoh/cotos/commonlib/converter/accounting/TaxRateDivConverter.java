package jp.co.ricoh.cotos.commonlib.converter.accounting;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.accounting.CommissionData.TaxRateDiv;

@Converter(autoApply = true)
public class TaxRateDivConverter implements AttributeConverter<TaxRateDiv, String> {
	@Override
	public String convertToDatabaseColumn(TaxRateDiv taxRateDiv) {
		if (taxRateDiv == null)
			return null;
		return taxRateDiv.toString();
	}

	@Override
	public TaxRateDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return TaxRateDiv.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
