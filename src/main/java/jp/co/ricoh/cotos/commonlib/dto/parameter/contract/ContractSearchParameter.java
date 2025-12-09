package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 契約を検索するためのキー項目クラスを表します。
 */
@Data
public class ContractSearchParameter {

	public enum SortOrder {

		昇順, 降順;

		@JsonValue
		public String toValue() {
			return this.name();
		}

		@JsonCreator
		public static Enum<SortOrder> fromValue(String name) {
			return Arrays.stream(values()).filter(v -> v.name() == name).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(name)));
		}
	}

	/**
	 * 契約番号
	 */
	@Parameter(description = "契約番号", required = false)
	@Schema(description = "契約番号", required = false, allowableValues = "range[0,15]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Parameter(description = "契約番号枝番", required = false)
	@Schema(description = "契約番号枝番", required = false, allowableValues = "range[0,2]")
	private String contractBranchNumber;

	/**
	 * サービス識別番号
	 */
	@Parameter(description = "サービス識別番号", required = false)
	@Schema(description = "サービス識別番号", required = false, allowableValues = "range[0,18]")
	private String serviceIdentificationNumber;

	/**
	 * RJ管理番号
	 */
	@Parameter(description = "RJ管理番号", required = false)
	@Schema(description = "RJ管理番号", required = false, allowableValues = "range[0,20]")
	private String rjManageNumber;

	/**
	 * FFM発注問合せ番号
	 */
	@Parameter(description = "FFM発注問合せ番号", required = false)
	@Schema(description = "FFM発注問合せ番号", required = false, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * WEB受注番号
	 */
	@Parameter(description = "WEB受注番号", required = false)
	@Schema(description = "WEB受注番号", required = false, allowableValues = "range[0,20]")
	private String webOrderNumber;

	/**
	 * 見積書番号
	 */
	@Parameter(description = "見積番号", required = false)
	@Schema(description = "見積番号", required = false, allowableValues = "range[0,15]")
	private String estimateNumber;

	/**
	 * 見積書番号
	 */
	@Parameter(description = "見積番号枝番", required = false)
	@Schema(description = "見積番号枝番", required = false, allowableValues = "range[0,2]")
	private String estimateBranchNumber;

	/**
	 * 見積件名
	 */
	@Parameter(description = "見積件名:部分一致", required = false)
	@Schema(description = "見積件名:部分一致<br />" //
			+ "条件入力時、最低2文字以上の入力とする。", //
			required = false, allowableValues = "range[2,255]") //
	private String likeSearchEstimationTitle;

	/**
	 * 案件番号
	 */
	@Parameter(description = "案件番号", required = false)
	@Schema(description = "案件番号", required = false, allowableValues = "range[0,255]")
	private String caseNumber;

	/**
	 * 案件名
	 */
	@Parameter(description = "案件名:部分一致", required = false)
	@Schema(description = "案件名:部分一致<br />" //
			+ "条件入力時、最低2文字以上の入力とする。", //
			required = false, allowableValues = "range[2,255]") //
	private String likeSearchCaseTitle;

	/**
	 * 契約状態
	 */
	@Parameter(description = "契約状態:カンマ区切りで複数指定可", required = false)
	@Schema(description = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false) //
	private String lifecycleStatus;

	/**
	 * サービス開始日(前)
	 */
	@Parameter(description = "サービス開始日(前)", required = false)
	@Schema(description = "サービス開始日(前)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date serviceTermStartFrom;

	/**
	 * サービス開始日(後)
	 */
	@Parameter(description = "サービス開始日(後)", required = false)
	@Schema(description = "サービス開始日(後)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date serviceTermStartTo;

	/**
	 * サービス終了日(前)
	 */
	@Parameter(description = "サービス終了日(前)", required = false)
	@Schema(description = "サービス終了日(前)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date serviceTermEndFrom;

	/**
	 * サービス終了日(後)
	 */
	@Parameter(description = "サービス終了日(後)", required = false)
	@Schema(description = "サービス終了日(後)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date serviceTermEndTo;

	/**
	 * お客様企事部ID
	 */
	@Parameter(description = "お客様企事部ID", required = false)
	@Schema(description = "お客様企事部ID", required = false, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * お客様企業名
	 */
	@Parameter(description = "お客様企業名:部分一致", required = false)
	@Schema(description = "お客様企業名:部分一致<br />" //
			+ "条件入力時、最低2文字以上の入力とする。", //
			required = false, allowableValues = "range[2,255]") //
	private String likeSearchCustomerName;

	/**
	 * 事業所名
	 */
	@Parameter(description = "お客様事業所名:部分一致", required = false)
	@Schema(description = "お客様事業所名:部分一致<br />" //
			+ "条件入力時、最低2文字以上の入力とする。", //
			required = false, allowableValues = "range[2,255]") //
	private String likeSearchOfficeName;

	/**
	 * 部門名
	 */
	@Parameter(description = "お客様部門名:部分一致", required = false)
	@Schema(description = "お客様部門名:部分一致<br />" //
			+ "条件入力時、最低2文字以上の入力とする。", //
			required = false, allowableValues = "range[2,255]") //
	private String likeSearchDepartmentName;

	/**
	 * 希望納期(前)
	 */
	@Parameter(description = "希望納期(前)", required = false)
	@Schema(description = "希望納期(前)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date desiredDeliveryDateFrom;

	/**
	 * 希望納期(後)
	 */
	@Parameter(description = "希望納期(後)", required = false)
	@Schema(description = "希望納期(後)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date desiredDeliveryDateTo;

	/**
	 * 得意先コード
	 */
	@Parameter(description = "得意先コード", required = false)
	@Schema(description = "得意先コード", required = false, allowableValues = "range[0,255]")
	private String billingCustomerSpCode;

	/**
	 * 担当支社
	 */
	@Parameter(description = "第1階層", required = false)
	@Schema(description = "第1階層<br />" //
			+ "設定値はMoM組織ID。", //
			required = false, allowableValues = "range[0,255]") //
	private String picAffiliateId;

	/**
	 * 担当部門
	 */
	@Parameter(description = "第2階層", required = false)
	@Schema(description = "第2階層<br />" //
			+ "設定値はMoM組織ID。", //
			required = false, allowableValues = "range[0,255]") //
	private String picDepartmentId;

	/**
	 * 担当課所
	 */
	@Parameter(description = "第3階層", required = false)
	@Schema(description = "第3階層<br />" //
			+ "設定値はMoM組織ID。", //
			required = false, allowableValues = "range[0,255]") //
	private String picDivisionId;

	/**
	 * 担当者
	 */
	@Parameter(description = "担当者：MoM社員IDを指定", required = false)
	@Schema(description = "担当者<br />" //
			+ "担当者にはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picEmptxId;

	/**
	 * 受付担当SS
	 */
	@Parameter(description = "受付担当SS：MoM組織IDを指定", required = false)
	@Schema(description = "受付担当SS<br />" //
			+ "受付担当SSにはMoM組織IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picAccSsId;

	/**
	 * 受付担当CE
	 */
	@Parameter(description = "受付担当CE：MoM社員IDを指定", required = false)
	@Schema(description = "受付担当CE<br />" //
			+ "受付担当CEにはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picAccCeId;

	/**
	 * 審査／承認者
	 */
	@Parameter(description = "審査／承認者：MoM社員IDを指定", required = false)
	@Schema(description = "審査／承認者<br />" //
			+ "審査／承認者にはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String approvalEmptxId;

	/**
	 * 協力者
	 */
	@Parameter(description = "協力者：MoM社員IDを指定", required = false)
	@Schema(description = "協力者<br />" //
			+ "協力者にはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String collaborationEmptxId;

	/**
	 * 請求開始月
	 */
	@Parameter(description = "請求開始月", required = false)
	@Schema(description = "請求開始月<br />" //
			+ "日付フォーマット：yyyy/MM", //
			required = false) //
	private String billingMonth;

	/**
	 * 契約ステータス
	 */
	@Parameter(description = "契約ステータス:カンマ区切りで複数指定可", required = false)
	@Schema(description = "契約ステータス<br />" //
			+ "状態遷移上のワークフローステータスを表す。", //
			required = false) //
	private String workflowStatus;

	/**
	 * 契約種別
	 */
	@Parameter(description = "契約種別", required = false)
	@Schema(description = "契約種別<br />" //
			+ "新規, 契約変更, 解約などの契約種別を表す。", //
			required = false)
	private String contractType;

	/**
	 * 商品マスタID
	 */
	@Parameter(description = "商品マスタID", required = false)
	@Schema(description = "商品マスタID", required = false)
	private Long productId;

	/**
	 * 恒久契約識別番号
	 */
	@Parameter(description = "恒久契約識別番号:部分一致", required = false)
	@Schema(description = "恒久契約識別番号:部分一致<br />" //
			+ "条件入力時、最低2文字以上の入力とする。", //
			required = false, allowableValues = "range[2,255]") //
	private String immutableContIdentNumber;

	/**
	 * 導入担当SS
	 */
	@Parameter(description = "導入担当SS：MoM組織IDを指定", required = false)
	@Schema(description = "導入担当SS<br />" //
			+ "導入担当SSにはMoM組織IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picIntSsId;

	/**
	 * 導入担当CE
	 */
	@Parameter(description = "導入担当CE：MoM社員IDを指定", required = false)
	@Schema(description = "導入担当CE<br />" //
			+ "導入担当CEにはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picIntCeId;

	/**
	 * 保守担当SS
	 */
	@Parameter(description = "保守担当SS：MoM組織IDを指定", required = false)
	@Schema(description = "保守担当SS<br />" //
			+ "保守担当SSにはMoM組織IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picMntSsId;

	/**
	 * 保守担当CE
	 */
	@Parameter(description = "保守担当CE：MoM社員IDを指定", required = false)
	@Schema(description = "保守担当CE<br />" //
			+ "保守担当CEにはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picMntCeId;

	/**
	 * お客様企業ID
	 */
	@Parameter(description = "お客様企業ID", required = false)
	@Schema(description = "お客様企業ID<br />" //
			+ "企業IDにはMoM企業IDを指定する。", //
			required = false, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * 商材固有項目 プロパティ名リスト
	 */
	@Parameter(description = "商材固有項目 プロパティ名リスト", required = false)
	@Schema(description = "商材固有項目 プロパティ名リスト", required = false)
	private List<String> productUniqueItemNameList;

	/**
	 * 商材固有項目値
	 */
	@Parameter(description = "商材固有項目値", required = false)
	@Schema(description = "商材固有項目値", required = false)
	private String productUniqueItemValue;

	/**
	 * V-UP見積番号
	 */
	@Parameter(description = "V-UP見積番号", required = false)
	@Schema(description = "V-UP見積番号", required = false, allowableValues = "range[0,255]")
	private String vupEstimationNumber;

	/**
	 * ベンダー管理番号
	 */
	@Parameter(description = "ベンダー管理番号", required = false)
	@Schema(description = "ベンダー管理番号", required = false, allowableValues = "range[0,255]")
	private String vendorManageNumber;

	/**
	 * サービス利用希望日(前)
	 */
	@Parameter(description = "サービス利用希望日(前)", required = false)
	@Schema(description = "サービス利用希望日(前)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date conclusionPreferredDateFrom;

	/**
	 * サービス利用希望日(後)
	 */
	@Parameter(description = "サービス利用希望日(後)", required = false)
	@Schema(description = "サービス利用希望日(後)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date conclusionPreferredDateTo;

	/**
	 * 解約予定日(前)
	 */
	@Parameter(description = "解約予定日(前)", required = false)
	@Schema(description = "解約予定日(前)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date cancelScheduledDateFrom;

	/**
	 * 解約予定日(後)
	 */
	@Parameter(description = "解約予定日(後)", required = false)
	@Schema(description = "解約予定日(後)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date cancelScheduledDateTo;

	/**
	 * 商品マスタIDリスト
	 */
	@Parameter(description = "商品マスタIDリスト", required = false)
	@Schema(description = "商品マスタIDリスト", required = false)
	private String productIdList;

	/**
	 * お客様：会社代表電話番号
	 */
	@Parameter(description = "お客様：会社代表電話番号", required = false)
	@Schema(description = "お客様：会社代表電話番号", required = false)
	private String customerPhoneNumber;

	/**
	 * 設置先：会社代表電話番号
	 */
	@Parameter(description = "設置先：会社代表電話番号", required = false)
	@Schema(description = "設置先：会社代表電話番号", required = false)
	private String installationPhoneNumber;

	/**
	 * モバイル：端末電話番号
	 */
	@Parameter(description = "モバイル：端末電話番号", required = false)
	@Schema(description = "モバイル：端末電話番号", required = false)
	private String mobilePhoneNumber;

	/**
	 * お客様：担当者電話番号
	 */
	@Parameter(description = "お客様：担当者電話番号", required = false)
	@Schema(description = "お客様：担当者電話番号", required = false)
	private String customerPicPhoneNumber;

	/**
	 * 設置先：担当者電話番号
	 */
	@Parameter(description = "設置先：担当者電話番号", required = false)
	@Schema(description = "設置先：担当者電話番号", required = false)
	private String installationPicPhoneNumber;

	/**
	 * ソート項目
	 */
	@NotNull(message = "{SortColumn}{NotEmptyError}:{SortColumn}{NotEmptyErrorMsg}")
	@Parameter(description = "ソート項目", required = false)
	@Schema(description = "ソート項目<br />" //
			+ "ソート項目のint値は以下の通り各項目とマッピングされる。<br />" //
			+ "0:契約番号<br />" //
			+ "1:サービス識別番号<br />" //
			+ "2:契約種別<br />" //
			+ "3:契約ステータス<br />" //
			+ "4:契約状態<br />" //
			+ "5:見積ID" + "6:見積番号<br />" //
			+ "7:見積件名<br />" //
			+ "8:案件番号<br />" //
			+ "9:請求開始月<br />" //
			+ "10:お客様企業名<br />" //
			+ "11:事業所<br />"//
			+ "12:部門<br />" //
			+ "13:サービス開始日<br />" //
			+ "14:サービス終了日<br />" //
			+ "15:商品名称<br />" //
			+ "16:担当営業<br />" //
			+ "17:担当営業所属<br />" //
			+ "18:受付担当SS組織<br />" //
			+ "19:受付担当CE氏名<br />" //
			+ "20:導入担当SS組織<br />" //
			+ "21:導入担当CE氏名<br />" //
			+ "22:保守担当SS組織<br />" //
			+ "23:保守担当CE氏名<br />" //
			+ "24:登録日時<br />" //
			+ "25:更新日時", //
			required = false, allowableValues = "range[0,15]") //
	private int sortColumn;

	/**
	 * ソート順
	 */
	@Parameter(description = "ソート順", required = false)
	@Enumerated(EnumType.STRING)
	@Schema(description = "ソート順", required = false)
	private SortOrder sortOrder;

	/**
	 * アプリケーションIDリスト
	 */
	@Parameter(description = "アプリケーションIDリスト", required = false)
	@Schema(description = "アプリケーションIDリスト", required = false)
	private List<String> appId;

	/**
	 * 他システムデータ排他フラグ
	 */
	@Parameter(description = "他システムデータ排他フラグ", required = false)
	@Schema(description = "他システムデータ排他フラグ", required = false)
	private int otherSysDataExcludeFlg;

	/**
	 * ディスパッチ対応
	 */
	@Parameter(description = "ディスパッチ対応", required = false)
	@Schema(description = "ディスパッチ対応", required = false)
	private Integer dispatchFlg;

	/**
	 * 破棄ステータスを除外
	 */
	@Parameter(description = "破棄ステータスを除外", required = false)
	@Schema(description = "破棄ステータスを除外", required = false)
	private boolean excludesDiscarded;

	/**
	 * 承認周りの情報を除外
	 */
	@Parameter(description = "承認周りの情報を除外", required = false)
	@Schema(description = "承認周りの情報を除外", required = false)
	private boolean excludesApprovalInfo;

	/**
	 * パラメータをMapにする。
	 */
	public Map<String, Object> createParamaterMap() {
		Map<String, Object> retMap = new HashMap<>();

		FieldUtils.getAllFieldsList(this.getClass()).stream().filter(putField -> !putField.getName().startsWith("$")).forEach(field -> {
			try {
				retMap.put(field.getName(), field.get(this));
			} catch (IllegalAccessException e) {
				retMap.put(field.getName(), null);
			}
		});

		return retMap;
	}
}