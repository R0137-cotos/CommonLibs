package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessMaster.OperationDiv;

@Converter(autoApply = true)
public class OperationDivConverter implements AttributeConverter<OperationDiv, String> {

	@Override
	public String convertToDatabaseColumn(OperationDiv operationDiv) {
		if (operationDiv == null)
			return null;
		return operationDiv.toString();
	}

	@Override
	public OperationDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OperationDiv.fromString(value); //IllegalArgumentExceptionはOperationDiv.fromString側で投げている
	}
}