package jp.co.ricoh.cotos.commonlib.entity.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.CollectLocation.LinkageState;


@Converter(autoApply = true)
public class LinkageStateConverter implements AttributeConverter<LinkageState, String> {

	@Override
	public String convertToDatabaseColumn(LinkageState linkageState) {
		if (linkageState == null)
			return null;
		return linkageState.toString();
	}

	@Override
	public LinkageState convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return LinkageState.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
