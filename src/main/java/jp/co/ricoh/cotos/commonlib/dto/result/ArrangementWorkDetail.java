package jp.co.ricoh.cotos.commonlib.dto.result;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * BPN SACM設定変更情報取得API レスポンスのSACM手配業務.手配業務明細リストのDTO
 */

@Entity
@Data
public class ArrangementWorkDetail {

	/**
	 * 手配業務明細ID
	 */
	@Id
	@ApiModelProperty(value = "ID", required = false, position = 1)
	@JsonIgnore
	private long id;

	/**
	 * 作業種別区分
	 */
	@ApiModelProperty(value = "作業種別区分", required = false, position = 2)
	private String workTypeDiv;

	/**
	 * 作業種別
	 */
	@ApiModelProperty(value = "作業種別", required = false, position = 3)
	private String workType;

	/**
	 * 品種
	 */
	@ApiModelProperty(value = "品種", required = false, position = 4)
	private String itemContractName;

	/**
	 * ハブ文書番号
	 */
	@ApiModelProperty(value = "ハブ文書番号", required = false, position = 5)
	private String hubContractNumber;

	/**
	 * ハブ契約ID
	 */
	@ApiModelProperty(value = "ハブ契約ID", required = false, position = 6)
	private String hubRjManageNumber;

	/**
	 * SAコード
	 */
	@ApiModelProperty(value = "SAコード", required = false, position = 7)
	private String saCode;

	/**
	 * CAF番号
	 */
	@ApiModelProperty(value = "CAF番号", required = false, position = 8)
	private String cafNo;

	/**
	 * アクセスキー
	 */
	@ApiModelProperty(value = "アクセスキー", required = false, position = 9)
	private String accessKey;

	/**
	 * 導入希望日
	 */
	@ApiModelProperty(value = "導入希望日", required = false, position = 10)
	private String conclusionPreferredDate;

	/**
	 * CE課所名
	 */
	@ApiModelProperty(value = "CE課所名", required = false, position = 11)
	private String serviceOrgName;

	/**
	 * CE名
	 */
	@ApiModelProperty(value = "CE名", required = false, position = 12)
	private String employeeName;

	/**
	 * CEアドレス
	 */
	@ApiModelProperty(value = "CEアドレス", required = false, position = 13)
	private String mailAddress;

	/**
	 * 解約日
	 */
	@ApiModelProperty(value = "解約日", required = false, position = 14)
	private String cancelDecisionDate;

	/**
	 * お客様名
	 */
	@ApiModelProperty(value = "お客様名", required = false, position = 15)
	private String companyName;

	/**
	 * 接続ユーザーID
	 */
	@ApiModelProperty(value = "接続ユーザーID", required = false, position = 16)
	private String internetAccount;

	/**
	 * 接続パスワード
	 */
	@ApiModelProperty(value = "接続パスワード", required = false, position = 17)
	private String internetPass;

}
