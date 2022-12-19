package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.VendorMaster.EtransporterLinkageDiv;

@Converter(autoApply = true)
public class EtransporterLinkageDivConverter implements AttributeConverter<EtransporterLinkageDiv, String> {

	@Override
	public String convertToDatabaseColumn(EtransporterLinkageDiv etransporterLinkageDiv) {
		if (etransporterLinkageDiv == null)
			return null;
		return etransporterLinkageDiv.toString();
	}

	@Override
	public EtransporterLinkageDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		// IllegalArgumentExceptionはAlertTargetDateType.fromString側で投げている
		return EtransporterLinkageDiv.fromString(value);
	}
}
