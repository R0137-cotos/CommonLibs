package jp.co.ricoh.cotos.commonlib.logic.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	/**
	 * 【java.time.LocalDate用】一般的な日付フォーマット
	 * <b>「yyyy」は暦（和暦含む）の際に使用するため、従来のyyyy形式はuuuuで指定すること</b>
	 */
	public static final String DATE_FORMAT = "uuuu/MM/dd";

	/**
	 * 【java.time.LocalDateTime用】一般的な日時フォーマット
	 * <b>「yyyy」は暦（和暦含む）の際に使用するため、従来のyyyy形式はuuuuで指定すること</b>
	 */
	public static final String DATETIME_FORMAT = "uuuu/MM/dd HH:mm:ss";

	/**
	 * システム日付を取得する
	 * @return
	 */
	public LocalDate getSystemDate() {
		return LocalDate.now();
	}

	/**
	 * システム日時を取得する
	 * @return
	 */
	public LocalDateTime getSystemDateTime() {
		return LocalDateTime.now();
	}

	/**
	 * {@link java.util.Date}や{@link java.sql.Date}をLocalDateに変換する<br>
	 * <b>LocalDateに変換することで時分秒切り捨てとなる</b>
	 * @param date 変換対象
	 * @return
	 */
	public LocalDate convertDate2LocalDate(Date date) {
		if (date instanceof java.sql.Date) {
			date = new Date(date.getTime());
		}

		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * LocalDateをDateに変換する
	 * @param localDate 変換対象
	 * @return
	 */
	public Date convertLocalDate2Date(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * DateをLocalDateTimeに変換する<br>
	 * <b>時分秒が必要 = {@link java.sql.Date}ではありえないため、チェックをわざと外している</b>
	 * @param date 変換対象
	 * @return
	 */
	public LocalDateTime convertDate2LocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * LocalDateTimeをDateに変換する
	 * @param localDateTime 変換対象
	 * @return
	 */
	public Date convertLocalDateTime2Date(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * LocalDateを日付文字列に変換する
	 * @param localDate
	 * @param format 変換用フォーマット文字列。例：{@link DateUtil#DATE_FORMAT}
	 * @return
	 */
	public String convertLocalDate2String(LocalDate localDate, String format) {
		return localDate.format(createDateTimeFormatter(format));
	}

	/**
	 * 日付文字列をLocalDateに変換する
	 * @param dateStr 日付文字列
	 * @param format 変換用フォーマット文字列。例：{@link DateUtil#DATE_FORMAT}
	 * @return
	 * @throws DateTimeParseException フォーマットに失敗した場合or日付としてありえない文字列を渡された場合（実際に存在しない2023/01/32等）
	 */
	public LocalDate convertString2LocalDate(String dateStr, String format) throws DateTimeParseException {
		return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(format).withResolverStyle(ResolverStyle.STRICT));
	}

	/**
	 * LocalDateTimeを日時文字列に変換する
	 * @param localDate
	 * @param format 変換用フォーマット文字列。例：{@link DateUtil#DATETIME_FORMAT}
	 * @return
	 */
	public String convertLocalDateTime2String(LocalDateTime localDateTime, String format) {
		return localDateTime.format(createDateTimeFormatter(format));
	}

	/**
	 * 日時文字列をLocalDateTimeに変換する
	 * @param dateStr 日時文字列
	 * @param format 変換用フォーマット文字列。例：{@link DateUtil#DATETIME_FORMAT}
	 * @return
	 * @throws DateTimeParseException フォーマットに失敗した場合or日付としてありえない文字列を渡された場合（実際に存在しない2023/01/32等）
	 */
	public LocalDateTime convertString2LocalDateTime(String dateStr, String format) throws DateTimeParseException {
		return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(format).withResolverStyle(ResolverStyle.STRICT));
	}

	/**
	 * 2つのLocalDateを比較し、一致しているかを判断する
	 * @param localDate1 比較対象
	 * @param localDate2 比較対象
	 * @return 一致していたらTrue
	 */
	public boolean sameDate(LocalDate localDate1, LocalDate localDate2) {
		return sameDate(ChronoUnit.DAYS, localDate1, localDate2);
	}

	/**
	 * 2つのLocalDateまたは、LocalDateTimeを指定の単位まで比較する
	 * @param chronoUnit 比較対象単位。日付までの比較の場合{@link ChronoUnit#DAYS}、日時までの場合{@link ChronoUnit#SECONDS}、ミリ秒までの場合{@link ChronoUnit#MILLIS}等を指定
	 * @param dateTemporal1 比較対象
	 * @param dateTemporal2 比較対象
	 * @return
	 */
	public boolean sameDate(ChronoUnit chronoUnit, Temporal dateTemporal1, Temporal dateTemporal2) {
		return (chronoUnit.between(dateTemporal1, dateTemporal2) == 0);
	}

	/**
	 * 文字列変換時に厳密な比較をするよう設定する
	 * @param format 変換用フォーマット文字列
	 * @return
	 */
	private DateTimeFormatter createDateTimeFormatter(String format) {
		return DateTimeFormatter.ofPattern(format).withResolverStyle(ResolverStyle.STRICT);
	}
}
