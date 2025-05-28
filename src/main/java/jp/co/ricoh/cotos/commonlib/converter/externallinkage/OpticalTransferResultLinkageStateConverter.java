package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalTransferManage.OpticalTransferResultLinkageState;

@Converter(autoApply = true)
public class OpticalTransferResultLinkageStateConverter implements AttributeConverter<OpticalTransferResultLinkageState, String> {

	@Override
	public String convertToDatabaseColumn(OpticalTransferResultLinkageState opticalTransferResultLinkageState) {
		if (opticalTransferResultLinkageState == null)
			return null;
		return opticalTransferResultLinkageState.toString();
	}

	@Override
	public OpticalTransferResultLinkageState convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OpticalTransferResultLinkageState.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
