package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.DomainRtorFlg;

@Converter(autoApply = true)
public class DomainRtorFlgConverter implements AttributeConverter<DomainRtorFlg, String> {
	@Override
	public String convertToDatabaseColumn(DomainRtorFlg domainRtorFlg) {
		if (domainRtorFlg == null)
			return null;
		return domainRtorFlg.toString();
	}

	@Override
	public DomainRtorFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DomainRtorFlg.fromString(value);
	}
}
