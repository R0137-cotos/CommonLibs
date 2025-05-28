package jp.co.ricoh.cotos.commonlib.converter.accounting;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.accounting.CommissionData.NspCommercialFlowDiv;

@Converter(autoApply = true)
public class NspCommercialFlowDivConverter implements AttributeConverter<NspCommercialFlowDiv, String> {
	@Override
	public String convertToDatabaseColumn(NspCommercialFlowDiv nspCommercialFlowDiv) {
		if (nspCommercialFlowDiv == null)
			return null;
		return nspCommercialFlowDiv.toString();
	}

	@Override
	public NspCommercialFlowDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return NspCommercialFlowDiv.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
