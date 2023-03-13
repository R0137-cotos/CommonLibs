package jp.co.ricoh.cotos.commonlib.dto.result;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * アップグレード対象ライセンス対応品種マスタ結果
 */
@Data
@Entity
public class LicenseUpgradeItemMasterResult {

	/**
	 * アップグレード対象ライセンス対応品種マスタID
	 */
	@Id
	@ApiModelProperty(value = "アップグレード対象ライセンス対応品種マスタID(作成時不要)", required = true, position = 1)
	private Long id;

	/**
	 * 商品マスタID
	 */
	@ApiModelProperty(value = "商品マスタID", required = true, position = 2)
	private Long productMasterId;

	/**
	 * アップグレード元品種マスタID
	 */
	@ApiModelProperty(value = "アップグレード元品種マスタID", required = true, position = 3)
	private Long fromItemMasterId;

	/**
	 * アップグレード元品種コード
	 */
	@ApiModelProperty(value = "アップグレード元品種コード", required = true, position = 4)
	private String fromRicohItemCode;

	/**
	 * アップグレード元品種名
	 */
	@ApiModelProperty(value = "アップグレード元品種名", required = true, position = 5)
	private String fromItemName;

	/**
	 * アップグレード先品種マスタID
	 */
	@ApiModelProperty(value = "アップグレード先品種マスタID", required = true, position = 6)
	private Long toItemMasterId;

	/**
	 * アップグレード先品種コード
	 */
	@ApiModelProperty(value = "アップグレード先品種コード", required = true, position = 7)
	private String toRicohItemCode;

	/**
	 * アップグレード先品種名
	 */
	@ApiModelProperty(value = "アップグレード先品種名", required = true, position = 8)
	private String toItemName;
}
