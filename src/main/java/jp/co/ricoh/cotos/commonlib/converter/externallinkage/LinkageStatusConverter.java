package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.McafeeLinkageInfo.LinkageStatus;

@Converter(autoApply = true)
public class LinkageStatusConverter implements AttributeConverter<LinkageStatus, String> {

	@Override
	public String convertToDatabaseColumn(LinkageStatus processDiv) {
		if (processDiv == null)
			return null;
		return processDiv.toString();
	}

	@Override
	public LinkageStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return LinkageStatus.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
