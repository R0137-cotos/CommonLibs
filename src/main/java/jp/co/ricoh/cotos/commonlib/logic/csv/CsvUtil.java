package jp.co.ricoh.cotos.commonlib.logic.csv;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.text.Transliterator;

import jp.co.ricoh.cotos.commonlib.dto.parameter.common.CsvParameter;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.WidthForm;
import jp.co.ricoh.cotos.commonlib.entity.master.CsvFileSettingMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.logic.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
@NoArgsConstructor
public class CsvUtil {

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	JsonUtil jsonUtil;

	private static final String CHARSET_NAME = "MS932";

	private static final Charset BOM_SETTING_POSSIBLE_ENCODE = Charset.forName("UTF-8");

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

		if (prm.isQuote() && prm.isWithoutQuoteChar()) {
			throw new IllegalArgumentException("quoteとwithoutQuoteCharの両方にtrueを設定することはできません。");
		}

		// 各種パラメーター設定
		CsvSchema schema;
		if (prm.isWithoutQuoteChar()) {
			schema = mapper.typedSchemaFor(entityList.get(0).getClass()) //
					.withUseHeader(prm.isHeader()) //
					.withColumnSeparator(prm.getSeparator()) //
					.withLineSeparator(prm.getLineSeparator()) //
					.withNullValue(prm.getNullValueString()) //
					.withoutQuoteChar();
		} else {
			schema = mapper.typedSchemaFor(entityList.get(0).getClass()) //
					.withUseHeader(prm.isHeader()) //
					.withColumnSeparator(prm.getSeparator()) //
					.withLineSeparator(prm.getLineSeparator()) //
					.withNullValue(prm.getNullValueString()); //
		}
		mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, prm.isQuote());

		// シリアライザーの設定
		DefaultSerializerProvider dsp = new DefaultSerializerProvider.Impl();
		dsp.setNullValueSerializer(new NullValueSerializer(prm.getNullValueString()));
		mapper.setSerializerProvider(dsp);

		String csv = mapper.writer(schema).writeValueAsString(entityList);

		return convertStrToByteArray(csv, prm);
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
	 * 
	 * @param CsvFileSettingMaster
	 * @return CSV設定情報
	 */
	public CsvParameter getCsvParameter(CsvFileSettingMaster csvFileSettingMaster) {
		if (csvFileSettingMaster == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ParameterEmptyError", new String[] { "CSVファイル設定マスタ" }));
		}
		CsvParameter csvParameter = CsvParameter.builder().build();
		csvParameter.setHeader(flgBooleanConverter(csvFileSettingMaster.getCsvHeaderFlg()));
		csvParameter.setSeparator(getSeparator(csvFileSettingMaster.getCsvSeparator()));
		csvParameter.setCharset(Charset.forName(Optional.ofNullable(csvFileSettingMaster.getCsvCharset()).orElse("UTF-8")));
		csvParameter.setLineSeparator(getLineSeparator(csvFileSettingMaster.getCsvLineSeparator()));
		csvParameter.setQuote(flgBooleanConverter(csvFileSettingMaster.getCsvQuote()));
		csvParameter.setNullValueString(Optional.ofNullable(csvFileSettingMaster.getCsvNullValueString()).orElse(""));
		csvParameter.setWithoutQuoteChar(flgBooleanConverter(csvFileSettingMaster.getCsvWithoutQuoteChar(), false));
		csvParameter.setBomSettingFlg(flgBooleanConverter(csvFileSettingMaster.getBomSettingFlg(), false));
		return csvParameter;
	}

	private boolean flgBooleanConverter(Integer flg) {
		return flgBooleanConverter(flg, true);
	}

	/**
	 * フラグを真偽値にコンバートします。
	 *
	 * @param flg
	 * @return boolean
	 */
	private boolean flgBooleanConverter(Integer flg, boolean defaultValue) {
		if (null == flg) {
			return defaultValue;
		}
		if (flg == 1) {
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
		if (StringUtils.isEmpty(val)) {
			return separator;
		}
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
		case "4":
			separator = '\t';
			break;
		}
		return separator;
	}

	/**
	 * 改行コードを返却します。
	 *
	 * @param val
	 * @return 改行コード
	 */
	private String getLineSeparator(String val) {
		String lineSeparator = "\n";
		if (StringUtils.isEmpty(val)) {
			return lineSeparator;
		}
		switch (val) {
		case "1":
			lineSeparator = "\n";
			break;
		case "2":
			lineSeparator = "\r\n";
			break;
		}
		return lineSeparator;
	}

	/**
	 * 前方から指定バイト数の文字列を取得
	 *
	 * @param baseStr
	 *            文字列
	 * @param byteCount
	 *            バイト数
	 * @param widthForm
	 *            半角 or 全角
	 * @return 文末パディング
	 * @throws UnsupportedEncodingException
	 */
	public String padding(String baseStr, int byteCount, WidthForm widthForm) throws UnsupportedEncodingException {
		if (byteCount < 1 || widthForm == null) {
			throw new IllegalArgumentException();
		}
		if (baseStr == null)
			baseStr = "";
		// 半角・全角変換
		if (widthForm != WidthForm.Nothing) {
			Transliterator transliterator = Transliterator.getInstance(widthForm.getId());
			baseStr = transliterator.transliterate(baseStr);
		}

		if (widthForm == WidthForm.Half) {
			// 全角が残っている場合は、除去
			baseStr = removeDoubleByte(baseStr);
		}

		// 文字列が指定バイト数を超えている
		int blen = baseStr.getBytes(CHARSET_NAME).length;
		if (blen > byteCount) {
			// 超えた文字を削除
			baseStr = new String(baseStr.getBytes(CHARSET_NAME), 0, byteCount, CHARSET_NAME);
		}

		// パディング実施
		StringBuilder builder = new StringBuilder();
		builder.append(baseStr);
		if (widthForm != WidthForm.Nothing) {
			int slen = widthForm.getValue().getBytes(CHARSET_NAME).length;
			for (int i = blen; i < byteCount; i += slen) {
				builder.append(widthForm.getValue());
			}
		}
		// 結果が指定バイト数かチェック
		String result = builder.toString();
		if (result.getBytes(CHARSET_NAME).length > byteCount) {
			throw new IllegalStateException("Request:" + byteCount + " Response:" + result.getBytes(CHARSET_NAME).length);
		}
		return result;
	}

	/**
	 * 後方から指定バイト数の文字列を取得
	 *
	 * @param baseStr
	 *            文字列
	 * @param byteCount
	 *            バイト数
	 * @param widthForm
	 *            半角 or 全角 or 設定無し
	 * @return 文末パディング
	 * @throws UnsupportedEncodingException
	 */
	public String paddingBack(String baseStr, int byteCount, WidthForm widthForm) throws UnsupportedEncodingException {
		if (byteCount < 1 || widthForm == null) {
			throw new IllegalArgumentException();
		}
		if (baseStr == null)
			baseStr = "";
		// 半角・全角変換
		if (widthForm != WidthForm.Nothing) {
			Transliterator transliterator = Transliterator.getInstance(widthForm.getId());
			baseStr = transliterator.transliterate(baseStr);
		}

		if (widthForm == WidthForm.Half) {
			// 全角が残っている場合は、除去
			baseStr = removeDoubleByte(baseStr);
		}

		// 文字列が指定バイト数を超えている
		int blen = baseStr.getBytes(CHARSET_NAME).length;
		if (blen > byteCount) {
			// 超えた文字を削除
			baseStr = new String(baseStr.getBytes(CHARSET_NAME), blen - byteCount, byteCount, CHARSET_NAME);
		}

		// パディング実施
		StringBuilder builder = new StringBuilder();
		builder.append(baseStr);
		if (widthForm != WidthForm.Nothing) {
			int slen = widthForm.getValue().getBytes(CHARSET_NAME).length;
			for (int i = blen; i < byteCount; i += slen) {
				builder.append(widthForm.getValue());
			}
		}
		// バイト数チェック
		String result = builder.toString();
		if (result.getBytes(CHARSET_NAME).length > byteCount) {
			throw new IllegalStateException("Request:" + byteCount + " Response:" + result.getBytes(CHARSET_NAME).length);
		}
		return result;
	}

	private String removeDoubleByte(String source) {
		String result = "";
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			if (!isDoubleByte(c)) {
				result += String.valueOf(c);
			}
		}
		return result;
	}

	private Boolean isDoubleByte(char source) {
		if ((source <= '\u007e') || // 英数字
				(source == '\u00a5') || // \記号
				(source == '\u203e') || // ~記号
				(source >= '\uff61' && source <= '\uff9f') // 半角カナ
		) {
			return false;
		}
		return true;
	}

	/**
	 * CSVデータ内にある文字化け対象の文字を文字化けしない文字に変換します。
	 *
	 * @param csvList
	 *            CSVデータリスト
	 * @param obj
	 *            対応するCSVのDTOクラス
	 * @return 文字化け対応後のCSVデータリスト
	 */
	public <T> List<T> convertGarbledCharForCsvData(List<T> csvList, Class<T> obj) {
		// CSVデータリストがない場合
		if (CollectionUtils.isEmpty(csvList)) {
			return csvList;
		}
		// 対応するCSVのDTOクラスが指定されてない場合
		if (obj == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "Object" }));
		}
		// 文字化け対応後のリスト
		List<T> list = new ArrayList<>();
		csvList.forEach(c -> {
			// DTOをJSON文字列に変換
			String json = jsonUtil.convertToStr(c);
			// 文字化けする文字を変換
			String convertStr = json.replace("‑", "-").replace("–", "-").replace("—", "-").replace("−", "-").replace("∼", "～").replace("－", "-");
			// JSON文字列からDTOに変換
			T convertDto = jsonUtil.convertToDto(convertStr, obj);
			// リストに追加
			list.add(convertDto);
		});

		return list;
	}

	/**
	 * 文字列をバイナリデータに変換します。その際、UTF-8でBOM設定フラグがtrueならBOM付きにします。
	 *
	 * @param str
	 * @param csvParameter
	 * @return
	 */
	private byte[] convertStrToByteArray(String str, CsvParameter csvParameter) {
		if (StringUtils.isBlank(str)) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "RequiredConvertDataNothing"));
		}
		if (csvParameter == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ParameterEmptyError", new String[] { "CSVパラメータ" }));
		}
		// バイナリデータに変換
		byte[] csv = str.getBytes(csvParameter.getCharset());
		// エンコードがBOM設定対象エンコード以外またはBOM設定フラグがfalseの場合
		if (csvParameter.getCharset() != BOM_SETTING_POSSIBLE_ENCODE || !csvParameter.isBomSettingFlg()) {
			return csv;
		}
		// BOM設定用
		byte[] bom = new byte[] { (byte) 0xef, (byte) 0xbb, (byte) 0xbf };
		// BOM設定後バイナリデータ
		byte[] bomSetData = new byte[bom.length + csv.length];
		// CSVデータにBOM設定
		System.arraycopy(bom, 0, bomSetData, 0, bom.length);
		System.arraycopy(csv, 0, bomSetData, bom.length, csv.length);

		return bomSetData;
	}

	/**
	 * CSVのバックアップを対象のディレクトリ配下に作成する
	 * 
	 * @param csvData
	 * @param filePath(末尾に/無しのパス)
	 * @param fileName
	 * @throws IOException
	 */
	public void backupCsv(byte[] csvData, String filePath, String fileName) throws IOException {
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String timeStamp = date.format(new Date());
		Path fileFullPath = Paths.get(filePath + "/" + fileName + "_backup_" + timeStamp);
		// 現状考えられるExceptionが発生するケースとして、
		// ・サーバ容量がいっぱいの場合
		try {
			Files.write(fileFullPath, csvData);
		} catch (IOException e) {
			log.warn("バックアップファイルの作成に失敗しました。", e);
			throw e;
		}
		log.info("バックアップファイル「" + fileFullPath.toString() + "」の作成が完了しました。");
	}
}
