package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.CancellationStatus;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（O365）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterO365Dto {

	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * 希望サブドメイン名
	 */
	private String desiredSubDomainName;

	/**
	 * ディレクトリID
	 */
	private String directoryId;

	/**
	 * サブドメイン名
	 */
	private String subDomainName;

	/**
	 * ドメイン名
	 */
	private String domainName;

	/**
	 * プライマリドメイン名
	 */
	private String primaryDomainName;

	/**
	 * 品種(契約用)
	 */
	@JsonProperty("itemContract")
	private List<ItemContractO365Dto> itemContractDtoList;

	/**
	 * キャンセル申込日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date cancellationApplicationDate;

	/**
	 * キャンセル処理状態
	 */
	private CancellationStatus cancellationStatus;

	/**
	 * キャンセル可能期日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date cancellationDate;

	/**
	 * 担当者氏名
	 */
	private String picName;

	/**
	 * 電話番号
	 */
	private String picPhoneNumber;

	/**
	 * メールアドレス
	 */
	private String picMailAddress;
}
