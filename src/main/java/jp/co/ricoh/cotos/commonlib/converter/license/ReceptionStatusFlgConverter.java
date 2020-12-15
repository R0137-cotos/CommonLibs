package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.license.LicenseDetailExtendsParameterGspDto.ReceptionStatusFlg;


@Converter(autoApply = true)
public class ReceptionStatusFlgConverter implements AttributeConverter<ReceptionStatusFlg, String> {

	@Override
	public String convertToDatabaseColumn(ReceptionStatusFlg receptionStatusFlg) {
		if (receptionStatusFlg == null)
			return null;
		return receptionStatusFlg.toString();
	}

	@Override
	public ReceptionStatusFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ReceptionStatusFlg.fromString(value); // IllegalArgumentExceptionはAllocationDiv.fromString側で投げている
	}
}
