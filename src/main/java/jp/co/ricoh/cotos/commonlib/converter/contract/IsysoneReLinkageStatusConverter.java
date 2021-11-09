package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.IsysoneReLinkageStatus;
@Converter(autoApply = true)
public class IsysoneReLinkageStatusConverter implements AttributeConverter<IsysoneReLinkageStatus, String> {

	@Override
	public String convertToDatabaseColumn(IsysoneReLinkageStatus isysoneReLinkageStatus) {
		if (isysoneReLinkageStatus == null)
			return null;
		return isysoneReLinkageStatus.toString();
	}

	@Override
	public IsysoneReLinkageStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return IsysoneReLinkageStatus.fromString(value); // IllegalArgumentExceptionはIsysoneReLinkageStatus.fromString側で投げている
	}

}
