package jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DateCalcPatternMasterDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DateCalcPatternMasterExtendsParameterDto;
import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster.DateCalcBusinessDayType;
import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster.DateCalcType;
import jp.co.ricoh.cotos.commonlib.logic.businessday.BusinessDayUtil;
import jp.co.ricoh.cotos.commonlib.logic.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 日付計算パターン共通クラス
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
public class DateCalcPatternUtil {

	@Autowired
	protected BusinessDayUtil businessDayUtil;

	@Autowired
	protected JsonUtil jsonUtil;

	@Autowired
	ObjectMapper mapper;

	private final String PLUS = "+";

	private final String MINUS = "-";

	private final String END = "end";

	/**
	 * 日付計算パターンマスタの設定値により日付を計算。計算結果の日付を返却する。
	 *
	 * @param dateCalcPatternMaster 日付計算パターンマスタ
	 * @param referenceDate		 計算基準日
	 * @return 計算結果の日付
	 */
	public Date dateCalc(DateCalcPatternMaster dateCalcPatternMaster, Date referenceDate) {

		if(dateCalcPatternMaster == null || referenceDate == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(referenceDate);

		// 日付計算パターンマスタのデータを計算条件格納用DTOへ設定
		DateCalcPatternMasterExtendsParameterDto dateCalcPatternDto = new DateCalcPatternMasterExtendsParameterDto();
		BeanUtils.copyProperties(dateCalcPatternMaster, dateCalcPatternDto);

		// 日付計算処理
		dateCalc(dateCalcPatternDto, cal);

		// 拡張項目に設定される条件で日付計算。
		String extendsParameter = dateCalcPatternMaster.getExtendsParameter();
		if(StringUtils.isNoneEmpty(extendsParameter)) {

			// 拡張項目設定されている計算条件をリストへ格納。計算順でソートする。
			DateCalcPatternMasterDto dateCalcPatternMasterDto = jsonUtil.convertToDto(extendsParameter, DateCalcPatternMasterDto.class);

			if(dateCalcPatternMasterDto != null
					&& dateCalcPatternMasterDto.getDateCalcPatternMasterExtendsParameterDtoList() != null
					&& !dateCalcPatternMasterDto.getDateCalcPatternMasterExtendsParameterDtoList().isEmpty()) {

				List<DateCalcPatternMasterExtendsParameterDto> extendsParameteList = dateCalcPatternMasterDto.getDateCalcPatternMasterExtendsParameterDtoList();
				extendsParameteList = extendsParameteList.stream()
						.sorted(Comparator.comparing(DateCalcPatternMasterExtendsParameterDto::getSort))
						.collect(Collectors.toList());
				extendsParameteList.stream().forEach(s -> dateCalc(s, cal));
			}
		}
		return cal.getTime();
	}

	/**
	 * 日付計算
	 *
	 * @param dateCalcDto	 計算条件格納用DTO
	 * @param trgetCalendar 計算対象カレンダーオブジェクト
	 */
	private void dateCalc(DateCalcPatternMasterExtendsParameterDto dateCalcDto, Calendar trgetCalendar) {

		if(dateCalcDto.getDateCalcType() == DateCalcType.月日加算) {
			Integer businessDayFlg = Optional.ofNullable(dateCalcDto.getDateCalcBusinessDayFlg()).orElse(0);
			DateCalcBusinessDayType businessDayType = dateCalcDto.getDateCalcBusinessDayType();

			boolean monthCalucBusinessDayFlg = true;

			// 月の計算
			Optional.ofNullable(dateCalcDto.getDateCalcMonth()).ifPresent(addMonth -> {
				addCalucDay(trgetCalendar, Calendar.MONTH, dateCalcDto.getDateCalcMonth());
			});
			// 日の加算
			String addDay = dateCalcDto.getDateCalcDay();
			if(StringUtils.isNoneBlank(addDay)) {

				// 接頭辞(+ or -)取得
				String prefix = addDay.substring(0, 1);

				if(PLUS.equals(prefix) || MINUS.equals(prefix)) {
					int days = Integer.valueOf(addDay.substring(1));

					// 指定日数分繰り返す
					IntStream.rangeClosed(1, days).forEach(i -> {
						addCalucDay(trgetCalendar, Calendar.DATE, prefix + "1");
						addCalucBusinessDay(trgetCalendar, addDay, businessDayType, businessDayFlg);
					});
				} else {
					addCalucDay(trgetCalendar, Calendar.DATE, addDay);
					addCalucBusinessDay(trgetCalendar, addDay, businessDayType, businessDayFlg);

					// 以下条件の場合、営業日リスト[DateCalcDay-1](= DateCalcDayの営業日) を設定する
					// 1．日付計算パターンマスタ．date_calc_dayに+/-がつかない
					// 2．日付計算パターンマスタ．date_calc_business_day_flg = 1
					if ((!PLUS.equals(addDay.substring(0, 1)) && !MINUS.equals(addDay.substring(0, 1))) && (businessDayFlg == 1)) {
						// 営業日リストを取得する
						List<Date> businessDayList = businessDayUtil.findBusinessDayCalendarForSpecifiedMonth(trgetCalendar);
						// 営業日リストのcalcValue-1のデータを計算対象カレンダーオブジェクトに設定する
						trgetCalendar.setTime(businessDayList.get(Integer.parseInt(addDay) - 1));
					}
				}
				monthCalucBusinessDayFlg = false;
			}
			// 日の計算を行わない場合、月の計算結果に対して営業日判定処理を行う
			if(monthCalucBusinessDayFlg) {
				addCalucBusinessDay(trgetCalendar, "1", businessDayType, businessDayFlg);
			}
			// 時分秒の設定
			if(StringUtils.isNotBlank(dateCalcDto.getDateCalcSetTime())) {
				String[] hourList = dateCalcDto.getDateCalcSetTime().split(":");
				trgetCalendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hourList[0]));
				if(hourList.length >= 2)trgetCalendar.set(Calendar.MINUTE, Integer.valueOf(hourList[1]));
				if(hourList.length >= 3)trgetCalendar.set(Calendar.SECOND, Integer.valueOf(hourList[2]));
			}
		}
	}

	/**
	 * 日付加減算処理
	 *
	 * @param trgetCalendar 計算対象カレンダーオブジェクト
	 * @param caluTrgetType 加減算対象：月 or 日
	 * @param calcValue	 加減算値
	 */
	private void addCalucDay(Calendar trgetCalendar, int caluTrgetType, String calcValue) {

		if(END.equals(calcValue)) {
			// 月の月末日を設定
			trgetCalendar.set(caluTrgetType, trgetCalendar.getActualMaximum(Calendar.DATE));
		} else {
			String prefix = calcValue.substring(0, 1);

			if(PLUS.equals(prefix)) {
				// 加算
				trgetCalendar.add(caluTrgetType, Integer.valueOf(calcValue.substring(1)));
			} else if(MINUS.equals(prefix)) {
				// 減算
				trgetCalendar.add(caluTrgetType, -Integer.valueOf(calcValue.substring(1)));
			} else {
				// 直接月or日の値を設定
				if(Calendar.MONTH == caluTrgetType) {
					trgetCalendar.set(caluTrgetType, Integer.parseInt(calcValue) - 1);
				} else {
					trgetCalendar.set(caluTrgetType, Integer.parseInt(calcValue));
				}
			}
		}
	}

	/**
	 * 営業日加減算処理
	 *
	 * @param trgetCalendar   計算対象カレンダーオブジェクト
	 * @param calcValue	   加減算値
	 * @param businessDayType 営業日計算区分
	 * @param businessDayFlg  営業日フラグ
	 */
	private void addCalucBusinessDay(Calendar trgetCalendar, String calcValue, DateCalcBusinessDayType businessDayType, Integer businessDayFlg) {

		if(StringUtils.isBlank(calcValue) || (businessDayType == null && businessDayFlg == 0)) {
			// 営業日判定不要
			return;
		}
		// 接頭辞(+ or -)取得
		String prefix = calcValue.substring(0, 1);

		// 日付直接指定時、前後どちらの営業日にずらすかの判定
		if(!PLUS.equals(prefix) && !MINUS.equals(prefix)) {
			if(businessDayType == null) {
				return;
			}
			if(DateCalcBusinessDayType.前営業日.equals(businessDayType)) {
				prefix = MINUS;
			} else if(DateCalcBusinessDayType.後営業日.equals(businessDayType)) {
				prefix = PLUS;
			}
		} else if(businessDayFlg == null || businessDayFlg == 0) {
			// 日付直接指定以外で営業日を考慮しない場合、処理不要
			return;
		}
		String addDay = prefix + 1;
		while (true) {
			// 計算結果が営業日になるまで繰り返す
			if (businessDayUtil.isBusinessDay(DateUtils.truncate(trgetCalendar.getTime(), Calendar.DAY_OF_MONTH))) {
				return;
			}
			// 日付加減算処理
			addCalucDay(trgetCalendar, Calendar.DATE, addDay);
		}
	}

	/**
	 * String→Date変換
	 *
	 * @param trgValue 変換対象値
	 * @param format	フォーマット
	 * @return Date
	 */
	public Date stringToDateConverter(String trgValue, String format) {

		String tmpFormat = Optional.ofNullable(format).orElse("yyyyMMdd");
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat(tmpFormat);
			return sdFormat.parse(trgValue);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 *  Date⇒String変換
	 *
	 * @param trgValue	変換対象値
	 * @param format	フォーマット
	 * @return String
	 */
	public String dateToStringConverter(Date trgValue, String format) {

		String tmpFormat = Optional.ofNullable(format).orElse("yyyyMMdd");
		SimpleDateFormat sdFormat = new SimpleDateFormat(tmpFormat);
		return sdFormat.format(trgValue);
	}
}
