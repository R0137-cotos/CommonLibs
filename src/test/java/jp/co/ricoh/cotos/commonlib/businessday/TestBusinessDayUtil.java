package jp.co.ricoh.cotos.commonlib.businessday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
	public void 営業日判定_ベンダー略称指定() throws Exception {
		テストデータ作成();

		List<String> vendor1Only = new ArrayList<String>();
		vendor1Only.add("vendor1");

		List<String> vendor2Only = new ArrayList<String>();
		vendor2Only.add("vendor2");

		List<String> vendor3Only = new ArrayList<String>();
		vendor3Only.add("vendor3");

		List<String> vendor12 = new ArrayList<String>();
		vendor12.add("vendor1");
		vendor12.add("vendor2");

		// 2019/01/02
		// 共通：営業日
		// vendor1：非営業日
		// vendor2：営業日
		// vendor3:営業日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = sdf.parse("2019/01/02");
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, null));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor1Only));
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, vendor2Only));
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, vendor3Only));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor12));

		// 2019/01/03
		// 共通：営業日
		// vendor1：非営業日
		// vendor2：営業日
		// vendor3:営業日
		date = sdf.parse("2019/01/03");
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, null));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor1Only));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor2Only));
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, vendor3Only));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor12));

		// 2019/01/04
		// 共通：営業日
		// vendor1：非営業日
		// vendor2：営業日
		// vendor3:非営業日
		date = sdf.parse("2019/01/04");
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, null));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor1Only));
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, vendor2Only));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor3Only));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor12));

		// 2019/01/05
		// 共通：非営業日
		// vendor1：非営業日
		// vendor2：非営業日
		// vendor3:非営業日
		date = sdf.parse("2019/01/05");
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, null));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor1Only));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor2Only));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor3Only));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, vendor12));

		// 略称が部分一致する場合(SB,SBS,SBSB)
		List<String> sbOnly = new ArrayList<String>();
		sbOnly.add("SB");

		List<String> sbsOnly = new ArrayList<String>();
		sbsOnly.add("SBS");

		List<String> sbsbOnly = new ArrayList<String>();
		sbsbOnly.add("SBSB");

		// 2019/01/06
		// 共通：営業日
		// SB:非営業日
		// SBS:営業日
		// SBSB:営業日
		date = sdf.parse("2019/01/06");
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, null));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, sbOnly));
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, sbsOnly));
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, sbsbOnly));

		// 2019/01/07
		// 共通：営業日
		// SB:営業日
		// SBS:非営業日
		// SBSB:営業日
		date = sdf.parse("2019/01/07");
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, null));
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, sbOnly));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, sbsOnly));
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, sbsbOnly));

		// 2019/01/08
		// 共通：営業日
		// SB:営業日
		// SBS:非営業日
		// SBSB:非営業日
		date = sdf.parse("2019/01/08");
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, null));
		Assert.assertEquals("営業日であること", true, businessDayUtil.isBusinessDay(date, sbOnly));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, sbsOnly));
		Assert.assertEquals("非営業日であること", false, businessDayUtil.isBusinessDay(date, sbsbOnly));
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
	public void 最短営業日取得_ベンダー略称指定() throws Exception {
		テストデータ作成();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		// 共通のテストと同条件
		Date date = sdf.parse("2019/06/27");
		Assert.assertEquals("最短営業日_加算が正しいこと", sdf.parse("2019/07/04"), businessDayUtil.findShortestBusinessDay(date, 5, false, null));
		Assert.assertEquals("最短営業日_減算が正しいこと", sdf.parse("2019/06/20"), businessDayUtil.findShortestBusinessDay(date, 5, true, null));

		// 略称が部分一致する場合(SB,SBS,SBSB)
		List<String> sbOnly = new ArrayList<String>();
		sbOnly.add("SB");

		List<String> sbsOnly = new ArrayList<String>();
		sbsOnly.add("SBS");

		List<String> sbsbOnly = new ArrayList<String>();
		sbsbOnly.add("SBSB");

		List<String> allVendor = new ArrayList<String>();
		allVendor.add("SB");
		allVendor.add("SBS");
		allVendor.add("SBSB");

		date = sdf.parse("2019/01/03");

		// 共通
		// 2019/01/01 非営業日
		// 2019/01/05 非営業日
		Assert.assertEquals("最短営業日_加算が正しいこと", sdf.parse("2019/01/09"), businessDayUtil.findShortestBusinessDay(date, 5, false, null));
		Assert.assertEquals("最短営業日_減算が正しいこと", sdf.parse("2018/12/28"), businessDayUtil.findShortestBusinessDay(date, 5, true, null));

		// SB
		// 2018/12/31 非営業日
		// 2019/01/01 非営業日(共通)
		// 2019/01/05 非営業日(共通)
		// 2019/01/06 非営業日
		Assert.assertEquals("最短営業日_加算が正しいこと", sdf.parse("2019/01/10"), businessDayUtil.findShortestBusinessDay(date, 5, false, sbOnly));
		Assert.assertEquals("最短営業日_減算が正しいこと", sdf.parse("2018/12/27"), businessDayUtil.findShortestBusinessDay(date, 5, true, sbOnly));

		// SBS
		// 2018/12/31 非営業日
		// 2019/01/01 非営業日(共通)
		// 2019/01/05 非営業日(共通)
		// 2019/01/07 非営業日
		// 2019/01/08 非営業日
		Assert.assertEquals("最短営業日_加算が正しいこと", sdf.parse("2019/01/11"), businessDayUtil.findShortestBusinessDay(date, 5, false, sbsOnly));
		Assert.assertEquals("最短営業日_減算が正しいこと", sdf.parse("2018/12/27"), businessDayUtil.findShortestBusinessDay(date, 5, true, sbsOnly));

		// SBSB
		// 2018/12/31 非営業日
		// 2019/01/01 非営業日(共通)
		// 2019/01/05 非営業日(共通)
		// 2019/01/08 非営業日
		// 2019/01/09 非営業日
		// 2019/01/10 非営業日
		Assert.assertEquals("最短営業日_加算が正しいこと", sdf.parse("2019/01/12"), businessDayUtil.findShortestBusinessDay(date, 5, false, sbsbOnly));
		Assert.assertEquals("最短営業日_減算が正しいこと", sdf.parse("2018/12/27"), businessDayUtil.findShortestBusinessDay(date, 5, true, sbsbOnly));

		// SB+SBS+SBSB
		// 2018/12/31 非営業日(SB,SBS,SBSB)
		// 2019/01/01 非営業日(共通)
		// 2019/01/05 非営業日(共通)
		// 2019/01/06 非営業日(SB)
		// 2019/01/07 非営業日(SBS)
		// 2019/01/08 非営業日(SBS,SBSB)
		// 2019/01/09 非営業日(SBSB)
		// 2019/01/10 非営業日(SBSB)
		Assert.assertEquals("最短営業日_加算が正しいこと", sdf.parse("2019/01/14"), businessDayUtil.findShortestBusinessDay(date, 5, false, allVendor));
		Assert.assertEquals("最短営業日_減算が正しいこと", sdf.parse("2018/12/27"), businessDayUtil.findShortestBusinessDay(date, 5, true, allVendor));
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
	public void 月末最終営業日取得_非営業日カレンダーマスタ() {
		context.getBean(DBConfig.class).initTargetTestData("sql/businessday/lastBusinessDayOfTheMonthTest.sql");
		// 2019年6月 月末日が非営業日
		// 2019年7月 月末日が非営業日ではない
		// 2019年8月 全ての日が非営業日
		// 2019年9月 全ての日が非営業日ではない
		// 2019年10月 ベンダー固有の最終営業日と共通の最終営業日が違う
		// 2019/10/29:SB固有の最終営業日(本メソッドは共通の最終営業日しか返さないため、取得されない)
		// 2019/10/30:SB固有の非営業日,共通の最終営業日
		// 2019/10/31:共通の非営業日
		// 引数文字列
		// 引数null

		// 引数
		List<String> argList = Arrays.asList("201906", "201907", "201908", "201909", "201910", "ああああ", null);
		// 期待値
		List<String> expectList = Arrays.asList("2019/06/28", "2019/07/31", null, "2019/09/30", "2019/10/30", null, null);
		for (int index = 0; index < argList.size(); index++) {
			Date result = businessDayUtil.getLastBusinessDayOfTheMonthFromNonBusinessCalendarMaster(argList.get(index));
			Assert.assertEquals(argList.get(index) + "の想定通りの月末最終営業日が取得できること。", 日付想定値取得(expectList.get(index)), result);
		}
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
		Assert.assertFalse(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/07")));
		// 月初から3営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/06")));
		// 月初から2営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/05")));
		// 月初から1営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/04")));
		// 月初営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/03")));
		// 月初から3営業日以内の休業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/06/01")));

		// 月初から3営業日以内にベンダー固有の非営業日を含む場合
		// 2019/08/01:月初営業日
		// 2019/08/02:月初 + 1営業日
		// 2019/08/03:非営業日(共通)
		// 2019/08/04:月初 + 2営業日(ベンダー固有の非営業日)
		// 2019/08/05:月初 + 3営業日
		// 2019/08/06:月初 + 4営業日
		// 月初から4営業日
		Assert.assertFalse(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/08/06")));
		// 月初から3営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/08/05")));
		// 月初から2営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/08/04")));
		// 月初から1営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/08/02")));
		// 月初営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/08/01")));
		// 月初から3営業日以内の休業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromFirstDay(days, dateFormat.parse("2019/08/03")));
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
		Assert.assertFalse(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/24")));
		// 月末から3営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/25")));
		// 月末から2営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/26")));
		// 月末から1営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/27")));
		// 月末営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/28")));
		// 月末から3営業日以内の休業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/06/30")));

		// 月末から3営業日以内にベンダー固有の非営業日を含む場合
		// 2019/07/25:月末 - 4営業日
		// 2019/07/26:月末 - 3営業日
		// 2019/07/27:非営業日(共通)
		// 2019/07/28:月末 - 2営業日(ベンダー固有の非営業日)
		// 2019/07/29:月末 - 1営業日
		// 2019/07/30:月末営業日
		// 2019/07/31:非営業日(共通)
		// 月末から4営業日
		Assert.assertFalse(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/07/25")));
		// 月末から3営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/07/26")));
		// 月末から2営業日(ベンダー固有の非営業日)
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/07/28")));
		// 月末営業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/07/30")));
		// 月末から3営業日以内の休業日
		Assert.assertTrue(businessDayUtil.isWithinBusinessDaysFromLastDay(days, dateFormat.parse("2019/07/27")));
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
		// 2019/07/27
		// 2019/07/28(ベンダー固有)
		// 2019/07/31
		テストデータ作成();

		// 間に非営業日を挟まない期間 2019/06/05-2019/06/07
		Assert.assertEquals("2営業日差であること", 2, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 5), LocalDate.of(2019, 6, 7)));
		// 間に非営業日を挟む期間 2019/06/05-2019/06/10
		Assert.assertEquals("3営業日差であること", 3, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 5), LocalDate.of(2019, 6, 10)));
		// 間に非営業日を挟む期間 2019/06/05-2019/06/18
		Assert.assertEquals("9営業日差であること", 9, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 6, 5), LocalDate.of(2019, 6, 18)));
		// 間にベンダー固有の非営業日を挟む期間 2019/07/26 - 2019/08/01
		Assert.assertEquals("4営業日差であること", 4, businessDayUtil.calculateDifferenceBetweenBusinessDates(LocalDate.of(2019, 7, 26), LocalDate.of(2019, 8, 1)));
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
		// 2019/10/12
		// 2019/10/13
		// 2019/10/14
		// 2019/10/17(ベンダー固有)
		テストデータ作成();


		// 間に非営業日を挟まない期間 2019/06/05 - 2営業日 = 2019/06/03
		Assert.assertEquals("2019/06/05の2営業日前は2019/06/03であること", LocalDate.of(2019, 6, 3), businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 5), 2));
		// 間に非営業日を挟む期間 2019/06/11 - 2営業日 = 2019/06/07
		Assert.assertEquals("2019/06/11の2営業日前は2019/06/07であること", LocalDate.of(2019, 6, 7), businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 11), 2));
		// 間に非営業日を挟む期間 2019/06/17 - 10営業日 = 2019/06/03
		Assert.assertEquals("2019/06/17の10営業日前は2019/06/03であること", LocalDate.of(2019, 6, 3), businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 17), 10));
		// 間に共通の非営業日とベンダ固有の非営業日を挟む期間 2019/10/18 - 4営業日 = 2019/10/11
		// ベンダー固有の非営業日も非営業日と見做した場合、2019/10/10になってしまう
		Assert.assertEquals("2019/10/18の4営業日前は2019/10/11であること", LocalDate.of(2019, 10, 11), businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 10, 18), 4));
		// 0営業日前 2019/06/17 - 0営業日 = 2019/06/17
		Assert.assertEquals("2019/06/17の0営業日前は2019/06/17であること", LocalDate.of(2019, 6, 17), businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 17), 0));
		// baseDate = null
		Assert.assertEquals("baseDateがnullの場合、戻り値はnullであること", null, businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(null, 0));
		// baseDate = 非営業日 2019/06/09
		Assert.assertEquals("baseDateが非営業日の場合、戻り値はnullであること", null, businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 9), 0));
		// beforeNumber < 0
		Assert.assertEquals("beforeNumberが負数の場合、戻り値はnullであること", null, businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 5), -2));
		// baseDate = 非営業日 2019/06/09 判定を行う
		Assert.assertEquals("2019/06/09の1営業日前は2019/06/07であること", LocalDate.of(2019, 6, 7), businessDayUtil.getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate.of(2019, 6, 9), 1, true));
	}

	@Test
	public void 営業日リスト取得() {
		context.getBean(DBConfig.class).initTargetTestData("sql/businessday/testBusinessdayList.sql");
		// 2025年1月 営業日日数は19日

		// 日付の引数設定
		// テストしたい日付を設定
		int year = 2025;
		int month = 1;
		int day = 10;
		int day3 = 8;
		Calendar testDay = Calendar.getInstance();
		testDay.setTime(new Date());
		// 上記の年、月、日を設定（時間は実行した時間が入る想定）
		testDay.set(Calendar.YEAR, year);
		testDay.set(Calendar.MONTH, month - 1);
		testDay.set(Calendar.DATE, day);

		// 第3営業日を設定
		Calendar businessDay3 = Calendar.getInstance();
		businessDay3.set(Calendar.YEAR, year);
		businessDay3.set(Calendar.MONTH, month - 1);
		businessDay3.set(Calendar.DATE, day3);
		businessDay3.set(Calendar.HOUR_OF_DAY, 0);
		businessDay3.set(Calendar.MINUTE, 0);
		businessDay3.set(Calendar.SECOND, 0);
		businessDay3.set(Calendar.MILLISECOND, 0);

		// 期待値
		// 1.1月の営業日数19日
		int expectBusinessDayJan = 19;

		// 営業日リスト取得
		List<Date> resultBusinessDayList = businessDayUtil.findBusinessDayCalendarForSpecifiedMonth(testDay);

		// 比較結果
		Assert.assertEquals("2025年1月の営業日数が同じであること", expectBusinessDayJan, resultBusinessDayList.size());
		Assert.assertEquals("第3営業日が同じであること", businessDay3.getTime(), resultBusinessDayList.get(2));
	}

	private Date 日付想定値取得(String expected) {
		if (expected == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(StringUtils.substring(expected, 0, 4)), Integer.parseInt(StringUtils.substring(expected, 5, 7)) - 1, Integer.parseInt(StringUtils.substring(expected, 8, 10)), 0, 0, 0);
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
