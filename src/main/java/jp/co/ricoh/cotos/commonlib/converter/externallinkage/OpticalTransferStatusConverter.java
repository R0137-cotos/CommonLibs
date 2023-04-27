package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalTransferManage.OpticalTransferStatus;

@Converter(autoApply = true)
public class OpticalTransferStatusConverter implements AttributeConverter<OpticalTransferStatus, String> {

	@Override
	public String convertToDatabaseColumn(OpticalTransferStatus opticalTransferStatus) {
		if (opticalTransferStatus == null)
			return null;
		return opticalTransferStatus.toString();
	}

	@Override
	public OpticalTransferStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OpticalTransferStatus.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
