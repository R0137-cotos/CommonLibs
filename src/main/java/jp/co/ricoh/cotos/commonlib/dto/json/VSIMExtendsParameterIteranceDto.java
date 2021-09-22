package jp.co.ricoh.cotos.commonlib.dto.json;

import lombok.Data;

/**
 * 音声SIM拡張項目繰返DTO
 * 商品(見積)・商品(契約)共に共通
 * @author g10012030
 *
 */
@Data
public class VSIMExtendsParameterIteranceDto {

	private long id;

	/**
	 * 種別
	 */
	private String contractType;

	/**
	 * 商品コード
	 */
	private String productCode;

	/**
	 * 商品名
	 */
	private String productName;

	/**
	 * MNP
	 */
	private String mnpType;

	/**
	 * 回線番号
	 */
	private String lineNumber;

	/**
	 * ICCID(シリアル番号)
	 */
	private String serialNumber;

	/**
	 * IMEI (携帯シリアル番号)
	 */
	private String imeiNumber;

	/**
	 * 送り状番号
	 */
	private String invoiceNumber;

	/**
	 * 解約日
	 */
	private String cancelDate;

	/**
	 * 承諾番号
	 */
	private String mnpNumber;

}
