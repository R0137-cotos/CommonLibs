package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement;

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
 * 手配を検索するためのキー項目クラスを表します。
 */
@Data
public class ArrangementSearchParameter {

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
	 * 手配業務
	 */
	@Parameter(description = "手配業務タイプマスタID")
	@Schema(description = "手配業務タイプマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String arrangementWorkTypeId;

	/**
	 * 手配業務ステータス
	 */
	@Parameter(description = "手配業務ステータス:カンマ区切りで複数指定可")
	@Schema(description = "手配業務ステータス<br />" //
			+ "状態遷移上のワークフロー状態を表す。", //
			required = false) //
	private String arrangementWorkStatus;

	/**
	 * 手配業務担当者
	 */
	@Parameter(description = "手配業務担当者：MoM社員IDを指定")
	@Schema(description = "手配業務担当者<br />" //
			+ "手配業務担当者にはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String workUserEmptxId;

	/**
	 * 希望納期(前)
	 */
	@Parameter(description = "希望納期(前)")
	@Schema(description = "希望納期(前)<br />" //
			+ "日付フォーマット：yyyy/MM/dd", //
			required = false) //
	private Date desiredDeliveryDateFrom;

	/**
	 * 希望納期(後)
	 */
	@Parameter(description = "希望納期(後)")
	@Schema(description = "希望納期(後)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date desiredDeliveryDateTo;

	/**
	 * 業務受理日時(前)
	 */
	@Parameter(description = "業務受理日時(前)")
	@Schema(description = "業務受理日時<br />" //
			+ "日付フォーマット:yyyy/MM/dd HH", //
			required = false) //
	private Date businessAcceptanceDateTimeFrom;

	/**
	 * 業務受理日時(後)
	 */
	@Parameter(description = "業務受理日時(後)")
	@Schema(description = "業務受理日時<br />" //
			+ "日付フォーマット:yyyy/MM/dd HH", //
			required = false) //
	private Date businessAcceptanceDateTime;

	// =========================== 以下、契約ドメインと同一(希望納期を除く)
	/**
	 * 契約番号
	 */
	@Parameter(description = "契約番号")
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Parameter(description = "契約番号枝番")
	@Schema(description = "契約番号枝番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String contractBranchNumber;

	/**
	 * サービス識別番号
	 */
	@Parameter(description = "サービス識別番号")
	@Schema(description = "サービス識別番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,18]")
	private String serviceIdentificationNumber;

	/**
	 * RJ管理番号
	 */
	@Parameter(description = "RJ管理番号")
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,20]")
	private String rjManageNumber;

	/**
	 * 見積書番号
	 */
	@Parameter(description = "見積番号")
	@Schema(description = "見積番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String estimateNumber;

	/**
	 * 見積書番号
	 */
	@Parameter(description = "見積番号枝番")
	@Schema(description = "見積番号枝番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String estimateBranchNumber;

	/**
	 * 見積件名
	 */
	@Parameter(description = "見積件名:部分一致")
	@Schema(description = "見積件名:部分一致<br />" //
			+ "条件入力時、最低2文字以上の入力とする。", //
			required = false, allowableValues = "range[2,255]") //
	private String likeSearchEstimationTitle;

	/**
	 * 案件番号
	 */
	@Parameter(description = "案件番号")
	@Schema(description = "案件番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String caseNumber;

	/**
	 * 案件名
	 */
	@Parameter(description = "案件名:部分一致")
	@Schema(description = "案件名:部分一致", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String likeSearchCaseTitle;

	/**
	 * 契約状態
	 */
	@Parameter(description = "契約状態:カンマ区切りで複数指定可")
	@Schema(description = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false) //
	private String lifecycleStatus;

	/**
	 * サービス開始日(前)
	 */
	@Parameter(description = "サービス開始日(前)")
	@Schema(description = "サービス開始日(前)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date serviceTermStartFrom;

	/**
	 * サービス開始日(後)
	 */
	@Parameter(description = "サービス開始日(後)")
	@Schema(description = "サービス開始日(後)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date serviceTermStartTo;

	/**
	 * サービス終了日(前)
	 */
	@Parameter(description = "サービス終了日(前)")
	@Schema(description = "サービス終了日(前)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date serviceTermEndFrom;

	/**
	 * サービス終了日(後)
	 */
	@Parameter(description = "サービス終了日(後)")
	@Schema(description = "サービス終了日(後)<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date serviceTermEndTo;

	/**
	 * お客様企業ID
	 */
	@Parameter(description = "企事部ID")
	@Schema(description = "企事部ID<br />" //
			+ "企事部IDにはMoM企事部IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String customerId;

	/**
	 * お客様企業名
	 */
	@Parameter(description = "お客様企業名:部分一致")
	@Schema(description = "お客様企業名:部分一致<br />" //
			+ "条件入力時、最低2文字以上の入力とする。", //
			required = false, allowableValues = "range[2,255]") //
	private String likeSearchCustomerName;

	/**
	 * 事業所名
	 */
	@Parameter(description = "お客様事業所名:部分一致")
	@Schema(description = "お客様事業所名:部分一致<br />" //
			+ "条件入力時、最低2文字以上の入力とする。", //
			required = false, allowableValues = "range[2,255]") //
	private String likeSearchOfficeName;

	/**
	 * 部門名
	 */
	@Parameter(description = "お客様部門名:部分一致")
	@Schema(description = "お客様部門名:部分一致<br />" //
			+ "条件入力時、最低2文字以上の入力とする。", //
			required = false, allowableValues = "range[2,255]") //
	private String likeSearchDepartmentName;

	/**
	 * 得意先コード
	 */
	@Parameter(description = "得意先コード")
	@Schema(description = "得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String billingCustomerSpCode;

	/**
	 * 担当支社
	 */
	@Parameter(description = "第1階層")
	@Schema(description = "第1階層<br />" //
			+ "設定値はMoM組織ID。", //
			required = false, allowableValues = "range[0,255]") //
	private String picAffiliateId;

	/**
	 * 担当部門
	 */
	@Parameter(description = "第2階層")
	@Schema(description = "第2階層<br />" //
			+ "設定値はMoM組織ID。", //
			required = false, allowableValues = "range[0,255]") //
	private String picDepartmentId;

	/**
	 * 担当課所
	 */
	@Parameter(description = "第3階層")
	@Schema(description = "第3階層<br />" //
			+ "設定値はMoM組織ID。", //
			required = false, allowableValues = "range[0,255]") //
	private String picDivisionId;

	/**
	 * 担当者
	 */
	@Parameter(description = "担当者：MoM社員IDを指定")
	@Schema(description = "担当者<br />" //
			+ "担当者にはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picEmptxId;

	/**
	 * 審査／承認者
	 */
	@Parameter(description = "審査／承認者：MoM社員IDを指定")
	@Schema(description = "審査／承認者<br />" //
			+ "審査／承認者にはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String approvalEmptxId;

	/**
	 * 協力者
	 */
	@Parameter(description = "協力者：MoM社員IDを指定")
	@Schema(description = "協力者<br />" //
			+ "協力者にはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String collaborationEmptxId;

	/**
	 * 請求開始月
	 */
	@Parameter(description = "請求開始月")
	@Schema(description = "請求開始月<br />" //
			+ "日付フォーマット:yyyy/MM", //
			required = false) //
	private String billingMonth;

	/**
	 * 契約ステータス
	 */
	@Parameter(description = "契約ステータス:カンマ区切りで複数指定可")
	@Schema(description = "契約ステータス<br />" //
			+ "状態遷移上のワークフローステータスを表す。", //
			required = false) //
	private String workflowStatus;

	/**
	 * 契約種別
	 */
	@Parameter(description = "契約種別")
	@Schema(description = "契約種別<br />" //
			+ "新規, 契約変更, 解約などの契約種別を表す。", //
			required = false)
	private String contractType;

	/**
	 * 商品マスタID
	 */
	@Parameter(description = "商品マスタID")
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long productId;

	/**
	 * 保留フラグ
	 */
	@Parameter(description = "保留フラグ")
	@Schema(description = "保留フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer holdingFlg;

	/**
	 * お客様企業ID
	 */
	@Parameter(description = "お客様企業ID")
	@Schema(description = "お客様企業ID<br />" //
			+ "企業IDにはMoM企業IDを指定する。", //
			required = false, allowableValues = "range[0,255]")
	private String companyId;

	// ===========================
	/**
	 * 恒久契約識別番号
	 */
	@Parameter(description = "恒久契約識別番号:部分一致")
	@Schema(description = "恒久契約識別番号:部分一致<br />" //
			+ "条件入力時、最低2文字以上の入力とする。", //
			required = false, allowableValues = "range[2,255]") //
	private String immutableContIdentNumber;

	/**
	 * 回線番号
	 */
	@Parameter(description = "回線番号")
	@Schema(description = "回線番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String lineNumber;

	/**
	 * ICCID
	 */
	@Parameter(description = "ICCID")
	@Schema(description = "ICCID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * V-UP見積番号
	 */
	@Parameter(description = "V-UP見積番号")
	@Schema(description = "V-UP見積番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vupEstimationNumber;

	/**
	 * ベンダー管理番号
	 */
	@Parameter(description = "ベンダー管理番号")
	@Schema(description = "ベンダー管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vendorManageNumber;

	/**
	 * ソート項目
	 */
	@NotNull(message = "{SortColumn}{NotEmptyError}:{SortColumn}{NotEmptyErrorMsg}")
	@Parameter(description = "ソート項目")
	@Schema(description = "ソート項目<br />" //
			+ "ソート項目のint値は以下の通り各項目とマッピングされる。<br />" //
			+ "0:契約番号<br />" //
			+ "1:サービス識別番号<br />" //
			+ "2:契約状態<br />" //
			+ "3:契約種別<br />" //
			+ "4:お客様企業名<br />" //
			+ "5:商品名称<br />" //
			+ "6:希望納期<br />" //
			+ "7:手配業務<br />" //
			+ "8:手配作成日<br />" //
			+ "9:業務担当者<br />" //
			+ "10:業務ステータス<br />" //
			+ "11:見積番号<br />" //
			+ "12:担当営業<br />"//
			+ "13:担当支社<br />" //
			+ "14:サービス開始日<br />" //
			+ "15:サービス終了日<br />" //
			+ "16:保留フラグ<br />" //
			+ "17:RJ管理番号<br />"//
			+ "18:恒久契約識別番号" //
			+ "19:受付担当SS組織<br />" //
			+ "20:受付担当CE氏名<br />" //
			+ "21:導入担当SS組織<br />" //
			+ "22:導入担当CE氏名<br />" //
			+ "23:保守担当SS組織<br />" //
			+ "24:保守担当CE氏名<br />" //
			+ "25:登録日時<br />" //
			+ "26:更新日時<br />", //
			required = false, allowableValues = "range[0,14]") //
	private int sortColumn;

	/**
	 * ソート順
	 */
	@Parameter(description = "ソート順")
	@Enumerated(EnumType.STRING)
	@Schema(description = "ソート順", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private SortOrder sortOrder;

	/**
	 * アプリケーションIDリスト
	 */
	@Parameter(description = "アプリケーションIDリスト")
	@Schema(description = "アプリケーションIDリスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<String> appId;

	/**
	 * 他システムデータ排他フラグ
	 */
	@Parameter(description = "他システムデータ排他フラグ")
	@Schema(description = "他システムデータ排他フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private int otherSysDataExcludeFlg;

	/**
	 * 受付担当SS
	 */
	@Parameter(description = "受付担当SS：MoM組織IDを指定")
	@Schema(description = "受付担当SS<br />" //
			+ "受付担当SSにはMoM組織IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picAccSsId;

	/**
	 * 受付担当CE
	 */
	@Parameter(description = "受付担当CE：MoM社員IDを指定")
	@Schema(description = "受付担当CE<br />" //
			+ "受付担当CEにはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picAccCeId;

	/**
	 * 導入担当SS
	 */
	@Parameter(description = "導入担当SS：MoM組織IDを指定")
	@Schema(description = "導入担当SS<br />" //
			+ "導入担当SSにはMoM組織IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picIntSsId;

	/**
	 * 導入担当CE
	 */
	@Parameter(description = "導入担当CE：MoM社員IDを指定")
	@Schema(description = "導入担当CE<br />" //
			+ "導入担当CEにはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picIntCeId;

	/**
	 * 保守担当SS
	 */
	@Parameter(description = "保守担当SS：MoM組織IDを指定")
	@Schema(description = "保守担当SS<br />" //
			+ "保守担当SSにはMoM組織IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picMntSsId;

	/**
	 * 保守担当CE
	 */
	@Parameter(description = "保守担当CE：MoM社員IDを指定")
	@Schema(description = "保守担当CE<br />" //
			+ "保守担当CEにはMoM社員IDを指定する。", //
			required = false, allowableValues = "range[0,255]") //
	private String picMntCeId;

	/**
	 * 解約フラグ
	 */
	@Parameter(description = "解約フラグ")
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer disengagementFlg;

	/**
	 * サービス利用希望日
	 */
	@Parameter(description = "サービス利用希望日")
	@Schema(description = "サービス利用希望日<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date conclusionPreferredDate;

	/**
	 * 導入設置先距離
	 */
	@Parameter(description = "導入設置先距離")
	@Schema(description = "導入設置先距離", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long introductionDistance;

	/**
	 * 承認手配業務
	 */
	@Parameter(description = "承認手配業務タイプマスタID")
	@Schema(description = "承認手配業務タイプマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String approvalArrangementWorkTypeId;

	/**
	 * 手配承認日時(前)
	 */
	@Parameter(description = "手配承認日時(前)")
	@Schema(description = "手配承認日時<br />" //
			+ "日付フォーマット:yyyy/MM/dd HH", //
			required = false) //
	private Date approvalDateFrom;

	/**
	 * 手配承認日時(後)
	 */
	@Parameter(description = "手配承認日時(後)")
	@Schema(description = "手配承認日時<br />" //
			+ "日付フォーマット:yyyy/MM/dd HH", //
			required = false) //
	private Date approvalDate;

	/**
	 * IMEI (携帯シリアル番号)
	 */
	@Parameter(description = "IMEI (携帯シリアル番号)")
	@Schema(description = "IMEI (携帯シリアル番号)", allowableValues = "range[0,255]")
	private String imeiNumber;

	/**
	 * 手配業務ステータス(手配承認日時検索条件)
	 */
	@Parameter(description = "手配業務ステータス:カンマ区切りで複数指定可")
	@Schema(description = "手配業務ステータス(手配承認日時検索条件)<br />" //
			+ "状態遷移上のワークフロー状態を表す。", //
			required = false) //
	private String approvalArrangementWorkStatus;

	/**
	 * 関連手配業務
	 */
	@Parameter(description = "関連手配業務タイプマスタID")
	@Schema(description = "関連手配業務タイプマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String relatedArrangementWorkTypeId;

	/**
	 * 関連手配業務ステータス(関連手配業務状況検索条件)
	 */
	@Parameter(description = "関連手配業務ステータス")
	@Schema(description = "関連手配業務ステータス(関連手配業務状況検索条件)<br />" //
			+ "状態遷移上のワークフロー状態を表す。", //
			required = false) //
	private String relatedArrangementWorkStatus;

	/**
	 * 商品マスタIDリスト
	 *
	 * productIdの既存の実装ではlong型で一つのIDしか渡すことができない。
	 * 別ドメインから呼ばれるため、既存の変数は変えずに手配検索画面から商品マスタIDを複数含んだ文字列として受け取るために変数を追加
	 */
	@Parameter(description = "商品マスタIDリスト")
	@Schema(description = "商品マスタIDリスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String productIdList;

	/**
	 * 手配業務タイプマスタIDリスト
	 */
	@Parameter(description = "手配業務タイプマスタIDリスト")
	@Schema(description = "手配業務タイプマスタIDリスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String arrangementWorkTypeIdList;

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