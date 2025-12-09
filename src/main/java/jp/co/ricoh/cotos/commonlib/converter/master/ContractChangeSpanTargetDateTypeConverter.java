package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster.ContractChangeSpanTargetDateType;

@Converter(autoApply = true)
public class ContractChangeSpanTargetDateTypeConverter implements AttributeConverter<ContractChangeSpanTargetDateType, String> {

	@Override
	public String convertToDatabaseColumn(ContractChangeSpanTargetDateType contractChangeSpanTargetDateType) {
		if (contractChangeSpanTargetDateType == null)
			return null;
		return contractChangeSpanTargetDateType.toString();
	}

	@Override
	public ContractChangeSpanTargetDateType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ContractChangeSpanTargetDateType.fromString(value); //IllegalArgumentExceptionはContractChangeSpanTargetDateType.fromString側で投げている
	}
}