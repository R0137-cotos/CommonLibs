package jp.co.ricoh.cotos.commonlib.converter.accounting;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.accounting.InvoiceLinkage.InvoiceTaxType;

@Converter(autoApply = true)
public class InvoiceTaxTypeConverter implements AttributeConverter<InvoiceTaxType, String> {

	@Override
	public String convertToDatabaseColumn(InvoiceTaxType invoiceTaxType) {
		if (invoiceTaxType == null)
			return null;
		return invoiceTaxType.toString();
	}

	@Override
	public InvoiceTaxType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return InvoiceTaxType.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
