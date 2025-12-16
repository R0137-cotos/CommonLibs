package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.InfoChangeSkipFlg;

@Converter(autoApply = true)
public class InfoChangeSkipFlgConverter implements AttributeConverter<InfoChangeSkipFlg, String> {

	@Override
	public String convertToDatabaseColumn(InfoChangeSkipFlg infoChangeSkipFlg) {
		if (infoChangeSkipFlg == null)
			return null;
		return infoChangeSkipFlg.toString();
	}

	@Override
	public InfoChangeSkipFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return InfoChangeSkipFlg.fromString(value); // IllegalArgumentExceptionはSaleDiv.fromString側で投げている
	}
}
