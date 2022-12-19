package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.HostingRtoRFlg;

@Converter(autoApply = true)
public class HostingRtoRFlgConverter implements AttributeConverter<HostingRtoRFlg, String> {

	@Override
	public String convertToDatabaseColumn(HostingRtoRFlg hostingRtoRFlg) {
		if (hostingRtoRFlg == null)
			return null;
		return hostingRtoRFlg.toString();
	}

	@Override
	public HostingRtoRFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return HostingRtoRFlg.fromString(value); // IllegalArgumentExceptionはSaleDiv.fromString側で投げている
	}
}
