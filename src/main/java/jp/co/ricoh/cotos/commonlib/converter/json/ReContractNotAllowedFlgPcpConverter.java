package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
