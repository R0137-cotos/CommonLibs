package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.ElectronicContractLinkageReportDiv;

@Converter(autoApply = true)
public class ElectronicContractLinkageReportDivConverter implements AttributeConverter<ElectronicContractLinkageReportDiv, String> {

	@Override
	public String convertToDatabaseColumn(ElectronicContractLinkageReportDiv electronicContractLinkageReportDiv) {
		if (electronicContractLinkageReportDiv == null)
			return null;
		return electronicContractLinkageReportDiv.toString();
	}

	@Override
	public ElectronicContractLinkageReportDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ElectronicContractLinkageReportDiv.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}

}
