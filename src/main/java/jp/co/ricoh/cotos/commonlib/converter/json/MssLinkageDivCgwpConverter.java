package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeCgwp.MssLinkageDivCgwp;

@Converter(autoApply = true)
public class MssLinkageDivCgwpConverter implements AttributeConverter<MssLinkageDivCgwp, String> {
	@Override
	public String convertToDatabaseColumn(MssLinkageDivCgwp mssLinkageDivCgwp) {
		if (mssLinkageDivCgwp == null)
			return null;
		return mssLinkageDivCgwp.toString();
	}

	@Override
	public MssLinkageDivCgwp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MssLinkageDivCgwp.fromString(value);
	}
}
