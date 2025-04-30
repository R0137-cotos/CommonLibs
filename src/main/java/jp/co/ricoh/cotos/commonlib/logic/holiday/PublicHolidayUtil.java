package jp.co.ricoh.cotos.commonlib.logic.holiday;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.master.PublicHolidayMaster;
import jp.co.ricoh.cotos.commonlib.repository.master.PublicHolidayMasterRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 祝日共通クラス
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
public class PublicHolidayUtil {

	@Autowired
	private PublicHolidayMasterRepository publicHolidayMasterRepository;

	/**
	 * 祝日かどうか
	 *
	 * @param date
	 *            日付
	 * @return
	 */
	public boolean isPublicHoliday(Date date) {
		PublicHolidayMaster publicHolidayMaster = publicHolidayMasterRepository.findById(truncateDate(date)).get();
		return publicHolidayMaster != null;
	}

	/**
	 * 土日祝日かどうか
	 *
	 * @param date
	 *            日付
	 * @return
	 */
	public boolean isHoliday(Date date) {

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
		cal.setTime(date);

		// 土日
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
			return true;
		}

		return isPublicHoliday(date);
	}

	/**
	 * 平日日数加減
	 *
	 * <pre>
	 * 「指定日:平日」「加減値:1」「指定日を含む:true」⇒指定日をそのまま
	 * 「指定日:平日」「加減値:1」「指定日を含む:false」⇒指定日の翌平日
	 * 「指定日:祝日」「加減値:1」「指定日を含む:true」⇒指定日の翌平日
	 * 「指定日:祝日」「加減値:1」「指定日を含む:false」⇒指定日の翌々平日
	 * </pre>
	 *
	 * @param baseDate 指定日
	 * @param num 加減値
	 * @param includeBaseDate 指定日を含むか
	 * @return 加減後の平日日付
	 */
	public Date addWeekdays(Date baseDate, int num, boolean includeBaseDate) {

		Date retDate = (Date) baseDate.clone();

		// ゼロの場合そのまま返却
		if (num == 0) {
			return retDate;
		}

		// 増分値（マイナス、プラス）
		int addNum = num < 0 ? -1 : +1;
		// 加減合計日数
		int addDaysTotal = 0;

		// 指定日当日を含む場合
		if (includeBaseDate) {
			addDaysTotal += 1;
		}

		// 指定日が休日の場合、平日になるまで繰り返し
		while (isHoliday(retDate)) {
			retDate = DateUtils.addDays(retDate, addNum);
		}

		// 加減合計日数が加減値を超えるまで繰り返し
		while (addDaysTotal != Math.abs(num)) {
			// 翌平日
			retDate = DateUtils.addDays(retDate, addNum);

			if (!isHoliday(retDate)) {
				// 平日だったら加減合計日数に加算
				addDaysTotal += 1;
			}
		}

		return retDate;
	}

	/**
	 * 祝日マスタリポジトリ設定
	 * @param publicHolidayMasterRepository
	 */
	public void setPublicHolidayMasterRepository(PublicHolidayMasterRepository publicHolidayMasterRepository) {
		this.publicHolidayMasterRepository = publicHolidayMasterRepository;
	}

	/**
	 * 日付切り捨て
	 * @param date
	 * @return
	 */
	private Date truncateDate(Date date) {
		if (date != null) {
			date = DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
		}
		return date;
	}
}