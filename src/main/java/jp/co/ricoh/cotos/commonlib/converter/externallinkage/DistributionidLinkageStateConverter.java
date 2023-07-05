package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.LaitLinkageInfo.DistributionidLinkageState;

@Converter(autoApply = true)
public class DistributionidLinkageStateConverter implements AttributeConverter<DistributionidLinkageState, String> {

	@Override
	public String convertToDatabaseColumn(DistributionidLinkageState distributionidLinkageState) {
		if (distributionidLinkageState == null)
			return null;
		return distributionidLinkageState.toString();
	}

	@Override
	public DistributionidLinkageState convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DistributionidLinkageState.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
