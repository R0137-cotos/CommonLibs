package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.LaitLinkageInfo.EntryStatus;

@Converter(autoApply = true)
public class EntryStatusConverter implements AttributeConverter<EntryStatus, String> {

	@Override
	public String convertToDatabaseColumn(EntryStatus EntryStatus) {
		if (EntryStatus == null)
			return null;
		return EntryStatus.toString();
	}

	@Override
	public EntryStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return EntryStatus.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
