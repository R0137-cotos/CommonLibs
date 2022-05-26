package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.AccountFixFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.NewExistingAccountType;
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
	 * 設置（予定）日
	 */
	private Date installationScheduledDate;
	
	/**
	 * 設置（予定）日：時間
	 */
	private String installationScheduledTime;
	
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
	 * 顔認証端末：電話番号
	 */
	private String faceRecognitionTerminalPhoneNumber;
}
