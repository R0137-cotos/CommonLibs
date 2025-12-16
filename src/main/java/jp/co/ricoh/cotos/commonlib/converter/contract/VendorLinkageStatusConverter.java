package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.NextUpdateDetailInfo.VendorLinkageStatus;

@Converter(autoApply = true)
public class VendorLinkageStatusConverter implements AttributeConverter<VendorLinkageStatus, String> {
	@Override
	public String convertToDatabaseColumn(VendorLinkageStatus vendorLinkageStatus) {
		if (vendorLinkageStatus == null)
			return null;
		return vendorLinkageStatus.toString();
	}

	@Override
	public VendorLinkageStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return VendorLinkageStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
