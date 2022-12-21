package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.TransferFlg;

@Converter(autoApply = true)
public class TransferFlgConverter implements AttributeConverter<TransferFlg, String> {
	@Override
	public String convertToDatabaseColumn(TransferFlg transferFlg) {
		if (transferFlg == null)
			return null;
		return transferFlg.toString();
	}

	@Override
	public TransferFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return TransferFlg.fromString(value);
	}
}
