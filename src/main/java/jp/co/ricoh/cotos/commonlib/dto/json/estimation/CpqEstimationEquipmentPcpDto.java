package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.ClassificationCodePcp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.InstallFlgPcp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.InstallationFlgPcp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.MakerCodePcp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.NetworkFlgPcp;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値 見積機種リストDTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqEstimationEquipmentPcpDto {

	/**
	 * 機種コード
	 */
	private String equipmentCode;

	/**
	 * 機種名
	 */
	private String equipmentName;

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
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 契約年数
	 */
	private String contractYears;

	/**
	 * 品種コード
	 */
	private String itemCode;

	/**
	 * 自動更新品種コード
	 */
	private String contractAutoItemCode;

	/**
	 * メーカーコード
	 */
	private MakerCodePcp makerCode;

	/**
	 * メーカー名
	 */
	private String makerName;

	/**
	 * 分類コード
	 */
	private ClassificationCodePcp classificationCode;

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
