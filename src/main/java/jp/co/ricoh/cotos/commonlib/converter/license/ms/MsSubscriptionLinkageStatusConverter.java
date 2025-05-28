package jp.co.ricoh.cotos.commonlib.converter.license.ms;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsSubscriptionRegisterRequestWork.MsSubscriptionLinkageStatus;

@Converter(autoApply = true)
public class MsSubscriptionLinkageStatusConverter implements AttributeConverter<MsSubscriptionLinkageStatus, String> {
	@Override
	public String convertToDatabaseColumn(MsSubscriptionLinkageStatus subscriptionLinkageStatus) {
		if (subscriptionLinkageStatus == null)
			return null;
		return subscriptionLinkageStatus.toString();
	}

	@Override
	public MsSubscriptionLinkageStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MsSubscriptionLinkageStatus.fromString(value); // IllegalArgumentExceptionはMsSubscriptionLinkageStatus.fromString側で投げている
	}
}
