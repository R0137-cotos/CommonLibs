package jp.co.ricoh.cotos.commonlib.converter.license;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.HardSoftDiv;

@Converter(autoApply = true)
public class HardSoftDivConverter implements AttributeConverter<HardSoftDiv, String> {

	@Override
	public String convertToDatabaseColumn(HardSoftDiv hardSoftDiv) {
		if (hardSoftDiv == null)
			return null;
		return hardSoftDiv.toString();
	}

	@Override
	public HardSoftDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return HardSoftDiv.fromString(value); // IllegalArgumentExceptionはHardSoftDiv.fromString側で投げている
	}

}
