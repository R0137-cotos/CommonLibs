package jp.co.ricoh.cotos.commonlib.dto.json.license;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.CustomTermPattern;
import lombok.Data;

/**
 * ライセンスキー情報拡張項目DTO（O365）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseKeyInfoExtendsParameterO365Dto {

	/**
	 * 品種マスターID
	 */
	private Long itemMasterId;

	/**
	 * 減数反映日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date reflectionDate;

	/**
	 * 減数可能期間（From）
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date possibleReduceTermStart;

	/**
	 * 減数可能期間（To）
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date possibleReduceTermEnd;

	/**
	 * 課金開始年月
	 */
	@JsonFormat(pattern = "yyyy/MM", timezone = "Asia/Tokyo")
	private Date billingStartMonth;

	/**
	 * 変更パターン
	 */
	private CustomTermPattern customPattern;

	/**
	 * 変更後の終了日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date customTermEndDate;

	/**
	 * 同時終了反映日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date coterminateReflectionDate;
}
