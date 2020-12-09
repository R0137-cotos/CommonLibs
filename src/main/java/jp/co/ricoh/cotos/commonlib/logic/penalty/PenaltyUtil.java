package jp.co.ricoh.cotos.commonlib.logic.penalty;

import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation.EstimationType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.ItemMasterRepository;

/**
 * 違約金処理共通クラス
 */
@Component
public class PenaltyUtil {

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	ItemMasterRepository itemMasterRepository;

	/**
	 *
	 * 違約金チェック（見積）
	 * 1.パラメーター.見積から変更元契約を取得する。
	 * 2.パラメーター.品種マスタIDに紐づく品種マスタを取得する。
	 * 3.取得した品種マスタの違約金有無フラグが「1：有」の場合、3以降の処理を実施。そうでない場合、処理終了。
	 * 4.品種マスタ.契約期間起算日区分に合わせた判定方法で判定し、条件に一致した品種マスタを返却する。
	 * @param Estimation 見積
	 * @param itemMasterId 品種マスタID
	 * @return ItemMaster 品種マスタ
	 */
	public ItemMaster penaltyCheck(Estimation estimation, long itemMasterId) {
		// 見積情報チェック
		if (estimation == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistEstimation", new String[] { "見積" }));
		}
		// 1.パラメーター.見積から変更元契約を取得する。
		if (EstimationType.契約変更 != estimation.getEstimationType() || estimation.getOriginContractId() == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistContract", new String[] { "変更元契約" }));
		}
		Contract originContract = contractRepository.findOne(estimation.getOriginContractId());
		if (originContract == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistContract", new String[] { "変更元契約" }));
		}
		// 2.パラメーター.品種マスタIDに紐づく品種マスタを取得する。
		ItemMaster itemMaster = itemMasterRepository.findOne(itemMasterId);
		if (itemMaster == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistContract", new String[] { "品種マスタID" }));
		}
		// 3.取得した品種マスタの違約金有無フラグが「1：有」の場合、3以降の処理を実施。そうでない場合、処理終了。
		if (Arrays.stream(new Boolean[] { Optional.ofNullable(itemMaster.getPenaltyFlg()).orElse(0) == 1 }).allMatch(s -> s == true)) {
			// 4.品種マスタ.契約期間起算日区分に合わせた判定方法で判定し、条件に一致した品種マスタを返却する。
			switch (itemMaster.getPenaltyStartDateType()) {
			case サービス開始日:
				// 「変更元契約.サービス開始日」とシステム日付の差分 ＜ 品種マスタ.最低契約月数の場合、品種マスタを返却
				if (getDiffMonth(truncateMonth(originContract.getServiceTermStart()), truncateMonth(new Date())) < itemMaster.getMinContractMonths()) {
					return itemMaster;
				}
				break;
			case サービス開始翌月１日:
				// 「変更元契約.サービス開始日翌月1日」とシステム日付の差分 ＜ 品種マスタ.最低契約月数の場合、品種マスタを返却
				if (getDiffMonth(truncateMonth(originContract.getServiceTermStart(), 1), truncateMonth(new Date())) < itemMaster.getMinContractMonths()) {
					return itemMaster;
				}
				break;
			}
		}
		return null;
	}

	/**
	 *
	 * 違約金チェック（契約）
	 * 1.パラメーター.品種マスタIDに紐づく品種マスタを取得する。
	 * 2.取得した品種マスタの違約金有無フラグが「1：有」の場合、3以降の処理を実施。そうでない場合、処理終了。
	 *   また、サービス開始日・解約予定日がNULLの場合、違約金は発生しないので処理終了する。
	 * 3.品種マスタ.契約期間起算日区分に合わせた判定方法で判定し、条件に一致した品種マスタを返却する。
	 * @param Contract 契約
	 * @param itemMasterId 品種マスタID
	 * @return ItemMaster 品種マスタ
	 */
	public ItemMaster penaltyCheck(Contract contract, long itemMasterId) {
		// 契約情報チェック
		if (contract == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistContract", new String[] { "契約" }));
		}
		// 1.パラメーター.品種マスタIDに紐づく品種マスタを取得する。
		ItemMaster itemMaster = itemMasterRepository.findOne(itemMasterId);
		if (itemMaster == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistContract", new String[] { "品種マスタID" }));
		}
		// 2.取得した品種マスタの違約金有無フラグが「1：有」の場合、3以降の処理を実施。そうでない場合、処理終了。
		//   また、サービス開始日・解約予定日がNULLの場合、違約金は発生しないので処理終了する。
		if (Arrays.stream(new Boolean[] { Optional.ofNullable(itemMaster.getPenaltyFlg()).orElse(0) == 1, contract.getCancelScheduledDate() != null, contract.getServiceTermStart() != null }).allMatch(s -> s == true)) {
			// 3.品種マスタ.契約期間起算日区分に合わせた判定方法で判定し、条件に一致した品種マスタを返却する。
			switch (itemMaster.getPenaltyStartDateType()) {
			case サービス開始日:
				// 「契約.サービス開始日」と「契約.解約予定日」の差分 ＜ 品種マスタ.最低契約月数の場合、品種マスタを返却
				if (getDiffMonth(truncateMonth(contract.getServiceTermStart()), truncateMonth(contract.getCancelScheduledDate())) < itemMaster.getMinContractMonths()) {
					return itemMaster;
				}
				break;
			case サービス開始翌月１日:
				// 「契約.サービス開始日翌月1日」と「契約.解約予定日」の差分 ＜ 品種マスタ.最低契約月数の場合、品種マスタを返却
				if (getDiffMonth(truncateMonth(contract.getServiceTermStart(), 1), truncateMonth(contract.getCancelScheduledDate())) < itemMaster.getMinContractMonths()) {
					return itemMaster;
				}
				break;
			}
		}
		return null;
	}

	/**
	 * 日付以降を切り捨て
	 * @param Date
	 * @return Date
	 */
	private Date truncateMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtils.truncate(date, Calendar.MONTH));
		return calendar.getTime();
	}
	/**
	 * @param Date
	 * @param INT
	 * @return Date
	 */
	private Date truncateMonth(Date date, int addMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtils.truncate(date, Calendar.MONTH));
		calendar.add(Calendar.MONTH, addMonth);
		return calendar.getTime();
	}

	/**
	 * 月の差分取得処理（当月もカウント）
	 * @param fromDate
	 * @param toDate
	 * @return diffMonth
	 */
	private int getDiffMonth(Date fromDate, Date toDate) {
		// fromDateとtoDateの差分を取得
		Period period = Period.between(convertToJavaUtilDate(fromDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), convertToJavaUtilDate(toDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		// 取得した差分を月単位に換算し、当月分をカウントする。
		int diffMonth = (period.getYears() * 12) + (period.getMonths()) + 1;
		return diffMonth;
	}

	/**
	 * JavaUtilDate型変換
	 * java.sql.Dateとjava.sql.Timeをjava.Util.Dateへ変換する。
	 * @param Date
	 * @return Date
	 */
	private Date convertToJavaUtilDate(Date date) {
		if (date instanceof java.sql.Date || date instanceof java.sql.Time) {
			return new Date(date.getTime());
		}
		return date;
	}
}
