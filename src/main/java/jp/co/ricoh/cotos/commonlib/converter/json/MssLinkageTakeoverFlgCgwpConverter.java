package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeCgwp.MssLinkageTakeoverFlgCgwp;

@Converter(autoApply = true)
public class MssLinkageTakeoverFlgCgwpConverter implements AttributeConverter<MssLinkageTakeoverFlgCgwp, String> {
	@Override
	public String convertToDatabaseColumn(MssLinkageTakeoverFlgCgwp mssLinkageTakeoverFlgCgwp) {
		if (mssLinkageTakeoverFlgCgwp == null)
			return null;
		return mssLinkageTakeoverFlgCgwp.toString();
	}

	@Override
	public MssLinkageTakeoverFlgCgwp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MssLinkageTakeoverFlgCgwp.fromString(value);
	}
}
