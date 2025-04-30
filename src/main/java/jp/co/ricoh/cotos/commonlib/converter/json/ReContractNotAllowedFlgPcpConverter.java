package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.ReContractNotAllowedFlgPcp;

@Converter(autoApply = true)
public class ReContractNotAllowedFlgPcpConverter implements AttributeConverter<ReContractNotAllowedFlgPcp, String> {
	@Override
	public String convertToDatabaseColumn(ReContractNotAllowedFlgPcp reContractNotAllowedFlgPcp) {
		if (reContractNotAllowedFlgPcp == null)
			return null;
		return reContractNotAllowedFlgPcp.toString();
	}

	@Override
	public ReContractNotAllowedFlgPcp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ReContractNotAllowedFlgPcp.fromString(value);
	}
}
