package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster.DateCalcBusinessDayType;
import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster.DateCalcType;
import lombok.Data;

/**
 * 日付計算パターンマスタの拡張項目を表すDto
 */
@Data
public class DateCalcPatternMasterExtendsParameterDto {

	/**
	 * 処理順
	 */
	private Integer sort;

	/**
	 * 日付計算区分
	 */
	private DateCalcType dateCalcType;

	/**
	 * 日付計算月数
	 */
	private String dateCalcMonth;

	/**
	 * 日付計算日数
	 */
	private String dateCalcDay;

	/**
	 * 日付計算営業日計算区分
	 */
	private DateCalcBusinessDayType dateCalcBusinessDayType;

	/**
	 * 日付計算設定時間
	 */
	private String dateCalcSetTime;

	/**
	 * 日付計算営業日フラグ
	 */
	private Integer dateCalcBusinessDayFlg;
}
