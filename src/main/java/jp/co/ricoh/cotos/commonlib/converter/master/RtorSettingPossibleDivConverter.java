package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ProductMaster.RtorSettingPossibleDiv;

@Converter(autoApply = true)
public class RtorSettingPossibleDivConverter implements AttributeConverter<RtorSettingPossibleDiv, String> {

	@Override
	public String convertToDatabaseColumn(RtorSettingPossibleDiv rtorSettingPossibleDiv) {
		if (rtorSettingPossibleDiv == null)
			return null;
		return rtorSettingPossibleDiv.toString();
	}

	@Override
	public RtorSettingPossibleDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return RtorSettingPossibleDiv.fromString(value); //IllegalArgumentExceptionはfromString側で投げている
	}
}