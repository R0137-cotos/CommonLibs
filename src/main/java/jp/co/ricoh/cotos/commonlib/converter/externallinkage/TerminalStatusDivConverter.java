package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.MobileEquipment.TerminalStatusDiv;

@Converter(autoApply = true)
public class TerminalStatusDivConverter implements AttributeConverter<TerminalStatusDiv, String> {

	@Override
	public String convertToDatabaseColumn(TerminalStatusDiv terminalStatusDiv) {
		if (terminalStatusDiv == null)
			return null;
		return terminalStatusDiv.toString();
	}

	@Override
	public TerminalStatusDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return TerminalStatusDiv.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
