package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.contract.ProductContractExtendsParameterNspDto.TrendLinkageFlg;

@Converter(autoApply = true)
public class TrendLinkageFlgConverter implements AttributeConverter<TrendLinkageFlg, String> {
	@Override
	public String convertToDatabaseColumn(TrendLinkageFlg trendLinkageFlg) {
		if (trendLinkageFlg == null)
			return null;
		return trendLinkageFlg.toString();
	}

	@Override
	public TrendLinkageFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return TrendLinkageFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
