package jp.co.ricoh.cotos.commonlib.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.ContractChangeTiming;

@Converter(autoApply = true)
public class ContractChangeTimingConverter implements AttributeConverter<ContractChangeTiming, String> {

	@Override
	public String convertToDatabaseColumn(ContractChangeTiming contractChangeTiming) {
		if (contractChangeTiming == null)
			return null;
		return contractChangeTiming.toString();
	}

	@Override
	public ContractChangeTiming convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ContractChangeTiming.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}

}