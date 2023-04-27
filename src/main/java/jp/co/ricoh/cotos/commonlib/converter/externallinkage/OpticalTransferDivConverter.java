package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalTransfer.OpticalTransferDiv;

@Converter(autoApply = true)
public class OpticalTransferDivConverter implements AttributeConverter<OpticalTransferDiv, String> {

	@Override
	public String convertToDatabaseColumn(OpticalTransferDiv opticalTransferDiv) {
		if (opticalTransferDiv == null)
			return null;
		return opticalTransferDiv.toString();
	}

	@Override
	public OpticalTransferDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OpticalTransferDiv.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
