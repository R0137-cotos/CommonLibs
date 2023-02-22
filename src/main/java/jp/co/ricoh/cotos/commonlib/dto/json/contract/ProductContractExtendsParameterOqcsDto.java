package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（OQCS）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterOqcsDto {

	/**
	 * 現地確認日
	 */
	private Date onsiteConfirmationDate;

	/**
	 * 現地確認日：時間
	 */
	private String onsiteConfirmationTime;

	/**
	 * 設置日
	 */
	private Date installationDate;

	/**
	 * 設置日：時間
	 */
	private String installationTime;
	/**
	 * オン資 専用ソフト：問合先
	 */
	private String onlineQualificationSoftwareContactInfo;

	/**
	 * オン資 専用ソフト：電話番号
	 */
	private String onlineQualificationSoftwarePhoneNumber;

	/**
	 * レセコンソフト：問合先
	 */
	private String receiptComputerSoftwareContactInfo;

	/**
	 * レセコンソフト：電話番号
	 */
	private String receiptComputerSoftwarePhoneNumber;

	/**
	 * 顔認証端末：問合先
	 */
	private String faceRecognitionTerminalContactInfo;

	/**
	 * SS調整済みフラグ
	 */
	private int sSAdjustedFlg;

	/**
	 * SS長了解済みフラグ
	 */
	private int sSChiefAcceptedFlg;

	/**
	 * SS調整確認者(SS長)
	 */
	private String acceptedChiefNameOfSSAdjusted;

	/**
	 * 調整内容
	 */
	private String adjustedDescription;
}
