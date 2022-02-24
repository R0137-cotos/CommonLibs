package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.RequestProcessDiv;

@Converter(autoApply = true)
public class RequestProcessDivConverter implements AttributeConverter<RequestProcessDiv, String> {

	@Override
	public String convertToDatabaseColumn(RequestProcessDiv receptionStatusFlg) {
		if (receptionStatusFlg == null)
			return null;
		return receptionStatusFlg.toString();
	}

	@Override
	public RequestProcessDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return RequestProcessDiv.fromString(value); // IllegalArgumentExceptionはAllocationDiv.fromString側で投げている
	}
}
