package jp.co.ricoh.cotos.commonlib.dateCalcPattern;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster;
import jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern.DateCalcPatternUtil;
import jp.co.ricoh.cotos.commonlib.repository.master.DateCalcPatternMasterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestDateCalcPatternUtil {

	@Autowired
	private DateCalcPatternUtil dateCalcPatternUtil;

	@Autowired
	private DateCalcPatternMasterRepository dateCalcPatternMasterRepository;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		テストデータ作成();
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void パラメータ不正_日付計算パターンマスタエンティティNULL() throws Exception {

		String testDate = "20201202";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Date resultDate = dateCalcPatternUtil.dateCalc(null, referenceDate);
		Assert.assertEquals("戻り値がNULLであること", null, resultDate);
	}

	@Test
	public void パラメータ不正_計算基準日NULL() throws Exception {

		Date resultDate = dateCalcPatternUtil.dateCalc(new DateCalcPatternMaster(), null);
		Assert.assertEquals("戻り値がNULLであること", null, resultDate);
	}

	@Test
	public void 月の加減算_日設定なし() throws Exception {

		String testDate = "20201202";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 1L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("指定月が指定されていること", "20201002", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		trgetId = 2L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("基準日 + 1ヵ月であること", "20210102", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		trgetId = 3L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("基準日 + 5ヶ月であること", "20210502", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		trgetId = 4L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("基準日 - 1ヵ月であること", "20201102", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		trgetId = 5L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("基準日 - 3ヵ月であること(時間指定あり)", "20200902 220501", dateCalcPatternUtil.dateToStringConverter(resultDate, "yyyyMMdd HHmmss"));

	}

	@Test
	public void 月の加減算_日設定なし_営業日考慮() throws Exception {

		String testDate = "20210131";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 6L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("基準日 - 1ヵ月であること(前営業日)", "20201228", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		testDate = "20201231";
		referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		trgetId = 7L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("基準日 + 1ヵ月であること(後営業日)", "20210201", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		testDate = "20201101";
		referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		trgetId = 8L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("基準日 + 1ヵ月であること(1ヵ月後が営業日)", "20201201", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 日の加減算_月設定なし() throws Exception {

		String testDate = "20201202";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 9L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("指定日が設定されていること", "20201210", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		trgetId = 10L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("指定日の月の最終日が設定されていること", "20201231", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		trgetId = 11L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("基準日 + 1日", "20201203", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		trgetId = 12L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("基準日 + 10日", "20201212", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		trgetId = 13L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("基準日 - 1日", "20201201", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		trgetId = 14L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("基準日 - 5日(時間指定あり)", "20201127 090000", dateCalcPatternUtil.dateToStringConverter(resultDate, "yyyyMMdd HHmmss"));
	}

	@Test
	public void 日の加減算_月設定なし_営業日考慮_日直接指定() throws Exception {

		String testDate = "20201202";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 15L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("前営業日が設定されること", "20201204", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		trgetId = 16L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("後営業日が設定されること", "20201207", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		trgetId = 17L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("指定された日が営業日の場合、指定日が設定されること", "20201210", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		trgetId = 18L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("月末指定_後営業日が設定されること", "20210105", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		testDate = "20201101";
		referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		trgetId = 19L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("月末指定_対象が営業日でない場合そのまま設定されること", "20201130", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 日の加減算_月設定なし_営業日考慮() throws Exception {

		String testDate = "20201202";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 20L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("指定日数分の営業日のみカウントされること(-)", "20201125", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		testDate = "20201031";
		referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		trgetId = 21L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("指定日数分の営業日のみカウントされること(+)", "20201102", dateCalcPatternUtil.dateToStringConverter(resultDate, null));

		trgetId = 22L;
		dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("日付計算営業日フラグが0の場合、営業日が考慮されないこと", "20201101", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 満了月の2ヶ月前の1日() throws Exception {

		String testDate = "20201202";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId =23L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("満了月の2ヶ月前の1日であること", "20201001", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 満了日の７営業日前() throws Exception {

		String testDate = "20201206";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 24L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("満了日の7営業日前であること", "20201126", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 満了月の20日() throws Exception {

		String testDate = "20201206";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 25L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("満了月の20日であること", "20201220", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 満了月の3ヶ月前の月末() throws Exception {

		String testDate = "20201206";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 26L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("満了月の3ヶ月前の月末であること", "20200930", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 満了月の4ヶ月前の最終日から7日前のさらに７営業日前() throws Exception {

		String testDate = "20201206";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 27L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("満了月の4ヶ月前の最終日から7日前のさらに７営業日前の日付であること", "20200806", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 満了日の4ヶ月の20日から10営業日をマイナスした日付() throws Exception {

		String testDate = "20201206";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 28L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("満了日の4ヶ月の20日から10営業日をマイナスした日付であること", "20200730", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 当月20日22時() throws Exception {

		String testDate = "20201206";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 29L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("当月20日の22時", "20201220220000", dateCalcPatternUtil.dateToStringConverter(resultDate, "yyyyMMddHHmmss"));
	}

	@Test
	public void 拡張項目のソート確認() throws Exception {

		String testDate = "20201206";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 30L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("拡張項目のソート確認", "20200813091000", dateCalcPatternUtil.dateToStringConverter(resultDate, "yyyyMMddHHmmss"));
	}

	@Test
	public void 拡張項目の月の加減算確認() throws Exception {

		String testDate = "20201206";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 31L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("拡張項目のソート確認", "20200918000000", dateCalcPatternUtil.dateToStringConverter(resultDate, "yyyyMMddHHmmss"));
	}

	@Test
	public void 契約満了月の4ヶ月前の月末から7営業日前() throws Exception {

		String testDate = "20210702";
		Date referenceDate = dateCalcPatternUtil.stringToDateConverter(testDate, null);
		Long trgetId = 32L;
		DateCalcPatternMaster dateCalcPatternMaster = dateCalcPatternMasterRepository.findOne(trgetId);
		Date resultDate = dateCalcPatternUtil.dateCalc(dateCalcPatternMaster, referenceDate);
		Assert.assertEquals("契約満了月の4ヶ月前の月末から7営業日前", "20210323", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	private void テストデータ作成() {
		context.getBean(DBConfig.class).initTargetTestData("sql/dateCalcPattern/testDateCalcPatternMasterInsert.sql");
		context.getBean(DBConfig.class).initTargetTestData("sql/dateCalcPattern/testNonBusinessDayCalendarMasterInsert.sql");
	}
}
