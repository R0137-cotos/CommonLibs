package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.FileKindManagementMaster.ImportExportType;

@Converter(autoApply = true)
public class ImportExportTypeConverter implements AttributeConverter<ImportExportType, String> {

	@Override
	public String convertToDatabaseColumn(ImportExportType inportExportType) {
		if (inportExportType == null)
			return null;
		return inportExportType.toString();
	}

	@Override
	public ImportExportType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ImportExportType.fromString(value); //IllegalArgumentExceptionはInportExportType.fromString側で投げている
	}
}