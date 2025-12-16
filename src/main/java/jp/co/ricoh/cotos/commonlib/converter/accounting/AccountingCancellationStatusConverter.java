package jp.co.ricoh.cotos.commonlib.converter.accounting;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.accounting.AccountedCancellationData.AccountingCancellationStatus;

@Converter(autoApply = true)
public class AccountingCancellationStatusConverter implements AttributeConverter<AccountingCancellationStatus, String> {
	@Override
	public String convertToDatabaseColumn(AccountingCancellationStatus dataDiv) {
		if (dataDiv == null)
			return null;
		return dataDiv.toString();
	}

	@Override
	public AccountingCancellationStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return AccountingCancellationStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}