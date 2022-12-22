package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.McafeeLinkageInfo.DisengagementFlg;

@Converter(autoApply = true)
public class DisengagementFlgConverter implements AttributeConverter<DisengagementFlg, String> {

	@Override
	public String convertToDatabaseColumn(DisengagementFlg processDiv) {
		if (processDiv == null)
			return null;
		return processDiv.toString();
	}

	@Override
	public DisengagementFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DisengagementFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
