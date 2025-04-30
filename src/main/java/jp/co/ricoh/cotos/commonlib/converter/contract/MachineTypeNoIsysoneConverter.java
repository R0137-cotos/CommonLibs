package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentNoIsysone.MachineTypeNoIsysone;

@Converter(autoApply = true)
public class MachineTypeNoIsysoneConverter implements AttributeConverter<MachineTypeNoIsysone, String> {

	@Override
	public String convertToDatabaseColumn(MachineTypeNoIsysone machineType) {
		if (machineType == null)
			return null;
		return machineType.toString();
	}

	@Override
	public MachineTypeNoIsysone convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MachineTypeNoIsysone.fromString(value); // IllegalArgumentExceptionはMachineType.fromString側で投げている
	}

}
