package jp.co.ricoh.cotos.commonlib.businessday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
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
import jp.co.ricoh.cotos.commonlib.logic.businessday.BusinessDayUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestBusinessDayUtil {

	@Autowired
	BusinessDayUtil businessDayUtil;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	@Transactional
	public void 営業日判定() throws Exception {
		テストデータ作成();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = sdf.parse("2019/01/01");
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date));
		date = sdf.parse("2019/01/02");
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date));
	}

	@Test
	@Transactional
	public void 最短営業日取得() throws Exception {
		テストデータ作成();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = sdf.parse("2019/06/27");
		Assert.assertEquals("最短営業日_加算が正しいこと", sdf.parse("2019/07/04"), businessDayUtil.findShortestBusinessDay(date, 5, false));
		Assert.assertEquals("最短営業日_減算が正しいこと", sdf.parse("2019/06/20"), businessDayUtil.findShortestBusinessDay(date, 5, true));
	}

	@Test
	@Transactional
	public void 最短営業日取得_時間計算() throws Exception {
		テストデータ作成();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = sdf.parse("2019/06/27");
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date baseDateTime = sdfTime.parse("2019/06/27 11:59:59");
		Assert.assertEquals("最短営業日(午前)が正しいこと", sdf.parse("2019/07/04"), businessDayUtil.findShortestBusinessDayTimeCalc(date, 5, baseDateTime));
		baseDateTime = sdfTime.parse("2019/06/27 12:00:00");
		Assert.assertEquals("最短営業日(午後)が正しいこと", sdf.parse("2019/07/05"), businessDayUtil.findShortestBusinessDayTimeCalc(date, 5, baseDateTime));
		date = sdf.parse("2019/07/05");
		baseDateTime = sdfTime.parse("2019/07/05 12:00:00");
		Assert.assertEquals("最短営業日_加算時営業日考慮(午後)が正しいこと", sdf.parse("2019/07/16"), businessDayUtil.findShortestBusinessDayTimeCalc(date, 5, baseDateTime));
	}

	@Test
	public void 月末最終営業日取得() {
		業務カレンダーマスタ関連テストデータ作成();
		// 引数
		List<String> argList = Arrays.asList("2019/06", "2019/07", "2019/08", "2019/09", "2019/10", "2019/11", "2019/12", "2020/01", "2020/02", "2020/03");
		// 期待値
		List<String> expectList = Arrays.asList("2019/06/28", "2019/07/31", "2019/08/30", "2019/09/30", "2019/10/31", "2019/11/29", "2019/12/27", "2020/01/31", "2020/02/28", "2020/03/31");
		for (int index = 0; index < argList.size(); index++) {
			Date result = businessDayUtil.getLastBusinessDayOfTheMonth(argList.get(index));
			Assert.assertEquals(argList.get(index) + "の想定通りの月末最終営業日が取得できること。", 日付想定値取得(expectList.get(index)), result);
		}

		Assert.assertEquals("年月がyyyyMM形式でも月末最終営業日が取得できること", 日付想定値取得("2019/06/28"), businessDayUtil.getLastBusinessDayOfTheMonth("201906"));
		Assert.assertEquals("年月がyyyy-MM形式でも月末最終営業日が取得できること", 日付想定値取得("2019/06/28"), businessDayUtil.getLastBusinessDayOfTheMonth("2019-06"));
		Assert.assertNull("業務カレンダーマスタに登録されていない年月の場合はnullが返ること", businessDayUtil.getLastBusinessDayOfTheMonth("2919/06"));
	}

	@Test
	public void 月初からn営業日以内か() throws ParseException {
		// 月初から3営業日以内をテスト
		int days = 3;
		// 2019年6月の非営業日は以下を想定
		// 2019/06/01 
		// 2019/06/02
		// 2019/06/08
		// 2019/06/09
		// 2019/06/15
		// 2019/06/16
		// 2019/06/22
		// 2019/06/23
		// 2019/06/29
		// 2019/06/30
		テストデータ作成();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		// 月末非営業日
		Assert.assertFalse(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/30")));
		// 月末営業日
		Assert.assertFalse(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/28")));
		// 月初から4営業日
		Assert.assertFalse(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/06")));
		// 月初から3営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/05")));
		// 月初から2営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/04")));
		// 月初営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/03")));
		// 月初から3営業日以内の休業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/01")));
	}

	@Test
	public void 月末からn営業日以内か() throws ParseException {
		// 月末から3営業日以内をテスト
		int days = 3;
		// 2019年6月の非営業日は以下を想定
		// 2019/06/01 
		// 2019/06/02
		// 2019/06/08
		// 2019/06/09
		// 2019/06/15
		// 2019/06/16
		// 2019/06/22
		// 2019/06/23
		// 2019/06/29
		// 2019/06/30
		テストデータ作成();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		// 月初非営業日
		Assert.assertFalse(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/01")));
		// 月初営業日
		Assert.assertFalse(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/03")));
		// 月末から4営業日
		Assert.assertFalse(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/25")));
		// 月末から3営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/26")));
		// 月末から2営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/27")));
		// 月末営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/28")));
		// 月末から3営業日以内の休業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/30")));
	}

	@Test
	public void 日付1は日付2からn営業日以内か() throws ParseException {
		// 2019年6月の非営業日は以下を想定
		// 2019/06/01 
		// 2019/06/02
		// 2019/06/08
		// 2019/06/09
		// 2019/06/15
		// 2019/06/16
		// 2019/06/22
		// 2019/06/23
		// 2019/06/29
		// 2019/06/30
		テストデータ作成();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		int days = 3;

		// 始点が営業日 終点が営業日 非営業日を挟まない範囲 3営業日以内　6/3 - 6/6 ※始点の日付は含まない
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/03"), dateFormat.parse("2019/06/06"), days));
		// 始点が営業日 終点が営業日  非営業日を挟まない範囲 3営業日以内でない(4営業日差)　6/3 - 6/7
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/03"), dateFormat.parse("2019/06/07"), days));
		// 始点が営業日 終点が営業日 非営業日を挟む範囲 3営業日以内　6/6 - 6/11
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/06"), dateFormat.parse("2019/06/11"), days));
		// 始点が営業日 終点が営業日 非営業日を挟む範囲 3営業日以内でない(4営業日差)　6/6 - 6/12
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/06"), dateFormat.parse("2019/06/12"), days));

		// ↑4つ同じ範囲引数逆順
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/06"), dateFormat.parse("2019/06/03"), days));
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/07"), dateFormat.parse("2019/06/03"), days));
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/11"), dateFormat.parse("2019/06/06"), days));
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/12"), dateFormat.parse("2019/06/06"), days));

		// 始点が非営業日 終点が営業日 非営業日を挟まない範囲 3営業日以内　6/2 - 6/5
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/02"), dateFormat.parse("2019/06/05"), days));
		// 始点が非営業日 終点が営業日 非営業日を挟まない範囲 3営業日以内でない(4営業日差)　6/2 - 6/6
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/02"), dateFormat.parse("2019/06/06"), days));
		// 始点が非営業日 終点が営業日 非営業日を挟む範囲 3営業日以内　6/1 - 6/5
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/01"), dateFormat.parse("2019/06/05"), days));
		// 始点が非営業日 終点が営業日 非営業日を挟む範囲 3営業日以内でない(4営業日差)　6/1 - 6/6
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/01"), dateFormat.parse("2019/06/06"), days));

		// ↑4つ同じ範囲引数逆順
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/05"), dateFormat.parse("2019/06/02"), days));
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/06"), dateFormat.parse("2019/06/02"), days));
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/05"), dateFormat.parse("2019/06/01"), days));
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/06"), dateFormat.parse("2019/06/01"), days));

		// 始点が営業日 終点が非営業日 非営業日を挟まない範囲 3営業日以内　6/5 - 6/8 (6/6,6/7,6/10が営業日なので、3営業日以内)
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/05"), dateFormat.parse("2019/06/08"), days));
		// 始点が営業日 終点が非営業日 非営業日を挟まない範囲 3営業日以内でない　6/4 - 6/8 (6/5,6/6,6/7が営業日だが、終点が6/8なので3営業日以内とは呼ばない)
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/04"), dateFormat.parse("2019/06/08"), days));
		// 始点が営業日 終点が非営業日 非営業日を挟む範囲 3営業日以内　6/5 - 6/9 (6/6,6/7,6/10が営業日なので、3営業日以内)
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/05"), dateFormat.parse("2019/06/09"), days));
		// 始点が営業日 終点が非営業日 非営業日を挟む範囲 3営業日以内でない　6/4 - 6/9 (6/5,6/6,6/7が営業日だが、終点が6/9なので3営業日以内とは呼ばない)
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/04"), dateFormat.parse("2019/06/09"), days));

		// ↑4つ同じ範囲引数逆順
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/08"), dateFormat.parse("2019/06/05"), days));
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/08"), dateFormat.parse("2019/06/04"), days));
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/09"), dateFormat.parse("2019/06/05"), days));
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/09"), dateFormat.parse("2019/06/04"), days));

		days = 6;
		// 始点が非営業日 終点が非営業日 非営業日を挟まない範囲 6営業日以内　6/2 - 6/8
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/02"), dateFormat.parse("2019/06/08"), days));
		days = 5;
		// 始点が非営業日 終点が非営業日 非営業日を挟まない範囲 5営業日以内でない(5営業日が間に入っている範囲)　6/2 - 6/8
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/02"), dateFormat.parse("2019/06/08"), days));
		days = 11;
		// 始点が非営業日 終点が非営業日 非営業日を挟む範囲 11営業日以内　6/1 - 6/16
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/01"), dateFormat.parse("2019/06/16"), days));
		days = 10;
		// 始点が非営業日 終点が非営業日 非営業日を挟む範囲 10営業日以内でない(10営業日が間に入っている範囲)　6/1 - 6/16
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/01"), dateFormat.parse("2019/06/16"), days));

		// ↑4つ同じ範囲引数逆順
		days = 6;
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/08"), dateFormat.parse("2019/06/02"), days));
		days = 5;
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/08"), dateFormat.parse("2019/06/02"), days));
		days = 11;
		Assert.assertTrue(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/16"), dateFormat.parse("2019/06/01"), days));
		days = 10;
		Assert.assertFalse(businessDayUtil.isDate1WithinNumBusinessDaysOfDate2(dateFormat.parse("2019/06/16"), dateFormat.parse("2019/06/01"), days));

	}

	@Test
	public void 日付間の営業日差取得() throws ParseException {
		// 2019年6月の非営業日は以下を想定
		// 2019/06/01 
		// 2019/06/02
		// 2019/06/08
		// 2019/06/09
		// 2019/06/15
		// 2019/06/16
		// 2019/06/22
		// 2019/06/23
		// 2019/06/29
		// 2019/06/30
		テストデータ作成();

		// 間に非営業日を挟まない期間 2019/06/05-2019/06/07
		Assert.assertEquals("2営業日差であること", 2, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 5), LocalDate.of(2019, 6, 7)));
		// 間に非営業日を挟む期間 2019/06/05-2019/06/10
		Assert.assertEquals("3営業日差であること", 3, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 5), LocalDate.of(2019, 6, 10)));
		// 間に非営業日を挟む期間 2019/06/05-2019/06/18
		Assert.assertEquals("9営業日差であること", 9, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 5), LocalDate.of(2019, 6, 18)));
		// 同日 2019/06/05-2019/06/05
		Assert.assertEquals("0営業日差であること", 0, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 5), LocalDate.of(2019, 6, 5)));
		// smallDate = null
		Assert.assertEquals("エラーであること", -1, businessDayUtil.calculateDifferenceBetweenBusinessDates(null, LocalDate.of(2019, 6, 18)));
		// bigDate = null
		Assert.assertEquals("エラーであること", -1, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 5), null));
		// smallDate = null かつ bigDate = null
		Assert.assertEquals("エラーであること", -1, businessDayUtil.calculateDifferenceBetweenBusinessDates(null, null));
		// bigDate < smallDate 
		Assert.assertEquals("エラーであること", -1, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 18), LocalDate.of(2019, 6, 5)));
		// smallDate = 非営業日
		Assert.assertEquals("エラーであること", -1, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 2), LocalDate.of(2019, 6, 18)));
		// bigDate = 非営業日
		Assert.assertEquals("エラーであること", -1, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 5), LocalDate.of(2019, 6, 8)));
		// smallDate = 非営業日 かつ bigDate = 非営業日
		Assert.assertEquals("エラーであること", -1, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 2), LocalDate.of(2019, 6, 8)));
	}

	@Test
	public void n営業日前の営業日を取得() throws ParseException {
		// 2019年6月の非営業日は以下を想定
		// 2019/06/01 
		// 2019/06/02
		// 2019/06/08
		// 2019/06/09
		// 2019/06/15
		// 2019/06/16
		// 2019/06/22
		// 2019/06/23
		// 2019/06/29
		// 2019/06/30
		テストデータ作成();

		// 間に非営業日を挟まない期間 2019/06/05 - 2営業日 = 2019/06/03
		Assert.assertEquals("2019/06/05の2営業日前は2019/06/03であること", LocalDate.of(2019, 6, 3), businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 5), 2));
		// 間に非営業日を挟む期間 2019/06/11 - 2営業日 = 2019/06/07
		Assert.assertEquals("2019/06/11の2営業日前は2019/06/07であること", LocalDate.of(2019, 6, 7), businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 11), 2));
		// 間に非営業日を挟む期間 2019/06/17 - 10営業日 = 2019/06/03
		Assert.assertEquals("2019/06/17の10営業日前は2019/06/03であること", LocalDate.of(2019, 6, 3), businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 17), 10));
		// 0営業日前 2019/06/17 - 0営業日 = 2019/06/17
		Assert.assertEquals("2019/06/17の0営業日前は2019/06/17であること", LocalDate.of(2019, 6, 17), businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 17), 0));
		// baseDate = null
		Assert.assertEquals("baseDateがnullの場合、戻り値はnullであること", null, businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(null, 0));
		// baseDate = 非営業日 2019/06/09
		Assert.assertEquals("baseDateが非営業日の場合、戻り値はnullであること", null, businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 9), 0));
		// beforeNumber < 0
		Assert.assertEquals("beforeNumberが負数の場合、戻り値はnullであること", null, businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 5), -2));
	}

	private Date 日付想定値取得(String expectrd) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(StringUtils.substring(expectrd, 0, 4)), Integer.parseInt(StringUtils.substring(expectrd, 5, 7)) - 1, Integer.parseInt(StringUtils.substring(expectrd, 8, 10)), 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	private void テストデータ作成() {
		context.getBean(DBConfig.class).initTargetTestData("sql/businessday/testNonBusinessDayCalendarMasterInsert.sql");
	}

	private void 業務カレンダーマスタ関連テストデータ作成() {
		context.getBean(DBConfig.class).initTargetTestData("sql/businessday/testBusinessCalenderMasterInsert.sql");
	}
}
