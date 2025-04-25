package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractDateSettingMaster.BillingStartDateSettingDate;

@Converter(autoApply = true)
public class BillingStartDateSettingDateConverter implements AttributeConverter<BillingStartDateSettingDate, String> {

	@Override
	public String convertToDatabaseColumn(BillingStartDateSettingDate billingStartDateSettingDate) {
		if (billingStartDateSettingDate == null)
			return null;
		return billingStartDateSettingDate.toString();
	}

	@Override
	public BillingStartDateSettingDate convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return BillingStartDateSettingDate.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}