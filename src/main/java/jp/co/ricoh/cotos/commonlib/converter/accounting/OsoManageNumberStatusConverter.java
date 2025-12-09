package jp.co.ricoh.cotos.commonlib.converter.accounting;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.common.OsoRequestDataAbstractEntity.OsoManageNumberStatus;

@Converter(autoApply = true)
public class OsoManageNumberStatusConverter implements AttributeConverter<OsoManageNumberStatus, String> {
	@Override
	public String convertToDatabaseColumn(OsoManageNumberStatus osoManageNumberStatus) {
		if (osoManageNumberStatus == null)
			return null;
		return osoManageNumberStatus.toString();
	}

	@Override
	public OsoManageNumberStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OsoManageNumberStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}