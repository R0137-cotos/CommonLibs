package jp.co.ricoh.cotos.commonlib.logic.maintenancePossibleLimit;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import lombok.NoArgsConstructor;

/**
 * 保守可能期限算出クラス
 */
@Component
@NoArgsConstructor
public class MaintenancePossibleLimitUtil {

	/**
	 * 保守可能期限を算出する
	 * @param contractType 契約種別
	 * @param maintenanceEndDate メーカー保守終了日
	 * @param purchaseDate 購入日
	 * @return 保守可能期限
	 */
	public Date calcMaintenancePossibleLimit(ContractType contractType, Date maintenanceEndDate, Date purchaseDate) {
		if (purchaseDate != null && contractType != null) {
			// 時、分、秒に関しては不要であるため、引数を修正する
			Date purchaseDateTruncate = DateUtils.truncate(purchaseDate, Calendar.DAY_OF_MONTH);
			Calendar maintCal = null;
			Calendar purchaseCal = null;
			if (ContractType.新規 == contractType) {

				// 以下のうち、短い方を保守可能期限とする。
				// ・(メーカー保守終了日 + 31日)
				if (maintenanceEndDate != null) {
					Date maintenanceEndDateTruncate = DateUtils.truncate(maintenanceEndDate, Calendar.DAY_OF_MONTH);
					maintCal = Calendar.getInstance();
					maintCal.setTime(maintenanceEndDateTruncate);
					maintCal.add(Calendar.DATE, 31);
				} else {
					// メーカー保守終了日が取得できない場合
					// 購入日 + 5年 + 31日
					maintCal = Calendar.getInstance();
					maintCal.setTime(purchaseDateTruncate);
					maintCal.add(Calendar.YEAR, 5);
					maintCal.add(Calendar.DATE, 31);
				}
				// ・(購入日 + 5年 + 62日）
				purchaseCal = Calendar.getInstance();
				purchaseCal.setTime(purchaseDateTruncate);
				purchaseCal.add(Calendar.YEAR, 5);
				purchaseCal.add(Calendar.DATE, 62);
			} else {
				// 以下のうち、短い方を保守可能期限とする。
				// ・(メーカー保守終了日 + 5ヶ月後の月末 + 31日)
				if (maintenanceEndDate != null) {
					Date maintenanceEndDateTruncate = DateUtils.truncate(maintenanceEndDate, Calendar.DAY_OF_MONTH);
					maintCal = Calendar.getInstance();
					maintCal.setTime(maintenanceEndDateTruncate);
					maintCal.add(Calendar.MONTH, 5);
					maintCal.set(Calendar.DATE, maintCal.getActualMaximum(Calendar.DATE));
					maintCal.add(Calendar.DATE, 31);
				} else {
					// メーカー保守終了日が取得できない場合
					// 購入日 + 5年5ヶ月の月末 + 31日
					maintCal = Calendar.getInstance();
					maintCal.setTime(purchaseDateTruncate);
					maintCal.add(Calendar.YEAR, 5);
					maintCal.add(Calendar.MONTH, 5);
					maintCal.set(Calendar.DATE, maintCal.getActualMaximum(Calendar.DATE));
					maintCal.add(Calendar.DATE, 31);
				}
				// ・(購入日 + 5年5ヶ月の月末 + 62日）
				purchaseCal = Calendar.getInstance();
				purchaseCal.setTime(purchaseDateTruncate);
				purchaseCal.add(Calendar.YEAR, 5);
				purchaseCal.add(Calendar.MONTH, 5);
				purchaseCal.set(Calendar.DATE, purchaseCal.getActualMaximum(Calendar.DATE));
				purchaseCal.add(Calendar.DATE, 62);
			}
			Calendar result = null;
			if (maintCal.compareTo(purchaseCal) <= 0) {
				result = maintCal;
			} else {
				result = purchaseCal;
			}
			return result.getTime();
		}
		return null;
	}
}
