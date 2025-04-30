package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentAdditionInfo.MigarateEquipmentDiv;

@Converter(autoApply = true)
public class MigarateEquipmentDivConverter implements AttributeConverter<MigarateEquipmentDiv, String> {

	@Override
	public String convertToDatabaseColumn(MigarateEquipmentDiv migarateEquipmentDiv) {
		if (migarateEquipmentDiv == null)
			return null;
		return migarateEquipmentDiv.toString();
	}

	@Override
	public MigarateEquipmentDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MigarateEquipmentDiv.fromString(value);
	}

}
