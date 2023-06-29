package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.AttachedFileProductGrpCheckMaster.CheckTimingDiv;

@Converter(autoApply = true)
public class CheckTimingDivConverter implements AttributeConverter<CheckTimingDiv, String> {

	@Override
	public String convertToDatabaseColumn(CheckTimingDiv checkTimingDiv) {
		if (checkTimingDiv == null)
			return null;
		return checkTimingDiv.toString();
	}

	@Override
	public CheckTimingDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CheckTimingDiv.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}