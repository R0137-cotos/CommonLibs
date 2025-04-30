package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.AccountFixFlg;

@Converter(autoApply = true)
public class AccountFixFlgConverter implements AttributeConverter<AccountFixFlg, String> {
	@Override
	public String convertToDatabaseColumn(AccountFixFlg accountFixFlg) {
		if (accountFixFlg == null)
			return null;
		return accountFixFlg.toString();
	}

	@Override
	public AccountFixFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return AccountFixFlg.fromString(value);
	}
}
