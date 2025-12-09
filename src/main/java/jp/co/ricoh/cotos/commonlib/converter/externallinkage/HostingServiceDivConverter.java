package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverIspLinkage.HostingServiceDiv;

@Converter(autoApply = true)
public class HostingServiceDivConverter implements AttributeConverter<HostingServiceDiv, String> {

	@Override
	public String convertToDatabaseColumn(HostingServiceDiv hostingServiceDiv) {
		if (hostingServiceDiv == null)
			return null;
		return hostingServiceDiv.toString();
	}

	@Override
	public HostingServiceDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return HostingServiceDiv.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
