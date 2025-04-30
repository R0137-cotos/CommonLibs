package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeCgwp.MssLinkageStopDivCgwp;

@Converter(autoApply = true)
public class MssLinkageStopDivCgwpConverter implements AttributeConverter<MssLinkageStopDivCgwp, String> {
	@Override
	public String convertToDatabaseColumn(MssLinkageStopDivCgwp mssLinkageStopDivCgwp) {
		if (mssLinkageStopDivCgwp == null)
			return null;
		return mssLinkageStopDivCgwp.toString();
	}

	@Override
	public MssLinkageStopDivCgwp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MssLinkageStopDivCgwp.fromString(value);
	}
}
