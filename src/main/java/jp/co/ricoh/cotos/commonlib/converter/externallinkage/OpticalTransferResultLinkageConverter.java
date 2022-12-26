package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalTransferManage.OpticalTransferResultLinkage;

@Converter(autoApply = true)
public class OpticalTransferResultLinkageConverter implements AttributeConverter<OpticalTransferResultLinkage, String> {

	@Override
	public String convertToDatabaseColumn(OpticalTransferResultLinkage opticalTransferResultLinkage) {
		if (opticalTransferResultLinkage == null)
			return null;
		return opticalTransferResultLinkage.toString();
	}

	@Override
	public OpticalTransferResultLinkage convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OpticalTransferResultLinkage.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
