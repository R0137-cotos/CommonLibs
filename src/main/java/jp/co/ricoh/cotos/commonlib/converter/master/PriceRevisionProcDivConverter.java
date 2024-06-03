package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster.PriceRevisionProcDiv;

@Converter(autoApply = true)
public class PriceRevisionProcDivConverter implements AttributeConverter<PriceRevisionProcDiv, String> {
	@Override
	public String convertToDatabaseColumn(PriceRevisionProcDiv increaseDecreaseDiv) {
		if (increaseDecreaseDiv == null)
			return null;
		return increaseDecreaseDiv.toString();
	}

	@Override
	public PriceRevisionProcDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return PriceRevisionProcDiv.fromString(value);
	}
}