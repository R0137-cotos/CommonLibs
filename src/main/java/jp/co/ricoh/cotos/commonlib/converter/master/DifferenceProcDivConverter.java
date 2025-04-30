package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.PriceRevisionDateMaster.DifferenceProcDiv;


@Converter(autoApply = true)
public class DifferenceProcDivConverter implements AttributeConverter<DifferenceProcDiv, String> {

	@Override
	public String convertToDatabaseColumn(DifferenceProcDiv differenceProcDiv) {
		if (differenceProcDiv == null)
			return null;
		return differenceProcDiv.toString();
	}

	@Override
	public DifferenceProcDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DifferenceProcDiv.fromString(value); //IllegalArgumentExceptionはDifferenceProcDiv.fromString側で投げている
	}
}