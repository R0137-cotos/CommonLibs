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
	@Schema(description = "アップグレード対象ライセンス対応品種マスタID(作成時不要)")
	private Long id;

	/**
	 * 商品マスタID
	 */
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long productMasterId;

	/**
	 * アップグレード元品種マスタID
	 */
	@Schema(description = "アップグレード元品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long fromItemMasterId;

	/**
	 * アップグレード元品種コード
	 */
	@Schema(description = "アップグレード元品種コード", requiredMode = Schema.RequiredMode.REQUIRED)
	private String fromRicohItemCode;

	/**
	 * アップグレード元品種名
	 */
	@Schema(description = "アップグレード元品種名", requiredMode = Schema.RequiredMode.REQUIRED)
	private String fromItemName;

	/**
	 * アップグレード先品種マスタID
	 */
	@Schema(description = "アップグレード先品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long toItemMasterId;

	/**
	 * アップグレード先品種コード
	 */
	@Schema(description = "アップグレード先品種コード", requiredMode = Schema.RequiredMode.REQUIRED)
	private String toRicohItemCode;

	/**
	 * アップグレード先品種名
	 */
	@Schema(description = "アップグレード先品種名", requiredMode = Schema.RequiredMode.REQUIRED)
	private String toItemName;
}
