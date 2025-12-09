package jp.co.ricoh.cotos.commonlib.converter.accounting;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.common.OsoRequestDetailDataAbstractEntity.OsoCostType;

@Converter(autoApply = true)
public class OsoCostTypeConverter implements AttributeConverter<OsoCostType, String> {
	@Override
	public String convertToDatabaseColumn(OsoCostType osoCostType) {
		if (osoCostType == null)
			return null;
		return osoCostType.toString();
	}

	@Override
	public OsoCostType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OsoCostType.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
