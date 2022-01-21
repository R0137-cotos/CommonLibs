package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.license.LicenseDetailExtendsParameterGspDto.ReceptionStatusFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.license.LicenseInfoExtendsParameterSsecDto.IntActingDiv;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.WorkflowStatus;
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
	@ApiModelProperty(value = "ライセンス情報ID", required = true, position = 1)
	private long licenseInfoId;

	/**
	 * 契約番号
	 */
	@ApiModelProperty(value = "契約番号", required = false, position = 2)
	private String contractNumber;

	/**
	 * RJ管理番号
	 */
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 3)
	private String rjManageNumber;

	/**
	 * 工程ステータス
	 */
	@ApiModelProperty(value = "工程ステータス", required = true, position = 4)
	private String processStatus;

	/**
	 * オーガニゼーションID
	 */
	@ApiModelProperty(value = "オーガニゼーションID", required = false, position = 5)
	private String organizationId;

	/**
	 * ライセンスキー
	 */
	@ApiModelProperty(value = "ライセンスキー", required = false, position = 6)
	private String licenseKey;

	/**
	 * アカウント
	 */
	@ApiModelProperty(value = "アカウント", required = false, position = 7)
	private String account;

	/**
	 * 工程実施日時
	 */
	@ApiModelProperty(value = "工程実施日時", required = false, position = 8)
	private Date processOperatedDateTime;

	/**
	 * 契約種別
	 */
	@ApiModelProperty(value = "契約種別<br />" //
			+ "新規、契約変更、情報変更、契約更新などの契約種別を表す。", //
			required = false, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")", position = 9) //
	private ContractType contractType;

	/**
	 * 契約種別詳細
	 */
	@ApiModelProperty(value = "契約種別詳細<br />" //
			+ "選択したライセンスに紐づく契約種別詳細を表す。", //
			required = false, position = 10)
	private ContractTypeDetails contractTypeDetails;

	/**
	 * 契約ステータス
	 */
	@ApiModelProperty(value = "契約ステータス<br />" //
			+ "契約のワークフローステータスを表す。", //
			required = false, allowableValues = "作成中(\"1\"), 承認依頼中(\"2\"), 承認済(\"3\"), 業務依頼中(\"4\"), 業務処理完了(\"5\"), キャンセル申請中(\"6\"), 売上可能(\"7\"), 解約申請中(\"8\")", //
			position = 11) //
	private WorkflowStatus workflowStatus;

	/**
	 * 契約状態
	 */
	@ApiModelProperty(value = "契約状態<br />" //
			+ "契約のライフサイクル状態を表す。", //
			required = false, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\"), 締結待ち(\"11\")", //
			position = 12)
	private LifecycleStatus lifecycleStatus;

	/**
	 * キャンセル状態
	 */
	@ApiModelProperty(value = "キャンセル状態", required = false, allowableValues = "未(\"0\"), 済(\"1\")", position = 13)
	private CancelStatus cancelStatus;

	/**
	 * 受付状況フラグ
	 */
	@ApiModelProperty(value = "受付状況フラグ", required = false, allowableValues = "未(\"0\"), 受理(\"1\"), 未受理(\"2\")", position = 14)
	private ReceptionStatusFlg receptionStatusFlg;

	/**
	 * 不受理理由
	 */
	@ApiModelProperty(value = "不受理理由", required = false, position = 15)
	private String notAcceptReason;

	/**
	 * 割当区分
	 */
	@ApiModelProperty(value = "割当区分", required = false, allowableValues = "未(\"0\"), 済(\"1\"), 破棄(\"2\")", position = 16)
	private AllocationDiv allocationDiv;

	/**
	 * 顧客名
	 */
	@ApiModelProperty(value = "顧客名", required = false, position = 17)
	private String customerName;

	/**
	 * 企業担当者
	 */
	@ApiModelProperty(value = "企業担当者", required = false, position = 18)
	private String licenseAccountPicName;

	/**
	 * お客様メールアドレス
	 */
	@ApiModelProperty(value = "お客様メールアドレス", required = false, position = 19)
	private String licenseInfoMailAddress;

	/**
	 * 都道府県
	 */
	@ApiModelProperty(value = "都道府県", required = false, position = 20)
	private String licenseAccountPrefectures;

	/**
	 * 市町区村
	 */
	@ApiModelProperty(value = "市町区村", required = false, position = 21)
	private String licenseAccountMunicipality;

	/**
	 * ライセンス数
	 */
	@ApiModelProperty(value = "ライセンス数", required = false, position = 22)
	private String licenseDetailChangeQuantity;

	/**
	 * サービス利用希望日
	 */
	@ApiModelProperty(value = "サービス利用希望日", required = false, position = 23)
	private Date conclusionPreferredDate;

	/**
	 * 利用申込日
	 */
	@ApiModelProperty(value = "CSV出力日時", required = false, position = 24)
	private Date csvOutputAt;

	/**
	 * 商品区分
	 */
	@ApiModelProperty(value = "商品区分", required = false, position = 25)
	private String productType;

	/**
	 * サービス種別
	 */
	@ApiModelProperty(value = "サービス種別", required = false, position = 26)
	private String serviceType;

	/**
	 * 受付担当SS組織
	 */
	@ApiModelProperty(value = "受付担当SS組織", required = false, position = 27)
	private String picAccSsName;

	/**
	 * 受付担当CE氏名
	 */
	@ApiModelProperty(value = "受付担当CE氏名", required = false, position = 28)
	private String picAccCeName;

	/**
	 * 設置先企業名
	 */
	@ApiModelProperty(value = "設置先企業名", required = false, position = 29)
	private String instrallationLocationCompanyName;

	/**
	 * 設置先住所
	 */
	@ApiModelProperty(value = "設置先住所", required = false, position = 30)
	private String instrallationLocationAddress;

	/**
	 * 設置先担当者名
	 */
	@ApiModelProperty(value = "設置先担当者名", required = false, position = 31)
	private String instrallationLocationPicName;

	/**
	 * 設置先担当者電話番号
	 */
	@ApiModelProperty(value = "設置先担当者電話番号", required = false, position = 32)
	private String instrallationLocationPicPhoneNumber;

	/**
	 * VPN
	 */
	@ApiModelProperty(value = "VPN", required = false, position = 33)
	private String vpn;

	/**
	 * POナンバー
	 */
	@ApiModelProperty(value = "POナンバー", required = false, position = 34)
	private String poNumber;

	/**
	 * サービス開始日
	 */
	@ApiModelProperty(value = "サービス開始日", required = false, position = 35)
	private Date serviceTermStart;

	/**
	 * サービス終了日
	 */
	@ApiModelProperty(value = "サービス終了日", required = false, position = 36)
	private Date serviceTermEnd;

	/**
	 * CE連絡日時
	 */
	@ApiModelProperty(value = "CE連絡日時", required = false, position = 37)
	private String ceContactAt;

	/**
	 * 解約予定日
	 */
	@ApiModelProperty(value = "解約予定日", required = false, position = 38)
	private Date cancelScheduledDate;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = true, position = 39)
	private long contractId;

	/**
	 * メール送信日
	 */
	@ApiModelProperty(value = "メール送信日", required = false, position = 40)
	private String mailSendAt;

	/**
	 * 送信結果区分
	 */
	@ApiModelProperty(value = "送信結果区分", required = false, position = 41)
	private String mailSendResultDiv;

	/**
	 * 登録日時
	 */
	@ApiModelProperty(value = "登録日時", required = false, position = 42)
	private String createdAt;

	/**
	 * 企業名
	 */
	@ApiModelProperty(value = "企業名", required = false, position = 43)
	private String licenseAccountCompanyName;

	/**
	 * 導入代行
	 */
	@ApiModelProperty(value = "導入代行", required = false, allowableValues = "導入代行なし(\"1\"), リモート導入(\"2\"), オンサイト導入(\"3\")", position = 14)
	private IntActingDiv intActingDiv;
}
