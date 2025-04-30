package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.MvbAccountEntryDiv;

@Converter(autoApply = true)
public class MvbAccountEntryDivConverter implements AttributeConverter<MvbAccountEntryDiv, String> {
	@Override
	public String convertToDatabaseColumn(MvbAccountEntryDiv mvbAccountEntryDiv) {
		if (mvbAccountEntryDiv == null)
			return null;
		return mvbAccountEntryDiv.toString();
	}

	@Override
	public MvbAccountEntryDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MvbAccountEntryDiv.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
