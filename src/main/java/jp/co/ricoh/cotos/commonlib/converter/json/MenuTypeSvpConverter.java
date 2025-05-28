package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MenuTypeSvp;

@Converter(autoApply = true)
public class MenuTypeSvpConverter implements AttributeConverter<MenuTypeSvp, String> {
	@Override
	public String convertToDatabaseColumn(MenuTypeSvp menuTypeSvp) {
		if (menuTypeSvp == null)
			return null;
		return menuTypeSvp.toString();
	}

	@Override
	public MenuTypeSvp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MenuTypeSvp.fromString(value);
	}
}
