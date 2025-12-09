package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster.TransferHeaderSettingDiv;

@Converter(autoApply = true)
public class TransferHeaderSettingDivConverter implements AttributeConverter<TransferHeaderSettingDiv, String> {
	@Override
	public String convertToDatabaseColumn(TransferHeaderSettingDiv increaseDecreaseDiv) {
		if (increaseDecreaseDiv == null)
			return null;
		return increaseDecreaseDiv.toString();
	}

	@Override
	public TransferHeaderSettingDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return TransferHeaderSettingDiv.fromString(value);
	}
}