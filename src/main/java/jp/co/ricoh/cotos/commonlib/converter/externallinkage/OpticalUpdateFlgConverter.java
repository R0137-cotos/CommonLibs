package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalTransfer.OpticalUpdateFlg;

@Converter(autoApply = true)
public class OpticalUpdateFlgConverter implements AttributeConverter<OpticalUpdateFlg, String> {

	@Override
	public String convertToDatabaseColumn(OpticalUpdateFlg opticalUpdateFlg) {
		if (opticalUpdateFlg == null)
			return null;
		return opticalUpdateFlg.toString();
	}

	@Override
	public OpticalUpdateFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OpticalUpdateFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
