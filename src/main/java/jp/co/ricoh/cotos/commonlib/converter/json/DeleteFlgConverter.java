package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.DeleteFlg;

@Converter(autoApply = true)
public class DeleteFlgConverter implements AttributeConverter<DeleteFlg, String> {
	@Override
	public String convertToDatabaseColumn(DeleteFlg deleteFlg) {
		if (deleteFlg == null)
			return null;
		return deleteFlg.toString();
	}

	@Override
	public DeleteFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DeleteFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
