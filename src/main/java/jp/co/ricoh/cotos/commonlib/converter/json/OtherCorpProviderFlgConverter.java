package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.OtherCorpProviderFlg;

@Converter(autoApply = true)
public class OtherCorpProviderFlgConverter implements AttributeConverter<OtherCorpProviderFlg, String> {
	@Override
	public String convertToDatabaseColumn(OtherCorpProviderFlg otherCorpProviderFlg) {
		if (otherCorpProviderFlg == null)
			return null;
		return otherCorpProviderFlg.toString();
	}

	@Override
	public OtherCorpProviderFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OtherCorpProviderFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
