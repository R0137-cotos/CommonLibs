package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.HostingRtorFlg;

@Converter(autoApply = true)
public class HostingRtorFlgConverter implements AttributeConverter<HostingRtorFlg, String> {

	@Override
	public String convertToDatabaseColumn(HostingRtorFlg hostingRtorFlg) {
		if (hostingRtorFlg == null)
			return null;
		return hostingRtorFlg.toString();
	}

	@Override
	public HostingRtorFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return HostingRtorFlg.fromString(value); // IllegalArgumentExceptionはSaleDiv.fromString側で投げている
	}
}
