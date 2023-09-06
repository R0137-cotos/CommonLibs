package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.TaxCategory;

@Converter(autoApply = true)
public class TaxCategoryConverter implements AttributeConverter<TaxCategory, String> {
	@Override
	public String convertToDatabaseColumn(TaxCategory taxCategory) {
		if (taxCategory == null)
			return null;
		return taxCategory.toString();
	}

	@Override
	public TaxCategory convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return TaxCategory.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
