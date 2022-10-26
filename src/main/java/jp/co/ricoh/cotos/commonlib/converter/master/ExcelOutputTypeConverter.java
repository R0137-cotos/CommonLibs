package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ReportTemplateMaster.ExcelOutputType;

@Converter(autoApply = true)
public class ExcelOutputTypeConverter implements AttributeConverter<ExcelOutputType, String> {

	@Override
	public String convertToDatabaseColumn(ExcelOutputType excelOutputType) {
		if (excelOutputType == null)
			return null;
		return excelOutputType.toString();
	}

	@Override
	public ExcelOutputType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ExcelOutputType.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
