package jp.co.ricoh.cotos.commonlib.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.common.VMailAddressList.TableType;

@Converter(autoApply = true)
public class TableTypeConverter implements AttributeConverter<TableType, String> {
	@Override
	public String convertToDatabaseColumn(TableType tableType) {
		if (tableType == null)
			return null;
		return tableType.toString();
	}

	@Override
	public TableType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return TableType.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
