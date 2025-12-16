package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractDateSettingMaster.BillingStartDateSettingType;

@Converter(autoApply = true)
public class BillingStartDateSettingTypeConverter implements AttributeConverter<BillingStartDateSettingType, String> {

	@Override
	public String convertToDatabaseColumn(BillingStartDateSettingType billingStartDateSettingType) {
		if (billingStartDateSettingType == null)
			return null;
		return billingStartDateSettingType.toString();
	}

	@Override
	public BillingStartDateSettingType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return BillingStartDateSettingType.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}