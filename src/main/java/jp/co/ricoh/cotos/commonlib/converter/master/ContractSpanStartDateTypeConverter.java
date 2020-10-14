package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ContractSpanStartDateType;

@Converter(autoApply = true)
public class ContractSpanStartDateTypeConverter implements AttributeConverter<ContractSpanStartDateType, String> {

	@Override
	public String convertToDatabaseColumn(ContractSpanStartDateType contractSpanStartDateType) {
		if (contractSpanStartDateType == null)
			return null;
		return contractSpanStartDateType.toString();
	}

	@Override
	public ContractSpanStartDateType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ContractSpanStartDateType.fromString(value); //IllegalArgumentExceptionはContractSpanStartDateType.fromString側で投げている
	}
}