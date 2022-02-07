package jp.co.ricoh.cotos.commonlib.converter.estimation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation.ContractChangeTiming;

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
			value = "0";
		return ContractChangeTiming.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}

}
