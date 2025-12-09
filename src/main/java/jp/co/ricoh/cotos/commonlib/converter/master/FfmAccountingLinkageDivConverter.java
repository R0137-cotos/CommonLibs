package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
