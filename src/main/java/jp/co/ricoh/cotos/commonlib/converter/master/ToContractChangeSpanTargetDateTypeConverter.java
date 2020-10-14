package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster.ToContractChangeSpanTargetDateType;

@Converter(autoApply = true)
public class ToContractChangeSpanTargetDateTypeConverter implements AttributeConverter<ToContractChangeSpanTargetDateType, String> {

	@Override
	public String convertToDatabaseColumn(ToContractChangeSpanTargetDateType toContractChangeSpanTargetDateType) {
		if (toContractChangeSpanTargetDateType == null)
			return null;
		return toContractChangeSpanTargetDateType.toString();
	}

	@Override
	public ToContractChangeSpanTargetDateType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ToContractChangeSpanTargetDateType.fromString(value); //IllegalArgumentExceptionはToContractChangeSpanTargetDateType.fromString側で投げている
	}
}