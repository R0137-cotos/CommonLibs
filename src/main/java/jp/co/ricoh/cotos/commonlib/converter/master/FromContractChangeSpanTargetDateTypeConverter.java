package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster.FromContractChangeSpanTargetDateType;


@Converter(autoApply = true)
public class FromContractChangeSpanTargetDateTypeConverter implements AttributeConverter<FromContractChangeSpanTargetDateType, String> {

	@Override
	public String convertToDatabaseColumn(FromContractChangeSpanTargetDateType fromContractChangeSpanTargetDateType) {
		if (fromContractChangeSpanTargetDateType == null)
			return null;
		return fromContractChangeSpanTargetDateType.toString();
	}

	@Override
	public FromContractChangeSpanTargetDateType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return FromContractChangeSpanTargetDateType.fromString(value); //IllegalArgumentExceptionはFromContractChangeSpanTargetDateType.fromString側で投げている
	}
}