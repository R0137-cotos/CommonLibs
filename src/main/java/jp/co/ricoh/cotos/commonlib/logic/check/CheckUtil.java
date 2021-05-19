package jp.co.ricoh.cotos.commonlib.logic.check;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonValue;

import jp.co.ricoh.cotos.commonlib.dto.result.MessageInfo;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ToleranceType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.WorkflowStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;

/**
 * チェック共通クラス
 */
@Component
public class CheckUtil {

	@Autowired
	MessageUtil messageUtil;

	/**
	 * 社員モード(パラメータ,操作者)
	 */
	public enum EmpMode {

		パラメータ, 操作者;

		@JsonValue
		public String toValue() {
			return this.name();
		}
	}

	/**
	 * Entityチェック
	 *
	 * @param result
	 *            Entityチェック結果
	 * @param ignoreFields
	 *            Entityチェック対象外項目名配列(未指定可)
	 * @throws ErrorCheckException
	 *             エラーチェックException
	 */
	public void checkEntity(BindingResult result, String... ignoreFields) throws ErrorCheckException {
		List<ErrorInfo> errorInfoList = new ArrayList<>();
		if (result.hasErrors()) {
			for (FieldError fieldError : result.getFieldErrors()) {

				if (Arrays.asList(ignoreFields).stream().anyMatch(fieldError.getField()::contains)) {
					continue;
				}

				// 親エンティティから子エンティティのフィールド名は「子エンティティ名.項目名」となっているため、項目名のみ抜き出す
				String fieldOrigNm = Optional.of(fieldError.getField()).filter(s -> s.contains(".")).map(s -> {
					return Optional.of(s.split(Pattern.quote("."))).map(m -> m[m.length - 1]).get();
				}).orElse(fieldError.getField());

				String fieldNm = messageUtil.convertSingleValue(fieldOrigNm);
				String errCode = fieldError.getCode();
				String max = null;
				String min = null;
				String digits = null;
				if (fieldError.getArguments() != null && fieldError.getArguments().length > 0) {
					if ("Max".equals(errCode) || "Size".equals(errCode)) {
						max = fieldError.getArguments()[1].toString();
					}
					if ("Min".equals(errCode)) {
						min = fieldError.getArguments()[1].toString();
					}
					if ("DecimalMax".equals(errCode)) {
						max = ((MessageSourceResolvable) fieldError.getArguments()[2]).getDefaultMessage();
					}
					if ("DecimalMin".equals(errCode)) {
						min = ((MessageSourceResolvable) fieldError.getArguments()[2]).getDefaultMessage();
					}
					if ("Digits".equals(errCode)) {
						digits = fieldError.getArguments()[1].toString();
					}
				}

				String errKey = null;
				String[] regexList = null;
				// 必須チェック
				if ("NotNull".equals(errCode) || "NotEmpty".equals(errCode)) {
					errKey = "EntityCheckNotNullError";
					regexList = new String[] { fieldNm };
				}
				// 文字列桁数チェック
				if ("Size".equals(errCode)) {
					errKey = "EntityCheckStringSizeError";
					regexList = new String[] { fieldNm, max };
				}
				// 数値桁数チェック
				if ("Max".equals(errCode) || "DecimalMax".equals(errCode)) {
					errKey = "EntityCheckNumberMaxError";
					regexList = new String[] { fieldNm, max };
				}
				// 数値桁数チェック
				if ("Min".equals(errCode) || "DecimalMin".equals(errCode)) {
					errKey = "EntityCheckNumberMinError";
					regexList = new String[] { fieldNm, min };
				}
				// 数値桁数チェック
				if ("Digits".equals(errCode)) {
					errKey = "EntityCheckNumberDigitsError";
					regexList = new String[] { fieldNm, digits };
				}
				MessageInfo messageInfo = messageUtil.createMessageInfo(errKey, regexList);
				ErrorInfo errorInfo = new ErrorInfo();
				errorInfo.setErrorId(messageInfo.getId());
				errorInfo.setErrorMessage(messageInfo.getMsg());
				errorInfoList.add(errorInfo);
			}

			if (!errorInfoList.isEmpty()) {
				throw new ErrorCheckException(errorInfoList);
			}
		}
	}

	/**
	 * 子エンティティ名も含めたメッセージを返すEntityチェック
	 *
	 * @param result
	 *            Entityチェック結果
	 * @param ignoreFields
	 *            Entityチェック対象外項目名配列(未指定可)
	 * @throws ErrorCheckException
	 *             エラーチェックException
	 */
	public void checkEntityWithEntityName(BindingResult result, String... ignoreFields) throws ErrorCheckException {
		List<ErrorInfo> errorInfoList = new ArrayList<>();
		if (result.hasErrors()) {
			for (FieldError fieldError : result.getFieldErrors()) {

				if (Arrays.asList(ignoreFields).stream().anyMatch(fieldError.getField()::contains)) {
					continue;
				}

				// 親エンティティから子エンティティのフィールド名は「子エンティティ名.項目名」となっているため、項目名のみ抜き出す
				String fieldOrigNm = Optional.of(fieldError.getField()).filter(s -> s.contains(".")).map(s -> {
					return Optional.of(s.split(Pattern.quote("."))).map(m -> m[m.length - 1]).get();
				}).orElse(fieldError.getField());

				String entityOrigNm = Optional.of(fieldError.getField()).filter(s -> s.contains(".")).map(s -> {
					return Optional.of(s.split(Pattern.quote("."))).map(m -> m[m.length - 2]).get().replaceAll("\\[\\d+\\]", "");
				}).orElse(null);

				String fieldNm = messageUtil.convertSingleValue(fieldOrigNm);
				if (null != entityOrigNm) {
					fieldNm = messageUtil.convertSingleValue(entityOrigNm) + "の" + fieldNm;
				}
				String errCode = fieldError.getCode();
				String max = null;
				String min = null;
				String digits = null;
				if (fieldError.getArguments() != null && fieldError.getArguments().length > 0) {
					if ("Max".equals(errCode) || "Size".equals(errCode)) {
						max = fieldError.getArguments()[1].toString();
					}
					if ("Min".equals(errCode)) {
						min = fieldError.getArguments()[1].toString();
					}
					if ("DecimalMax".equals(errCode)) {
						max = ((MessageSourceResolvable) fieldError.getArguments()[2]).getDefaultMessage();
					}
					if ("DecimalMin".equals(errCode)) {
						min = ((MessageSourceResolvable) fieldError.getArguments()[2]).getDefaultMessage();
					}
					if ("Digits".equals(errCode)) {
						digits = fieldError.getArguments()[1].toString();
					}
				}

				String errKey = null;
				String[] regexList = null;
				// 必須チェック
				if ("NotNull".equals(errCode) || "NotEmpty".equals(errCode)) {
					errKey = "EntityCheckNotNullError";
					regexList = new String[] { fieldNm };
				}
				// 文字列桁数チェック
				if ("Size".equals(errCode)) {
					errKey = "EntityCheckStringSizeError";
					regexList = new String[] { fieldNm, max };
				}
				// 数値桁数チェック
				if ("Max".equals(errCode) || "DecimalMax".equals(errCode)) {
					errKey = "EntityCheckNumberMaxError";
					regexList = new String[] { fieldNm, max };
				}
				// 数値桁数チェック
				if ("Min".equals(errCode) || "DecimalMin".equals(errCode)) {
					errKey = "EntityCheckNumberMinError";
					regexList = new String[] { fieldNm, min };
				}
				// 数値桁数チェック
				if ("Digits".equals(errCode)) {
					errKey = "EntityCheckNumberDigitsError";
					regexList = new String[] { fieldNm, digits };
				}
				MessageInfo messageInfo = messageUtil.createMessageInfo(errKey, regexList);
				ErrorInfo errorInfo = new ErrorInfo();
				errorInfo.setErrorId(messageInfo.getId());
				errorInfo.setErrorMessage(messageInfo.getMsg());
				errorInfo.setErrorField(fieldOrigNm);
				errorInfo.setErrorEntity(entityOrigNm);
				errorInfoList.add(errorInfo);
			}

			if (!errorInfoList.isEmpty()) {
				throw new ErrorCheckException(errorInfoList);
			}
		}
	}

	/**
	 * リストサイズチェック
	 *
	 * @param list
	 *            チェック対象リスト
	 * @return boolean チェック結果（true：正常 false：異常）
	 */
	public boolean checkListSize(List<T> list) {
		if (list == null || list.size() == 0) {
			BindingResult errors = new BeanPropertyBindingResult(list, "list");
			checkEntity(errors);
			return false;
		}
		for (T element : list) {
			if (element == null) {
				BindingResult errors = new BeanPropertyBindingResult(element, "entity");
				checkEntity(errors);
				return false;
			}
		}
		return true;
	}

	/**
	 * 日付文字列が指定のフォーマットに変換できるか確認します
	 *
	 * @param dateString
	 *            日付文字列
	 * @param format
	 *            日付フォーマット
	 * @return True:変換可能/False:変換不可
	 */
	public boolean tryParseDate(String dateString, String format) {

		// Nullの場合、変換可能（処理スルー）とする
		if (dateString == null) {
			return true;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		try {
			sdf.parse(dateString);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * エラー情報追加
	 *
	 * @param errorInfoList
	 *            エラーリスト
	 * @param messageKey
	 *            メッセージキー
	 * @return エラーリスト
	 */
	public List<ErrorInfo> addErrorInfo(List<ErrorInfo> errorInfoList, String messageKey) {
		errorInfoList.add(createErrorInfo(messageKey));
		return errorInfoList;
	}

	/**
	 * エラー情報追加
	 *
	 * @param errorInfoList
	 *            エラーリスト
	 * @param messageKey
	 *            メッセージキー
	 * @param regexList
	 *            メッセージ引数
	 * @return エラーリスト
	 */
	public List<ErrorInfo> addErrorInfo(List<ErrorInfo> errorInfoList, String messageKey, String[] regexList) {
		errorInfoList.add(createErrorInfo(messageKey, regexList));
		return errorInfoList;
	}

	/**
	 * エラー情報追加(項目用)
	 *
	 * @param errorInfoList
	 *            エラーリスト
	 * @param columnNm
	 *            カラム名
	 * @param errorType
	 *            エラータイプ
	 * @return エラーリスト
	 */
	public List<ErrorInfo> addErrorInfoColumnCheck(List<ErrorInfo> errorInfoList, String columnNm, String errorType) {
		errorInfoList.add(createErrorInfoColumnCheck(columnNm, errorType));
		return errorInfoList;
	}

	/**
	 * エラー情報追加
	 *
	 * @param errorInfoList
	 *            エラーリスト
	 * @param messageKey
	 *            メッセージキー
	 * @param regexList
	 *            メッセージ引数
	 * @param filed
	 *            フィールド
	 * @param entity
	 *            エンティティ
	 * @return エラーリスト
	 */
	public List<ErrorInfo> addErrorInfoFiledEntity(List<ErrorInfo> errorInfoList, String messageKey, String[] regexList, String filed, String entity) {
		errorInfoList.add(createErrorInfoFieldEntity(messageKey, regexList, filed, entity));
		return errorInfoList;
	}

	/**
	 * エラー情報生成
	 *
	 * @param messageKey
	 *            メッセージキー
	 * @return エラーリスト
	 */
	private ErrorInfo createErrorInfo(String messageKey) {

		MessageInfo messageInfo = messageUtil.createMessageInfo(messageKey);

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorId(messageInfo.getId());
		errorInfo.setErrorMessage(messageInfo.getMsg());
		return errorInfo;
	}

	/**
	 * エラー情報生成
	 *
	 * @param messageKey
	 *            メッセージキー
	 * @param regexList
	 *            メッセージ引数
	 * @return エラーリスト
	 */
	private ErrorInfo createErrorInfo(String messageKey, String[] regexList) {

		MessageInfo messageInfo = messageUtil.createMessageInfo(messageKey, regexList);

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorId(messageInfo.getId());
		errorInfo.setErrorMessage(messageInfo.getMsg());
		return errorInfo;
	}

	/**
	 * エラー情報生成(項目用)
	 *
	 * @param columnNm
	 *            カラム名
	 * @param messageKey
	 *            メッセージキー
	 * @return エラーリスト
	 */
	private ErrorInfo createErrorInfoColumnCheck(String columnNm, String messageKey) {

		MessageInfo messageInfo = messageUtil.createMessageInfo(messageKey);

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorId(messageInfo.getId());
		errorInfo.setErrorMessage(messageUtil.convertSingleValue(columnNm) + messageInfo.getMsg());
		return errorInfo;
	}

	/**
	 * エラー情報生成
	 *
	 * @param messageKey
	 *            メッセージキー
	 * @param regexList
	 *            メッセージ引数
	 * @param filed
	 *            フィールド
	 * @param entity
	 *            エンティティ
	 * @return エラーリスト
	 */
	private ErrorInfo createErrorInfoFieldEntity(String messageKey, String[] regexList, String filed, String entity) {

		MessageInfo messageInfo = messageUtil.createMessageInfo(messageKey, regexList);

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorId(messageInfo.getId());
		errorInfo.setErrorMessage(messageInfo.getMsg());
		errorInfo.setErrorField(filed);
		errorInfo.setErrorEntity(entity);
		return errorInfo;
	}

	/**
	 * 契約情報確定APIのエラーチェック
	 *
	 * @param contract
	 */
	public void fixCheck(Contract contract) {
		// 契約.契約種別が「新規」または「契約変更」かどうかチェックする。
		if (ContractType.新規 != contract.getContractType() && ContractType.契約変更 != contract.getContractType()) {
			throw new ErrorCheckException(addErrorInfo(new ArrayList<ErrorInfo>(), "CannotContractActionByStatus", new String[] { "契約種別", "「新規」「契約変更」", "契約情報確定" }));
		}

		// 契約.ライフサイクル状態が「作成完了」かどうかチェックする。
		if (LifecycleStatus.作成完了 != contract.getLifecycleStatus()) {
			throw new ErrorCheckException(addErrorInfo(new ArrayList<ErrorInfo>(), "CannotContractActionByStatus", new String[] { "ライフサイクル状態", "「作成完了」", "契約情報確定" }));
		}

		// 契約.ワークフロー状態が「承認済」かどうかチェックする。
		if (WorkflowStatus.承認済 != contract.getWorkflowStatus()) {
			throw new ErrorCheckException(addErrorInfo(new ArrayList<ErrorInfo>(), "CannotContractActionByStatus", new String[] { "ワークフロー状態", "「承認済」", "契約情報確定" }));
		}
	}

	/**
	 * 締結開始指示APIのエラーチェック
	 *
	 * @param contract
	 */
	public void startConclusionCheck(Contract contract) {
		// 契約.契約種別が「新規」または「契約変更」かどうかチェックする。
		if (ContractType.新規 != contract.getContractType() && ContractType.契約変更 != contract.getContractType()) {
			throw new ErrorCheckException(addErrorInfo(new ArrayList<ErrorInfo>(), "CannotContractActionByStatus", new String[] { "契約種別", "「新規」「契約変更」", "締結開始指示" }));
		}

		// 契約.ライフサイクル状態が「作成完了」かどうかチェックする。
		if (LifecycleStatus.作成完了 != contract.getLifecycleStatus()) {
			throw new ErrorCheckException(addErrorInfo(new ArrayList<ErrorInfo>(), "CannotContractActionByStatus", new String[] { "ライフサイクル状態", "「作成完了」", "締結開始指示" }));
		}

		// 契約.ワークフロー状態が「売上可能」かどうかチェックする。
		if (WorkflowStatus.売上可能 != contract.getWorkflowStatus()) {
			throw new ErrorCheckException(addErrorInfo(new ArrayList<ErrorInfo>(), "CannotContractActionByStatus", new String[] { "ワークフロー状態", "「売上可能」", "締結開始指示" }));
		}
	}

	/**
	 * 月ずれチェック
	 * 契約月数によって数量を積上げる品種のチェック処理
	 * チェック品種コードの積上げ数量が、チェック日付From～チェック日付Toの月数を比較する。
	 * パラメーター.許容値区分によって比較方法を切り替える。
	 *
	 * @param contract 契約情報
	 * @param checkItemMasterId チェック品種コード
	 * @param checkFromDate チェック日付From
	 * @param checkToDate チェック日付To
	 * @param toleranceTyped Enum許容値区分
	 * @return errorInfo
	 */
	public boolean monthMisalignCheck(Contract contract, long checkItemMasterId, Date checkFromDate, Date checkToDate, ToleranceType toleranceTyped) {

		Boolean checkFlg = false;

		// 契約情報チェック
		if (contract == null) {
			throw new ErrorCheckException(addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistArrangement", new String[] { "契約" }));
		}

		// チェック日付チェック
		if (checkFromDate == null || checkToDate == null) {
			throw new ErrorCheckException(addErrorInfo(new ArrayList<ErrorInfo>(), "ContractInvalidParameterError"));
		}
		;

		// チェック日付のFrom～To整合性チェック
		if (checkFromDate.after(checkToDate)) {
			throw new ErrorCheckException(addErrorInfo(new ArrayList<ErrorInfo>(), "ContractInvalidParameterError"));
		}

		// 日、時、分、秒は不要なため、フォーマットする(1日と00:00:00で固定)
		Date formatCheckFromDate = DateUtils.truncate(checkFromDate, Calendar.MONTH);
		Date formatCheckToDate = DateUtils.truncate(checkToDate, Calendar.MONTH);

		// チェック日付Fromとチェック日付Toの月の差分を取得する。
		Period period = Period.between(convertToJavaUtilDate(formatCheckFromDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), convertToJavaUtilDate(formatCheckToDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		int diffMonth = (period.getYears() * 12) + (period.getMonths());

		// 契約明細に紐づく品種（契約用）を取得し、チェック品種コードと一致する品種の数量と月の差分が一致しているかチェックする。
		Optional<ContractDetail> cd = contract.getContractDetailList().stream().filter(fil -> fil.getItemContract().getItemMasterId() == checkItemMasterId).findFirst();
		if (cd.isPresent()) {

			// 許容値区分によって比較分岐
			switch (toleranceTyped) {
			case 一致:
				// 積上げ数量と月数が一致しない場合、エラーとする。
				if (cd.get().getQuantity() != diffMonth) {
					checkFlg = true;
				}
				break;
			case 数量より月数大:
				// 積上げ数量が月数より小さい場合、エラーとする。
				if (cd.get().getQuantity() < diffMonth) {
					// エラーメッセージは決まったら変更する
					checkFlg = true;
				}
				break;
			case 数量より月数小:
				// 積上げ数量が月数より大きい場合、エラーとする。
				if (cd.get().getQuantity() > diffMonth) {
					checkFlg = true;
				}
				break;
			case 数量が月数or数量プラス1が月数:
				// 積上げ数量が月数or数量プラス1が月数と一致しない場合、エラーとする。
				if (cd.get().getQuantity() != diffMonth && cd.get().getQuantity() + 1 != diffMonth) {
					checkFlg = true;
				}
				break;
			case 数量が月数マイナス1or月数:
				// 積上げ数量が月数-1or月数と一致しない場合、エラーとする。
				if (cd.get().getQuantity() != diffMonth - 1 && cd.get().getQuantity() != diffMonth) {
					checkFlg = true;
				}
				break;
			case 数量が月数マイナス1or月数or月数プラス1:
				// 積上げ数量が月数-1or月数or月数+1と一致しない場合、エラーとする。
				if (cd.get().getQuantity() != diffMonth - 1 && cd.get().getQuantity() != diffMonth && cd.get().getQuantity() != diffMonth + 1) {
					checkFlg = true;
				}
			}
		}

		return checkFlg;
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

	/**
	 * MessageUtil生成
	 *
	 * @param basename
	 *            ベースネーム
	 * @param defaultEncoding
	 *            デフォルトエンコーディング
	 */
	public void setMessageUtil(String basename, String defaultEncoding) {
		this.messageUtil = new MessageUtil();
		this.messageUtil.setMessageSource(basename, defaultEncoding);
	}
}