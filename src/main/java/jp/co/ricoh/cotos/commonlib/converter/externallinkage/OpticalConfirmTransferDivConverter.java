package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalTransferManage.OpticalConfirmTransferDiv;

@Converter(autoApply = true)
public class OpticalConfirmTransferDivConverter implements AttributeConverter<OpticalConfirmTransferDiv, String> {

	@Override
	public String convertToDatabaseColumn(OpticalConfirmTransferDiv opticalConfirmTransferDiv) {
		if (opticalConfirmTransferDiv == null)
			return null;
		return opticalConfirmTransferDiv.toString();
	}

	@Override
	public OpticalConfirmTransferDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OpticalConfirmTransferDiv.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
