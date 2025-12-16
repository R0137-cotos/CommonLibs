package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.McafeeLinkageInfo.DisengagementFlg;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.McafeeLinkageInfo.LinkageStatus;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.McafeeLinkageInfo.McafeeLinkageProcessDiv;
import lombok.Data;

@Data
public class McafeeLinkageInfoDetailDtoResult {

	/**
	 * McAfee連携情報ID
	 */
	@Id
	@Schema(description = "McAfee連携情報ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long id;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long contractId;

	/**
	 * 手配業務ID
	 */
	@Schema(description = "手配業務ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long arrangementWorkId;

	/**
	 * 処理種別
	 */
	@Schema(description = "処理種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private McafeeLinkageProcessDiv processDiv;

	/**
	 * 顧客参照番号
	 */
	@Schema(description = "顧客参照番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerReferenceNo;

	/**
	 * オーダーNo
	 */
	@Schema(description = "オーダーNo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String orderNo;

	/**
	 * メールアドレス
	 */
	@Schema(description = "メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String mailAddress;

	/**
	 * パスワード
	 */
	@Schema(description = "パスワード", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String password;

	/**
	 * 都道府県コード
	 */
	@Schema(description = "都道府県コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String prefecturesCd;

	/**
	 * 市区町村
	 */
	@Schema(description = "市区町村", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String municipalities;

	/**
	 * 町名番地
	 */
	@Schema(description = "市区町村", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String streetBunch;

	/**
	 * 建物名
	 */
	@Schema(description = "建物名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String buildingName;

	/**
	 * 住所詳細情報
	 */
	@Schema(description = "住所詳細情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String addressDetailInfo;

	/**
	 * 注文日
	 */
	@Schema(description = "注文日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date orderDate;

	/**
	 * ノード数
	 */
	@Schema(description = "ノード数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer nodeNum;

	/**
	 * 注文送信日
	 */
	@Schema(description = "注文送信日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date orderSendDate;

	/**
	 * ePOCloud顧客ID
	 */
	@Schema(description = "ePOCloud顧客ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String epocloudCustomerId;

	/**
	 * ePOCloud注文ID
	 */
	@Schema(description = "ePOCloud注文ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String epocloudOrderId;

	/**
	 * アクティベーションURL
	 */
	@Schema(description = "アクティベーションURL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String activationUrl;

	/**
	 * オーダーCSV名称
	 */
	@Schema(description = "オーダーCSV名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String orderCsvName;

	/**
	 * オーダーCSV連携日
	 */
	@Schema(description = "オーダーCSV連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date orderCsvLinkageDate;

	/**
	 * リプライCSV名称
	 */
	@Schema(description = "リプライCSV名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String replyCsvName;

	/**
	 * リプライCSV取込日
	 */
	@Schema(description = "リプライCSV取込日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date replyCsvLinkageDate;

	/**
	 * 連携状況
	 */
	@Schema(description = "連携状況", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private LinkageStatus linkageStatus;

	/**
	 * 解約フラグ
	 */
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private DisengagementFlg disengagementFlg;

	/**
	 * 処理種別名称
	 */
	@Schema(description = "処理種別名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String processDivName;
}
