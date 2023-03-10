package jp.co.ricoh.cotos.commonlib.dto.result;

import javax.persistence.Entity;
import javax.persistence.Id;

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
	private Long id;

	/**
	 * 商品マスタID
	 */
	private Long productMasterId;

	/**
	 * アップグレード元品種マスタID
	 */
	private Long fromItemMasterId;

	/**
	 * アップグレード元品種コード
	 */
	private String fromRicohItemCode;

	/**
	 * アップグレード元品種名
	 */
	private String fromItemName;

	/**
	 * アップグレード先品種マスタID
	 */
	private Long toItemMasterId;

	/**
	 * アップグレード先品種コード
	 */
	private String toRicohItemCode;

	/**
	 * アップグレード先品種名
	 */
	private String toItemName;
}
