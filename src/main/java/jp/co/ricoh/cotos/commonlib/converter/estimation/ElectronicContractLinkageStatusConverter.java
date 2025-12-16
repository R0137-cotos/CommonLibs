package jp.co.ricoh.cotos.commonlib.converter.estimation;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation.ElectronicContractLinkageStatus;

@Converter(autoApply = true)
public class ElectronicContractLinkageStatusConverter implements AttributeConverter<ElectronicContractLinkageStatus, String> {
	@Override
	public String convertToDatabaseColumn(ElectronicContractLinkageStatus electronicContractLinkageStatus) {
		if (electronicContractLinkageStatus == null)
			return null;
		return electronicContractLinkageStatus.toString();
	}

	@Override
	public ElectronicContractLinkageStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ElectronicContractLinkageStatus.fromString(value);
	}
}
