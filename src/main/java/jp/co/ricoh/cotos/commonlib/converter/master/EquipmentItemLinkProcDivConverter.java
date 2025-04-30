package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster.EquipmentItemLinkProcDiv;

@Converter(autoApply = true)
public class EquipmentItemLinkProcDivConverter implements AttributeConverter<EquipmentItemLinkProcDiv, String> {
	@Override
	public String convertToDatabaseColumn(EquipmentItemLinkProcDiv equipmentItemLinkProcDiv) {
		if (equipmentItemLinkProcDiv == null)
			return null;
		return equipmentItemLinkProcDiv.toString();
	}

	@Override
	public EquipmentItemLinkProcDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return EquipmentItemLinkProcDiv.fromString(value);
	}
}