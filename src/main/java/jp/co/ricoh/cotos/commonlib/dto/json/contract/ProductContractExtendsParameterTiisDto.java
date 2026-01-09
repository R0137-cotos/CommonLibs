package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（TIIS）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterTiisDto {

	/**
	 * 現地確認日
	 */
	private Date onsiteConfirmationDate;

	/**
	 * 現地確認日：時間
	 */
	private String onsiteConfirmationTime;

	/**
	 * 設置（予定）日
	 * ※画面上では設置日で表示される
	 */
	private Date installationScheduledDate;

	/**
	 * 設置（予定）日：時間
	 * ※画面上では設置日：時間で表示される
	 */
	private String installationScheduledTime;

	/**
	 * SS長了解済みフラグ
	 */
	private String ssChiefAcceptedFlg;

	/**
	 * SS調整確認者(SS長)
	 */
	private String acceptedChief;

	/**
	 * 調整内容
	 */
	private String adjustedDescription;
}
