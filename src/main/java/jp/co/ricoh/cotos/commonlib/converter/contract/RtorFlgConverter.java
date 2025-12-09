package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorFlg;

@Converter(autoApply = true)
public class RtorFlgConverter implements AttributeConverter<RtorFlg, String> {

	@Override
	public String convertToDatabaseColumn(RtorFlg rtorFlg) {
		if (rtorFlg == null)
			return null;
		return rtorFlg.toString();
	}

	@Override
	public RtorFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return RtorFlg.fromString(value); // IllegalArgumentExceptionはSaleDiv.fromString側で投げている
	}
}
