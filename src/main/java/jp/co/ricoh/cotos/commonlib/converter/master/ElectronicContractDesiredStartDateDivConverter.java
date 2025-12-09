package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster.ElectronicContractDesiredStartDateDiv;

@Converter(autoApply = true)
public class ElectronicContractDesiredStartDateDivConverter implements AttributeConverter<ElectronicContractDesiredStartDateDiv, String> {

	@Override
	public String convertToDatabaseColumn(ElectronicContractDesiredStartDateDiv electronicContractDesiredStartDateDiv) {
		if (electronicContractDesiredStartDateDiv == null)
			return null;
		return electronicContractDesiredStartDateDiv.toString();
	}

	@Override
	public ElectronicContractDesiredStartDateDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ElectronicContractDesiredStartDateDiv.fromString(value); //IllegalArgumentExceptionはContractSpanStartDateType.fromString側で投げている
	}
}