package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "McAfee連携情報ID", required = true, position = 1)
	private long id;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = true, position = 2)
	private long contractId;

	/**
	 * 手配業務ID
	 */
	@ApiModelProperty(value = "手配業務ID", required = true, position = 3)
	private long arrangementWorkId;

	/**
	 * 処理種別
	 */
	@ApiModelProperty(value = "処理種別", required = false, position = 4)
	private McafeeLinkageProcessDiv processDiv;

	/**
	 * 顧客参照番号
	 */
	@ApiModelProperty(value = "顧客参照番号", required = false, position = 5)
	private String customerReferenceNo;

	/**
	 * オーダーNo
	 */
	@ApiModelProperty(value = "オーダーNo", required = false, position = 6)
	private String orderNo;

	/**
	 * メールアドレス
	 */
	@ApiModelProperty(value = "メールアドレス", required = false, position = 7)
	private String mailAddress;

	/**
	 * パスワード
	 */
	@ApiModelProperty(value = "パスワード", required = false, position = 8)
	private String password;

	/**
	 * 都道府県コード
	 */
	@ApiModelProperty(value = "都道府県コード", required = false, position = 9)
	private String prefecturesCd;

	/**
	 * 市区町村
	 */
	@ApiModelProperty(value = "市区町村", required = false, position = 10)
	private String municipalities;

	/**
	 * 町名番地
	 */
	@ApiModelProperty(value = "市区町村", required = false, position = 11)
	private String streetBunch;

	/**
	 * 建物名
	 */
	@ApiModelProperty(value = "建物名", required = false, position = 12)
	private String buildingName;

	/**
	 * 住所詳細情報
	 */
	@ApiModelProperty(value = "住所詳細情報", required = false, position = 13)
	private String addressDetailInfo;

	/**
	 * 注文日
	 */
	@ApiModelProperty(value = "注文日", required = false)
	private Date orderDate;

	/**
	 * ノード数
	 */
	@ApiModelProperty(value = "ノード数", required = false, position = 15)
	private Integer nodeNum;

	/**
	 * 注文送信日
	 */
	@ApiModelProperty(value = "注文送信日", required = false, position = 16)
	private Date orderSendDate;

	/**
	 * ePOCloud顧客ID
	 */
	@ApiModelProperty(value = "ePOCloud顧客ID", required = false, position = 17)
	private String epocloudCustomerId;

	/**
	 * ePOCloud注文ID
	 */
	@ApiModelProperty(value = "ePOCloud注文ID", required = false, position = 18)
	private String epocloudOrderId;

	/**
	 * アクティベーションURL
	 */
	@ApiModelProperty(value = "アクティベーションURL", required = false, position = 19)
	private String activationUrl;

	/**
	 * オーダーCSV名称
	 */
	@ApiModelProperty(value = "オーダーCSV名称", required = false, position = 20)
	private String orderCsvName;

	/**
	 * オーダーCSV連携日
	 */
	@ApiModelProperty(value = "オーダーCSV連携日", required = false, position = 21)
	private Date orderCsvLinkageDate;

	/**
	 * リプライCSV名称
	 */
	@ApiModelProperty(value = "リプライCSV名称", required = false, position = 22)
	private String replyCsvName;

	/**
	 * リプライCSV取込日
	 */
	@ApiModelProperty(value = "リプライCSV取込日", required = false, position = 23)
	private Date replyCsvLinkageDate;

	/**
	 * 連携状況
	 */
	@ApiModelProperty(value = "連携状況", required = false, position = 24)
	private LinkageStatus linkageStatus;

	/**
	 * 解約フラグ
	 */
	@ApiModelProperty(value = "解約フラグ", required = false, position = 25)
	private DisengagementFlg disengagementFlg;

	/**
	 * 処理種別名称
	 */
	@ApiModelProperty(value = "処理種別名称", required = false, position = 26)
	private String processDivName;
}
