package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentAdditionInfo.ChangeKbn;

@Converter(autoApply = true)
public class ChangeKbnConverter implements AttributeConverter<ChangeKbn, String> {

	@Override
	public String convertToDatabaseColumn(ChangeKbn changeKbn) {
		if (changeKbn == null)
			return null;
		return changeKbn.toString();
	}

	@Override
	public ChangeKbn convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ChangeKbn.fromString(value);
	}

}
