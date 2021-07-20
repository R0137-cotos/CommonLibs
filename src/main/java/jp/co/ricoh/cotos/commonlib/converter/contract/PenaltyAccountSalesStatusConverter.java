package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.PenaltyDetailContract.PenaltyAccountSalesStatus;

@Converter(autoApply = true)
public class PenaltyAccountSalesStatusConverter implements AttributeConverter<PenaltyAccountSalesStatus, String> {

	@Override
	public String convertToDatabaseColumn(PenaltyAccountSalesStatus penaltyAccountSalesStatus) {
		if (penaltyAccountSalesStatus == null)
			return null;
		return penaltyAccountSalesStatus.toString();
	}

	@Override
	public PenaltyAccountSalesStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return PenaltyAccountSalesStatus.fromString(value); // IllegalArgumentExceptionはPenaltyAccountSalesStatus.fromString側で投げている
	}

}
