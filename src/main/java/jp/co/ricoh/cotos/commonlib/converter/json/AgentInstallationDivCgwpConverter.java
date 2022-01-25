package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeCgwp.AgentInstallationDivCgwp;

@Converter(autoApply = true)
public class AgentInstallationDivCgwpConverter implements AttributeConverter<AgentInstallationDivCgwp, String> {
	@Override
	public String convertToDatabaseColumn(AgentInstallationDivCgwp agentInstallationDivCgwp) {
		if (agentInstallationDivCgwp == null)
			return null;
		return agentInstallationDivCgwp.toString();
	}

	@Override
	public AgentInstallationDivCgwp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return AgentInstallationDivCgwp.fromString(value);
	}
}
