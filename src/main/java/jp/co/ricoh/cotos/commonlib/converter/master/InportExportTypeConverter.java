package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.FileKindManagementMaster.InportExportType;

@Converter(autoApply = true)
public class InportExportTypeConverter implements AttributeConverter<InportExportType, String> {

	@Override
	public String convertToDatabaseColumn(InportExportType inportExportType) {
		if (inportExportType == null)
			return null;
		return inportExportType.toString();
	}

	@Override
	public InportExportType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return InportExportType.fromString(value); //IllegalArgumentExceptionはInportExportType.fromString側で投げている
	}
}