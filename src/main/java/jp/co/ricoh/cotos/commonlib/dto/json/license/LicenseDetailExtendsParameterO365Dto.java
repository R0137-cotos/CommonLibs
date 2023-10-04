package jp.co.ricoh.cotos.commonlib.dto.json.license;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.ContractTerm;
import lombok.Data;

/**
 * ライセンス明細拡張項目DTO（O365）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseDetailExtendsParameterO365Dto {

	/**
	 * 反映日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date reflectionDate;

	/**
	 * プロダクト名
	 */
	private String productName;

	/**
	 * 契約期間
	 */
	private ContractTerm contractTerm;

	/**
	 * 移行元品種マスタID
	 */
	private Long fromItemMasterId;

	/**
	 * 移行元ライセンスキー
	 */
	private String fromLicenseKey;

	/**
	 * 移行元製品ID
	 */
	private String fromOfferId;

	/**
	 * 処理優先順
	 */
	private Integer priorityOrder;

	/**
	 * 変更後の終了日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date customTermEndDate;
}
