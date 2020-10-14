package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseRemainingNumber.AllocationDiv;

@Converter(autoApply = true)
public class AllocationDivConverter implements AttributeConverter<AllocationDiv, String> {

	@Override
	public String convertToDatabaseColumn(AllocationDiv allocationDiv) {
		if (allocationDiv == null)
			return null;
		return allocationDiv.toString();
	}

	@Override
	public AllocationDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return AllocationDiv.fromString(value); // IllegalArgumentExceptionはAllocationDiv.fromString側で投げている
	}

}
