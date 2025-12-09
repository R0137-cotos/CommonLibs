package jp.co.ricoh.cotos.commonlib.dto.result;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "アップグレード対象ライセンス対応品種マスタID(作成時不要)", required = true)
	private Long id;

	/**
	 * 商品マスタID
	 */
	@Schema(description = "商品マスタID", required = true)
	private Long productMasterId;

	/**
	 * アップグレード元品種マスタID
	 */
	@Schema(description = "アップグレード元品種マスタID", required = true)
	private Long fromItemMasterId;

	/**
	 * アップグレード元品種コード
	 */
	@Schema(description = "アップグレード元品種コード", required = true)
	private String fromRicohItemCode;

	/**
	 * アップグレード元品種名
	 */
	@Schema(description = "アップグレード元品種名", required = true)
	private String fromItemName;

	/**
	 * アップグレード先品種マスタID
	 */
	@Schema(description = "アップグレード先品種マスタID", required = true)
	private Long toItemMasterId;

	/**
	 * アップグレード先品種コード
	 */
	@Schema(description = "アップグレード先品種コード", required = true)
	private String toRicohItemCode;

	/**
	 * アップグレード先品種名
	 */
	@Schema(description = "アップグレード先品種名", required = true)
	private String toItemName;
}
