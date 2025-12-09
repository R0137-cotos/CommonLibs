package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.VendorDiv;

@Converter(autoApply = true)
public class VendorDivConverter implements AttributeConverter<VendorDiv, String> {

	@Override
	public String convertToDatabaseColumn(VendorDiv vendorDiv) {
		if (vendorDiv == null)
			return null;
		return vendorDiv.toString();
	}

	@Override
	public VendorDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return VendorDiv.fromString(value); // IllegalArgumentExceptionはSaleDiv.fromString側で投げている
	}
}
