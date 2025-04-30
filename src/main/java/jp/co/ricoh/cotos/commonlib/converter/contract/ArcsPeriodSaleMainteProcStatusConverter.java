package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.ArcsPeriodSaleMainteProcStatus;

@Converter(autoApply = true)
public class ArcsPeriodSaleMainteProcStatusConverter implements AttributeConverter<ArcsPeriodSaleMainteProcStatus, String> {

	@Override
	public String convertToDatabaseColumn(ArcsPeriodSaleMainteProcStatus arcsPeriodSaleMainteProcStatus) {
		if (arcsPeriodSaleMainteProcStatus == null)
			return null;
		return arcsPeriodSaleMainteProcStatus.toString();
	}

	@Override
	public ArcsPeriodSaleMainteProcStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ArcsPeriodSaleMainteProcStatus.fromString(value); // IllegalArgumentExceptionはArcsPeriodSaleMainteProcStatus.fromString側で投げている
	}

}
