package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.MachineType;

@Converter(autoApply = true)
public class MachineTypeConverter implements AttributeConverter<MachineType, String> {

	@Override
	public String convertToDatabaseColumn(MachineType machineType) {
		if (machineType == null)
			return null;
		return machineType.toString();
	}

	@Override
	public MachineType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MachineType.fromString(value); // IllegalArgumentExceptionはMachineType.fromString側で投げている
	}

}
