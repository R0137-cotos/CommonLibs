package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ArcsPeriodSaleMntOriginStatus;

@Converter(autoApply = true)
public class ArcsPeriodSaleMntOriginStatusConverter implements AttributeConverter<ArcsPeriodSaleMntOriginStatus, String> {

	@Override
	public String convertToDatabaseColumn(ArcsPeriodSaleMntOriginStatus arcsPeriodSaleMntOriginStatus) {
		if (arcsPeriodSaleMntOriginStatus == null)
			return null;
		return arcsPeriodSaleMntOriginStatus.toString();
	}

	@Override
	public ArcsPeriodSaleMntOriginStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ArcsPeriodSaleMntOriginStatus.fromString(value); // IllegalArgumentExceptionはArcsPeriodSaleMainteProcStatus.fromString側で投げている
	}

}
