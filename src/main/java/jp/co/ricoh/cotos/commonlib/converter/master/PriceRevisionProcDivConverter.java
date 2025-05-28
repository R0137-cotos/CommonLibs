package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster.PriceRevisionProcDiv;

@Converter(autoApply = true)
public class PriceRevisionProcDivConverter implements AttributeConverter<PriceRevisionProcDiv, String> {
	@Override
	public String convertToDatabaseColumn(PriceRevisionProcDiv priceRevisionProcDiv) {
		if (priceRevisionProcDiv == null)
			return null;
		return priceRevisionProcDiv.toString();
	}

	@Override
	public PriceRevisionProcDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return PriceRevisionProcDiv.fromString(value);
	}
}