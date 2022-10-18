package jp.co.ricoh.cotos.commonlib.logic.holiday;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PublicHolidayUtilTest {

	static ConfigurableApplicationContext context;

	@Autowired
	private PublicHolidayUtil publicHolidayUtil;

	private SimpleDateFormat sfd = null;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		if (sfd == null) {
			sfd = new SimpleDateFormat("yyyy/MM/dd");
			context.getBean(DBConfig.class).clearData();
			context.getBean(DBConfig.class).initTargetTestData("logic/holiday/publicHolidayMaster.sql");
		}

		// 祝日レコードは2件
		// 2022/12/28(水)
		// 2022/12/29(木)
		// 2022/12/30(金)
		// 2022/12/31(土)：土日
		// 2023/01/01(日)：祝日
		// 2023/01/02(月)：祝日
		// 2023/01/03(火)
		// 2023/01/04(水)
		// 2023/01/05(木)
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void 祝日判定() throws ParseException {
		assertFalse("2022/12/31が祝日でないこと", publicHolidayUtil.isPublicHoliday(sfd.parse("2022/12/31")));
		assertTrue("2023/01/01が祝日であること", publicHolidayUtil.isPublicHoliday(sfd.parse("2023/01/01")));
		assertTrue("2023/01/02が祝日であること", publicHolidayUtil.isPublicHoliday(sfd.parse("2023/01/02")));
		assertFalse("2023/01/03が祝日でないこと", publicHolidayUtil.isPublicHoliday(sfd.parse("2023/01/03")));
	}

	@Test
	public void 休日判定() throws ParseException {
		assertTrue("2022/12/31が休日であること", publicHolidayUtil.isHoliday(sfd.parse("2022/12/31")));
		assertTrue("2023/01/01が休日であること", publicHolidayUtil.isHoliday(sfd.parse("2023/01/01")));
		assertTrue("2023/01/02が休日であること", publicHolidayUtil.isHoliday(sfd.parse("2023/01/02")));
		assertFalse("2023/01/03が休日でないこと", publicHolidayUtil.isHoliday(sfd.parse("2023/01/03")));
	}

	@Test
	public void 平日加算_指定日平日_加算_指定日含む() throws ParseException {

		Date inputDate = DateUtils.parseDate("2022/12/30", "yyyy/MM/dd");
		Date actualDate = publicHolidayUtil.addWeekdays(inputDate, 3, true);

		assertEquals("加算後日付が2023/01/04であること", "2023/01/04", sfd.format(actualDate));

		// -----------

		Date inputDate2 = DateUtils.parseDate("2022/12/29", "yyyy/MM/dd");
		Date actualDate2 = publicHolidayUtil.addWeekdays(inputDate2, 1, true);

		assertEquals("加算後日付が2022/12/29であること", "2022/12/29", sfd.format(actualDate2));
	}

	@Test
	public void 平日加算_指定日平日_加算_指定日含まず() throws ParseException {

		Date inputDate = DateUtils.parseDate("2022/12/30", "yyyy/MM/dd");
		Date actualDate = publicHolidayUtil.addWeekdays(inputDate, 3, false);

		assertEquals("加算後日付が2023/01/05であること", "2023/01/05", sfd.format(actualDate));

		// -----------

		Date inputDate2 = DateUtils.parseDate("2022/12/29", "yyyy/MM/dd");
		Date actualDate2 = publicHolidayUtil.addWeekdays(inputDate2, 1, false);

		assertEquals("加算後日付が2022/12/30であること", "2022/12/30", sfd.format(actualDate2));
	}

	@Test
	public void 平日加算_指定日祝日_減算_指定日含む() throws ParseException {

		Date inputDate = DateUtils.parseDate("2023/01/02", "yyyy/MM/dd");
		Date actualDate = publicHolidayUtil.addWeekdays(inputDate, -3, true);

		assertEquals("加算後日付が2022/12/28であること", "2022/12/28", sfd.format(actualDate));

		// -----------

		Date inputDate2 = DateUtils.parseDate("2023/01/02", "yyyy/MM/dd");
		Date actualDate2 = publicHolidayUtil.addWeekdays(inputDate2, -1, true);

		assertEquals("加算後日付が2022/12/30であること", "2022/12/30", sfd.format(actualDate2));
	}

	@Test
	public void 平日加算_指定日祝日_減算_指定日含まず() throws ParseException {

		Date inputDate = DateUtils.parseDate("2023/01/02", "yyyy/MM/dd");
		Date actualDate = publicHolidayUtil.addWeekdays(inputDate, -3, false);

		assertEquals("加算後日付が2022/12/27であること", "2022/12/27", sfd.format(actualDate));

		// -----------

		Date inputDate2 = DateUtils.parseDate("2023/01/02", "yyyy/MM/dd");
		Date actualDate2 = publicHolidayUtil.addWeekdays(inputDate2, -1, false);

		assertEquals("加算後日付が2022/12/29であること", "2022/12/29", sfd.format(actualDate2));
	}

}
