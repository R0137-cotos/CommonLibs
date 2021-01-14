package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.HwNosType;

@Converter(autoApply = true)
public class HwNosTypeConverter implements AttributeConverter<HwNosType, String> {

	@Override
	public String convertToDatabaseColumn(HwNosType hwNosType) {
		if (hwNosType == null)
			return null;
		return hwNosType.toString();
	}

	@Override
	public HwNosType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return HwNosType.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}