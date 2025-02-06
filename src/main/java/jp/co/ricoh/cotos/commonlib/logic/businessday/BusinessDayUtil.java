package jp.co.ricoh.cotos.commonlib.logic.businessday;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SortOrder;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.ricoh.cotos.commonlib.entity.master.BusinessCalendar;
import jp.co.ricoh.cotos.commonlib.entity.master.NonBusinessDayCalendarMaster;
import jp.co.ricoh.cotos.commonlib.repository.master.BusinessCalendarRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.NonBusinessDayCalendarMasterRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 営業日共通クラス
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
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
		NonBusinessDayCalendarMaster nonBusinessDayCalendarMaster = nonBusinessDayCalendarMasterRepository.findOneByNonBusinessDayAndVendorShortNameIsNull(date);
		return nonBusinessDayCalendarMaster == null;
	}

	/**
	 * 営業日かどうか
	 * @param date 調査対象日付
	 * @param vendorShortNameList ベンダー略称リスト
	 * @return true:営業日である false:営業日でない
	 */
	public boolean isBusinessDay(Date date, List<String> vendorShortNameList) {
		// 全ベンダー共通の非営業日(vendorName == null)を非営業日カレンダーマスタから取得
		NonBusinessDayCalendarMaster nonBusinessDayCalendarMaster = nonBusinessDayCalendarMasterRepository.findOneByNonBusinessDayAndVendorShortNameIsNull(date);
		// 全ベンダー共通の非営業日でない場合、特定ベンダーの非営業日であるかを確認する
		if (nonBusinessDayCalendarMaster == null && !CollectionUtils.isEmpty(vendorShortNameList)) {
			for (String vendorShortName : vendorShortNameList) {
				nonBusinessDayCalendarMaster = nonBusinessDayCalendarMasterRepository.findOneByNonBusinessDayAndVendorShortNameLike(date, "%" + vendorShortName + "%");
				if (nonBusinessDayCalendarMaster != null && !StringUtils.isEmpty(nonBusinessDayCalendarMaster.getVendorShortName())) {
					// ベンダー略称の完全一致検索を行う
					String[] vendorShortNameArr = nonBusinessDayCalendarMaster.getVendorShortName().split(",");
					nonBusinessDayCalendarMaster = Arrays.stream(vendorShortNameArr).filter(e -> vendorShortName.equals(e)).count() > 0 ? nonBusinessDayCalendarMaster : null;
					if (nonBusinessDayCalendarMaster != null) {
						break;
					}
				}
			}
		}
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

		return truncateDate(retDate);
	}

	/**
	 * 最短営業日取得
	 * @param date 調査対象日付
	 * @param leadTime 日数
	 * @param isSubtract 減算するかどうか
	 * @param vendorShortNameList ベンダー略称リスト
	 * @return 最短営業日取得
	 */
	public Date findShortestBusinessDay(Date date, int leadTime, boolean isSubtract, List<String> vendorShortNameList) {
		Date retDate = date;
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < leadTime; i++) {
			while (true) {
				calendar.setTime(retDate);
				calendar.add(Calendar.DATE, !isSubtract ? 1 : -1);
				retDate = calendar.getTime();
				if (isBusinessDay(retDate, vendorShortNameList)) {
					break;
				}
			}
		}

		return truncateDate(retDate);
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

		return truncateDate(retDate);
	}

	/**
	 * 引数月の月末最終営業日取得(業務カレンダーマスタ)
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
	 * 引数月の月末最終営業日取得(非営業日カレンダーマスタ)
	 *
	 * @param targetYm 月末最終営業日を取得したい月(yyyyMM)
	 * @return 月末最終営業日
	 */
	public Date getLastBusinessDayOfTheMonthFromNonBusinessCalendarMaster(String targetYm) {
		// 対象月特定不可能の場合は月末最終営業日を返さない
		if (targetYm == null || targetYm.length() != 6) {
			return null;
		}
		try {
			int year = Integer.parseInt(StringUtils.substring(targetYm, 0, 4));
			int month = Integer.parseInt(StringUtils.substring(targetYm, -2));

			// 対象月月初日
			LocalDate firstDayOfTheMonth = LocalDate.of(year, month, 1);
			// 対象月月末日
			LocalDate lastDayOfTheMonth = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);

			// 対象月の非営業日カレンダーマスタリストを取得する
			List<NonBusinessDayCalendarMaster> targetMonthNonBusinessDayList = findNonBusinessDayCalendarForSpecifiedMonth(year, month);

			if (!CollectionUtils.isEmpty(targetMonthNonBusinessDayList)) {
				// 非営業日リスト
				List<LocalDate> nonBusinessDayList = new ArrayList<>();
				for (NonBusinessDayCalendarMaster nonBusinessDay : targetMonthNonBusinessDayList) {
					if (nonBusinessDay.getNonBusinessDay() != null) {
						nonBusinessDayList.add(nonBusinessDay.getNonBusinessDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
					}
				}

				if (!CollectionUtils.isEmpty(nonBusinessDayList)) {
					// 月末日から一日ずつ減らしていき、月初日と同日になるまで回す
					while (!firstDayOfTheMonth.isEqual(lastDayOfTheMonth)) {
						// 非営業日リストに入っていない日付の場合、月末最終営業日である
						if (!nonBusinessDayList.contains(lastDayOfTheMonth)) {
							return Date.from(lastDayOfTheMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
						}
						lastDayOfTheMonth = lastDayOfTheMonth.minusDays(1);
					}
					// 対象月の全てが非営業日の場合
					return null;
				}
			}

			// 非営業日カレンダーマスタリストが存在しない場合、対象月月末日を返す
			return Date.from(lastDayOfTheMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
		} catch (Exception ex) {
			return null;
		}
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
		LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();

		// 検査対象月の営業日リスト(昇順)
		List<LocalDate> businessList = createTargetMonthBusinessDayList(localDate, SortOrder.ASCENDING);

		if (businessList != null && businessList.size() >= arg + 1) {
			// n営業日後の日付
			LocalDate baseDate = businessList.get(arg);
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
		LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();

		// 検査対象月の営業日リスト(降順)
		List<LocalDate> businessList = createTargetMonthBusinessDayList(localDate, SortOrder.DESCENDING);

		if (businessList != null && businessList.size() >= arg + 1) {
			// n営業日前の日付
			LocalDate baseDate = businessList.get(arg);
			// 「n営業日前の日付」以降の場合、true
			if (localDate.isAfter(baseDate) || localDate.isEqual(baseDate)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 引数日付間の営業日差を計算して返す
	 * 以下の場合にエラーとし、-1を返す
	 * １．bigDate=null または smallDate=null
	 * ２．bigDate=非営業日 または smallDate=非営業日
	 * ３．bigDate < smallDate
	 * @param smallDate 日付(小)
	 * @param bigDate 日付(大)
	 * @return 日付(大)-日付(小)の営業日差
	 */
	public int calculateDifferenceBetweenBusinessDates(LocalDate smallDate, LocalDate bigDate) {
		// １．bigDate=null または smallDate=null
		if (smallDate == null || bigDate == null) {
			return -1;
		}
		// ２．bigDate=非営業日 または smallDate=非営業日
		if (!isBusinessDay(Date.from(bigDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())) || !isBusinessDay(Date.from(smallDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
			return -1;
		}
		// ３．bigDate < smallDate
		if (bigDate.isBefore(smallDate)) {
			return -1;
		}
		// 同日の場合差は0
		if (bigDate == smallDate) {
			return 0;
		}

		// createBetweenBusinessDayListが始点と終点を含まないリストを取得するため、日付(小)のみリストに含めるようにずらす
		// 日付(大)を基準に差を計算するため、日付(大)はリストに含めない
		LocalDate tmpSmallDate = smallDate.minusDays(1);
		// 日付間の営業日リストのサイズが日付(大)-日付(小)の営業日差
		List<LocalDate> businessDayList = createBetweenBusinessDayList(tmpSmallDate, bigDate, SortOrder.ASCENDING);
		// 営業日リストの取得に失敗した場合エラー
		if (businessDayList == null) {
			return -1;
		}
		return businessDayList.size();
	}

	/**
	 * 基準日(営業日)からn営業日前の営業日を取得する
	 * @param baseDate 基準日
	 * @param beforeNumber n営業日前の指定
	 * @return 基準日からn営業日前の営業日
	 */
	public LocalDate getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate baseDate, int beforeNumber) {
		return getBusinessDateNumberBusinessDaysBeforeBaseDate(baseDate, beforeNumber, false);
	}

	/**
	 * 基準日(営業日)からn営業日前の営業日を取得する
	 * @param baseDate 基準日
	 * @param beforeNumber n営業日前の指定
	 * @param permitBaseDateNonBusiness 基準日が非営業日でも値を取得する
	 * @return 基準日からn営業日前の営業日
	 */
	public LocalDate getBusinessDateNumberBusinessDaysBeforeBaseDate(LocalDate baseDate, int beforeNumber, boolean permitBaseDateNonBusiness) {
		if (baseDate == null) {
			return null;
		}
		// baseDate=非営業日の場合、nullを返す
		if (!permitBaseDateNonBusiness
				&& !isBusinessDay(Date.from(baseDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
			return null;
		}
		if (beforeNumber < 0) {
			return null;
		}
		if (beforeNumber == 0) {
			return baseDate;
		}

		// 非営業日リストを取得
		Iterable<NonBusinessDayCalendarMaster> nonBusinessDayIterable = nonBusinessDayCalendarMasterRepository.findByVendorShortNameIsNull();

		if (nonBusinessDayIterable == null) {
			// 非営業日リストが存在しない場合、全て営業日なので単純に日数分遡れば良い
			return baseDate.minusDays(beforeNumber);
		}

		// 非営業日リスト
		List<Date> nonBusinessDayList = new ArrayList<>();

		for (NonBusinessDayCalendarMaster nonBusinessDay : nonBusinessDayIterable) {
			nonBusinessDayList.add(nonBusinessDay.getNonBusinessDay());
		}

		boolean notTarget = true;
		while (notTarget) {
			// カウントが0になった時の日付が取得対象
			if (beforeNumber == 0) {
				notTarget = false;
			} else {
				baseDate = baseDate.minusDays(1);
			}

			// 非営業日リストにない場合カウントを進める
			if (!nonBusinessDayList.contains(Date.from(baseDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
				beforeNumber--;
			}
		}

		return baseDate;
	}

	/**
	 * 日付1は日付2のn営業日以内か
	 * @param date1 日付1
	 * @param date2 日付2
	 * @param num n営業日以内の指定
	 * @return　true:n営業日以内である, false:n営業日以内でない
	 */
	public boolean isDate1WithinNumBusinessDaysOfDate2(Date date1, Date date2, int num) {
		date1 = truncateDate(date1);
		date2 = truncateDate(date2);

		// 同一日の場合、必ず0営業日以内
		if (date1 == date2) {
			return true;
		}
		// 同一日ではないので、必ず0営業日以内ではない
		if (num == 0) {
			return false;
		}

		LocalDate bigDate;
		LocalDate smallDate;
		// 後に営業日判定で利用するので終点をDate型で保存しておく
		Date endDate = date1;
		if (date2.after(date1)) {
			bigDate = new java.sql.Date(date2.getTime()).toLocalDate();
			smallDate = new java.sql.Date(date1.getTime()).toLocalDate();
			endDate = date2;
		} else {
			bigDate = new java.sql.Date(date1.getTime()).toLocalDate();
			smallDate = new java.sql.Date(date2.getTime()).toLocalDate();
		}

		// 日付1-日付2間の営業日リスト取得(昇順)
		// isDate1WithinNumBusinessDaysOfDate2は「日付1は日付2のn営業日以内か」を調べるので、始点を営業日に含めず、終点を営業日に含めたい
		// createBetweenBusinessDayListは始点・終点は営業日に含まずに取得するので、終点の日付を一日後ろにずらす
		bigDate = bigDate.plusDays(1);
		List<LocalDate> businessList = createBetweenBusinessDayList(smallDate, bigDate, SortOrder.ASCENDING);

		// 日付1-日付2どちらも非営業日の場合
		// if (!isBusinessDay(date1) && !isBusinessDay(date2)) {
		// 終点が非営業日の場合
		if (!isBusinessDay(endDate)) {
			// 日付1-日付2間の営業日リストがn-1より大きい場合、n営業日以内ではない
			// 例)間の営業日数=5の時、非営業日to非営業日の場合は外側に範囲日付を持っているため、5営業日以内ではなく6営業日以内となる
			if (businessList != null && businessList.size() > num - 1) {
				return false;
			}
		} else {
			// 日付1-日付2間の営業日リストがnより大きい場合、n営業日以内ではない
			if (businessList != null && businessList.size() > num) {
				return false;
			}
		}

		return true;
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
	 * 引数月の非営業日カレンダーリスト取得
	 *
	 * @param year 非営業日カレンダーを取得したい年
	 * @param month 非営業日カレンダーを取得したい月
	 * @return 非営業日カレンダーリスト（1月分）
	 */
	private List<NonBusinessDayCalendarMaster> findNonBusinessDayCalendarForSpecifiedMonth(int year, int month) {
		// 対象月の非営業日リスト
		List<NonBusinessDayCalendarMaster> targetMonthList = new ArrayList<>();

		// 対象月月初日
		LocalDate firstDayOfTheTargetMonth = LocalDate.of(year, month, 1);
		// 対象月月末日
		LocalDate lastDayOfTheTargetMonth = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);

		// 全非営業日カレンダーマスタを取得
		List<NonBusinessDayCalendarMaster> nonBusinessDayCalendarMasterList = (List<NonBusinessDayCalendarMaster>) nonBusinessDayCalendarMasterRepository.findByVendorShortNameIsNull();

		if (CollectionUtils.isEmpty(nonBusinessDayCalendarMasterList)) {
			return null;
		}

		// filter: 非営業日 != null
		// filter: 対象月月初日 <= 非営業日
		// filter: 非営業日 <= 対象月月末日
		nonBusinessDayCalendarMasterList.stream().filter(e -> e.getNonBusinessDay() != null).filter(e -> e.getNonBusinessDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(lastDayOfTheTargetMonth) || e.getNonBusinessDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(lastDayOfTheTargetMonth)).filter(e -> e.getNonBusinessDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(firstDayOfTheTargetMonth) || e.getNonBusinessDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(firstDayOfTheTargetMonth)).forEach(e -> {
			// 対象月の非営業日をリストに格納する
			targetMonthList.add(e);
		});

		// 対象月の非営業日リストを返す
		return targetMonthList;
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
		Iterable<NonBusinessDayCalendarMaster> nonBusinessDayList = nonBusinessDayCalendarMasterRepository.findByVendorShortNameIsNull();
		// 非営業日セットを作成
		Set<LocalDate> nonBusinessDaySet = new HashSet<LocalDate>();
		for (NonBusinessDayCalendarMaster nonBusinessDay : nonBusinessDayList) {
			nonBusinessDaySet.add(new java.sql.Date(nonBusinessDay.getNonBusinessDay().getTime()).toLocalDate());
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

	/**
	 * 日付1と日付2の間の営業日リストを作成する(始点・終点は営業日に含まない)
	 * @param date1 日付1
	 * @param date2 日付2
	 * @param sortOrder ソートオーダー
	 * @return 営業日リスト
	 */
	private List<LocalDate> createBetweenBusinessDayList(LocalDate date1, LocalDate date2, SortOrder sortOrder) {
		// 営業日リスト
		List<LocalDate> businessDayList = new ArrayList<LocalDate>();

		// 非営業日リストを取得
		Iterable<NonBusinessDayCalendarMaster> nonBusinessDayList = nonBusinessDayCalendarMasterRepository.findByVendorShortNameIsNull();
		// 非営業日セットを作成
		Set<LocalDate> nonBusinessDaySet = new HashSet<LocalDate>();
		for (NonBusinessDayCalendarMaster nonBusinessDay : nonBusinessDayList) {
			nonBusinessDaySet.add(new java.sql.Date(nonBusinessDay.getNonBusinessDay().getTime()).toLocalDate());
		}

		// 同一日付の場合、対象日付が営業日かだけ確認する
		if (date1.isEqual(date2)) {
			// 非営業日リストに入っていなければ営業日
			if (nonBusinessDaySet.contains(date1)) {
				businessDayList.add(date1);
			}
			return businessDayList;
		}

		LocalDate bigDate;
		LocalDate smallDate;
		if (date2.isAfter(date1)) {
			bigDate = date2;
			smallDate = date1;
		} else {
			bigDate = date1;
			smallDate = date2;
		}

		// 終点日付をずらすことで、終点日付を営業日換算から省く
		bigDate = bigDate.minusDays(1);

		//　営業日リストに営業日を設定
		while (!smallDate.isEqual(bigDate)) {
			// 先に始点日付をずらすことで、始点日付を営業日換算から省く
			smallDate = smallDate.plusDays(1);
			// 非営業日セットに存在しない場合、営業日を設定
			if (!nonBusinessDaySet.contains(smallDate)) {
				businessDayList.add(smallDate);
			}

		}

		// 降順指定の場合ソート実施
		if (sortOrder.equals(SortOrder.DESCENDING)) {
			businessDayList.sort(Comparator.reverseOrder());
		}

		return businessDayList;
	}

	/**
	 * nonBusinessDayCalendarMasterRepository生成
	 * @param nonBusinessDayCalendarMasterRepository 非営業日カレンダーマスタリポジトリ
	 */
	public void setNonBusinessDayCalendarMasterRepository(NonBusinessDayCalendarMasterRepository nonBusinessDayCalendarMasterRepository) {
		this.nonBusinessDayCalendarMasterRepository = nonBusinessDayCalendarMasterRepository;
	}

	/**
	 * businessCalendarRepository生成
	 * @param businessCalendarRepository 業務カレンダーリポジトリ
	 */
	public void setBusinessCalendarRepository(BusinessCalendarRepository businessCalendarRepository) {
		this.BusinessCalendarRepository = businessCalendarRepository;
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

	/**
	 * 引数月の営業日カレンダーリスト取得
	 *
	 * @param date 日付
	 * @return 営業日カレンダーリスト（1月分）
	 */
	public List<Date> findBusinessDayCalendarForSpecifiedMonth(Calendar date) {

		// 対象月の営業日リスト
		List<Date> targetMonthList = new ArrayList<>();

		// 引数の年を設定
		int year = date.get(Calendar.YEAR);
		// 引数の月を設定
		// 月初日と月末日取得の処理でmonth-1がされていため、+1する（MONTHは0番目が1月となっている）
		int month = date.get(Calendar.MONTH) + 1;
		// 加算する日数を設定
		String addDay = "+1";

		// 対象月月初日
		Date firstDayOfTheTargetMonth = getStartOfMonth(year, month);
		// 対象月月末日
		Date lastDayOfTheTargetMonth = getEndOfMonth(year, month);

		// 引数に月初日を設定
		date.setTime(firstDayOfTheTargetMonth);

		// 全非営業日カレンダーマスタを取得
		List<NonBusinessDayCalendarMaster> nonBusinessDayCalendarMasterList = (List<NonBusinessDayCalendarMaster>) nonBusinessDayCalendarMasterRepository.findByVendorShortNameIsNull();
		// 非営業日リストを作成nonBusinessDayCalendarMasterList.stream().filter(null)
		// 非営業日リスト
		List<Date> nonBusinessDayList = new ArrayList<>();

		for (NonBusinessDayCalendarMaster nonBusinessDay : nonBusinessDayCalendarMasterList) {
			nonBusinessDayList.add(nonBusinessDay.getNonBusinessDay());
		}

		if (CollectionUtils.isEmpty(nonBusinessDayCalendarMasterList)) {
			return null;
		}

		// 営業日リストを作成
		// 月末日になるまで繰り返す
		while (true) {
			// 営業日チェック
			// 営業日なら、リストに追加
			if (!nonBusinessDayList.contains(date.getTime())) {
				targetMonthList.add(date.getTime());
			}

			if (DateUtils.isSameDay(DateUtils.truncate(date.getTime(), Calendar.DAY_OF_MONTH), lastDayOfTheTargetMonth)) {
				return targetMonthList;
			}

			// 加算処理
			date.add(Calendar.DATE, Integer.valueOf(addDay.substring(1)));
		}
	}
}