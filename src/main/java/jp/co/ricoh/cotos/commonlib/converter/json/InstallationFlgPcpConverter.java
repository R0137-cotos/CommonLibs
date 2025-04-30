package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.InstallationFlgPcp;

@Converter(autoApply = true)
public class InstallationFlgPcpConverter implements AttributeConverter<InstallationFlgPcp, String> {
	@Override
	public String convertToDatabaseColumn(InstallationFlgPcp installationFlgPcp) {
		if (installationFlgPcp == null)
			return null;
		return installationFlgPcp.toString();
	}

	@Override
	public InstallationFlgPcp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return InstallationFlgPcp.fromString(value);
	}
}
