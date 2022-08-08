package jp.co.ricoh.cotos.commonlib.csv;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.dto.parameter.common.CsvParameter;
import jp.co.ricoh.cotos.commonlib.entity.master.CsvFileSettingMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.csv.CsvUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestCsvUtil {

	@Autowired
	CsvUtil csvUtil;

	@Before
	public void init() throws IOException {
		Files.createDirectories(Paths.get("outputCsv/"));
		Stream.of(Paths.get("outputCsv/").toFile().listFiles()).forEach(s -> {
			s.setWritable(true);
			try {
				Files.delete(s.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void 正常系_CSV生成テスト_デフォルトパラメーター() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().build();

		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvData(2, "テスト２", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvData(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, param);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_default.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "UTF-8"), new String(expected, "UTF-8"));
	}

	@Test
	public void 正常系_CSV生成テスト_デフォルトパラメーターNULL() throws ErrorCheckException, IOException, ParseException {
		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvData(2, "テスト２", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvData(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, null);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_default.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "UTF-8"), new String(expected, "UTF-8"));
	}

	@Test
	public void 正常系_CSV生成テスト_ヘッダーなし() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().header(false).build();

		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvData(2, "テスト２", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvData(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, param);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_withoutHeader.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "UTF-8"), new String(expected, "UTF-8"));
	}

	@Test
	public void 正常系_CSV生成テスト_ヘッダー2バイト文字() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().build();

		List<TestCsvDataHeaderJapanese> list = new ArrayList<>();
		list.add(new TestCsvDataHeaderJapanese(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvDataHeaderJapanese(2, "テスト２", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvDataHeaderJapanese(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, param);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_columnnameJapanese.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "UTF-8"), new String(expected, "UTF-8"));
	}

	@Test
	public void 正常系_CSV生成テスト_セパレータータブ() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().separator('\t').build();

		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvData(2, "テスト２", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvData(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, param);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_seperatorTab.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "UTF-8"), new String(expected, "UTF-8"));
	}

	@Test
	public void 正常系_CSV生成テスト_SJISのCSVファイル() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().charset(Charset.forName("Shift-JIS")).build();

		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvData(2, "テスト２", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvData(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, param);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_default.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "Shift-JIS"), new String(expected, "UTF-8"));
	}

	@Test
	public void 正常系_CSV生成テスト_SJISのCSVファイル_半角スペースあり_withoutQuoteChar() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().charset(Charset.forName("Shift-JIS")).quote(false).withoutQuoteChar(true).build();

		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "testtest test", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvData(2, "東京都大田区平和島6-1-1東京流通センターB棟5F", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvData(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, param);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_default_space.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "Shift-JIS"), new String(expected, "UTF-8"));
	}

	@Test
	public void 正常系_CSV生成テスト_改行コードCRLF() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().lineSeparator("\r\n").build();

		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvData(2, "テスト２", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvData(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, param);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_crlf.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "UTF-8"), new String(expected, "UTF-8"));
	}

	@Test
	public void 正常系_CSV生成テスト_囲み文字なし() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().quote(false).build();

		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvData(2, "テスト２", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvData(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, param);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_withoutQuote.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "UTF-8"), new String(expected, "UTF-8"));
	}

	@Test
	public void 正常系_CSV生成テスト_文字列にカンマが含まれるがダブルクォートで囲みなし指定() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().quote(false).build();

		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvData(2, "テスト,２", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvData(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, param);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_commaItemWithoutQuote.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "UTF-8"), new String(expected, "UTF-8"));
	}

	@Test
	public void 正常系_CSV生成テスト_NULL文字列変更() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().nullValueString("").build();

		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvData(2, "テスト２", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvData(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, param);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_nullValue.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "UTF-8"), new String(expected, "UTF-8"));
	}

	@Test
	public void 異常系_CSV生成テスト_エンティティパラメーターにNULLを与える() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().build();

		try {
			csvUtil.createCsvData(null, param);
			Assert.fail("正常終了した");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00107", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "CSV生成時に必要なエンティティリストが設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_CSV生成テスト_エンティティパラメーターにサイズゼロのリストを与える() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().build();

		try {
			csvUtil.createCsvData(new ArrayList<TestCsvData>(), param);
			Assert.fail("正常終了した");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00107", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "CSV生成時に必要なエンティティリストが設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void 正常系_CSVファイル設定マスタエンティティからCsvParameterにコンバートできること_デフォルト値() {
		// CSVマスタエンティティ
		CsvFileSettingMaster csvFileSettingMaster = new CsvFileSettingMaster();
		// 期待値
		CsvParameter assertParam = CsvParameter.builder().build();
		assertParam.setHeader(true);
		assertParam.setSeparator(',');
		assertParam.setCharset(Charset.forName("UTF-8"));
		assertParam.setLineSeparator("\n");
		assertParam.setQuote(true);
		assertParam.setNullValueString("");
		assertParam.setWithoutQuoteChar(false);
		assertParam.setBomSettingFlg(false);
		try {
			CsvParameter resultParam = csvUtil.getCsvParameter(csvFileSettingMaster);
			Assert.assertEquals("ヘッダー行の有無が一致すること", assertParam.isHeader(), resultParam.isHeader());
			Assert.assertEquals("セパレータ文字が一致すること", assertParam.getSeparator(), resultParam.getSeparator());
			Assert.assertEquals("文字コードが一致すること", assertParam.getCharset(), resultParam.getCharset());
			Assert.assertEquals("改行コードが一致すること", assertParam.getLineSeparator(), resultParam.getLineSeparator());
			Assert.assertEquals("文字のダブルクォート有無が一致すること", assertParam.isQuote(), resultParam.isQuote());
			Assert.assertEquals("Null項目の文字列が一致すること", assertParam.getNullValueString(), resultParam.getNullValueString());
			Assert.assertEquals("文字列に囲み文字を付与しないフラグが一致すること", assertParam.isWithoutQuoteChar(), resultParam.isWithoutQuoteChar());
			Assert.assertEquals("BOM設定フラグが一致すること", assertParam.isBomSettingFlg(), resultParam.isBomSettingFlg());
		} catch (ErrorCheckException e) {
			fail("エラーが発生した");
		}
	}

	@Test
	public void 正常系_CSVファイル設定マスタエンティティからCsvParameterにコンバートできること_セパレータ文字_カンマ() {
		// CSVマスタエンティティ
		CsvFileSettingMaster csvFileSettingMaster = new CsvFileSettingMaster();
		csvFileSettingMaster.setCsvHeaderFlg(0);
		csvFileSettingMaster.setCsvSeparator("1");
		csvFileSettingMaster.setCsvCharset("Shift_JIS");
		csvFileSettingMaster.setCsvLineSeparator("2");
		csvFileSettingMaster.setCsvQuote(1);
		csvFileSettingMaster.setCsvNullValueString("");
		csvFileSettingMaster.setCsvWithoutQuoteChar(1);
		csvFileSettingMaster.setBomSettingFlg(1);
		// 期待値
		CsvParameter assertParam = CsvParameter.builder().build();
		assertParam.setHeader(false);
		assertParam.setSeparator(',');
		assertParam.setCharset(Charset.forName("Shift_JIS"));
		assertParam.setLineSeparator("\r\n");
		assertParam.setQuote(true);
		assertParam.setNullValueString("");
		assertParam.setWithoutQuoteChar(true);
		assertParam.setBomSettingFlg(true);
		try {
			CsvParameter resultParam = csvUtil.getCsvParameter(csvFileSettingMaster);
			Assert.assertEquals("ヘッダー行の有無が一致すること", assertParam.isHeader(), resultParam.isHeader());
			Assert.assertEquals("セパレータ文字が一致すること", assertParam.getSeparator(), resultParam.getSeparator());
			Assert.assertEquals("文字コードが一致すること", assertParam.getCharset(), resultParam.getCharset());
			Assert.assertEquals("改行コードが一致すること", assertParam.getLineSeparator(), resultParam.getLineSeparator());
			Assert.assertEquals("文字のダブルクォート有無が一致すること", assertParam.isQuote(), resultParam.isQuote());
			Assert.assertEquals("Null項目の文字列が一致すること", assertParam.getNullValueString(), resultParam.getNullValueString());
			Assert.assertEquals("文字列に囲み文字を付与しないフラグが一致すること", assertParam.isWithoutQuoteChar(), resultParam.isWithoutQuoteChar());
			Assert.assertEquals("BOM設定フラグが一致すること", assertParam.isBomSettingFlg(), resultParam.isBomSettingFlg());
		} catch (ErrorCheckException e) {
			fail("エラーが発生した");
		}
	}

	@Test
	public void 正常系_CSVファイル設定マスタエンティティからCsvParameterにコンバートできること_セパレータ文字_コロン() {
		// CSVマスタエンティティ
		CsvFileSettingMaster csvFileSettingMaster = new CsvFileSettingMaster();
		csvFileSettingMaster.setCsvHeaderFlg(1);
		csvFileSettingMaster.setCsvSeparator("2");
		csvFileSettingMaster.setCsvCharset("Shift_JIS");
		csvFileSettingMaster.setCsvLineSeparator("1");
		csvFileSettingMaster.setCsvQuote(0);
		csvFileSettingMaster.setCsvNullValueString("");
		csvFileSettingMaster.setCsvWithoutQuoteChar(0);
		csvFileSettingMaster.setBomSettingFlg(null);
		// 期待値
		CsvParameter assertParam = CsvParameter.builder().build();
		assertParam.setHeader(true);
		assertParam.setSeparator(':');
		assertParam.setCharset(Charset.forName("Shift_JIS"));
		assertParam.setLineSeparator("\n");
		assertParam.setQuote(false);
		assertParam.setNullValueString("");
		assertParam.setWithoutQuoteChar(false);
		assertParam.setBomSettingFlg(false);
		try {
			CsvParameter resultParam = csvUtil.getCsvParameter(csvFileSettingMaster);
			Assert.assertEquals("ヘッダー行の有無が一致すること", assertParam.isHeader(), resultParam.isHeader());
			Assert.assertEquals("セパレータ文字が一致すること", assertParam.getSeparator(), resultParam.getSeparator());
			Assert.assertEquals("文字コードが一致すること", assertParam.getCharset(), resultParam.getCharset());
			Assert.assertEquals("改行コードが一致すること", assertParam.getLineSeparator(), resultParam.getLineSeparator());
			Assert.assertEquals("文字のダブルクォート有無が一致すること", assertParam.isQuote(), resultParam.isQuote());
			Assert.assertEquals("Null項目の文字列が一致すること", assertParam.getNullValueString(), resultParam.getNullValueString());
			Assert.assertEquals("文字列に囲み文字を付与しないフラグが一致すること", assertParam.isWithoutQuoteChar(), resultParam.isWithoutQuoteChar());
			Assert.assertEquals("BOM設定フラグが一致すること", assertParam.isBomSettingFlg(), resultParam.isBomSettingFlg());
		} catch (ErrorCheckException e) {
			fail("エラーが発生した");
		}
	}

	@Test
	public void 正常系_CSVファイル設定マスタエンティティからCsvParameterにコンバートできること_セパレータ文字_セミコロン() {
		// CSVマスタエンティティ
		CsvFileSettingMaster csvFileSettingMaster = new CsvFileSettingMaster();
		csvFileSettingMaster.setCsvHeaderFlg(1);
		csvFileSettingMaster.setCsvSeparator("3");
		csvFileSettingMaster.setCsvCharset("UTF-8");
		csvFileSettingMaster.setCsvLineSeparator("1");
		csvFileSettingMaster.setCsvQuote(0);
		csvFileSettingMaster.setCsvNullValueString("null");
		csvFileSettingMaster.setCsvWithoutQuoteChar(1);
		csvFileSettingMaster.setBomSettingFlg(1);
		// 期待値
		CsvParameter assertParam = CsvParameter.builder().build();
		assertParam.setHeader(true);
		assertParam.setSeparator(';');
		assertParam.setCharset(Charset.forName("UTF-8"));
		assertParam.setLineSeparator("\n");
		assertParam.setQuote(false);
		assertParam.setNullValueString("null");
		assertParam.setWithoutQuoteChar(true);
		assertParam.setBomSettingFlg(true);
		try {
			CsvParameter resultParam = csvUtil.getCsvParameter(csvFileSettingMaster);
			Assert.assertEquals("ヘッダー行の有無が一致すること", assertParam.isHeader(), resultParam.isHeader());
			Assert.assertEquals("セパレータ文字が一致すること", assertParam.getSeparator(), resultParam.getSeparator());
			Assert.assertEquals("文字コードが一致すること", assertParam.getCharset(), resultParam.getCharset());
			Assert.assertEquals("改行コードが一致すること", assertParam.getLineSeparator(), resultParam.getLineSeparator());
			Assert.assertEquals("文字のダブルクォート有無が一致すること", assertParam.isQuote(), resultParam.isQuote());
			Assert.assertEquals("Null項目の文字列が一致すること", assertParam.getNullValueString(), resultParam.getNullValueString());
			Assert.assertEquals("文字列に囲み文字を付与しないフラグが一致すること", assertParam.isWithoutQuoteChar(), resultParam.isWithoutQuoteChar());
			Assert.assertEquals("BOM設定フラグが一致すること", assertParam.isBomSettingFlg(), resultParam.isBomSettingFlg());
		} catch (ErrorCheckException e) {
			fail("エラーが発生した");
		}
	}

	@Test
	public void 正常系_CSVファイル設定マスタエンティティからCsvParameterにコンバートできること_セパレータ文字_タブ() {
		// CSVマスタエンティティ
		CsvFileSettingMaster csvFileSettingMaster = new CsvFileSettingMaster();
		csvFileSettingMaster.setCsvHeaderFlg(1);
		csvFileSettingMaster.setCsvSeparator("4");
		csvFileSettingMaster.setCsvCharset("UTF-8");
		csvFileSettingMaster.setCsvLineSeparator("1");
		csvFileSettingMaster.setCsvQuote(0);
		csvFileSettingMaster.setCsvNullValueString("null");
		csvFileSettingMaster.setCsvWithoutQuoteChar(1);
		csvFileSettingMaster.setBomSettingFlg(0);
		// 期待値
		CsvParameter assertParam = CsvParameter.builder().build();
		assertParam.setHeader(true);
		assertParam.setSeparator('\t');
		assertParam.setCharset(Charset.forName("UTF-8"));
		assertParam.setLineSeparator("\n");
		assertParam.setQuote(false);
		assertParam.setNullValueString("null");
		assertParam.setWithoutQuoteChar(true);
		assertParam.setBomSettingFlg(false);
		try {
			CsvParameter resultParam = csvUtil.getCsvParameter(csvFileSettingMaster);
			Assert.assertEquals("ヘッダー行の有無が一致すること", assertParam.isHeader(), resultParam.isHeader());
			Assert.assertEquals("セパレータ文字が一致すること", assertParam.getSeparator(), resultParam.getSeparator());
			Assert.assertEquals("文字コードが一致すること", assertParam.getCharset(), resultParam.getCharset());
			Assert.assertEquals("改行コードが一致すること", assertParam.getLineSeparator(), resultParam.getLineSeparator());
			Assert.assertEquals("文字のダブルクォート有無が一致すること", assertParam.isQuote(), resultParam.isQuote());
			Assert.assertEquals("Null項目の文字列が一致すること", assertParam.getNullValueString(), resultParam.getNullValueString());
			Assert.assertEquals("文字列に囲み文字を付与しないフラグが一致すること", assertParam.isWithoutQuoteChar(), resultParam.isWithoutQuoteChar());
			Assert.assertEquals("BOM設定フラグが一致すること", assertParam.isBomSettingFlg(), resultParam.isBomSettingFlg());
		} catch (ErrorCheckException e) {
			fail("エラーが発生した");
		}
	}

	@Test
	public void 異常系_CSVファイル設定マスタエンティティがNULL() {
		// CSVマスタエンティティ
		CsvFileSettingMaster csvFileSettingMaster = null;
		try {
			csvUtil.getCsvParameter(csvFileSettingMaster);
			fail("正常終了した");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00001", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "パラメータ「CSVファイル設定マスタ」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_CSV生成テスト_quoteとwithoutQuoteCharの両方にtrue() throws ErrorCheckException, IOException, ParseException {
		CsvParameter param = CsvParameter.builder().quote(true).withoutQuoteChar(true).build();
		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));

		try {
			csvUtil.createCsvData(list, param);
			Assert.fail("正常終了した");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("IllegalArgumentExceptionが発生していること", "quoteとwithoutQuoteCharの両方にtrueを設定することはできません。", e.getMessage());
		} catch (Exception e) {
			Assert.fail("想定外のエラーが発生している");
		}
	}

	@Test
	public void 正常系_文字化け対象文字の変換() throws ParseException {
		// CSVデータリスト
		List<TestCsvData> csvList = createCsvData();
		// 実施
		List<TestCsvData> resultList = csvUtil.convertGarbledCharForCsvData(csvList, TestCsvData.class);
		// チェック
		// 1件目
		TestCsvData csv1 = csvList.get(0);
		TestCsvData result1 = resultList.get(0);
		Assert.assertEquals("【１件目】", csv1.getId(), result1.getId());
		Assert.assertEquals("【１件目】", "１１-２２", result1.getName());
		Assert.assertEquals("【１件目】", csv1.getAge(), result1.getAge());
		Assert.assertEquals("【１件目】", csv1.getBirthday(), result1.getBirthday());
		Assert.assertEquals("【１件目】", csv1.getWeight(), result1.getWeight(), 0.0);
		// 2件目
		TestCsvData csv2 = csvList.get(1);
		TestCsvData result2 = resultList.get(1);
		Assert.assertEquals("【２件目】", csv2.getId(), result2.getId());
		Assert.assertEquals("【２件目】", "２２-３３", result2.getName());
		Assert.assertEquals("【２件目】", csv2.getAge(), result2.getAge());
		Assert.assertEquals("【２件目】", csv2.getBirthday(), result2.getBirthday());
		Assert.assertEquals("【２件目】", csv2.getWeight(), result2.getWeight(), 0.0);
		// 3件目
		TestCsvData csv3 = csvList.get(2);
		TestCsvData result3 = resultList.get(2);
		Assert.assertEquals("【３件目】", csv3.getId(), result3.getId());
		Assert.assertEquals("【３件目】", "３３-４４", result3.getName());
		Assert.assertEquals("【３件目】", csv3.getAge(), result3.getAge());
		Assert.assertEquals("【３件目】", csv3.getBirthday(), result3.getBirthday());
		Assert.assertEquals("【３件目】", csv3.getWeight(), result3.getWeight(), 0.0);
		// 4件目
		TestCsvData csv4 = csvList.get(3);
		TestCsvData result4 = resultList.get(3);
		Assert.assertEquals("【４件目】", csv4.getId(), result4.getId());
		Assert.assertEquals("【４件目】", "４４-５５", result4.getName());
		Assert.assertEquals("【４件目】", csv4.getAge(), result4.getAge());
		Assert.assertEquals("【４件目】", csv4.getBirthday(), result4.getBirthday());
		Assert.assertEquals("【４件目】", csv4.getWeight(), result4.getWeight(), 0.0);
		// 5件目
		TestCsvData csv5 = csvList.get(4);
		TestCsvData result5 = resultList.get(4);
		Assert.assertEquals("【５件目】", csv5.getId(), result5.getId());
		Assert.assertEquals("【５件目】", "５５～６６", result5.getName());
		Assert.assertEquals("【５件目】", csv5.getAge(), result5.getAge());
		Assert.assertEquals("【５件目】", csv5.getBirthday(), result5.getBirthday());
		Assert.assertEquals("【５件目】", csv5.getWeight(), result5.getWeight(), 0.0);
		// 6件目
		TestCsvData csv6 = csvList.get(5);
		TestCsvData result6 = resultList.get(5);
		Assert.assertEquals("【６件目】", csv6.getId(), result6.getId());
		Assert.assertEquals("【６件目】", "６６-７７", result6.getName());
		Assert.assertEquals("【６件目】", csv6.getAge(), result6.getAge());
		Assert.assertEquals("【６件目】", csv6.getBirthday(), result6.getBirthday());
		Assert.assertEquals("【６件目】", csv6.getWeight(), result6.getWeight(), 0.0);
	}

	@Test
	public void 正常系_文字化け対象文字の変換_CSVデータがNull() {
		// 実施
		List<TestCsvData> list = csvUtil.convertGarbledCharForCsvData(null, TestCsvData.class);
		// チェック
		Assert.assertNull("戻り値がNullであること", list);
	}

	@Test
	public void 正常系_文字化け対象文字の変換_CSVデータ空() {
		// 実施
		List<TestCsvData> list = csvUtil.convertGarbledCharForCsvData(new ArrayList<TestCsvData>(), TestCsvData.class);
		// チェック
		Assert.assertEquals("戻り値が空であること", 0, list.size());
	}

	@Test
	public void 異常系_文字化け対象文字の変換_クラス指定なし() throws ParseException {
		// テストデータ取得
		List<TestCsvData> csvList = createCsvData();

		try {
			// 実施
			csvUtil.convertGarbledCharForCsvData(csvList, null);
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			Assert.assertEquals("エラー件数が一致すること", 1, errorList.size());
			Assert.assertEquals("エラーIDが一致すること", "ROT00013", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが一致すること", "Objectが設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void 正常系_BOM設定できること() throws ParseException, ErrorCheckException, IOException {
		CsvParameter param = CsvParameter.builder().bomSettingFlg(true).build();

		List<TestCsvData> list = new ArrayList<>();
		list.add(new TestCsvData(1, "テスト１", 12, new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/12"), 75.4));
		list.add(new TestCsvData(2, "テスト２", 10, new SimpleDateFormat("yyyy/MM/dd").parse("2016/03/15"), 40.5));
		list.add(new TestCsvData(3, null, 9, new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/02"), 100.1));

		byte[] actual = csvUtil.createCsvData(list, param);
		byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/csv/input_bom_data.csv"));
		Assert.assertEquals("生成されたCSV情報が正しいこと", new String(actual, "UTF-8"), new String(expected, "UTF-8"));
	}

	@Test
	public void 異常系_BOM設定_データなし() throws NoSuchMethodException, SecurityException {
		// メソッド
		Method method = CsvUtil.class.getDeclaredMethod("convertStrToByteArray", String.class, CsvParameter.class);
		method.setAccessible(true);
		CsvParameter param = CsvParameter.builder().bomSettingFlg(true).build();
		try {
			// 実行
			method.invoke(csvUtil, null, param);
		} catch (InvocationTargetException e) {
			ErrorCheckException error = (ErrorCheckException) e.getTargetException();
			List<ErrorInfo> errorList = error.getErrorInfoList();
			Assert.assertEquals("エラー件数が一致すること", 1, errorList.size());
			Assert.assertEquals("エラーIDが一致すること", "ROT00050", errorList.get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが一致すること", "変換に必要なデータが設定されていません。", errorList.get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外が発生している。");
		}
	}

	@Test
	public void 異常系_BOM設定_CSVパラメータなし() throws NoSuchMethodException, SecurityException {
		// メソッド
		Method method = CsvUtil.class.getDeclaredMethod("convertStrToByteArray", String.class, CsvParameter.class);
		method.setAccessible(true);
		try {
			// 実行
			method.invoke(csvUtil, "test", null);
		} catch (InvocationTargetException e) {
			ErrorCheckException error = (ErrorCheckException) e.getTargetException();
			List<ErrorInfo> errorList = error.getErrorInfoList();
			Assert.assertEquals("エラー件数が一致すること", 1, errorList.size());
			Assert.assertEquals("エラーIDが一致すること", "ROT00001", errorList.get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが一致すること", "パラメータ「CSVパラメータ」が設定されていません。", errorList.get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外が発生している。");
		}
	}

	@Test
	public void 正常系_バックアップCSV作成成功() throws IOException {
		// パラメータ作成
		String csvDataString = "項目1,項目2,項目3\r\n" + ",,\r\n" + "1,2,3\r\n" + "test1,test2,test3";
		byte[] csvData = csvDataString.getBytes();
		String filePath = "outputCsv";
		String fileName = "バックアップテスト.csv";

		try {
			csvUtil.backupCsv(csvData, filePath, fileName);
		} catch (IOException e) {
			Assert.fail("エラーが発生してしまった。");
			return;
		}

		// 検証のため出力されたファイルをバイト配列に変換
		File file = new File("outputCsv");
		File[] fileList = file.listFiles();

		byte[] exec = Files.readAllBytes(Paths.get(fileList[0].getPath()));
		Assert.assertArrayEquals("バイト配列が等しいこと", exec, csvData);
	}

	// Files、PathクラスがfinalクラスでMock化ができないためIgnore
	// 考えられるエラー発生原因がサーバの容量上限による作成エラーor書き込みエラーくらいの認識なので検証不可
	@Ignore
	@Test
	public void 正常系_バックアップCSV作成失敗_ファイル作成と書き込みエラー() throws IOException {
		// パラメータ作成
		byte[] csvData = {};
		String filePath = "outputCsv";
		String fileName = "バックアップテスト.csv";

		try {
			csvUtil.backupCsv(csvData, filePath, fileName);
		} catch (IOException e) {
			Assert.assertTrue("想定通りエラーが発生した。", true);
			return;
		}
		Assert.fail("正常終了してしまった。");
	}

	/**
	 * テスト用のCSVデータリストを作成します。
	 *
	 * @return
	 * @throws ParseException
	 */
	private List<TestCsvData> createCsvData() throws ParseException {

		List<TestCsvData> list = new ArrayList<>();
		String format = "yyyy/MM/dd";
		// リスト作成
		TestCsvData dto1 = new TestCsvData(1L, "１１‑２２", 50, new SimpleDateFormat(format).parse("2021/01/15"), 100D);
		list.add(dto1);
		TestCsvData dto2 = new TestCsvData(2L, "２２–３３", 51, new SimpleDateFormat(format).parse("2021/02/15"), 110D);
		list.add(dto2);
		TestCsvData dto3 = new TestCsvData(3L, "３３—４４", 52, new SimpleDateFormat(format).parse("2021/03/15"), 120D);
		list.add(dto3);
		TestCsvData dto4 = new TestCsvData(4L, "４４−５５", 53, new SimpleDateFormat(format).parse("2021/04/15"), 130D);
		list.add(dto4);
		TestCsvData dto5 = new TestCsvData(5L, "５５∼６６", 54, new SimpleDateFormat(format).parse("2021/05/15"), 140D);
		list.add(dto5);
		TestCsvData dto6 = new TestCsvData(6L, "６６－７７", 55, new SimpleDateFormat(format).parse("2021/12/15"), 150D);
		list.add(dto6);

		return list;
	}

}
