package jp.co.ricoh.cotos.commonlib.logic.penalty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.dto.result.PenaltyInfoDto;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DetailStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation.EstimationType;
import jp.co.ricoh.cotos.commonlib.entity.estimation.ItemEstimation;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.PenaltyDetailContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.PenaltyDetailEstimationRepository;
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

	@Autowired
	EstimationRepository estimationRepository;

	@Autowired
	PenaltyDetailEstimationRepository penaltyDetailEstimationRepository;

	@Autowired
	PenaltyDetailContractRepository penaltyDetailContractRepository;

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
		if (Optional.ofNullable(itemMaster.getPenaltyFlg()).orElse(0) == 1) {
			// 4.品種マスタ.契約期間起算日区分に合わせた判定方法で判定し、条件に一致した品種マスタを返却する。
			// 違約金発生解約日の最終日を取得。対象日が最終日以前か判定。
			Date lastDate = penaltyOccurCacnlLastDate(itemMaster, originContract.getServiceTermStart());
			if(lastDate != null && new Date().compareTo(lastDate) <= 0) {
				return itemMaster;
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
			// 違約金発生解約日の最終日を取得。対象日が最終日以前か判定。
			Date lastDate = penaltyOccurCacnlLastDate(itemMaster, contract.getServiceTermStart());
			if(lastDate != null && contract.getCancelScheduledDate().compareTo(lastDate) <= 0) {
				return itemMaster;
			}
		}
		return null;
	}

	/**
	 * 違約金情報取得（見積）
	 * システム日付、及び見積の品種より違約金発生の有無を判定。
	 * 違約金が発生する場合、違約金情報を返却する。
	 * @param estimationId 見積ID
	 * @return 違約金情報リスト
	 */
	public List<PenaltyInfoDto> getPenaltyInfo(Long estimationId) {

		// 見積情報取得
		Optional.ofNullable(estimationId).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ParameterEmptyError", new String[] { "見積ID" })));
		Estimation estimation = estimationRepository.findOne(estimationId);
		Optional.ofNullable(estimation).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistEstimation", new String[] { "見積" })));

		// 減数された品種、及び数量情報を取得
		Map<Long, Integer> decreaseItemMap = getDecreaseItemList(estimation);

		return createPenaltyInfoList(decreaseItemMap, new Date(), estimation);
	}

	/**
	 * 違約金情報取得（契約）
	 * 解約予定日、及び契約の品種より違約金発生の有無を判定。
	 * 違約金が発生する場合、違約金情報を返却する。
	 * @param contractId		   契約ID
	 * @param cancelScheduledDate 解約予定日
	 * @param allCancelFlg		   全解約フラグ 1：全解約 0：部分解約(減数されている品種のみ違約金判定の対象とする)
	 * @return 違約金情報リスト
	 */
	public List<PenaltyInfoDto> getPenaltyInfo(Long contractId, Date cancelScheduledDate, boolean allCancelFlg) {

		// 契約情報取得
		Optional.ofNullable(contractId).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ParameterEmptyError", new String[] { "契約ID" })));
		Contract contract = contractRepository.findOne(contractId);
		Optional.ofNullable(contract).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistContract", new String[] { "契約" })));
		Optional.ofNullable(cancelScheduledDate).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ParameterEmptyError", new String[] { "解約予定日" })));

		// 減数された品種、および数量を取得（全解約時は全品種)
		Map<Long, Integer> decreaseItemMap = null;
		if(allCancelFlg) {
			decreaseItemMap = new HashMap<Long, Integer>();
			for (ContractDetail detail : contract.getContractDetailList()) {
				if(detail.getQuantity() != 0) {
					decreaseItemMap.put(detail.getItemContract().getItemMasterId(), detail.getQuantity());
				}
			}
		} else {
			// 契約に紐づく見積情報取得
			Estimation estimation = estimationRepository.findOne(contract.getEstimationId());
			Optional.ofNullable(estimation).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistEstimation", new String[] { "見積" })));

			// 減数された品種、及び数量情報を取得
			decreaseItemMap = getDecreaseItemList(estimation);
		}
		return createPenaltyInfoList(decreaseItemMap, cancelScheduledDate, contract);
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
	 * 見積情報より減数した品種、数量のリストを取得する
	 *
	 * @param estimation 見積
	 * @return 減数品種数量マップ
	 */
	private Map<Long, Integer> getDecreaseItemList(Estimation estimation) {

		if(estimation == null || CollectionUtils.isEmpty(estimation.getEstimationDetailList())) {
			return null;
		}
		// 現在数量と変更前数量を比較し減数されている場合、マップへ格納する
		Map<Long, Integer> resultMap = new HashMap<Long, Integer>();
		estimation.getEstimationDetailList().stream()
			.filter(s -> DetailStatus.DELETE == s.getState() || (s.getBeforeQuantity() != null && s.getBeforeQuantity() > s.getQuantity()))
			.forEach(detail -> {
				ItemEstimation itemEstimation = detail.getItemEstimation();
				Integer decreaseQuantity = 0;
				if(DetailStatus.DELETE == detail.getState()) {
					decreaseQuantity = detail.getBeforeQuantity();
					if(decreaseQuantity == null || decreaseQuantity == 0) {
						decreaseQuantity = detail.getQuantity();
					}
				} else {
					decreaseQuantity = detail.getBeforeQuantity() - detail.getQuantity();
				}
				if(decreaseQuantity != 0) {
					resultMap.put(itemEstimation.getItemMasterId(), decreaseQuantity);
				}
		});
		return resultMap;
	}

	/**
	 * 違約金情報作成
	 *
	 * @param decreaseItemMap 減数品種数量マップ
	 * @param checkTrgetDate  チェック対象日
	 * @param entityBase	   見積 or 契約エンティティ
	 * @return 違約金情報リスト
	 */
	private List<PenaltyInfoDto> createPenaltyInfoList(Map<Long, Integer> decreaseItemMap, Date checkTrgetDate, EntityBase entityBase) {

		List<PenaltyInfoDto> resultList = new ArrayList<PenaltyInfoDto>();

		Optional.ofNullable(decreaseItemMap).ifPresent(itemMap -> {
			itemMap.forEach((itemMasterId, quantity) -> {
				ItemMaster itemMaster = null;
				Contract tmpContract = null;
				// 品種の解約に対する違約金が発生するか判定。
				if(entityBase instanceof Estimation) {
					Estimation estimation = (Estimation)entityBase;
					itemMaster = penaltyCheck(estimation, itemMasterId);
					if(itemMaster != null) {
						tmpContract = contractRepository.findOne(estimation.getOriginContractId());
					}
				} else if(entityBase instanceof Contract) {
					tmpContract = (Contract)entityBase;
					tmpContract.setCancelScheduledDate(checkTrgetDate);
					itemMaster = penaltyCheck(tmpContract, itemMasterId);
				}
				// 違約金が発生する場合、違約金品種情報、違約金金額を戻り値に設定する
				if(itemMaster != null && itemMaster.getPenaltyItemMaster() != null) {
					ItemMaster penaltyItem = itemMaster.getPenaltyItemMaster();
					PenaltyInfoDto penaltyInfoDto = new PenaltyInfoDto();
					penaltyInfoDto.setPenaltyItemMasterId(penaltyItem.getId());
					penaltyInfoDto.setPenaltyItemName(penaltyItem.getItemName());
					penaltyInfoDto.setPenaltyRicohItemCode(penaltyItem.getRicohItemCode());
					penaltyInfoDto.setPenaltyItemType(penaltyItem.getItemType());
					penaltyInfoDto.setOriginItemMasterId(itemMaster.getId());
					penaltyInfoDto.setPenaltyUnitPrice(penaltyItem.getStandardPrice());
					penaltyInfoDto.setQuantity(quantity);
					penaltyInfoDto.setPenaltyAmountSummary(calcPenaltyAmount(penaltyItem, quantity));
					penaltyInfoDto.setPenaltyOccurCacnlLastDate(penaltyOccurCacnlLastDate(itemMaster, tmpContract.getServiceTermStart()));
					resultList.add(penaltyInfoDto);
				}
			});
		});
		return resultList;
	}

	/**
	 * 違約金額計算
	 *
	 * @param penaltyItemMaster 違約金品種マスタ
	 * @param quantity			 数量
	 * @return 違約金額
	 */
	private BigDecimal calcPenaltyAmount(ItemMaster penaltyItemMaster, Integer quantity) {

		if (penaltyItemMaster == null || penaltyItemMaster.getStandardPrice() == null || quantity == null) {
			return new BigDecimal(0);
		}
		// 違約金品種.標準価格 * 解約数量の結果を返却する
		return penaltyItemMaster.getStandardPrice().multiply(BigDecimal.valueOf(quantity));
	}

	/**
	 * 違約金発生解約日の最終日取得
	 */
	private Date penaltyOccurCacnlLastDate(ItemMaster itemMaster, Date serviceTermStart) {

		if(itemMaster.getPenaltyStartDateType() == null || itemMaster.getMinContractMonths() == null) {
			return null;
		}
		Integer addMonth = null;
		switch (itemMaster.getPenaltyStartDateType()) {
		case サービス開始日:
			addMonth = 0;
			break;
		case サービス開始翌月１日:
			// 1月加算
			addMonth = 1;
			break;
		}
		if(addMonth == null) {
			return null;
		}
		// 違約金起算日に最低契約月数を加算して年月を取得
		Date lastMonth = truncateMonth(truncateMonth(truncateMonth(serviceTermStart, addMonth), itemMaster.getMinContractMonths()), -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(lastMonth);
		// 計算結果同月での解約は違約金発生しないため前月の最終日を違約金発生の最終日とする。
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		return cal.getTime();
	}
}
