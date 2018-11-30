package jp.co.ricoh.cotos.commonlib.converter.rsi;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.rsi.SalesCalcResultWork.CotosProcessingFlg;

@Converter(autoApply = true)
public class CotosProcessingFlgConverter implements AttributeConverter<CotosProcessingFlg, String> {

	@Override
	public String convertToDatabaseColumn(CotosProcessingFlg cotosProcessingFlg) {
		if (cotosProcessingFlg == null)
			return null;
		return cotosProcessingFlg.toString();
	}

	@Override
	public CotosProcessingFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CotosProcessingFlg.fromString(value); 
	}
}