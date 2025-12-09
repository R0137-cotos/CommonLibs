package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.InstallFlgPcp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.InstallationFlgPcp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.NetworkFlgPcp;
import lombok.Data;

/**
 * 契約機種拡張項目DTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractEquipmentExtendsParameterPcpDto {
	/**
	 * 枝番
	 */
	private String branchesNumber;

	/**
	 * メーカー名
	 */
	private String makerName;

	/**
	 * 型番
	 */
	private String modelNumber;

	/**
	 * 購入日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date purchaseDate;

	/**
	 * 保守終了日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date maintenanceEndDate;

	/**
	 * 分類名
	 */
	private String classificationName;

	/**
	 * 保守可能年数
	 */
	private String maintenanceYears;

	/**
	 * 開梱設置フラグ
	 */
	private InstallationFlgPcp installationFlg;

	/**
	 * ネットワーク設定フラグ
	 */
	private NetworkFlgPcp networkFlg;

	/**
	 * ネットワーク設定品種コード
	 */
	private String networkItemCode;

	/**
	 * ソフトインストールフラグ
	 */
	private InstallFlgPcp installFlg;

	/**
	 * ソフトインストール品種コード
	 */
	private String installItemCode;
}
