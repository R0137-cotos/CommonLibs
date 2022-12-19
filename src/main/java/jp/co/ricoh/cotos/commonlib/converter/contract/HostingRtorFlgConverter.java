package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.HostingRtorFlg;

@Converter(autoApply = true)
public class HostingRtorFlgConverter implements AttributeConverter<HostingRtorFlg, String> {

	@Override
	public String convertToDatabaseColumn(HostingRtorFlg hostingRtoRFlg) {
		if (hostingRtoRFlg == null)
			return null;
		return hostingRtoRFlg.toString();
	}

	@Override
	public HostingRtorFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return HostingRtorFlg.fromString(value); // IllegalArgumentExceptionはSaleDiv.fromString側で投げている
	}
}
