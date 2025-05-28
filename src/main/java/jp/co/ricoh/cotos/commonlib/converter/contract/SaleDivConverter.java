package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.SaleDiv;

@Converter(autoApply = true)
public class SaleDivConverter implements AttributeConverter<SaleDiv, String> {

	@Override
	public String convertToDatabaseColumn(SaleDiv saleDiv) {
		if (saleDiv == null)
			return null;
		return saleDiv.toString();
	}

	@Override
	public SaleDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return SaleDiv.fromString(value); // IllegalArgumentExceptionはSaleDiv.fromString側で投げている
	}
}
