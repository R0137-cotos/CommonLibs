package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ReportTemplateMaster.DeleteSheetDiv;

@Converter(autoApply = true)
public class DeleteSheetDivConverter implements AttributeConverter<DeleteSheetDiv, String> {

	@Override
	public String convertToDatabaseColumn(DeleteSheetDiv deleteSheetDiv) {
		if (deleteSheetDiv == null)
			return null;
		return deleteSheetDiv.toString();
	}

	@Override
	public DeleteSheetDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DeleteSheetDiv.fromString(value); //IllegalArgumentExceptionはDeleteSheetDiv.fromString側で投げている
	}
}
