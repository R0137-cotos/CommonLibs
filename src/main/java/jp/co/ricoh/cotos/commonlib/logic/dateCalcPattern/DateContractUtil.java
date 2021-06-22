package jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.MigrationDiv;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
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
	protected DateCalcPatternUtil dateCalcPatternUtil;

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
			baseContract = contractRepository.findOne(baseContract.getOriginContractId());
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
		ProductMaster productMaster = productMasterRepository.findOne(contract.getProductContractList().get(0).getProductMasterId());
		if (productMaster.getMaxContractMonths() != null) {
			// 最初の契約を取得する
			Contract firstContract = getFirstContract(contract);
			String extendesParameter = firstContract.getProductContractList().get(0).getExtendsParameter();
			Date billingStartDate = firstContract.getBillingStartDate();
			if (!StringUtils.isEmpty(extendesParameter)) {
				ObjectMapper mapper = new ObjectMapper();
				HashMap<String, HashMap<String, Object>> extendesParamMap = new HashMap<>();
				try {
					extendesParamMap = mapper.readValue(extendesParameter, new TypeReference<Object>() {
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
			// 課金開始日(ランニング) + 最長契約月数
			Date maxServiceEndDate = addMonthServiceTermEnd(billingStartDate, productMaster.getMaxContractMonths(), endOfMonthFlg);

			if (updateServiceEndDate.compareTo(maxServiceEndDate) > 0) {
				updatePossible = false;
			}
		}
		return updatePossible;
	}
}
