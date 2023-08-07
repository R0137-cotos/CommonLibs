package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.InfoChangeDisplayFlg;

@Converter(autoApply = true)
public class InfoChangeDisplayFlgConverter implements AttributeConverter<InfoChangeDisplayFlg, String> {

	@Override
	public String convertToDatabaseColumn(InfoChangeDisplayFlg infoChangeDisplayFlg) {
		if (infoChangeDisplayFlg == null)
			return null;
		return infoChangeDisplayFlg.toString();
	}

	@Override
	public InfoChangeDisplayFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return InfoChangeDisplayFlg.fromString(value); // IllegalArgumentExceptionはSaleDiv.fromString側で投げている
	}
}
