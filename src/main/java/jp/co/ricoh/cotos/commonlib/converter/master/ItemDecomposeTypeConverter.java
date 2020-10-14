package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemDecomposeType;

@Converter(autoApply = true)
public class ItemDecomposeTypeConverter implements AttributeConverter<ItemDecomposeType, String> {

	@Override
	public String convertToDatabaseColumn(ItemDecomposeType itemDecomposeType) {
		if (itemDecomposeType == null)
			return null;
		return itemDecomposeType.toString();
	}

	@Override
	public ItemDecomposeType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ItemDecomposeType.fromString(value); //IllegalArgumentExceptionはItemDecomposeType.fromString側で投げている
	}
}