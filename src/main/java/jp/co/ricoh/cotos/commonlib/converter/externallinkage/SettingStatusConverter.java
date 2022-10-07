package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.IijMachineInfo.SettingStatus;

@Converter(autoApply = true)
public class SettingStatusConverter implements AttributeConverter<SettingStatus, String> {

	@Override
	public String convertToDatabaseColumn(SettingStatus SettingStatus) {
		if (SettingStatus == null)
			return null;
		return SettingStatus.toString();
	}

	@Override
	public SettingStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return SettingStatus.fromString(value); // IllegalArgumentExceptionはInfoDiv.fromString側で投げている
	}
}
