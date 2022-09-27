package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster.SsWorkRequestRootDiv;

@Converter(autoApply = true)
public class SsWorkRequestRootDivConverter implements AttributeConverter<SsWorkRequestRootDiv, String> {

	@Override
	public String convertToDatabaseColumn(SsWorkRequestRootDiv ssWorkRequestRootDiv) {
		if (ssWorkRequestRootDiv == null)
			return null;
		return ssWorkRequestRootDiv.toString();
	}

	@Override
	public SsWorkRequestRootDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return SsWorkRequestRootDiv.fromString(value); //IllegalArgumentExceptionはContractSpanStartDateType.fromString側で投げている
	}
}