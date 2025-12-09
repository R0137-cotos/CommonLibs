package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.OrderDiv;
import jp.co.ricoh.cotos.commonlib.dto.json.license.LicenseDetailExtendsParameterGspDto.ReceptionStatusFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.license.LicenseInfoExtendsParameterSsecDto.IntActingDiv;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.WorkflowStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.InfoDiv;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo.CancelStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseRemainingNumber.AllocationDiv;
import lombok.Data;

/**
 * ライセンス一覧検索結果を取得する為のDTOです。
 */
@Entity
@Data
public class LicenseSearchResult {

	/**
	 * ライセンス情報ID
	 */
	@Id
	@Schema(description = "ライセンス情報ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long licenseInfoId;

	/**
	 * 契約番号
	 */
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String contractNumber;

	/**
	 * RJ管理番号
	 */
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String rjManageNumber;

	/**
	 * 工程ステータス
	 */
	@Schema(description = "工程ステータス", requiredMode = Schema.RequiredMode.REQUIRED)
	private String processStatus;

	/**
	 * オーガニゼーションID
	 */
	@Schema(description = "オーガニゼーションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String organizationId;

	/**
	 * ライセンスキー
	 */
	@Schema(description = "ライセンスキー", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String licenseKey;

	/**
	 * アカウント
	 */
	@Schema(description = "アカウント", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String account;

	/**
	 * 工程実施日時
	 */
	@Schema(description = "工程実施日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date processOperatedDateTime;

	/**
	 * 契約種別
	 */
	@Schema(description = "契約種別<br />" //
			+ "新規、契約変更、情報変更、契約更新などの契約種別を表す。", //
			required = false, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")") //
	private ContractType contractType;

	/**
	 * 契約種別詳細
	 */
	@Schema(description = "契約種別詳細<br />" //
			+ "選択したライセンスに紐づく契約種別詳細を表す。", //
			required = false)
	private ContractTypeDetails contractTypeDetails;

	/**
	 * 契約ステータス
	 */
	@Schema(description = "契約ステータス<br />" //
			+ "契約のワークフローステータスを表す。", //
			required = false, allowableValues = "作成中(\"1\"), 承認依頼中(\"2\"), 承認済(\"3\"), 業務依頼中(\"4\"), 業務処理完了(\"5\"), キャンセル申請中(\"6\"), 売上可能(\"7\"), 解約申請中(\"8\")") //
	private WorkflowStatus workflowStatus;

	/**
	 * 契約状態
	 */
	@Schema(description = "契約状態<br />" //
			+ "契約のライフサイクル状態を表す。", //
			required = false, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\"), 締結待ち(\"11\")")
	private LifecycleStatus lifecycleStatus;

	/**
	 * キャンセル状態
	 */
	@Schema(description = "キャンセル状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未(\"0\"), 済(\"1\")")
	private CancelStatus cancelStatus;

	/**
	 * 受付状況フラグ
	 */
	@Schema(description = "受付状況フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未(\"0\"), 受理(\"1\"), 未受理(\"2\")")
	private ReceptionStatusFlg receptionStatusFlg;

	/**
	 * 不受理理由
	 */
	@Schema(description = "不受理理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String notAcceptReason;

	/**
	 * 割当区分
	 */
	@Schema(description = "割当区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未(\"0\"), 済(\"1\"), 破棄(\"2\")")
	private AllocationDiv allocationDiv;

	/**
	 * 顧客名
	 */
	@Schema(description = "顧客名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerName;

	/**
	 * 企業担当者
	 */
	@Schema(description = "企業担当者", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String licenseAccountPicName;

	/**
	 * お客様メールアドレス
	 */
	@Schema(description = "お客様メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String licenseInfoMailAddress;

	/**
	 * 都道府県
	 */
	@Schema(description = "都道府県", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String licenseAccountPrefectures;

	/**
	 * 市町区村
	 */
	@Schema(description = "市町区村", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String licenseAccountMunicipality;

	/**
	 * ライセンス数
	 */
	@Schema(description = "ライセンス数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String licenseDetailChangeQuantity;

	/**
	 * サービス利用希望日
	 */
	@Schema(description = "サービス利用希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date conclusionPreferredDate;

	/**
	 * 利用申込日
	 */
	@Schema(description = "CSV出力日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date csvOutputAt;

	/**
	 * 商品区分
	 */
	@Schema(description = "商品区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String productType;

	/**
	 * サービス種別
	 */
	@Schema(description = "サービス種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String serviceType;

	/**
	 * 受付担当SS組織
	 */
	@Schema(description = "受付担当SS組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String picAccSsName;

	/**
	 * 受付担当CE氏名
	 */
	@Schema(description = "受付担当CE氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String picAccCeName;

	/**
	 * 設置先企業名
	 */
	@Schema(description = "設置先企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String instrallationLocationCompanyName;

	/**
	 * 設置先住所
	 */
	@Schema(description = "設置先住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String instrallationLocationAddress;

	/**
	 * 設置先担当者名
	 */
	@Schema(description = "設置先担当者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String instrallationLocationPicName;

	/**
	 * 設置先担当者電話番号
	 */
	@Schema(description = "設置先担当者電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String instrallationLocationPicPhoneNumber;

	/**
	 * VPN
	 */
	@Schema(description = "VPN", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpn;

	/**
	 * POナンバー
	 */
	@Schema(description = "POナンバー", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String poNumber;

	/**
	 * サービス開始日
	 */
	@Schema(description = "サービス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date serviceTermStart;

	/**
	 * サービス終了日
	 */
	@Schema(description = "サービス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date serviceTermEnd;

	/**
	 * CE連絡日時
	 */
	@Schema(description = "CE連絡日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String ceContactAt;

	/**
	 * 解約予定日
	 */
	@Schema(description = "解約予定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cancelScheduledDate;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long contractId;

	/**
	 * メール送信日
	 */
	@Schema(description = "メール送信日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date mailSendAt;

	/**
	 * 送信結果区分
	 */
	@Schema(description = "送信結果区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String mailSendResultDiv;

	/**
	 * 業務受理日時
	 */
	@Schema(description = "業務受理日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date businessAcceptanceDateTime;

	/**
	 * MerakiスマートサービスオーガニゼーションID
	 */
	@Schema(description = "MerakiスマートサービスオーガニゼーションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String mssLinkageOrganizationId;

	/**
	 * 解約フラグ
	 */
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer disengagementFlg;

	/**
	 * 企業名
	 */
	@Schema(description = "企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String licenseAccountCompanyName;

	/**
	 * 導入代行
	 */
	@Schema(description = "導入代行", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "導入代行なし(\"1\"), リモート導入(\"2\"), オンサイト導入(\"3\")")
	private IntActingDiv intActingDiv;

	/**
	 * 情報区分
	 */
	@Schema(description = "情報区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "新規(\"1\"), 減数(\"2\"), 増数(\"3\"), 情報変更(\"4\"), 解約(\"5\"), 乗換(\"6\"), 乗換え増数(\"7\"), 乗換え減数(\"8\"), 支払周期変更(\"9\")")
	private InfoDiv infoDiv;

	/**
	 * FFM発注問合せ番号
	 */
	@Schema(description = "FFM発注問合せ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String contactNo;

	/**
	 * 設置先郵便番号(手入力)
	 */
	@Schema(description = "設置先郵便番号(手入力)")
	private String instrallationLocationInputPostNumber;

	/**
	 * 設置先担当者部署
	 */
	@Schema(description = "設置先担当者部署", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String instrallationLocationPicDeptName;

	/**
	 * Microsoftアカウント
	 */
	@Schema(description = "Microsoftアカウント", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String userId;

	/**
	 * オンサイト
	 */
	@Schema(description = "オンサイト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String onsiteFlg;

	/**
	 * ユーザー登録
	 */
	@Schema(description = "ユーザー登録", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String userRegisterFlg;

	/**
	 * 訪販/WEB
	 */
	@Schema(description = "訪販/WEB", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private OrderDiv orderDiv;

	/**
	 * ベンダー区分
	 */
	@Schema(description = "ベンダー区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vendorDiv;

	/**
	 * サービスプラン名
	 */
	@Schema(description = "サービスプラン名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String licenseServiceName;
}
