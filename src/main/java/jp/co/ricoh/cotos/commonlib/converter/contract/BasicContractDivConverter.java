package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.BasicContractDiv;

@Converter(autoApply = true)
public class BasicContractDivConverter implements AttributeConverter<BasicContractDiv, String> {

	@Override
	public String convertToDatabaseColumn(BasicContractDiv basicContractDiv) {
		if (basicContractDiv == null)
			return null;
		return basicContractDiv.toString();
	}

	@Override
	public BasicContractDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return BasicContractDiv.fromString(value); // IllegalArgumentExceptionはBasicContractDiv.fromString側で投げている
	}
}
