package jp.co.ricoh.cotos.commonlib.logic.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateUtilTest {

	@Autowired
	private DateUtil dateUtil;

	@Test
	public void DateからLocalDateへの変換と比較() {
		Calendar cal = Calendar.getInstance();
		cal.set(2023, 0, 17, 12, 34, 56);
		Date date = cal.getTime();
		LocalDate localDate1 = dateUtil.convertDate2LocalDate(date);

		String dateStr = "2023/01/17";
		LocalDate localDate2 = dateUtil.convertString2LocalDate(dateStr, DateUtil.DATE_FORMAT);

		Date sqlDate = new java.sql.Date(date.getTime());
		LocalDate localDate3 = dateUtil.convertDate2LocalDate(sqlDate);

		Assert.assertTrue("java.util.Dateから変換した内容と文字列から変換した内容が一致すること", dateUtil.sameDate(localDate1, localDate2));
		Assert.assertTrue("java.sql.Dateから変換した内容と文字列から変換した内容が一致すること", dateUtil.sameDate(localDate3, localDate2));
	}

	@Test
	public void DateからLocalDateTimeへの変換と比較() {
		Calendar cal = Calendar.getInstance();
		cal.set(2023, 0, 17, 12, 34, 56);
		Date date = cal.getTime();
		LocalDateTime localDateTime1 = dateUtil.convertDate2LocalDateTime(date);

		String dateStr = "2023/01/17 12:34:56";
		LocalDateTime localDateTime2 = dateUtil.convertString2LocalDateTime(dateStr, DateUtil.DATETIME_FORMAT);

		Assert.assertTrue("java.util.Dateから変換した内容と文字列から変換した内容が時分秒まで一致すること", dateUtil.sameDate(ChronoUnit.SECONDS, localDateTime1, localDateTime2));

		String dateStr2 = "2023/01/17 12:34:10";
		LocalDateTime localDateTime3 = dateUtil.convertString2LocalDateTime(dateStr2, DateUtil.DATETIME_FORMAT);
		Assert.assertTrue("時分まで一致すること", dateUtil.sameDate(ChronoUnit.MINUTES, localDateTime2, localDateTime3));
		Assert.assertFalse("時分秒まで一致しないこと", dateUtil.sameDate(ChronoUnit.SECONDS, localDateTime2, localDateTime3));

		try {
			// java.sql.Dateは時間を持たないため、LocalDateTimeへの変換時にエラーする（LocalDateへの変換には成功する）
			Date sqlDate = new java.sql.Date(date.getTime());
			dateUtil.convertDate2LocalDateTime(sqlDate);

			Assert.fail("想定した例外が発生しなかった");
		} catch (UnsupportedOperationException uoe) {
			Assert.assertTrue("想定した例外が発生した", true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外の例外が発生");
		}
	}

	@Test
	public void LocalDateからStringへの変換() {
		String dateStr = "2023/01/17";
		LocalDate localDate = dateUtil.convertString2LocalDate(dateStr, DateUtil.DATE_FORMAT);

		// 一旦文字列に戻して比較する
		Assert.assertEquals("変換した文字列が一致すること", "2023/01/17", dateUtil.convertLocalDate2String(localDate, DateUtil.DATE_FORMAT));

		try {
			// 存在しない日付を指定
			dateUtil.convertString2LocalDate("2023/01/32", DateUtil.DATE_FORMAT);

			Assert.fail("想定した例外が発生しなかった");
		} catch (DateTimeParseException dte) {
			Assert.assertTrue("想定した例外が発生した", true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外の例外が発生");
		}
	}

	@Test
	public void LocalDateTimeからStringへの変換() {
		String dateStr = "2023/01/17 12:34:56";
		LocalDateTime localDateTime = dateUtil.convertString2LocalDateTime(dateStr, DateUtil.DATETIME_FORMAT);

		// 一旦文字列に戻して比較する
		Assert.assertEquals("変換した文字列が一致すること", "2023/01/17 12:34:56", dateUtil.convertLocalDateTime2String(localDateTime, DateUtil.DATETIME_FORMAT));

		try {
			// 存在するが、フォーマットが異なる日時を指定
			dateUtil.convertString2LocalDateTime("2023/01/17 12:34:56.789", DateUtil.DATETIME_FORMAT);

			Assert.fail("想定した例外が発生しなかった");
		} catch (DateTimeParseException dte) {
			Assert.assertTrue("想定した例外が発生した", true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外の例外が発生");
		}
	}
}
