package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.InstallFlgPcp;

@Converter(autoApply = true)
public class InstallFlgPcpConverter implements AttributeConverter<InstallFlgPcp, String> {
	@Override
	public String convertToDatabaseColumn(InstallFlgPcp installFlgPcp) {
		if (installFlgPcp == null)
			return null;
		return installFlgPcp.toString();
	}

	@Override
	public InstallFlgPcp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return InstallFlgPcp.fromString(value);
	}
}
