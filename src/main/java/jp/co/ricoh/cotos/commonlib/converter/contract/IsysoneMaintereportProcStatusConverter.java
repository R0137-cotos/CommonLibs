package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.IsysoneMaintereportProcStatus;

@Converter(autoApply = true)
public class IsysoneMaintereportProcStatusConverter implements AttributeConverter<IsysoneMaintereportProcStatus, String> {

	@Override
	public String convertToDatabaseColumn(IsysoneMaintereportProcStatus isysoneMaintereportProcStatus) {
		if (isysoneMaintereportProcStatus == null)
			return null;
		return isysoneMaintereportProcStatus.toString();
	}

	@Override
	public IsysoneMaintereportProcStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return IsysoneMaintereportProcStatus.fromString(value); // IllegalArgumentExceptionはIsysoneMaintereportProcStatus.fromString側で投げている
	}

}
