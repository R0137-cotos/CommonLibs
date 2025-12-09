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
	@Schema(description = "McAfee連携情報ID", required = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", required = true)
	private long contractId;

	/**
	 * 手配業務ID
	 */
	@Schema(description = "手配業務ID", required = true)
	private long arrangementWorkId;

	/**
	 * 処理種別
	 */
	@Schema(description = "処理種別", required = false)
	private McafeeLinkageProcessDiv processDiv;

	/**
	 * 顧客参照番号
	 */
	@Schema(description = "顧客参照番号", required = false)
	private String customerReferenceNo;

	/**
	 * オーダーNo
	 */
	@Schema(description = "オーダーNo", required = false)
	private String orderNo;

	/**
	 * メールアドレス
	 */
	@Schema(description = "メールアドレス", required = false)
	private String mailAddress;

	/**
	 * パスワード
	 */
	@Schema(description = "パスワード", required = false)
	private String password;

	/**
	 * 都道府県コード
	 */
	@Schema(description = "都道府県コード", required = false)
	private String prefecturesCd;

	/**
	 * 市区町村
	 */
	@Schema(description = "市区町村", required = false)
	private String municipalities;

	/**
	 * 町名番地
	 */
	@Schema(description = "市区町村", required = false)
	private String streetBunch;

	/**
	 * 建物名
	 */
	@Schema(description = "建物名", required = false)
	private String buildingName;

	/**
	 * 住所詳細情報
	 */
	@Schema(description = "住所詳細情報", required = false)
	private String addressDetailInfo;

	/**
	 * 注文日
	 */
	@Schema(description = "注文日", required = false)
	private Date orderDate;

	/**
	 * ノード数
	 */
	@Schema(description = "ノード数", required = false)
	private Integer nodeNum;

	/**
	 * 注文送信日
	 */
	@Schema(description = "注文送信日", required = false)
	private Date orderSendDate;

	/**
	 * ePOCloud顧客ID
	 */
	@Schema(description = "ePOCloud顧客ID", required = false)
	private String epocloudCustomerId;

	/**
	 * ePOCloud注文ID
	 */
	@Schema(description = "ePOCloud注文ID", required = false)
	private String epocloudOrderId;

	/**
	 * アクティベーションURL
	 */
	@Schema(description = "アクティベーションURL", required = false)
	private String activationUrl;

	/**
	 * オーダーCSV名称
	 */
	@Schema(description = "オーダーCSV名称", required = false)
	private String orderCsvName;

	/**
	 * オーダーCSV連携日
	 */
	@Schema(description = "オーダーCSV連携日", required = false)
	private Date orderCsvLinkageDate;

	/**
	 * リプライCSV名称
	 */
	@Schema(description = "リプライCSV名称", required = false)
	private String replyCsvName;

	/**
	 * リプライCSV取込日
	 */
	@Schema(description = "リプライCSV取込日", required = false)
	private Date replyCsvLinkageDate;

	/**
	 * 連携状況
	 */
	@Schema(description = "連携状況", required = false)
	private LinkageStatus linkageStatus;

	/**
	 * 解約フラグ
	 */
	@Schema(description = "解約フラグ", required = false)
	private DisengagementFlg disengagementFlg;

	/**
	 * 処理種別名称
	 */
	@Schema(description = "処理種別名称", required = false)
	private String processDivName;
}
