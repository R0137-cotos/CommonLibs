package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorMailFlg;

@Converter(autoApply = true)
public class RtorMailFlgConverter implements AttributeConverter<RtorMailFlg, String> {

	@Override
	public String convertToDatabaseColumn(RtorMailFlg rtorFlg) {
		if (rtorFlg == null)
			return null;
		return rtorFlg.toString();
	}

	@Override
	public RtorMailFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return RtorMailFlg.fromString(value); // IllegalArgumentExceptionはSaleDiv.fromString側で投げている
	}
}
