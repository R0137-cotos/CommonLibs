package jp.co.ricoh.cotos.commonlib.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.BatchRunDateManagementMaster.FfmAccountingLinkageDiv;

@Converter(autoApply = true)
public class FfmAccountingLinkageDivConverter implements AttributeConverter<FfmAccountingLinkageDiv, String> {

	@Override
	public String convertToDatabaseColumn(FfmAccountingLinkageDiv ffmAccountingLinkageDiv) {
		if (ffmAccountingLinkageDiv == null)
			return null;
		return ffmAccountingLinkageDiv.toString();
	}

	@Override
	public FfmAccountingLinkageDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return FfmAccountingLinkageDiv.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}

}