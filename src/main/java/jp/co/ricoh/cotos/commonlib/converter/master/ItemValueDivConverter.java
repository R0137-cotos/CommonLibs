package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.SpecificControlMaster.ItemValueDiv;

@Converter(autoApply = true)
public class ItemValueDivConverter implements AttributeConverter<ItemValueDiv, String> {

	@Override
	public String convertToDatabaseColumn(ItemValueDiv itemValueDiv) {
		if (itemValueDiv == null)
			return null;
		return itemValueDiv.toString();
	}

	@Override
	public ItemValueDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ItemValueDiv.fromString(value); //IllegalArgumentExceptionはItemValueDiv.fromString側で投げている
	}
}
