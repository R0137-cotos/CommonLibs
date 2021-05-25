package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo.CsvOutputFlg;

@Converter(autoApply = true)
public class CsvOutputFlgConverter implements AttributeConverter<CsvOutputFlg, String> {

	@Override
	public String convertToDatabaseColumn(CsvOutputFlg csvOutputFlg) {
		if (csvOutputFlg == null)
			return null;
		return csvOutputFlg.toString();
	}

	@Override
	public CsvOutputFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CsvOutputFlg.fromString(value); // IllegalArgumentExceptionはInfoDiv.fromString側で投げている
	}
}
