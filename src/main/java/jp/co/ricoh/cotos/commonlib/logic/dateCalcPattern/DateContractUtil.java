package jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.master.ItemMasterRepository;

/**
 * 契約に関連する共通処理を行うクラス
 */
@Component
public class DateContractUtil {

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	ItemMasterRepository itemMasterRepository;

	/**
	 * 積上がっている品種よりサービス終了日を取得する
	 *
	 * @param contract 			契約
	 * @param serviceTermStartDate サービス開始日
	 * @param endOfMonthFlg 		契約終了日月末フラグ
	 * @return サービス終了日
	 */
	public Date getServiceTermEndFromItem(Contract contract, Date serviceTermStartDate, boolean endOfMonthFlg) {

		// 品種マスタ取得
		List<ItemMaster> itemMasterList = new ArrayList<>();
		contract.getContractDetailList().stream().forEach(contractDetail -> {
			itemMasterList.add(itemMasterRepository.findOne(contractDetail.getItemContract().getItemMasterId()));
		});
		// 最大の契約月数を持つ品種マスタを取得
		ItemMaster itemMaster = itemMasterList.stream().filter(s -> s.getContractSpanMonth() != null).max(Comparator.comparing(ItemMaster::getContractSpanMonth)).orElse(null);
		if(itemMaster == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "契約期間月数" }));
		}
		return addMonthServiceTermEnd(serviceTermStartDate, itemMaster.getContractSpanMonth(), endOfMonthFlg);
	}

	/**
	 * 対象日付に指定の月数を加算しサービス終了日を取得する
	 * @param tagetDate
	 * @param addMonth
	 * @param endOfMonthFlg
	 * @return サービス終了日
	 */
	public Date addMonthServiceTermEnd(Date tagetDate, int addMonth, boolean endOfMonthFlg) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(tagetDate);
		cal.add(Calendar.MONTH, addMonth);
		// 契約終了日が月末の場合
		if (endOfMonthFlg) {
			cal.add(Calendar.MONTH, -1);
			cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		} else {
			cal.add(Calendar.DATE, -1);
		}
		return cal.getTime();
	}
}
