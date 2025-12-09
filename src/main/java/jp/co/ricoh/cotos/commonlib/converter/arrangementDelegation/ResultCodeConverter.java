package jp.co.ricoh.cotos.commonlib.converter.arrangementDelegation;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation.IspArrangementResultRegistrationDto.ResultCode;

@Converter(autoApply = true)
public class ResultCodeConverter implements AttributeConverter<ResultCode, String> {
	@Override
	public String convertToDatabaseColumn(ResultCode resultCode) {
		if (resultCode == null)
			return null;
		return resultCode.toString();
	}

	@Override
	public ResultCode convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ResultCode.fromString(value); // IllegalArgumentExceptionはResultCode.fromString側で投げている
	}

}
