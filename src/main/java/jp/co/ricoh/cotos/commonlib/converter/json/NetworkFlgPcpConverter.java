package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.NetworkFlgPcp;

@Converter(autoApply = true)
public class NetworkFlgPcpConverter implements AttributeConverter<NetworkFlgPcp, String> {
	@Override
	public String convertToDatabaseColumn(NetworkFlgPcp networkFlgPcp) {
		if (networkFlgPcp == null)
			return null;
		return networkFlgPcp.toString();
	}

	@Override
	public NetworkFlgPcp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return NetworkFlgPcp.fromString(value);
	}
}
