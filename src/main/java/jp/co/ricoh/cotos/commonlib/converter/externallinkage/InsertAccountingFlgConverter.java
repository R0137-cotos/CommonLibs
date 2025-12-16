package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalBilling.InsertAccountingFlg;

@Converter(autoApply = true)
public class InsertAccountingFlgConverter implements AttributeConverter<InsertAccountingFlg, String> {

	@Override
	public String convertToDatabaseColumn(InsertAccountingFlg insertAccountingFlg) {
		if (insertAccountingFlg == null)
			return null;
		return insertAccountingFlg.toString();
	}

	@Override
	public InsertAccountingFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return InsertAccountingFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
