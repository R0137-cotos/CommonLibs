package jp.co.ricoh.cotos.commonlib.logic.contractChangeSpanControl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.db.DBUtil;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.result.MessageInfo;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster.CheckPatternType;
import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster.ContractChangeSpanTargetDateType;
import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster.DateCalcStndType;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern.DateCalcPatternUtil;
import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.ContractChangeSpanMasterRepository;

/**
 * 契約変更期間管理共通クラス
 */
@Component
public class ContractChangeSpanControl {

	@Autowired
	private ContractChangeSpanMasterRepository contractChangeSpanMasterRepository;

	@Autowired
	private DateCalcPatternUtil dateCalcPatternUtil;

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private DBUtil dbUtil;

	@Autowired
	CheckUtil checkUtil;

	private final String PARENT_TABLE_ALIAS = "PARENT";

	/**
	 * 契約変更期間判定
	 *
	 * @param serviceCategory		対象ドメイン
	 * @param productMasterId		商品マスタID
	 * @param itemMasterId			品種マスタID
	 * @param contractType			契約種別     ※全解約操作時は設定しない
	 * @param contractTypeDetails	契約種別詳細 ※全解約操作時は設定しない
	 * @param lifecycleStatus		ライフサイクル状態
	 * @param workflowStatus		ワークフロー状態
	 * @param transactionTableId	対象トランザクションテーブルID
	 */
	public List<ErrorInfo> contractChangeSpanCheck(ServiceCategory serviceCategory, Long productMasterId, Long itemMasterId,
			ContractType contractType, ContractTypeDetails contractTypeDetail, String lifecycleStatus, String workflowStatus, Long transactionTableId) {

		// 引数チェック
		List<ErrorInfo> errorInfoList = new ArrayList<ErrorInfo>();

		if(serviceCategory == null) {
			checkUtil.addErrorInfo(errorInfoList, "ParameterEmptyError", new String[] { "対象ドメイン" });
		}
		if(productMasterId == null) {
			checkUtil.addErrorInfo(errorInfoList, "ParameterEmptyError", new String[] { "商品マスタID" });
		}
		if(lifecycleStatus == null) {
			checkUtil.addErrorInfo(errorInfoList, "ParameterEmptyError", new String[] { "ライフサイクル状態" });
		}
		if(workflowStatus == null) {
			checkUtil.addErrorInfo(errorInfoList, "ParameterEmptyError", new String[] { "ワークフロー状態" });
		}
		if(transactionTableId == null) {
			checkUtil.addErrorInfo(errorInfoList, "ParameterEmptyError", new String[] { "対象トランザクションテーブルID" });
		}
		if(!errorInfoList.isEmpty()) {
			throw new ErrorCheckException(errorInfoList);
		}
		String tmpContractType = Optional.ofNullable(contractType).map(s -> s.toString()).orElse(null);
		String tmpContractTypeDetail = Optional.ofNullable(contractTypeDetail).map(s -> s.toString()).orElse(null);

		// 引数に紐づく契約変更期間管理マスタ情報取得
		List<ContractChangeSpanMaster> findContractChangeSpanMasterList = contractChangeSpanMasterRepository.findContractChangeSpanMasterList(
				serviceCategory.toString(), productMasterId, tmpContractType, tmpContractTypeDetail, lifecycleStatus, workflowStatus);

		if(findContractChangeSpanMasterList == null || findContractChangeSpanMasterList.isEmpty()) {
			return errorInfoList;
		}

		// エンティティ情報取得
		EntityBase targetEntity = null;

		// ドメイン単位に対象トランザクションデータ取得。※現状想定しているのは契約のみ
		if(ServiceCategory.契約.equals(serviceCategory)) {
			targetEntity = contractRepository.findOne(transactionTableId);
		}
		Optional.ofNullable(targetEntity).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistContract", new String[] { "契約" })));

		// 契約情報に紐づく元契約エンティティの取得、及び契約されている品種の品種マスタIDを保持するリストを生成
		Contract originContract = null;
		List<Long> itemMasterIdList = new ArrayList<Long>();
		if(targetEntity instanceof Contract) {

			Contract contract = (Contract)targetEntity;

			// 契約種別が新規以外の場合、元契約のエンティティを取得
			if(ContractType.契約更新 == contractType || ContractType.契約変更 == contractType) {
				Optional.ofNullable(contract.getOriginContractId()).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "変更元契約ID" })));
				originContract = contractRepository.findOne(contract.getOriginContractId());
				Optional.ofNullable(originContract).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistContract", new String[] { "変更元契約" })));
			}
			if(contract.getContractDetailList() != null && !contract.getContractDetailList().isEmpty()) {
				contract.getContractDetailList().stream().forEach(item -> itemMasterIdList.add(item.getItemContract().getItemMasterId()));
			}
		}
		// 契約変更期間管理マスタ情報分処理を行う。
		// 引数.品種マスタIDが指定されている場合、品種マスタIDが紐づくレコードのみ処理対象とする。
		// 引数.品種マスタID == nullの場合、契約変更期間管理マスタ.品種マスタID == nullまたは対象エンティティに紐づく品種のレコードを処理対象とする。
		for (ContractChangeSpanMaster master : findContractChangeSpanMasterList) {

			Long tmpItemMasterId = master.getItemMasterId();
			if(itemMasterId != null) {
				if(!itemMasterId.equals(tmpItemMasterId)) {
					continue;
				}
			} else if(tmpItemMasterId != null && itemMasterIdList.indexOf(tmpItemMasterId) < 0) {
				continue;
			}
			// 追加条件式が設定されている場合、追加条件式によりトランザクションテーブルを検索
			if(StringUtils.isNoneBlank(master.getExtendsQuery())) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id", transactionTableId);
				List<BigDecimal> resultIdList = dbUtil.executeSelectWithSQL(createSql(master, transactionTableId), paramMap);
				if(resultIdList == null || resultIdList.isEmpty()) {
					// 条件に該当するレコードが存在しない場合、処理対象外
					continue;
				}
			}
			// 日付計算パターンマスタの情報により、契約変更可能期限開始日取得
			Date fromCheckDate = null;
			// 日付計算パターンマスタの情報により、契約変更可能期限終了日取得
			Date toCheckDate = null;
			// 契約変更可能期限開始日チェック対象日付
			Date fromCheckTrgetDate = null;
			//契約変更可能期限終了日チェック対象日付
			Date toCheckTrgetDate = null;

			// 契約変更可能期間FROM取得
			if(master.getFromContractChangeDateCalcPatternMaster() != null) {
				DateCalcPatternMaster fromDateCalcPatternMaster = master.getFromContractChangeDateCalcPatternMaster();
				Date referenceDate = getCheckTermReferenceDate(fromDateCalcPatternMaster, targetEntity, originContract);
				fromCheckDate = dateCalcPatternUtil.dateCalc(fromDateCalcPatternMaster, referenceDate);
				fromCheckTrgetDate = getCheckTargetDate(targetEntity, master.getFromContractChangeSpanTargetDateType());
			}
			// 契約変更可能期間TO取得
			if(master.getToContractChangeDateCalcPatternMaster() != null) {
				DateCalcPatternMaster toDateCalcPatternMaster = master.getToContractChangeDateCalcPatternMaster();
				Date referenceDate = getCheckTermReferenceDate(toDateCalcPatternMaster, targetEntity, originContract);
				toCheckDate = dateCalcPatternUtil.dateCalc(toDateCalcPatternMaster, referenceDate);
				toCheckTrgetDate = getCheckTargetDate(targetEntity, master.getToContractChangeSpanTargetDateType());
			}
			// 契約変更可能期間外判定
			if(isNotContractChangeSpan(master, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate)) {
				// 契約変更可能期間外の場合
				String[] regexList = null;
				if(StringUtils.isNoneBlank(master.getErrorMessageReplaceString())) {
					regexList = master.getErrorMessageReplaceString().split(",");
				}
				MessageInfo messageInfo = messageUtil.createMessageInfo(master.getErrorKey(), regexList);
				ErrorInfo errorInfo = new ErrorInfo();
				errorInfo.setErrorId(messageInfo.getId());
				errorInfo.setErrorMessage(messageInfo.getMsg());
				if (master.getErrorField() != null) {
					errorInfo.setErrorField(master.getErrorField());
				}
				errorInfoList.add(errorInfo);
			}
		}
		return errorInfoList;
	}

	/**
	 * 契約変更可能期間外判定
	 * @param spanMaster		  契約変更可能期間管理マスタ
	 * @param fromCheckDate	  契約変更可能期間FROM
	 * @param toCheckDate		  契約変更可能期間TO
	 * @param fromCheckTrgetDate 契約変更可能期間FROMチェック対象日
	 * @param toCheckTrgetDate   契約変更可能期間TOチェック対象日
	 * @return true：契約変更可能期間外 false：契約変更可能期間内
	 */
	private boolean isNotContractChangeSpan(ContractChangeSpanMaster spanMaster, Date fromCheckDate, Date toCheckDate, Date fromCheckTrgetDate, Date toCheckTrgetDate) {

		// チェック対象日がFROM～TOの期間内の場合、OK
		if(CheckPatternType.期間固定 == spanMaster.getCheckPatternType()) {
			// 契約変更可能期限開始日チェック
			if (fromCheckDate != null && (fromCheckTrgetDate != null && fromCheckTrgetDate.before(fromCheckDate))) {
				return true;
			}
			// 契約変更可能期限終了日チェック
			if (toCheckDate != null && (toCheckTrgetDate != null && toCheckTrgetDate.after(toCheckDate))) {
				return true;
			}
			// チェック対象日にFROM(yyyyMM)が指定されている場合、TOの期限まで申込可能
		} else if(CheckPatternType.期間可変 == spanMaster.getCheckPatternType()) {
			if (fromCheckDate != null && fromCheckTrgetDate != null && toCheckDate != null && toCheckTrgetDate != null) {
				if (dateCalcPatternUtil.dateToStringConverter(fromCheckTrgetDate, "yyyyMM").equals(dateCalcPatternUtil.dateToStringConverter(fromCheckDate, "yyyyMM")) && toCheckTrgetDate.after(toCheckDate)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * チェック対象日取得
	 *
	 * @param targetEntity		取得対象エンティティ
	 * @param serviceCategory	サービスカテゴリ
	 * @param trgetDateType	チェック対象日区分
	 * @return チェック対象日
	 */
	private Date getCheckTargetDate(EntityBase targetEntity, ContractChangeSpanTargetDateType trgetDateType) {
		try {
			Date returnDate = null;

			if(targetEntity instanceof Contract) {
				Contract contract = (Contract)targetEntity;

				switch(trgetDateType) {
				case 契約承認依頼日:
					returnDate = new Date();
					break;
				case システム日付:
					returnDate = new Date();
					break;
				case サービス利用希望日:
					returnDate = contract.getConclusionPreferredDate();
					break;
				case 解約希望日:
					returnDate =  contract.getCancelScheduledDate();
					break;
				}
			}
			return returnDate;

		} catch(Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * チェック対象期間計算基準日取得
	 *
	 * @param dateCalcPatternMaster 日付計算パターンマスタ
	 * @param entityBase			 トランザクションエンティティ
	 * @param serviceCategory
	 * @return チェック対象期間計算基準日
	 */
	private Date getCheckTermReferenceDate(DateCalcPatternMaster dateCalcPatternMaster, EntityBase targetEntity, Contract originContract) {

		Date referenceDate = null;
		DateCalcStndType dateCalcStndType = dateCalcPatternMaster.getDateCalcStndType();

		if(targetEntity instanceof Contract) {
			Contract contract = (Contract)targetEntity;

			switch(dateCalcStndType) {
			case サービス終了日:
				// 元契約の契約情報が存在する場合、元契約よりサービス終了日を取得する。
				if (originContract != null) {
					referenceDate = originContract.getServiceTermEnd();
				} else {
					referenceDate = contract.getServiceTermEnd();
				}
				break;
			case 契約開始日:
				referenceDate = contract.getServiceTermStart();
				break;
			case システム日付:
				referenceDate = new Date();
				break;
			}
		}
		return referenceDate;
	}

	/**
	 * トランザクションデータ取得SQL生成
	 * @param contractChangeSpanMaster
	 * @param transactionTableId
	 * @return 生成SQL
	 */
	private String createSql(ContractChangeSpanMaster contractChangeSpanMaster, Long transactionTableId) {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT " + PARENT_TABLE_ALIAS + ".ID FROM ");
		sb.append(contractChangeSpanMaster.getCheckTargetTransactionTableName() +  " " + PARENT_TABLE_ALIAS );
		sb.append(" WHERE " + PARENT_TABLE_ALIAS + ".ID = :id ");
		sb.append(contractChangeSpanMaster.getExtendsQuery());

		return sb.toString();
	}
}
