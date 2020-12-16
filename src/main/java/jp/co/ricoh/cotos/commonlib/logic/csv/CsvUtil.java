package jp.co.ricoh.cotos.commonlib.logic.csv;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import jp.co.ricoh.cotos.commonlib.dto.parameter.common.CsvParameter;
import jp.co.ricoh.cotos.commonlib.entity.master.CsvFileSettingMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import lombok.AllArgsConstructor;

@Component
public class CsvUtil {

	@Autowired
	CheckUtil checkUtil;

	/**
	 * CSV情報生成
	 *
	 * <pre>
	 * 【処理内容】
	 * ・引数のエンティティリストからCSV情報を作成
	 * ・各種設定は引数のCSVパラメータで設定可能
	 * </pre>
	 *
	 * @param entityList
	 *            エンティティリスト
	 * @param param
	 *            CSVパラメータ
	 * @return CSV情報
	 * @throws ErrorCheckException
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 */
	@SuppressWarnings("hiding")
	public <T> byte[] createCsvData(List<T> entityList, CsvParameter param) throws ErrorCheckException, JsonProcessingException {
		if (entityList == null || entityList.size() == 0) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "CsvEntityListEmptyError"));
		}

		CsvMapper mapper = new CsvMapper();
		CsvParameter prm = Optional.ofNullable(param).orElse(CsvParameter.builder().build());

		// 各種パラメーター設定
		CsvSchema schema = mapper.typedSchemaFor(entityList.get(0).getClass()) //
				.withUseHeader(prm.isHeader()) //
				.withColumnSeparator(prm.getSeparator()) //
				.withLineSeparator(prm.getLineSeparator()) //
				.withNullValue(prm.getNullValueString()); //
		mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, prm.isQuote());

		// シリアライザーの設定
		DefaultSerializerProvider dsp = new DefaultSerializerProvider.Impl();
		dsp.setNullValueSerializer(new NullValueSerializer(prm.getNullValueString()));
		mapper.setSerializerProvider(dsp);

		String csv = mapper.writer(schema).writeValueAsString(entityList);

		return csv.getBytes(prm.getCharset());
	}

	/**
	 * NULLオブジェクトのカスタムシリアライザー
	 */
	@AllArgsConstructor
	private static class NullValueSerializer extends JsonSerializer<Object> {
		private String nullValueString;

		@Override
		public void serialize(Object t, JsonGenerator jsonGenerator, SerializerProvider sp) throws IOException, JsonProcessingException {
			if (t == null) {
				jsonGenerator.writeRawValue(nullValueString);
			}
		}
	}

	/**
	 * CSVパラメータを取得します
	 * @param CsvFileSettingMaster
	 * @return CSV設定情報
	 */
	public CsvParameter getCsvParameter(CsvFileSettingMaster csvFileSettingMaster) {
		if (csvFileSettingMaster == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ParameterEmptyError", new String[] { "CSVファイル設定マスタ" }));
		}
		CsvParameter csvParameter = CsvParameter.builder().build();
		csvParameter.setHeader(isCsvHeder(csvFileSettingMaster.getCsvHeaderFlg()));
		csvParameter.setSeparator(getSeparator(csvFileSettingMaster.getCsvSeparator()));
		csvParameter.setCharset(Charset.forName(Optional.ofNullable(csvFileSettingMaster.getCsvCharset()).orElse("UTF-8")));
		csvParameter.setLineSeparator(Optional.ofNullable(csvFileSettingMaster.getCsvLineSeparator()).orElse("\n"));
		csvParameter.setQuote(isCsvQuote(Integer.valueOf(csvFileSettingMaster.getCsvQuote())));
		csvParameter.setNullValueString(Optional.ofNullable(csvFileSettingMaster.getCsvNullValueString()).orElse("null"));
		return csvParameter;
	}

	/**
	 * CSVファイルヘッダー有無を返却します。
	 *
	 * @param val
	 * @return boolean
	 */
	private boolean isCsvHeder(Integer val) {
		if (null == val || val == 1) {
			return true;
		}
		return false;
	}

	/**
	 * CSVファイル囲み文字有無を返却します。
	 *
	 * @param val
	 * @return boolean
	 */
	private boolean isCsvQuote(Integer val) {
		if (null == val || val == 1) {
			return true;
		}
		return false;
	}

	/**
	 * セパレータ文字を返却します。
	 *
	 * @param val
	 * @return セパレータ
	 */
	private char getSeparator(String val) {
		char separator = ',';
		switch (val) {
		case "1":
			separator = ',';
			break;
		case "2":
			separator = ':';
			break;
		case "3":
			separator = ';';
			break;
		}
		return separator;
	}
}
