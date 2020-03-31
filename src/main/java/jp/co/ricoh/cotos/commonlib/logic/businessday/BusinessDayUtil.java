package jp.co.ricoh.cotos.commonlib.logic.businessday;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SortOrder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.ricoh.cotos.commonlib.entity.master.BusinessCalendar;
import jp.co.ricoh.cotos.commonlib.entity.master.NonBusinessDayCalendarMaster;
import jp.co.ricoh.cotos.commonlib.repository.master.BusinessCalendarRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.NonBusinessDayCalendarMasterRepository;

/**
 * 営業日共通クラス
 */
@Component
public class BusinessDayUtil {

	@Autowired
	private NonBusinessDayCalendarMasterRepository nonBusinessDayCalendarMasterRepository;

	@Autowired
	private BusinessCalendarRepository BusinessCalendarRepository;

	/**
	 * 営業日かどうか
	 * 
	 * @param date
	 *            日付
	 * @return
	 */
	public boolean isBusinessDay(Date date) {
		NonBusinessDayCalendarMaster nonBusinessDayCalendarMaster = nonBusinessDayCalendarMasterRepository.findOne(date);
		return nonBusinessDayCalendarMaster == null;
	}

	/**
	 * 最短営業日取得
	 * 
	 * @param date
	 *            日付
	 * @param leadTime
	 *            日数
	 * @param isSubtract
	 *            減算するかどうか
	 * @return
	 */
	public Date findShortestBusinessDay(Date date, int leadTime, boolean isSubtract) {
		Date retDate = date;
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < leadTime; i++) {
			while (true) {
				calendar.setTime(retDate);
				calendar.add(Calendar.DATE, !isSubtract ? 1 : -1);
				retDate = calendar.getTime();
				if (isBusinessDay(retDate)) {
					break;
				}
			}
		}

		return retDate;
	}

	/**
	 * 最短営業日取得_時間計算
	 * 
	 * @param date
	 *            日付
	 * @param leadTime
	 *            日数
	 * @param baseDate
	 *            基準日時
	 * @return
	 */
	public Date findShortestBusinessDayTimeCalc(Date date, int leadTime, Date baseDateTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDateTime);
		Date retDate = findShortestBusinessDay(date, leadTime, false);
		if (calendar.get(Calendar.AM_PM) == Calendar.PM) {
			while (true) {
				calendar.setTime(retDate);
				calendar.add(Calendar.DATE, 1);
				retDate = calendar.getTime();
				if (isBusinessDay(retDate)) {
					break;
				}
			}
		}

		return retDate;
	}

	/**
	 * 引数月の月末最終営業日取得
	 * 
	 * @param targetYm
	 *            月末最終営業日を取得したい月
	 * @return 月末最終営業日
	 */
	public Date getLastBusinessDayOfTheMonth(String targetYm) {
		BusinessCalendar result = findBusinessCalendarForSpecifiedMonth(targetYm).stream().sorted(Comparator.comparing(BusinessCalendar::getBusinessDay).reversed()).findFirst().orElse(null);
		return ObjectUtils.isEmpty(result) ? null : result.getBusinessDay();
	}

	/**
	 * 月初からn営業日以内か
	 * @param arg n営業日以内の指定
	 * @param date 検査対象日付
	 * @return true:n営業日以内である, false:n営業日以内でない
	 */
	public boolean isWithinBusinessDaysFromFirstDay(int arg, Date date) {
		// 0営業日以内ではないので、指定日数が0の場合はfalse
		if (arg == 0) {
			return false;
		}

		// 検査対象日付をLocalDateに変換
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		// 検査対象月の営業日リスト(降順)
		List<LocalDate> businessList = createTargetMonthBusinessDayList(localDate, SortOrder.ASCENDING);

		if (businessList != null && businessList.size() >= arg) {
			// n営業日後の日付
			LocalDate baseDate = businessList.get(arg - 1);
			// 「n営業日後の日付」以前の場合、true
			if (localDate.isBefore(baseDate) || localDate.isEqual(baseDate)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 月末からn営業日以内か
	 * @param arg n営業日以内の指定
	 * @param date 検査対象日付
	 * @return true:n営業日以内である, false:n営業日以内でない
	 */
	public boolean isWithinBusinessDaysFromLastDay(int arg, Date date) {
		// 0営業日以内ではないので、指定日数が0の場合はfalse
		if (arg == 0) {
			return false;
		}
		
		// 検査対象日付をLocalDateに変換
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		// 検査対象月の営業日リスト(降順)
		List<LocalDate> businessList = createTargetMonthBusinessDayList(localDate, SortOrder.DESCENDING);

		if (businessList != null && businessList.size() >= arg) {
			// n営業日前の日付
			LocalDate baseDate = businessList.get(arg - 1);
			// 「n営業日前の日付」以降の場合、true
			if (localDate.isAfter(baseDate) || localDate.isEqual(baseDate)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 引数月の業務カレンダーリスト取得
	 * 
	 * @param targetYm
	 *            業務カレンダーを取得したい月
	 * @return 業務カレンダーリスト（1月分）
	 */
	private List<BusinessCalendar> findBusinessCalendarForSpecifiedMonth(String targetYm) {
		int year = Integer.parseInt(StringUtils.substring(targetYm, 0, 4));
		int month = Integer.parseInt(StringUtils.substring(targetYm, -2));
		return findBusinessCalendarForSpecifiedRange(getStartOfMonth(year, month), getEndOfMonth(year, month));
	}

	/**
	 * 引数範囲の業務カレンダーリスト取得
	 * 
	 * @param targetPriodFrom
	 *            業務カレンダー取得条件(From)
	 * @param targetPriodTo
	 *            業務カレンダー取得条件(To)
	 * @return 業務カレンダーリスト
	 */
	private List<BusinessCalendar> findBusinessCalendarForSpecifiedRange(Date targetPriodFrom, Date targetPriodTo) {
		return BusinessCalendarRepository.findByBusinessDayBetween(targetPriodFrom, targetPriodTo);
	}

	/**
	 * 引数年月の月初日取得
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 月初日
	 */
	private Date getStartOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		// 指定年, 指定月, 1日, 0時0分0秒
		calendar.set(year, month - 1, 1, 0, 0, 0);
		// きっちりミリ秒も0にする
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 引数年月の月末日取得
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 月末日
	 */
	private Date getEndOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();

		// 指定年, 指定月(-1), 1日, 0時0分0秒
		calendar.set(year, month - 1, 1, 0, 0, 0);
		// 翌月にする (年またぎ対応)
		calendar.add(Calendar.MONTH, 1);
		// 0時0分0秒0ミリ秒 - 1ミリ秒で、月末23時59分59秒999ミリ秒にできる
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}

	/**
	 * 対象月の営業日リストを作成
	 * @param targetDate 対象日付
	 * @param sortOrder ソートオーダー
	 * @return 営業日リスト
	 */
	private List<LocalDate> createTargetMonthBusinessDayList(LocalDate targetDate, SortOrder sortOrder) {
		// 営業日リスト
		List<LocalDate> businessDayList = new ArrayList<LocalDate>();

		// 非営業日リストを取得
		Iterable<NonBusinessDayCalendarMaster> nonBusinessDayList = nonBusinessDayCalendarMasterRepository.findAll();
		// 非営業日セットを作成
		Set<LocalDate> nonBusinessDaySet = new HashSet<LocalDate>();
		for (NonBusinessDayCalendarMaster nonBusinessDay : nonBusinessDayList) {
			nonBusinessDaySet.add(nonBusinessDay.getNonBusinessDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}

		// 検査対象年月の月初日を生成
		LocalDate tmpDate = targetDate.with(TemporalAdjusters.firstDayOfMonth());
		// 翌月の月初日を生成
		LocalDate nextMonthDate = tmpDate.with(TemporalAdjusters.lastDayOfMonth()).plusDays(1);

		//　営業日リストに営業日を設定
		while (!tmpDate.isEqual(nextMonthDate)) {
			// 非営業日セットに存在しない場合、営業日を設定
			if (!nonBusinessDaySet.contains(tmpDate)) {
				businessDayList.add(tmpDate);
			}
			tmpDate = tmpDate.plusDays(1);
		}

		// 降順指定の場合ソート実施
		if (sortOrder.equals(SortOrder.DESCENDING)) {
			businessDayList.sort(Comparator.reverseOrder());
		}

		return businessDayList;
	}
}
