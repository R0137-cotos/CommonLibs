package jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.MigrationDiv;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.UpdateMonthNotAccountingDiv;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail;
import jp.co.ricoh.cotos.commonlib.entity.contract.ItemContract;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.businessday.BusinessDayUtil;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.ItemMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.ProductMasterRepository;

/**
 * 契約に関連する共通処理を行うクラス
 */
@Component
public class DateContractUtil {

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	ItemMasterRepository itemMasterRepository;

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	ProductMasterRepository productMasterRepository;

	@Autowired
	DateCalcPatternUtil dateCalcPatternUtil;

	@Autowired
	BusinessDayUtil businessDayUtil;

	@Autowired
	ContractDetailRepository contractDetailRepository;

	/**
	 * 積上がっている品種より最大の契約期間月数を取得する
	 * @param contract 契約
	 * @return
	 */
	public Integer getMaxContractSpanMonthFromItem(Contract contract) {
		// 品種マスタ取得
		List<ItemMaster> itemMasterList = new ArrayList<>();
		contract.getContractDetailList().stream().forEach(contractDetail -> {
			itemMasterList.add(itemMasterRepository.findById(contractDetail.getItemContract().getItemMasterId()).orElse(null));
		});
		// 最大の契約月数を持つ品種マスタを取得
		ItemMaster itemMaster = itemMasterList.stream().filter(s -> s.getContractSpanMonth() != null).max(Comparator.comparing(ItemMaster::getContractSpanMonth)).orElse(null);
		if (itemMaster == null) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "契約期間月数" }));
		}

		return itemMaster.getContractSpanMonth();
	}

	/**
	 * 積上がっている品種よりサービス終了日を取得する
	 *
	 * @param contract 			契約
	 * @param serviceTermStartDate サービス開始日
	 * @param endOfMonthFlg 		契約終了日月末フラグ
	 * @return サービス終了日
	 */
	public Date getServiceTermEndFromItem(Contract contract, Date serviceTermStartDate, boolean endOfMonthFlg) {
		return addMonthServiceTermEnd(serviceTermStartDate, getMaxContractSpanMonthFromItem(contract), endOfMonthFlg);
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

	/**
	 * 次回更新時契約終了日取得
	 *
	 * @param trgServiceTermEnd 現在のサービス終了日
	 * @param updateMonthNum 	 更新月数
	 * @param endOfMonthFlg 	 契約終了日月末フラグ
	 * @return 次回更新時サービス終了日
	 */
	public Date nextUpdateServiceTermEnd(Date trgServiceTermEnd, int updateMonthNum, boolean endOfMonthFlg) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(trgServiceTermEnd);
		// サービス終了日 + 1日が次回更新のサービス開始日
		cal.add(Calendar.DATE, 1);
		// 更新月数を加算しサービス終了日取得
		return addMonthServiceTermEnd(cal.getTime(), updateMonthNum, endOfMonthFlg);
	}

	/**
	 * 最初の契約を取得
	 * @param contract 契約
	 * @return 最初の契約
	 */
	public Contract getFirstContract(Contract contract) {

		// 変更元契約IDがある場合、再帰的に元契約に遡って取得する
		Contract baseContract = contract;
		while (baseContract.getOriginContractId() != null) {
			baseContract = contractRepository.findById(baseContract.getOriginContractId()).orElse(null);
			if (baseContract == null) {
				throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistContract", new String[] { "変更元契約" }));
			}
		}
		return baseContract;
	}

	/**
	 * 契約更新可能判定
	 * 契約開始日、商品マスタ.最長契約月数より契約の更新が可能か判定する。
	 *
	 * @param contract 		 		契約
	 * @param updateServiceEndDate	契約更新時サービス終了日
	 * @param endOfMonthFlg 		契約終了日月末フラグ
	 * @return true：更新可能 false：更新不可
	 */
	public boolean contractUpdatePossibleCheck(Contract contract, Date updateServiceEndDate, boolean endOfMonthFlg) {

		boolean updatePossible = true;
		ProductMaster productMaster = productMasterRepository.findById(contract.getProductContractList().get(0).getProductMasterId()).orElse(null);
		if (productMaster.getMaxContractMonths() != null) {
			// 最初の契約を取得する
			Contract firstContract = getFirstContract(contract);
			// 課金開始日(ランニング) + 最長契約月数
			Date maxServiceEndDate = addMonthServiceTermEnd(getPenalyStartingDate(firstContract), productMaster.getMaxContractMonths(), endOfMonthFlg);

			if (updateServiceEndDate.compareTo(maxServiceEndDate) > 0) {
				updatePossible = false;
			}
		}
		return updatePossible;
	}

	/**
	 * 課金開始日を取得する
	 * 引数の契約がRITOS移行の場合、拡張項目から課金開始日を取得する
	 * @param contract
	 * @return 課金開始日
	 */
	public Date getPenalyStartingDate(Contract contract) {
		String extendesParameter = contract.getProductContractList().get(0).getExtendsParameter();
		Date billingStartDate = contract.getBillingStartDate();
		if (!StringUtils.isEmpty(extendesParameter)) {
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, HashMap<String, Object>> extendesParamMap = new HashMap<>();
			try {
				extendesParamMap = mapper.readValue(extendesParameter, new TypeReference<HashMap<String, HashMap<String, Object>>>() {
				});
			} catch (Exception e) {
				throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "JsonConvertFormTextToObjectError"));
			}
			if (extendesParamMap.containsKey("migrationParameter")) {
				HashMap<String, Object> migrationMap = extendesParamMap.get("migrationParameter");
				if (migrationMap != null && MigrationDiv.RITOS移行.toString().equals(String.valueOf(migrationMap.get("migrationDiv")))) {
					// 移行データの場合、拡張項目の初回課金開始日を取得
					billingStartDate = dateCalcPatternUtil.stringToDateConverter(String.valueOf(migrationMap.get("firstBillingStartDate")), "yyyy/MM/dd");
				}
			}
		}
		return billingStartDate;
	}

	/**
	 * 積上がっている商品よりサービス終了最大延長日を取得する
	 * 商品マスタに最長契約月数が設定されていない場合にはnullを返す
	 * @param contract 	契約
	 * @param startDate 開始日
	 * @param endOfMonthFlg 終了日月末フラグ
	 * @return サービス終了最大延長日
	 */
	public Date getServiceTermMaxEndFromProduct(Contract contract, Date startDate, boolean endOfMonthFlg) {
		ProductMaster productMaster = productMasterRepository.findById(contract.getProductContractList().get(0).getProductMasterId()).orElse(null);
		if (productMaster.getMaxContractMonths() == null) {
			return null;
		}
		return addMonthServiceTermEnd(startDate, productMaster.getMaxContractMonths(), endOfMonthFlg);
	}

	/**
	 * 延長可能契約月数を返す
	 * サービス終了最大延長日とサービス終了日の差分を月数に変換して返す。
	 * サービス終了最大延長日もしくはサービス終了日のどちらかがnullの場合にはnullを返す
	 * @param contract 	契約
	 * @return 延長可能契約月数
	 */
	public Long getMaxExtensionContractMonths(Contract contract) {
		if (contract.getServiceTermEnd() == null || contract.getServiceTermMaxEnd() == null) {
			return null;
		}
		LocalDate from = this.toLocalDate(contract.getServiceTermEnd());
		// うるう年の2/29の場合、2/28として計算する
		if (from.getMonthValue() == 2 && from.getDayOfMonth() == 29) {
			from = from.minusDays(1);
		}
		LocalDate to = this.toLocalDate(contract.getServiceTermMaxEnd());
		return ChronoUnit.MONTHS.between(from, to);
	}

	/**
	 * LocalDateへの変換を行います。
	 * java.util.Date → new Date()等コード内で生成したDate型
	 * java.sql.Date → DBから取得したDate型
	 * @param date 日付
	 * @return LocalDate
	 */
	private LocalDate toLocalDate(Date date) {
		if (date instanceof java.sql.Date) {
			return ((java.sql.Date) date).toLocalDate();
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * ランニング売上計上処理日設定
	 * 品種マスタ．更新月計上不要区分の設定値を元にランニング売上計上処理日に計算した結果を設定する。
	 * @param contract　契約
	 */
	public void setRunningAccountSalesDate(Contract contract) {
		List<ContractDetail> contractDetailList = contractDetailRepository.findByContractId(contract.getId());
		// 更新月計上不要区分取得　複数品種に別の更新月計上不要区分が設定されている場合は、先頭の品種の区分を使用する
		List<ItemMaster> itemMasterList = new ArrayList<ItemMaster>();
		contractDetailList.stream().forEach(detail -> {
			ItemContract itemContract = detail.getItemContract();
			ItemMaster itemMaster = itemMasterRepository.findById(itemContract.getItemMasterId()).orElse(null);
			if (itemMaster.getUpdateMonthNotAccountingDiv() != null) {
				itemMasterList.add(itemMaster);
				return;
			}
		});
		if (!CollectionUtils.isEmpty(itemMasterList)) {
			contractDetailList.stream().filter(s -> s.getItemContract().getCostType() == CostType.月額_定額).forEach(detail -> {
				Date runningAccountSalesDate = null;
				if (itemMasterList.get(0).getUpdateMonthNotAccountingDiv() != null) {
					if (UpdateMonthNotAccountingDiv.サービス開始日.equals(itemMasterList.get(0).getUpdateMonthNotAccountingDiv())) {
						runningAccountSalesDate = getFirstBusinessDay(contract.getServiceTermStart());
					}
					if (UpdateMonthNotAccountingDiv.課金開始日.equals(itemMasterList.get(0).getUpdateMonthNotAccountingDiv())) {
						runningAccountSalesDate = getFirstBusinessDay(contract.getBillingStartDate());
					}
				}
				detail.setRunningAccountSalesDate(runningAccountSalesDate);
			});
		}

		contract.setContractDetailList(contractDetailList);
	}

	/**
	 * 月初第一営業日を取得する
	 *
	 * @param baseDate 対象日付
	 * @return firstBusinessDay
	 */
	private Date getFirstBusinessDay(Date baseDate) {
		if (baseDate == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = 1;

		calendar.set(year, month, date, 0, 0, 0);
		Date firstBusinessDay = null;
		for (int i = 0; i <= calendar.getActualMaximum(Calendar.DATE); i++) {
			firstBusinessDay = calendar.getTime();
			if (businessDayUtil.isBusinessDay(firstBusinessDay)) {
				break;
			}
			calendar.add(Calendar.DATE, 1);
		}
		return firstBusinessDay;
	}
}
