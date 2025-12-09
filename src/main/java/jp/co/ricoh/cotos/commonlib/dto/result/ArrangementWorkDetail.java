package jp.co.ricoh.cotos.commonlib.dto.result;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonIgnore
	private long id;

	/**
	 * 作業種別区分
	 */
	@Schema(description = "作業種別区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String workTypeDiv;

	/**
	 * 作業種別
	 */
	@Schema(description = "作業種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String workType;

	/**
	 * 品種
	 */
	@Schema(description = "品種", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String itemContractName;

	/**
	 * ハブ文書番号
	 */
	@Schema(description = "ハブ文書番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String hubContractNumber;

	/**
	 * ハブ契約ID
	 */
	@Schema(description = "ハブ契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String hubRjManageNumber;

	/**
	 * SAコード
	 */
	@Schema(description = "SAコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String saCode;

	/**
	 * CAF番号
	 */
	@Schema(description = "CAF番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cafNo;

	/**
	 * アクセスキー
	 */
	@Schema(description = "アクセスキー", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String accessKey;

	/**
	 * 導入希望日
	 */
	@Schema(description = "導入希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String conclusionPreferredDate;

	/**
	 * CE課所名
	 */
	@Schema(description = "CE課所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String serviceOrgName;

	/**
	 * CE名
	 */
	@Schema(description = "CE名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String employeeName;

	/**
	 * CEアドレス
	 */
	@Schema(description = "CEアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String mailAddress;

	/**
	 * 解約日
	 */
	@Schema(description = "解約日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cancelDecisionDate;

	/**
	 * お客様名
	 */
	@Schema(description = "お客様名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String companyName;

	/**
	 * 接続ユーザーID
	 */
	@Schema(description = "接続ユーザーID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internetAccount;

	/**
	 * 接続パスワード
	 */
	@Schema(description = "接続パスワード", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internetPass;

}
